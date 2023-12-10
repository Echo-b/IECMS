// 单片机头文件
#include "stm32f10x.h"

// 网络设备
#include "esp8266.h"

// 协议文件
#include "onenet.h"
#include "mqttkit.h"

// 硬件驱动
#include "usart.h"
#include "delay.h"
#include "LED.h"
#include "Buzzer.h"

// C库
#include <string.h>
#include <stdio.h>
#include "cJSON.h"

#define PROID "77247"

#define AUTH_INFO "test"

#define DEVID "5616839"

extern unsigned char esp8266_buf[128];

//==========================================================
//	函数名称：	OneNet_DevLink
//
//	函数功能：	与onenet创建连接
//
//	入口参数：	无
//
//	返回参数：	1-成功	0-失败
//
//	说明：		与onenet平台建立连接
//==========================================================
_Bool OneNet_DevLink(void)
{

	MQTT_PACKET_STRUCTURE mqttPacket = {NULL, 0, 0, 0}; // 协议包

	unsigned char *dataPtr;

	_Bool status = 1;

	UsartPrintf(USART_DEBUG, "OneNet_DevLink\r\n"
							 "PROID: %s,	AUIF: %s,	DEVID:%s\r\n",
				PROID, AUTH_INFO, DEVID);

	if (MQTT_PacketConnect(PROID, AUTH_INFO, DEVID, 256, 0, MQTT_QOS_LEVEL0, NULL, NULL, 0, &mqttPacket) == 0)
	{
		ESP8266_SendData(mqttPacket._data, mqttPacket._len); // 上传平台

		dataPtr = ESP8266_GetIPD(250); // 等待平台响应
		if (dataPtr != NULL)
		{
			if (MQTT_UnPacketRecv(dataPtr) == MQTT_PKT_CONNACK)
			{
				switch (MQTT_UnPacketConnectAck(dataPtr))
				{
				case 0:
					UsartPrintf(USART_DEBUG, "Tips:	Connect Success\r\n");
					status = 0;
					break;
				case 1:
					UsartPrintf(USART_DEBUG, "WARN:	连接失败：协议错误\r\n");
					break;
				case 2:
					UsartPrintf(USART_DEBUG, "WARN:	连接失败：非法的clientid\r\n");
					break;
				case 3:
					UsartPrintf(USART_DEBUG, "WARN:	连接失败：服务器失败\r\n");
					break;
				case 4:
					UsartPrintf(USART_DEBUG, "WARN:	连接失败：用户名或密码错误\r\n");
					break;
				case 5:
					UsartPrintf(USART_DEBUG, "WARN:	连接失败，比如非法token\r\n");
					break;
				default:
					UsartPrintf(USART_DEBUG, "ERR:	连接失败：未知错误\r\n");
					break;
				}
			}
		}

		MQTT_DeleteBuffer(&mqttPacket); // 删包
	}
	else
		UsartPrintf(USART_DEBUG, "WARN:	MQTT_PacketConnect Failed\r\n");

	return status;
}

//==========================================================
//	函数名称：	OneNet_Subscribe
//
//	函数功能：	订阅
//
//	入口参数：	topics：订阅的topic
//				topic_cnt：topic个数
//
//	返回参数：	SEND_TYPE_OK-成功	SEND_TYPE_SUBSCRIBE-需要重发
//
//	说明：
//==========================================================
void OneNet_Subscribe(const char *topics[], unsigned char topic_cnt)
{

	unsigned char i = 0;

	MQTT_PACKET_STRUCTURE mqttPacket = {NULL, 0, 0, 0}; // 协议包

	for (; i < topic_cnt; i++)
		UsartPrintf(USART_DEBUG, "Subscribe Topic: %s\r\n", topics[i]);

	if (MQTT_PacketSubscribe(MQTT_SUBSCRIBE_ID, MQTT_QOS_LEVEL0, topics, topic_cnt, &mqttPacket) == 0)
	{
		ESP8266_SendData(mqttPacket._data, mqttPacket._len); // 向平台发送订阅请求

		MQTT_DeleteBuffer(&mqttPacket); // 删包
	}
}

//==========================================================
//	函数名称：	OneNet_Publish
//
//	函数功能：	发布消息
//
//	入口参数：	topic：发布的主题
//				msg：消息内容
//
//	返回参数：	SEND_TYPE_OK-成功	SEND_TYPE_PUBLISH-需要重送
//
//	说明：
//==========================================================
void OneNet_Publish(const char *topic, const char *msg)
{

	MQTT_PACKET_STRUCTURE mqttPacket = {NULL, 0, 0, 0}; // 协议包

	UsartPrintf(USART_DEBUG, "Publish Topic: %s, Msg: %s\r\n", topic, msg);

	if (MQTT_PacketPublish(MQTT_PUBLISH_ID, topic, msg, strlen(msg), MQTT_QOS_LEVEL0, 0, 1, &mqttPacket) == 0)
	{
		ESP8266_SendData(mqttPacket._data, mqttPacket._len); // 向平台发送订阅请求

		MQTT_DeleteBuffer(&mqttPacket); // 删包
	}
}

//==========================================================
//	函数名称：	OneNet_RevPro
//
//	函数功能：	平台返回数据检测
//
//	入口参数：	dataPtr：平台返回的数据
//
//	返回参数：	无
//
//	说明：
//==========================================================
void OneNet_RevPro(unsigned char *cmd)
{

	MQTT_PACKET_STRUCTURE mqttPacket = {NULL, 0, 0, 0}; // 协议包

	char *req_payload = NULL;
	char *cmdid_topic = NULL;

	unsigned short topic_len = 0;
	unsigned short req_len = 0;

	unsigned char type = 0;
	unsigned char qos = 0;
	static unsigned short pkt_id = 0;

	short result = 0;

	cJSON *json = NULL;

	char *dataPtr = NULL;
	char numBuf[10];
	int num = 0;

	type = MQTT_UnPacketRecv(cmd);
	switch (type)
	{
	case MQTT_PKT_CMD: // 命令下发

		result = MQTT_UnPacketCmd(cmd, &cmdid_topic, &req_payload, &req_len); // 解出topic和消息体
		if (result == 0)
		{
			UsartPrintf(USART_DEBUG, "cmdid: %s, req: %s, req_len: %d\r\n", cmdid_topic, req_payload, req_len);

			if (MQTT_PacketCmdResp(cmdid_topic, req_payload, &mqttPacket) == 0) // 命令回复组包
			{
				UsartPrintf(USART_DEBUG, "Tips:	Send CmdResp\r\n");

				ESP8266_SendData(mqttPacket._data, mqttPacket._len); // 回复命令
				MQTT_DeleteBuffer(&mqttPacket);						 // 删包
			}
		}

		break;

	case MQTT_PKT_PUBLISH: // 接收的Publish消息

		result = MQTT_UnPacketPublish(cmd, &cmdid_topic, &topic_len, &req_payload, &req_len, &qos, &pkt_id);
		if (result == 0)
		{
			DEBUG_LOG("topic: %s, topic_len: %d, payload: %s, payload_len: %d\r\n",
					  cmdid_topic, topic_len, req_payload, req_len);
			// 对数据包req_payload进行JSON格式解析
			cJSON *cjson_command = NULL;
			cJSON *cjson_led = NULL;
			cJSON *cjson_led_status = NULL;
			cJSON *cjson_led_delay = NULL;
			cJSON *cjson_beep = NULL;
			cJSON *cjson_beep_status = NULL;
			cJSON *cjson_beep_delay = NULL;
			cJSON *cjson_delay = NULL;
			cJSON *cjson_delay_status = NULL;
			cJSON *cjson_delay_did = NULL;
			cJSON *cjson_htSensor = NULL;
			cJSON *cjson_temperture = NULL;
			cJSON *cjson_humidity = NULL;
			cJSON *cjson_device_status = NULL;
			json = cJSON_Parse(req_payload);
			
			if (!json)
				UsartPrintf(USART_DEBUG, "Error before: [%s]\n", cJSON_GetErrorPtr());
			else
			{
				/* 依次根据名称提取JSON数据（键值对） */
				cjson_command = cJSON_GetObjectItem(json, "command");

				/* 解析嵌套json数据 */
				cjson_led = cJSON_GetObjectItem(cjson_command, "led");
				cjson_beep = cJSON_GetObjectItem(cjson_command, "beep");
				cjson_delay = cJSON_GetObjectItem(cjson_command, "delay");
				cjson_htSensor = cJSON_GetObjectItem(cjson_command, "htSensor");
				
				/* 解析led */
				cjson_led_status = cJSON_GetObjectItem(cjson_led, "status");
				cjson_led_delay = cJSON_GetObjectItem(cjson_led, "delay");
				cjson_beep_status = cJSON_GetObjectItem(cjson_beep, "status");
				cjson_beep_delay = cJSON_GetObjectItem(cjson_beep, "delay");
			  cjson_delay_status = cJSON_GetObjectItem(cjson_delay, "status");
				cjson_delay_did = cJSON_GetObjectItem(cjson_delay, "did");

				/* 解析htSensor数据 */
				cjson_temperture = cJSON_GetObjectItem(cjson_htSensor, "temperature");
				cjson_humidity = cJSON_GetObjectItem(cjson_htSensor, "humidity");
				cjson_device_status = cJSON_GetObjectItem(cjson_htSensor, "status");
				if(strstr(cjson_led_status->valuestring,"o")) {
					UsartPrintf(USART_DEBUG,"input led\n");
					if (strcmp(cjson_led_status->valuestring,"on") == 0 && cjson_led_delay->valueint == 0 ) // led status 为 on
					{
						LED_ON();
					}
					else if (strcmp(cjson_led_status->valuestring,"on") == 0 && cjson_led_delay->valueint > 0 ) // beep status 为 on
					{
						LED_ON();
						delay_ms(cjson_led_delay->valueint);
						LED_OFF();
					}
					else
					{
						LED_OFF(); // 关闭LED0
					}
				}
				else if(strstr(cjson_beep_status->valuestring,"o")) {
					UsartPrintf(USART_DEBUG,"input beep\n");
					if (strcmp(cjson_beep_status->valuestring,"on") == 0 && cjson_beep_delay->valueint == 0) // beep status 为 on
					{
						Buzzer_ON();
	
					}
					else if (strcmp(cjson_beep_status->valuestring,"on") == 0 && cjson_beep_delay->valueint > 0) // beep status 为 on
					{
						Buzzer_ON();
						delay_ms(cjson_beep_delay->valueint);
						Buzzer_OFF();
					}
					else
					{
						Buzzer_OFF(); // 关闭BEEP
					}
				}
				else if(strstr(cjson_delay_status->valuestring,"o")) {
					if(strcmp(cjson_delay_status->valuestring,"on") == 0){
						if(cjson_delay_did->valueint == 1)
							LED_ON();
						else if(cjson_delay_did->valueint == 2)
							Buzzer_ON();
					} 
					else if(strcmp(cjson_delay_status->valuestring,"off") == 0){
						if(cjson_delay_did->valueint == 1)
							LED_OFF();
						else if(cjson_delay_did->valueint == 2)
							Buzzer_OFF();
					}
				}
			}
			cJSON_Delete(json);
			
		}
		break;

	case MQTT_PKT_PUBACK: // 发送Publish消息，平台回复的Ack

		if (MQTT_UnPacketPublishAck(cmd) == 0)
			UsartPrintf(USART_DEBUG, "Tips:	MQTT Publish Send OK\r\n");

		break;

	case MQTT_PKT_PUBREC: // 发送Publish消息，平台回复的Rec，设备需回复Rel消息

		if (MQTT_UnPacketPublishRec(cmd) == 0)
		{
			UsartPrintf(USART_DEBUG, "Tips:	Rev PublishRec\r\n");
			if (MQTT_PacketPublishRel(MQTT_PUBLISH_ID, &mqttPacket) == 0)
			{
				UsartPrintf(USART_DEBUG, "Tips:	Send PublishRel\r\n");
				ESP8266_SendData(mqttPacket._data, mqttPacket._len);
				MQTT_DeleteBuffer(&mqttPacket);
			}
		}

		break;

	case MQTT_PKT_PUBREL: // 收到Publish消息，设备回复Rec后，平台回复的Rel，设备需再回复Comp

		if (MQTT_UnPacketPublishRel(cmd, pkt_id) == 0)
		{
			UsartPrintf(USART_DEBUG, "Tips:	Rev PublishRel\r\n");
			if (MQTT_PacketPublishComp(MQTT_PUBLISH_ID, &mqttPacket) == 0)
			{
				UsartPrintf(USART_DEBUG, "Tips:	Send PublishComp\r\n");
				ESP8266_SendData(mqttPacket._data, mqttPacket._len);
				MQTT_DeleteBuffer(&mqttPacket);
			}
		}

		break;

	case MQTT_PKT_PUBCOMP: // 发送Publish消息，平台返回Rec，设备回复Rel，平台再返回的Comp

		if (MQTT_UnPacketPublishComp(cmd) == 0)
		{
			UsartPrintf(USART_DEBUG, "Tips:	Rev PublishComp\r\n");
		}

		break;

	case MQTT_PKT_SUBACK: // 发送Subscribe消息的Ack

		if (MQTT_UnPacketSubscribe(cmd) == 0)
			UsartPrintf(USART_DEBUG, "Tips:	MQTT Subscribe OK\r\n");
		else
			UsartPrintf(USART_DEBUG, "Tips:	MQTT Subscribe Err\r\n");

		break;

	case MQTT_PKT_UNSUBACK: // 发送UnSubscribe消息的Ack

		if (MQTT_UnPacketUnSubscribe(cmd) == 0)
			UsartPrintf(USART_DEBUG, "Tips:	MQTT UnSubscribe OK\r\n");
		else
			UsartPrintf(USART_DEBUG, "Tips:	MQTT UnSubscribe Err\r\n");

		break;

	default:
		result = -1;
		break;
	}

	ESP8266_Clear(); // 清空缓存

	if (result == -1)
		return;

	dataPtr = strchr(req_payload, '}'); // 搜索'}'

	if (dataPtr != NULL && result != -1) // 如果找到了
	{
		dataPtr++;

		while (*dataPtr >= '0' && *dataPtr <= '9') // 判断是否是下发的命令控制数据
		{
			numBuf[num++] = *dataPtr++;
		}

		num = atoi((const char *)numBuf); // 转为数值形式
	}

	if (type == MQTT_PKT_CMD || type == MQTT_PKT_PUBLISH)
	{
		MQTT_FreeBuffer(cmdid_topic);
		MQTT_FreeBuffer(req_payload);
	}
}

// 单片机头文件
#include "stm32f10x.h"

// 网络设备驱动
#include "esp8266.h"

// 硬件驱动
#include "delay.h"
#include "usart.h"

// C库
#include <string.h>
#include <stdio.h>

#define WIFI_SSID "iQOONeo5"	 //	WIFI的名称 必须用2.4G的wifi不能用5G的，且不能用中文、空格
#define WIFI_PSWD "echopi314159" //	WIFI密码

#define SERVER_HOST "192.168.72.133" //	MQTT服务器域名或IP
#define SERVER_PORT "1883"			 //	MQTT服务器端口（一般为1883不用改）

#define ESP8266_WIFI_INFO "AT+CWJAP=\"" WIFI_SSID "\",\"" WIFI_PSWD "\"\r\n"
#define ESP8266_ONENET_INFO "AT+CIPSTART=\"TCP\",\"" SERVER_HOST "\"," SERVER_PORT "\r\n"

unsigned char esp8266_buf[128];
unsigned short esp8266_cnt = 0, esp8266_cntPre = 0;

//==========================================================
//	函数名称：	ESP8266_Clear
//
//	函数功能：	清空缓存
//
//	入口参数：	无
//
//	返回参数：	无
//
//	说明：
//==========================================================
void ESP8266_Clear(void)
{

	memset(esp8266_buf, 0, sizeof(esp8266_buf));
	esp8266_cnt = 0;
}

//==========================================================
//	函数名称：	ESP8266_WaitRecive
//
//	函数功能：	等待接收完成
//
//	入口参数：	无
//
//	返回参数：	REV_OK-接收完成		REV_WAIT-接收超时未完成
//
//	说明：		循环调用检测是否接收完成
//==========================================================
_Bool ESP8266_WaitRecive(void)
{

	if (esp8266_cnt == 0) // 如果接收计数为0 则说明没有处于接收数据中，所以直接跳出，结束函数
		return REV_WAIT;

	if (esp8266_cnt == esp8266_cntPre) // 如果上一次的值和这次相同，则说明接收完毕
	{
		esp8266_cnt = 0; // 清0接收计数

		return REV_OK; // 返回接收完成标志
	}

	esp8266_cntPre = esp8266_cnt; // 置为相同

	return REV_WAIT; // 返回接收未完成标志
}

//==========================================================
//	函数名称：	ESP8266_SendCmd
//
//	函数功能：	发送命令
//
//	入口参数：	cmd：命令
//				res：需要检查的返回指令
//
//	返回参数：	0-成功	1-失败
//
//	说明：
//==========================================================
_Bool ESP8266_SendCmd(char *cmd, char *res)
{

	unsigned char timeOut = 200;

	Usart_SendString(USART2, (unsigned char *)cmd, strlen((const char *)cmd));

	while (timeOut--)
	{
		if (ESP8266_WaitRecive() == REV_OK) // 如果收到数据
		{
			if (strstr((const char *)esp8266_buf, res) != NULL) // 如果检索到关键词
			{
				ESP8266_Clear(); // 清空缓存

				return 0;
			}
		}

		delay_ms(10);
	}

	return 1;
}

//==========================================================
//	函数名称：	ESP8266_SendData
//
//	函数功能：	发送数据
//
//	入口参数：	data：数据
//				len：长度
//
//	返回参数：	无
//
//	说明：
//==========================================================
void ESP8266_SendData(unsigned char *data, unsigned short len)
{

	char cmdBuf[32];

	ESP8266_Clear();						   // 清空接收缓存
	sprintf(cmdBuf, "AT+CIPSEND=%d\r\n", len); // 发送命令
	if (!ESP8266_SendCmd(cmdBuf, ">"))		   // 收到‘>’时可以发送数据
	{
		Usart_SendString(USART2, data, len); // 发送设备连接请求数据
	}
}

//==========================================================
//	函数名称：	ESP8266_GetIPD
//
//	函数功能：	获取平台返回的数据
//
//	入口参数：	等待的时间(乘以10ms)
//
//	返回参数：	平台返回的原始数据
//
//	说明：		不同网络设备返回的格式不同，需要去调试
//				如ESP8266的返回格式为	"+IPD,x:yyy"	x代表数据长度，yyy是数据内容
//==========================================================
unsigned char *ESP8266_GetIPD(unsigned short timeOut)
{

	char *ptrIPD = NULL;

	do
	{
		if (ESP8266_WaitRecive() == REV_OK) // 如果接收完成
		{
			ptrIPD = strstr((char *)esp8266_buf, "IPD,"); // 搜索“IPD”头
			if (ptrIPD == NULL)							  // 如果没找到，可能是IPD头的延迟，还是需要等待一会，但不会超过设定的时间
			{
				// UsartPrintf(USART_DEBUG, "\"IPD\" not found\r\n");
			}
			else
			{
				ptrIPD = strchr(ptrIPD, ':'); // 找到':'
				if (ptrIPD != NULL)
				{
					ptrIPD++;
					return (unsigned char *)(ptrIPD);
				}
				else
					return NULL;
			}
		}

		delay_ms(5); // 延时等待
		timeOut--;
	} while (timeOut > 0);

	return NULL; // 超时还未找到，返回空指针
}

//==========================================================
//	函数名称：	ESP8266_Init
//
//	函数功能：	初始化ESP8266
//
//	入口参数：	无
//
//	返回参数：	无
//
//	说明：
//==========================================================
void ESP8266_Init(void)
{

	GPIO_InitTypeDef GPIO_Initure;

	RCC_APB2PeriphClockCmd(RCC_APB2Periph_GPIOB, ENABLE);

	// ESP8266复位引脚.
	GPIO_Initure.GPIO_Pin = GPIO_Pin_8; // GPIOB8-复位
	GPIO_Initure.GPIO_Mode = GPIO_Mode_Out_PP;
	GPIO_Initure.GPIO_Speed = GPIO_Speed_50MHz;
	GPIO_Init(GPIOB, &GPIO_Initure);

	GPIO_WriteBit(GPIOB, GPIO_Pin_8, Bit_RESET);
	delay_ms(250);
	GPIO_WriteBit(GPIOB, GPIO_Pin_8, Bit_SET);
	delay_ms(500);

	ESP8266_Clear();

	//	UsartPrintf(USART_DEBUG, "1. AT\r\n");
	//	while(ESP8266_SendCmd("AT\r\n", "OK"))
	//		delay_ms(500);
	//
	//	UsartPrintf(USART_DEBUG, "2. CWMODE\r\n");
	//	while(ESP8266_SendCmd("AT+CWMODE=1\r\n", "OK"))
	//		delay_ms(500);
	//
	//	UsartPrintf(USART_DEBUG, "3. AT+CWDHCP\r\n");
	//	while(ESP8266_SendCmd("AT+CWDHCP=1,1\r\n", "OK"))
	//		delay_ms(500);
	//
	//	UsartPrintf(USART_DEBUG, "4. CWJAP\r\n");
	//	while(ESP8266_SendCmd(ESP8266_WIFI_INFO, "GOT IP"))
	//		delay_ms(500);
	//
	//	UsartPrintf(USART_DEBUG, "5. CIPSTART\r\n");
	//	while(ESP8266_SendCmd(ESP8266_ONENET_INFO, "CONNECT"))
	//		delay_ms(500);
	//
	//	UsartPrintf(USART_DEBUG, "6. ESP8266 Init OK\r\n");
	DEBUG_LOG("0. AT - Test MCU-8266");
	while (ESP8266_SendCmd("AT\r\n", "OK"))
		delay_ms(500);

	DEBUG_LOG("1. AT+RST - Soft RST 8266");
	ESP8266_SendCmd("AT+RST\r\n", "");
	delay_ms(500);
	ESP8266_SendCmd("AT+CIPCLOSE\r\n", "");
	delay_ms(500);
	DEBUG_LOG("2. AT+CWMODE=1,1 - SET 8266 WorkMode = STA");
	while (ESP8266_SendCmd("AT+CWMODE=1\r\n", "OK"))
		delay_ms(500);

	DEBUG_LOG("3. AT+CWDHCP=1,1 - ENABLE STAMODE DHCP");
	while (ESP8266_SendCmd("AT+CWDHCP=1,1\r\n", "OK"))
		delay_ms(500);

	DEBUG_LOG("4. AT+CWJAP - CONNECT WIFI -> [ SSID: %s ]  -> [ Password: %s ] ", WIFI_SSID, WIFI_PSWD);
	while (ESP8266_SendCmd(ESP8266_WIFI_INFO, "GOT IP"))
		delay_ms(500);

	DEBUG_LOG("5. AT+CIPSTART - CONNECT SERVER -> [ %s:%s ]", SERVER_HOST, SERVER_PORT);
	while (ESP8266_SendCmd(ESP8266_ONENET_INFO, "CONNECT"))
		delay_ms(500);
	//	ESP8266_INIT_OK = 1;
	DEBUG_LOG("6. ESP8266 Init OK ESP8266 INIT SUCCESS");
	DEBUG_LOG("ESP8266 INIT [OK]");
}

//==========================================================
//	函数名称：	USART2_IRQHandler
//
//	函数功能：	串口2收发中断
//
//	入口参数：	无
//
//	返回参数：	无
//
//	说明：
//==========================================================
void USART2_IRQHandler(void)
{

	if (USART_GetITStatus(USART2, USART_IT_RXNE) != RESET) // 接收中断
	{
		if (esp8266_cnt >= sizeof(esp8266_buf))
			esp8266_cnt = 0; // 防止串口被刷爆
		esp8266_buf[esp8266_cnt++] = USART2->DR;

		USART_ClearFlag(USART2, USART_FLAG_RXNE);
	}
}

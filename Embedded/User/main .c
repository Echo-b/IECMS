#include "stm32f10x.h"

// 网络协议层
#include "onenet.h"

// 网络设备
#include "esp8266.h"

// 硬件驱动
#include "delay.h"
#include "LED.h"
#include "usart.h"
#include "dht11.h"

// C库
#include <string.h>
#include <stdio.h>

const char devPubTopics[] = "/topics/led/pub";
const char *devSubTopics[] = {"/topics/led/sub"};

u8 humidityH;	 // 湿度整数部分
u8 humidityL;	 // 湿度小数部分
u8 temperatureH; // 温度整数部分
u8 temperatureL; // 温度小数部分
u8 Led_Status = 0;

char PUB_BUF[256]; // 上传数据的buf

/*
************************************************************
*	函数名称：	Hardware_Init
*
*	函数功能：	硬件初始化
*
*	入口参数：	无
*
*	返回参数：	无
*
*	说明：		初始化单片机功能以及外接设备
************************************************************
*/
void Hardware_Init(void)
{

	NVIC_PriorityGroupConfig(NVIC_PriorityGroup_2); // 中断控制器分组设置

	delay_init(); // systick初始化

	Usart1_Init(115200); // 串口1，打印信息用

	Usart2_Init(115200); // 串口2，驱动ESP8266用

	UsartPrintf(USART_DEBUG, " Hardware init OK\r\n");
}

int main(void)
{
	unsigned short timeCount = 0; // 发送间隔变量

	unsigned char *dataPtr = NULL;

	Hardware_Init(); // 初始化外围硬件

	//	DHT11_Init();
	LED_Init();
	while (DHT11_Init())
	{
		UsartPrintf(USART_DEBUG, " DHT11 ERROR\r\n");
		delay_ms(1000);
	}

	ESP8266_Init(); // 初始化ESP8266

	while (OneNet_DevLink()) // 接入OneNET
		delay_ms(500);

	LED_ON(); // 灯亮灭提示接入成功
	delay_ms(200);
	LED_OFF();

	OneNet_Subscribe(devSubTopics, 1);

	while (1)
	{
		if (timeCount % 40 == 0)
		{
			/********** 温湿度传感器获取数据**************/
			DHT11_Read_Data(&humidityH, &humidityL, &temperatureH, &temperatureL);
		}
		if (++timeCount >= 300) // 发送间隔5s
		{
			Led_Status = GPIO_ReadInputDataBit(GPIOA, GPIO_Pin_0); // 读取LED0的状态
			DEBUG_LOG("==================================================================================");
			DEBUG_LOG("publish data ----- OneNet_Publish");
			sprintf(PUB_BUF, "{\"Hum\":%d.%d,\"Temp\":%d.%d,\"Led\":%d}",
					humidityH, humidityL, temperatureH, temperatureL, Led_Status ? 0 : 1);
			OneNet_Publish(devPubTopics, PUB_BUF);
			DEBUG_LOG("==================================================================================");
			timeCount = 0;
			ESP8266_Clear();
		}

		dataPtr = ESP8266_GetIPD(0);
		if (dataPtr != NULL)
			OneNet_RevPro(dataPtr);

		delay_ms(10);
	}
}

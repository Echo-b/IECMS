## 项目需求

- 设备控制
  - 设备开关
  - 设置温度
- 设备管理
  - 告警
  - 增删改查
- 数据统计
  - 统计图展示
- 用户管理
  - 增删改查

## MQTT消息路径设计

|               设备               |             路径              |
| :------------------------------: | :---------------------------: |
|        LED灯上行数据路径         |      `devTopic/led/pub`       |
|        LED灯下行数据路径         |      `devTopic/led/sub`       |
|        蜂鸣器上行数据路径        |      `devTopic/beep/pub`      |
|        蜂鸣器下行数据路径        |      `devTopic/beep/sub`      |
| 光照传感器(`BH1750`)上行数据路径 |  `devTopic/lightSensor/pub`   |
|     温湿度传感器上行数据路径     |    `devTopic/htSensor/pub`    |
|      红外传感器上行数据路径      | `devTopic/infraredSensor/pub` |
|      红外传感器下行数据路径      | `devTopic/infraredSensor/sub` |

## MQTT中设备发送消息`json`格式设置

基本要求
- 确保复用性
- 可以用一个`data`字段来包含所有设备的信息，在`data`字段中去设置相应的字段值，从而实现对设备的控制。

例如
```c
{
    "data":{
        "led":{
            "status":"on"
            ...
        },
        "htSensor":{
            "temperture":23,
            "humidity":28,
            "status":"on"
            ...
        }
        ...
    }
}
```

但从实际使用情况来看，其实控制设备的时候发送的是命令指令(`command`)因此是否也可以为一系列指令集，诸如`LEDON,LEDOFF`之类，这样通过解析对应的`command`字段我们就可以解读出需要执行的指令。一个`command`可能是如下的形式

```c
command:[
  "LEDON","HTSENSOROFF","BEEPON","TEMPERTURE_DOWN_3"...    
]
```

通过这一步的封装，我们就可以在从前端网页界面对设备进行控制时，将对应的控制信息封装在数据中，当到达后端时在重新进行解析，`sub`或者`pub`的时候自动的获取或者发送所需要的数据。

## 设备信息数据库设计

### led数据表设计规定
|  名称  |   类型    |
| :----: | :-------: |
| status | vchar(32) |

### 温湿度数据表设计规定
|    名称    |   类型    |
| :--------: | :-------: |
| temperture |    int    |
|  humidity  |    int    |
|   status   | vchar(32) |


# 项目初步分工

| 成员   | 任务                         |
| ------ | ---------------------------- |
| 白仕博 | 嵌入式，mqtt协议，前端，文档 |
| 杨宇轩 | 后端 ，文档                  |
| 周天伦 | 前端 ，文档                  |
| 丁晨旭 | 前端 ，文档，嵌入式          |
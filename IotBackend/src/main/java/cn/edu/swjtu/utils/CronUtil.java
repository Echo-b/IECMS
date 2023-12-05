package cn.edu.swjtu.utils;

import cn.edu.swjtu.pojo.TaskCron;
import java.util.Calendar;

public class CronUtil {
    /**
     * 方法摘要：构建Cron表达式
     * @param TaskCron
     * @return String 获得corn表达式
     *  scheduleModel.setJobType(); 确定类型
     *  scheduleModel.setStartDate("2021-10-29 08:00:00"); 设置时间
     */
    public static String createCronExpression(TaskCron TaskCron) throws Exception{

        //拆分时间字符串 年，月，日，时，分，秒
        String[] split1 = TaskCron.getStartDate().split(" |-|:");


        StringBuffer cronExp = new StringBuffer("");

        if (null == TaskCron.getJobType()) {
            System.out.println("执行周期未配置");//执行周期未配置
        }

        if (null != split1[5] && null != split1[4] && null != split1[3]) {

            if(TaskCron.getJobType().intValue() == 0){

                //秒
                cronExp.append(split1[5]).append(" ");

                //每分钟
                cronExp.append("* ").append(" ");
                cronExp.append("* ");//小时
                cronExp.append("* ");//日
                cronExp.append("* ");//月
                cronExp.append("?");//周
            }else if(TaskCron.getJobType().intValue() == 1){

                //秒
                cronExp.append(split1[5]).append(" ");
                //分
                cronExp.append(split1[4]).append(" ");

                //每小时
                cronExp.append("* ");//小时
                cronExp.append("* ");//日
                cronExp.append("* ");//月
                cronExp.append("?");//周
            }else if(TaskCron.getJobType().intValue() == 2 || TaskCron.getJobType().intValue() == 3 ||
                    TaskCron.getJobType().intValue() == 4 || TaskCron.getJobType().intValue() == 5){

                //秒
                cronExp.append(split1[5]).append(" ");
                //分
                cronExp.append(split1[4]).append(" ");
                //时
                cronExp.append(split1[3]).append(" ");
            }

            //按每日
            if (TaskCron.getJobType().intValue() == 2) {

                cronExp.append("* ");//日
                cronExp.append("* ");//月
                cronExp.append("?");//周
            }
            //按每周
            else if (TaskCron.getJobType().intValue() == 3) {

                String[] split2 = TaskCron.getStartDate().split(" ");
                Calendar instance = Calendar.getInstance();//创建格林威治时间
//                instance.setTime(date);//将传来的时间设置到格林威治时间内
                int dayForWeek = instance.get(Calendar.DAY_OF_WEEK) - 1;
                //获取本周的周几
//                int dayForWeek = DataUtils.dayForWeek(split2[0]);
                //一个月中第几天
                cronExp.append("? ");
                //月份
                cronExp.append("* ");
                //周
                cronExp.append(dayForWeek + 1);
            }
            //按每月
            else if (TaskCron.getJobType().intValue() == 4) {
                //一个月中的哪几天
                cronExp.append(split1[2]);
                //月份
                cronExp.append(" * ");
                //周
                cronExp.append("?");
            }
            //按每年
            else if (TaskCron.getJobType().intValue() == 5) {

                //一个月中的哪几天
                cronExp.append(split1[2]).append(" ");
                //月份
                cronExp.append(split1[1]).append(" ");
                //周
                cronExp.append("?");
            }

        } else {
            System.out.println("时或分或秒参数未配置");//时或分或秒参数未配置
        }
        return cronExp.toString();
    }
}

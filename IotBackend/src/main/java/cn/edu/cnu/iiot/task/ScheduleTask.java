package cn.edu.cnu.iiot.task;

import cn.edu.cnu.iiot.mapper.DataMapper;
import cn.edu.cnu.iiot.pojo.NormalData;
import cn.edu.cnu.iiot.pojo.TData;
import cn.edu.cnu.iiot.utils.DateUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Data
@Component
@PropertySource("classpath:/taskConfig.ini")
public class ScheduleTask implements SchedulingConfigurer {
    @Value("${printTime.cron}")
    private String cron;

    private Long timer = 10000L;

    @Autowired
    private DataMapper mapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 动态使用cron表达式设置循环间隔
        taskRegistrar.addTriggerTask(() -> {
            System.out.println("avg temperature handle");
            int did=(int)(Math.random() * 4) + 1;
            System.out.println(did);
            handle(did);
        }, triggerContext -> {
            PeriodicTrigger periodicTrigger = new PeriodicTrigger(timer);
            Date nextExecutionTime = periodicTrigger.nextExecutionTime(triggerContext);
            return nextExecutionTime;
        });
    }

    public void handle(int did){
        ArrayList<TData> tData = new ArrayList<>();
        try {
            tData = mapper.getData(did);
        }catch (Exception e){
            e.printStackTrace();
        }
        for (TData item : tData) {
            item.setDate(DateUtil.getDate());
            try {
                mapper.insertAverageTemperature(item);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
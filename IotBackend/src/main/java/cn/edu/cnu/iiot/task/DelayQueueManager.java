package cn.edu.cnu.iiot.task;

import cn.edu.cnu.iiot.pojo.DeviceAction;
import cn.edu.cnu.iiot.service.MqttActions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DelayQueueManager implements CommandLineRunner {

    private final DelayQueue<DelayTask> delayQueue = new DelayQueue<>();

    @Autowired
    private MqttActions mqttActions;


    /**
     * 加入到延时队列中
     *
     * @param task
     */
    public void put(DelayTask task) {
        log.info("加入延时任务：{}", task);
        delayQueue.put(task);
    }

    /**
     * 取消延时任务
     *
     * @param task
     * @return
     */
    public boolean remove(DelayTask task) {
        log.info("取消延时任务：{}", task);
        return delayQueue.remove(task);
    }

    /**
     * 取消延时任务
     *
     * @param deviceAction
     * @return
     */
    public boolean remove(DeviceAction deviceAction) {
        return remove(new DelayTask(new TaskBase(deviceAction), 0));
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化延时队列");
        Executors.newSingleThreadExecutor().execute(new Thread(this::excuteThread));
    }

    /**
     * 延时任务执行线程
     */
    private void excuteThread() {
        while (true) {
            try {
                DelayTask task = delayQueue.take();
                //执行任务
                TimeUnit unit = TimeUnit.SECONDS;
                if(task.getDelay(unit) <= 0) {
                    processTask(task);
                    remove(task);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * 内部执行延时任务
     *
     * @param task
     */
    private void processTask(DelayTask task) {
        //获取任务参数，执行业务task.getData().getIdentifier()
        log.info("执行延时任务：{}", task);
        String topic = task.getData().getDeviceAction().getDeviceName();
        String msg = task.getData().getDeviceAction().getAction();
        mqttActions.pubMqttMsg(topic,msg);
    }
}

package cn.edu.cnu.iiot;

import cn.edu.cnu.iiot.pojo.DeviceAction;
import cn.edu.cnu.iiot.task.DelayQueueManager;
import cn.edu.cnu.iiot.task.DelayTask;
import cn.edu.cnu.iiot.task.TaskBase;
import cn.edu.cnu.iiot.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class IiotApplicationTests {

    @Autowired
    private DelayQueueManager delayQueueManager;

    @Test
    void contextLoads() throws InterruptedException {
        DeviceAction deviceAction = new DeviceAction("1","open","20", DateUtil.getDate());
        delayQueueManager.put(new DelayTask(new TaskBase(deviceAction), 1000 * 10));

//        TimeUnit unit = null;
//        if(delayQueueManager.)
//        Thread.sleep(10 * 1000);
    }

}

package cn.edu.swjtu.utils;

import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 线程池
 */
@Component
public class MyScheduleUtil implements SchedulingConfigurer {

    /** 定时任务注册器 */
    private ScheduledTaskRegistrar taskRegistrar;
    private Set<ScheduledFuture<?>> scheduledFutures = null;
    /** 存放所有定时任务 */
    private Map<String, ScheduledFuture<?>> taskMap = new ConcurrentHashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
    }

    @SuppressWarnings("unchecked")
    private Set<ScheduledFuture<?>> getScheduledFutures() {
        if (scheduledFutures == null) {
            try {
                scheduledFutures = (Set<ScheduledFuture<?>>) getProperty(taskRegistrar, "scheduledTasks");
            }
            catch (NoSuchFieldException e) {
                throw new SchedulingException("not found scheduledFutures field.");
            }
        }
        return scheduledFutures;
    }

    /**
     * 添加定时任务
     * @param taskId 定时任务唯一标识
     * @param runnable 定时任务执行的具体逻辑
     * @param trigger 下一个定时任务执行时机的触发器
     */
    public void addTriggerTask(String taskId, Runnable runnable, Trigger trigger) {
        // 若定时任务已经存在，则进行覆盖操作（即先移除）
        if (taskMap.containsKey(taskId)) {
            removeTriggerTask(taskId);
        }
        // 添加定时任务
        ScheduledFuture<?> schedule = Objects.requireNonNull(taskRegistrar.getScheduler()).schedule(runnable, trigger);
        getScheduledFutures().add(schedule);
        taskMap.put(taskId, schedule);
    }

    /**
     * 移除定时任务
     * @param taskId 定时任务 id
     */
    public void removeTriggerTask(String taskId) {
        ScheduledFuture<?> future = taskMap.get(taskId);
        if (future != null) {
            future.cancel(true);
        }
        taskMap.remove(taskId);
        getScheduledFutures().remove(future);
    }

    public static Field findField(Class<?> clazz, String name) {
        try {
            return clazz.getField(name);
        } catch (NoSuchFieldException ex) {
            return findDeclaredField(clazz, name);
        }
    }

    public static Field findDeclaredField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {
            if (clazz.getSuperclass() != null) {
                return findDeclaredField(clazz.getSuperclass(), name);
            }
            return null;
        }
    }

    public static Object getProperty(Object obj, String name) throws NoSuchFieldException {
        Object value = null;
        Field field = findField(obj.getClass(), name);
        if (field == null) {
            throw new NoSuchFieldException("no such field [" + name + "]");
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            value = field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(accessible);
        return value;
    }

}

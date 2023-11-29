package cn.edu.swjtu.service.impl;

import cn.edu.swjtu.mapper.TodoListMapper;
import cn.edu.swjtu.pojo.TodoList;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.TodoListService;
import cn.edu.swjtu.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListMapper mapper;

    @Override
    public ResponseData addTodoListTask(TodoList t) {
        try{
            t.setStatus(0);
            t.setDate(DateUtil.getDate());
            if(mapper.addTodoListTask(t) > 0)
                return ResponseData.success("增加任务成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("增加任务失败");
    }

    @Override
    public ResponseData findAllTodoListTask() {
        try{
            ArrayList<TodoList> tasks = mapper.findAllTodoListTask();
            if(tasks.size() > 0)
                return ResponseData.success("success").data("tasks",tasks);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("未能找到匹配数据");
    }

    @Override
    public ResponseData findTodoListTaskById(int tid) {
        try{
            TodoList task = mapper.findTodoListTaskById(tid);
            return ResponseData.success("success").data("task",task);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("未能找到匹配数据");
    }

    @Override
    public ResponseData updateTodoListTask(TodoList t) {
        try{
            if(mapper.updateTodoListTask(t) > 0)
                return ResponseData.success("更新任务成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseData.error("更新数据失败");
    }
}

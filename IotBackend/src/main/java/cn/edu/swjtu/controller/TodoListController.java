package cn.edu.swjtu.controller;

import cn.edu.swjtu.pojo.TodoList;
import cn.edu.swjtu.result.ResponseData;
import cn.edu.swjtu.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    @PostMapping("/add")
    ResponseData addTodoListTask(@RequestBody TodoList t){
        System.out.println("t = " + t);
        return todoListService.addTodoListTask(t);
    }

    @GetMapping("/appoint")
    ResponseData getTodoListTaskById(int tid) {
        return  todoListService.findTodoListTaskById(tid);
    }

    @GetMapping("/all")
    ResponseData getAllTodoListTask() {
        return  todoListService.findAllTodoListTask();
    }

    @PostMapping("/update")
    ResponseData updateTodoListTask(TodoList t){
        return todoListService.updateTodoListTask(t);
    }
}

package cn.edu.swjtu.service;

import cn.edu.swjtu.pojo.TodoList;
import cn.edu.swjtu.result.ResponseData;

public interface TodoListService {
    ResponseData addTodoListTask(TodoList t);

    ResponseData findAllTodoListTask();

    ResponseData findTodoListTaskById(int tid);

    ResponseData updateTodoListTask(TodoList t);
}

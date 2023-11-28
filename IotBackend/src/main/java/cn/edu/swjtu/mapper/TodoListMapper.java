package cn.edu.swjtu.mapper;

import cn.edu.swjtu.pojo.TodoList;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface TodoListMapper {

    @Select("select * from todolist where tid = #{tid};")
    TodoList findTodoListTaskById(int tid);

    @Select("select * from todolist;")
    ArrayList<TodoList> findAllTodoListTask();

    @Insert("insert into todolist (tid,date,apply,deviceName,did,status) " +
            "values (#{tid},#{date},#{apply},#{deviceName},#{did},#{status});")
    int addTodoListTask(TodoList t);

    @Update("update todolist set status = {status} where tid = {tid};")
    int updateTodoListTask(int tid);
}

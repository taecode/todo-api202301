package com.example.todo.todopai.repository;

import com.example.todo.todopai.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity,String> {

    //완료되지 않은 할 일들만 조회
    @Query("select t from TodoEntity t where t.done=0")
    List<TodoEntity> findNotYetTodos();
}

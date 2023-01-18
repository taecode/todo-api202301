package com.example.todo.todopai.service;

import com.example.todo.todopai.entity.TodoEntity;
import com.example.todo.todopai.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

//    @Autowired   ==> @RequiredArgsConstructor 이게 자동으로 만들어줌
//    public TodoService(TodoRepository todoRepository) {
//        this.todoRepository = todoRepository;
//    }

    //할 일 목록 조회
    public List<TodoEntity> retrieve(){
        return todoRepository.findAll();
    }

}

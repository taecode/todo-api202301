package com.example.todo.todopai.controller;


import com.example.todo.todopai.dto.request.TodoCreateRequestDTO;
import com.example.todo.todopai.dto.request.TodoModifyRequestDTO;
import com.example.todo.todopai.dto.response.TodoListResponseDTO;
import com.example.todo.todopai.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoApiController {

    private final TodoService todoService;

    //할 일 등록 요청
    @PostMapping
    public ResponseEntity<?>createTodo(
            @Validated @RequestBody TodoCreateRequestDTO requestDTO
            , BindingResult result
    ){
        if(result.hasErrors()){
            log.warn("DTO 검증 에러 발생 : {}",result.getFieldError());
            return ResponseEntity
                    .badRequest()
                    .body(result.getFieldError());
        }

        try {
            TodoListResponseDTO responseDTO = todoService.create(requestDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(TodoListResponseDTO.builder().error(e.getMessage()));
        }
    }

    //할 일 삭제 요청
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(
            @PathVariable("id") String todoId)
    {
        log.info("/api/todos/{} DELETE request!", todoId);

        if(todoId==null || todoId.equals("")){
            return ResponseEntity
                    .badRequest()
                    .body(TodoListResponseDTO.builder().error("ID를 전달해주세요"));
        }

        try{
            TodoListResponseDTO responseDTO = todoService.delete(todoId);
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError()
                    .body(TodoListResponseDTO.builder().error(e.getMessage()));
        }
    }

    //할 일 목록 요청(GET)
    @GetMapping
    public ResponseEntity<?> retrieveTodo()
    {

        try{
            TodoListResponseDTO responseDTO=todoService.retrieve();
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError()
                    .body(TodoListResponseDTO.builder().error(e.getMessage()));
        }

    }

    //할 일 수정 요청(PUT, PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTodo(
            @PathVariable("id") String todoId,
            @Validated @RequestBody TodoModifyRequestDTO modifyRequestDTO
    ){
        log.info("/api/todos/ {} update request!",todoId);
        log.info("수정할 할 일 : {}",modifyRequestDTO);

        try {
            TodoListResponseDTO todoListResponseDTO=todoService.update(todoId,modifyRequestDTO);
             return ResponseEntity
                    .ok()
                    .body(modifyRequestDTO);
        }
        catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(TodoListResponseDTO.builder().error(e.getMessage()));
        }

    }
}

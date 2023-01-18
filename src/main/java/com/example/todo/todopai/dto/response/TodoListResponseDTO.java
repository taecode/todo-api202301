package com.example.todo.todopai.dto.response;

import lombok.*;

import java.util.List;

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoListResponseDTO {

    private String error; //에러발생시 클라이언트에게 전달할 메시지
    private List<TodoDetailResponseDTO> todos;
}

package com.example.todo.todopai.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class TodoModifyRequestDTO {

    @NotBlank
    @Size(min=2,max=10)
    private String title;
    private boolean done;


}

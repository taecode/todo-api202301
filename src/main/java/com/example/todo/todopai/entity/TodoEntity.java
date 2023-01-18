package com.example.todo.todopai.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

//일정관리 프로그램
@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="todoId")
@Builder

@Entity
@Table(name="tbl_todo")
public class TodoEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    //PK가 중복 없는 랜덤 문자로 설정된다.
    private String todoId;

    @Column(nullable = false,length = 30)
    private String title; //제목
    private boolean done; //일정 완료 여부
    @CreationTimestamp
    private LocalDateTime createDate; //등록 시간

}

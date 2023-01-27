package com.example.todo.todopai.entity;

import com.example.todo.userapi.entity.UserEntity;
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
    private boolean done; //일정 완료 여부 (false 기본)
    @CreationTimestamp
    private LocalDateTime createDate; //등록 시간

    //회원과 관계 설정 (할 일이 N 회원이 1)
    @ManyToOne(fetch = FetchType.LAZY)
    //연관관계 설정은 했지만 INSERT, UPDATE 시에는 이 객체를 활용하지 않겠다.
    @JoinColumn(name="user_id",insertable = false,updatable = false)
    private UserEntity user;

    //할 일 추가, 수정 시 사용할 외래키
    @Column(name="user_id")
    private String userId;

}

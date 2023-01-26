package com.example.todo.userapi.service;

import com.example.todo.userapi.dto.UserSignUpDTO;
import com.example.todo.userapi.dto.UserSignUpResponseDTO;
import com.example.todo.userapi.entity.UserEntity;
import com.example.todo.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입 처리
    public UserSignUpResponseDTO create(final UserSignUpDTO userSignUpDTO){
        if(userSignUpDTO==null){
            throw new RuntimeException("가입정보가 없습니다.");
        }
        final String email=userSignUpDTO.getEmail();
        if(userRepository.existsByEmail(email)){
            log.warn("Email already exists - {}",email);
            throw new RuntimeException("중복된 이메일입니다.");
        }
        //패스워드 인코딩
        String rawPassword = userSignUpDTO.getPassword();//인코딩 안된 평문 암호
        String encodePassword = passwordEncoder.encode(rawPassword);//암호화 처리
        userSignUpDTO.setPassword(encodePassword);

        UserEntity savedUser = userRepository.save(userSignUpDTO.toEntity());
        return new UserSignUpResponseDTO(savedUser);
    }
}
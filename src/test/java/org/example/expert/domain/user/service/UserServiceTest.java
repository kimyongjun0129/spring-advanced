package org.example.expert.domain.user.service;

import org.example.expert.TestConst;
import org.example.expert.config.PasswordEncoder;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @InjectMocks
    private PasswordEncoder passwordEncoder;

    @Test
    void 유저_조회시_InvalidRequestException_오류_발생() {
        //given
        long userId = 1L;

        given(userRepository.findById(anyLong())).willReturn(Optional.empty());

        //when
        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            userService.getUser(userId);
        });

        //then
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    @Transactional
    void 유저_조회_성공() {
        //given
        long userId = 1L;

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(TestConst.USER));

        //when
        UserResponse user = userService.getUser(userId);

        //then
        assertEquals(user.getEmail(), TestConst.USER.getEmail());
    }
}
package org.example.expert.domain.user.service;

import org.example.expert.TestConst;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.example.expert.TestConst.USER_ROLE_CHANGE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserAdminServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private  UserAdminService userAdminService;

    @Test
    @Transactional
    void 유저_역할_변경시_InvalidRequestException_오류_발생() {
        //given
        long userId = 1L;

        given(userRepository.findById(anyLong())).willReturn(Optional.empty());

        //when & then
        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> {
            userAdminService.changeUserRole(userId, USER_ROLE_CHANGE);
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    @Transactional
    void 유저_역할_변경_성공() {
        //given
        long userId = 1L;

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(TestConst.USER));

        //when
        userAdminService.changeUserRole(userId, USER_ROLE_CHANGE);

        //then
        TestConst.USER.updateRole(UserRole.of("ADMIN"));
        assertEquals(UserRole.ADMIN, TestConst.USER.getUserRole());
    }
}
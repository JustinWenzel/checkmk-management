package com.checkmk.checkmk_management.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.checkmk.checkmk_management.user.exception.UserDoesNotExistException;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailService userDetailService;
    
    @Test
    void shouldThrowUserDoesNotExistException() {
        User user = new User();
        user.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());

        assertThrows(UserDoesNotExistException.class, () -> {
            userDetailService.loadUserByUsername("testUser");
        });
    }

    
}
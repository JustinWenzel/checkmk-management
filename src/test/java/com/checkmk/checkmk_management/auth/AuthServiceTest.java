package com.checkmk.checkmk_management.auth;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.checkmk.checkmk_management.auth.dto.RegisterFormDTO;
import com.checkmk.checkmk_management.auth.exception.PasswordIsNotEqualException;
import com.checkmk.checkmk_management.role.Role;
import com.checkmk.checkmk_management.role.RoleRepository;
import com.checkmk.checkmk_management.role.exception.RoleNotFoundException;
import com.checkmk.checkmk_management.user.User;
import com.checkmk.checkmk_management.user.UserMapper;
import com.checkmk.checkmk_management.user.UserRepository;
import com.checkmk.checkmk_management.user.exception.UserAlreadyExistsException;

@ExtendWith(MockitoExtension.class) //Activates Mockito
public class AuthServiceTest {

    // Create mock objects
    @Mock 
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper; 


    @InjectMocks
    private AuthService authService; //Create AuthService with injected Mock objects

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExist() {
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setEmailAddress("admin@gmail.com");

        // When someone asks if this email exists, say yes
        when(userRepository.existsByEmailAddress("admin@gmail.com")).thenReturn(true);

        // Run the method and verify it throws the expected exception
        assertThrows(UserAlreadyExistsException.class, () -> {
            authService.registerUser(registerFormDTO);
        });
    }

    @Test
    void shouldThrowExceptionWhenPasswordDoNotMatch(){
        RegisterFormDTO formDTO = new RegisterFormDTO();
        formDTO.setEmailAddress("admin@gmail.com");
        formDTO.setPassword("password");
        formDTO.setConfirmPassword("wrongPassword");

        when(userRepository.existsByEmailAddress("admin@gmail.com")).thenReturn(false);

        assertThrows(PasswordIsNotEqualException.class, () -> {
            authService.registerUser(formDTO);
        });
    }

    @Test
    void shouldThrowExceptionWhenRoleNotFound() {
        RegisterFormDTO formDTO = new RegisterFormDTO();
        formDTO.setEmailAddress("admin@gmail.com");
        formDTO.setPassword("password");
        formDTO.setConfirmPassword("password");
        formDTO.setRole("TEST_ROLE");

        when(userRepository.existsByEmailAddress("admin@gmail.com")).thenReturn(false);
        when(roleRepository.findRoleByName("TEST_ROLE")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> {
            authService.registerUser(formDTO);
        });

    }

    @Test
    void shouldRegisterSuccessfully() {
        RegisterFormDTO formDTO = new RegisterFormDTO();
        formDTO.setEmailAddress("admin@gmail.com");
        formDTO.setPassword("password");
        formDTO.setConfirmPassword("password");
        formDTO.setRole("TEST_ROLE");

        User user = new User();

        Role role = new Role();
        role.setName("TEST_ROLE");

        // Set response of the methods
        when(userRepository.existsByEmailAddress("admin@gmail.com")).thenReturn(false);
        when(roleRepository.findRoleByName("TEST_ROLE")).thenReturn(Optional.of(role));
        when(userMapper.toModel(formDTO, role)).thenReturn(user);

        authService.registerUser(formDTO);
        verify(userRepository).save(user);

    }

    


}

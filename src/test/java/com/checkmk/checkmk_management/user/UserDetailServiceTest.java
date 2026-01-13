package com.checkmk.checkmk_management.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.checkmk.checkmk_management.role.Role;
import com.checkmk.checkmk_management.user.exception.UserDoesNotExistException;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailService userDetailService;
    
    @Test
    void shouldThrowUserDoesNotExistException() {

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());

        assertThrows(UserDoesNotExistException.class, () -> {
            userDetailService.loadUserByUsername("testUser");
        });
    }

    @Test
    void loginUserSuccessfully(){
        Role role = new Role();
        role.setName("ADMIN");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setRoles(roles);


        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        UserDetails details = userDetailService.loadUserByUsername("testUser");

        // loadByUserName returns something, therefore we need to validate
        assertEquals(user.getUsername(), details.getUsername());
        assertEquals(user.getPassword(), details.getPassword());
        assertTrue(details.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));




    }

    
}
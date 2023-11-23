package com.spring.blog.springbootblogrestapi.security;
import com.spring.blog.springbootblogrestapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private LoginAttemptService loginAttemptService;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//        if(loginAttemptService.isBlocked()){
//            throw new RuntimeException("blocked");
//        }
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User not found with username or email: %s", usernameOrEmail)));
    }
}

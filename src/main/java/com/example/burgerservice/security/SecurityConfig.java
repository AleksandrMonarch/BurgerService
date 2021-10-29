package com.example.burgerservice.security;

import org.mapstruct.control.MappingControl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        List<UserDetails> userDetails = new ArrayList<>();
//        userDetails.add(new User("Alex", passwordEncoder.encode("easypassword"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        userDetails.add(new User("Dmitry", passwordEncoder.encode("hardpassword"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return userName -> {
            Optional<User> user = repository.findUserByUserName(userName);
            if (user.isPresent()) {
                return user.get();
            }
            throw new UsernameNotFoundException(String.format("%s is not found", userName));
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeRequests()
//                .antMatchers("/register", "/login", "/h2-console")
//                .permitAll()
//                .antMatchers("/**")
//                .hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/design")
//                .and()
//                .build();

        return httpSecurity
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/design", "/orders").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design")
                .and()
                .csrf()
                .disable()
                .build();
    }
}

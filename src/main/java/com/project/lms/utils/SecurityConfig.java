package com.project.lms.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new AuthUserService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests((authorize)-> authorize.
                       requestMatchers(HttpMethod.GET,"/student/**").hasAuthority(Constants.STUDENT_SELF_AUTHORITY).
                       requestMatchers(HttpMethod.GET,"/student-by-id/**").hasAuthority(Constants.STUDENT_INFO_AUTHORITY).
                       requestMatchers("/admin/**").hasAuthority(Constants.CREATE_ADMIN_AUTHORITY).
                       requestMatchers(HttpMethod.POST,"/book/**").hasAuthority(Constants.CREATE_BOOK_AUTH).
                       requestMatchers(HttpMethod.GET,"/book/**").hasAuthority(Constants.ACCESS_BOOK_AUTH).
                       requestMatchers(HttpMethod.POST,"/transaction/payment/**").hasAuthority(Constants.PAYMENT_AUTH).
                       requestMatchers(HttpMethod.GET,"/transaction/**").hasAuthority(Constants.INITIATE_TXN_AUTH).
                       requestMatchers("/**").permitAll().
                       anyRequest().authenticated()).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

}

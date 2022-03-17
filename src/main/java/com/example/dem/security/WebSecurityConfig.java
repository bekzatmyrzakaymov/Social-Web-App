package com.example.dem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("usr")
//                .password("pass")
//                .roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/user/getAllUsers").hasRole("ADMIN")
//                .antMatchers("/user/addNotes-get").hasRole("ADMIN")
//                .antMatchers("/note/getAllNotes").hasRole("ADMIN")
//                .antMatchers("/user/email").hasRole("ADMIN")
//                .antMatchers("/user/create-get").permitAll()
//                .antMatchers("/user/user-update").permitAll()
//                .antMatchers("/user/getUser").permitAll()
//                .antMatchers("/user/delete-get").permitAll()
//                .antMatchers("/note/createNote-get").permitAll()
//                .antMatchers("/note/note-update").permitAll()
//                .antMatchers("/note/delete-get").permitAll()
//                .and().formLogin();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
}

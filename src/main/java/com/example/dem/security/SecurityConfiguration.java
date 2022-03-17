package com.example.dem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers("/admin", "/admin/*", "/admin/**").permitAll()
                .antMatchers("/user", "/user/*", "/user/**").permitAll()
                .antMatchers("/user/getAllUsers").permitAll()
                .antMatchers("/user/addNotes-get").permitAll()
                .antMatchers("/note/getAllNotes").permitAll()
                .antMatchers("/user/email").permitAll()
                .antMatchers("/user/create-get").permitAll()
                .antMatchers("/user/user-update").permitAll()
                .antMatchers("/user/getUser").permitAll()
                .antMatchers("/user/delete-get").permitAll()
                .antMatchers("/note/createNote-get").permitAll()
                .antMatchers("/note/note-update").permitAll()
                .antMatchers("/note/delete-get").permitAll()
                .antMatchers("/").permitAll()
                .and().formLogin().defaultSuccessUrl("/note/getAllNotes", true).and().csrf().disable();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

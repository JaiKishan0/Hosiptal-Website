//package com.Hospital.Management.System.config;
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("admin").password("{noop}admin123").roles("ADMIN")
//            .and()
//            .withUser("doctor").password("{noop}doctor123").roles("DOCTOR")
//            .and()
//            .withUser("patient").password("{noop}patient123").roles("PATIENT");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/api/admin/**").hasRole("ADMIN")
//            .antMatchers("/api/doctors/**").hasRole("DOCTOR")
//            .antMatchers("/api/patients/**").hasRole("PATIENT")
//            .anyRequest().authenticated()
//            .and()
//            .formLogin();
//    }
//}

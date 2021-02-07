package com.surf_test.calculator.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Bean
   public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .antMatchers(GET,"/api/v1/calculation/*").permitAll()
                .antMatchers(GET,"/api/v1/calculation/search/*").hasRole("USER")
                .antMatchers(GET,"/api/v1/calculation/search/*").hasRole("ADMIN");
                //.and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

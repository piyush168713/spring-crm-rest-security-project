package com.luv2code.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource securityDataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.securityDataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/customer/delete*").hasAnyRole("OWNER", "ADMIN")
                .antMatchers("/customer/showFormForAdd*").hasAnyRole("OWNER", "MANAGER")
                .antMatchers("/customer/showFormForUpdate*").hasAnyRole("MANAGER", "OWNER")
                .antMatchers("/customer*").hasRole("EMPLOYEE")
                .antMatchers("/**").authenticated()
                    .and()
                .formLogin()
                .permitAll()
                    .and()
                .logout()
                .permitAll()
                    .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
}

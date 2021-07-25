package com.pyc.demo.config;
/*
 * @product IntelliJ IDEA
 * @project spring-boot-demo-set
 * @file SecurityConfig
 * @pack com.pyc.demo.config
 * @date 2021/7/25
 * @time 9:36
 * @author 御承扬
 * @E-mail 2923616405@qq.com
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 彭友聪
 * @date 2021/7/25
 */
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    protected UserDetailsService myUserDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        String[][] userGroupsAndRoles = {
                {"salaboy",
                        "password",
                        "ROLE_ACTIVITI_USER",
                        "GROUP_activitiTeam"},
                {"ryandawsonuk",
                        "password",
                        "ROLE_ACTIVITI_USER",
                        "GROUP_activitiTeam"},
                {"erdemedeiros",
                        "password",
                        "ROLE_ACTIVITI_USER",
                        "GROUP_activitiTeam"},
                {"other", "password",
                        "ROLE_ACTIVITI_USER",
                        "GROUP_otherTeam"},
                {"admin",
                        "password",
                        "ROLE_ACTIVITI_ADMIN"}};
        for (String[] user : userGroupsAndRoles) {
            List<String> authoritiesStrings = Arrays
                    .asList(Arrays
                            .copyOfRange(user, 2, user.length));
            log.info("> Registering new user: " + user[0] + " with the following Authorities[" + authoritiesStrings + "]");
            inMemoryUserDetailsManager.createUser(
                    new User(
                            user[0],
                            passwordEncoder()
                                    .encode(user[1]),
                            authoritiesStrings
                                    .stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList())));
        }
        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.pyc.demo.util;
/*
 * @product IntelliJ IDEA
 * @project spring-boot-demo-set
 * @file SecurityUtil
 * @pack com.pyc.demo.util
 * @date 2021/7/25
 * @time 9:54
 * @author 御承扬
 * @E-mail 2923616405@qq.com
 **/

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author 彭友聪
 * @date 2021/7/25
 */
@Component
@RequiredArgsConstructor()
public class SecurityUtil {

    private final UserDetailsService userDetailsService;

    public void logInAs(String username) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User" + username + "doesn't exist, please provide a valid user");
        }

        SecurityContextHolder.setContext(new SecurityContextImpl(
                new Authentication() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return user.getAuthorities();
                    }

                    @Override
                    public Object getCredentials() {
                        return user.getPassword();
                    }

                    @Override
                    public Object getDetails() {
                        return user;
                    }

                    @Override
                    public Object getPrincipal() {
                        return user;
                    }

                    @Override
                    public boolean isAuthenticated() {
                        return true;
                    }

                    @Override
                    public void setAuthenticated(boolean b) throws IllegalArgumentException {

                    }

                    @Override
                    public String getName() {
                        return user.getUsername();
                    }
                }
        ));
        org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(username);
    }
}

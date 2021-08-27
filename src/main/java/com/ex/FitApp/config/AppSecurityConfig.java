package com.ex.FitApp.config;

import com.ex.FitApp.security.DBUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final DBUserService userService;

    public AppSecurityConfig(PasswordEncoder passwordEncoder, DBUserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests().
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                        antMatchers("/errors/**","/about-us","/contact-us","/").permitAll().
                        antMatchers( "/users/login", "/users/register").anonymous().
                        antMatchers("/home","/users/**").authenticated().
                        antMatchers("/control-panel/**").hasRole("ROOT_ADMIN").
                and().
                        formLogin().
                        loginPage("/users/login").
                        defaultSuccessUrl("/home").
                         failureForwardUrl("/users/login-error").
                and().
                logout().
                        logoutUrl("/users/logout").
                        logoutSuccessUrl("/").
                        invalidateHttpSession(true).
                        deleteCookies("JSESSIONID")
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .fullyAuthenticated();//bye! :-)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }
}

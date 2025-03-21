package com.codegym.finalModule.config;

import com.codegym.finalModule.service.impl.UserInforDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserInforDetailService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/login", "/error","/register","/").permitAll()
                                .requestMatchers("/css/**", "/js/**", "/img/**", "/static/**").permitAll() // Updated line
                                .requestMatchers("/ShopPhone/css/**", "/ShopPhone/js/**", "/ShopPhone/img/**", "/ShopPhone/static/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/warehouse/**").hasAnyRole("WAREHOUSE","ADMIN")
                                .requestMatchers("/sales/**").hasAnyRole("SALES","ADMIN")
                                .requestMatchers("/business/**").hasAnyRole("BUSINESS","ADMIN")
                                .requestMatchers("/account", "/account/update", "/account/change-password").authenticated() // Allow authenticated users
                                .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                                .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginPage("/login")
                                .failureUrl("/login?error=true")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home", true))
                .logout((logout) ->
                        logout.deleteCookies("remove")
                                .invalidateHttpSession(false)
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/logoutSuccessful"))
                .rememberMe((remember) ->
                        remember.rememberMeParameter("remember-me")
                                .tokenValiditySeconds(60 * 60 * 24 * 365))
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .accessDeniedPage("/403"));

        return http.build();
    }
}
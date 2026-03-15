package com.bitsandbytes.product.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    //    add a filter
    @Bean
//    SecurityFilterChain is a Interface --->Yeh ek interface hai
//    jo Spring Security framework mein define kiya gaya hai (package: org.springframework.security.web).
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        returns http-------->aab ham hhtp build karenge
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests ->{
                    requests.requestMatchers(HttpMethod.GET,"/user/register").permitAll();
                        requests.requestMatchers(HttpMethod.GET,"/api/**").permitAll() //match all api and permit all, and get api----->  which matches to url in matcher
                                .anyRequest().authenticated();
    }).authenticationProvider(authenticationProvider())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    //    aab passwordEncoder (interface hai) se password encode kaise karna hai wo define (bata de) karde
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    aab for local users ko UserDetailsService se local storage se permission de sakte hai(usually database use karna chiea)
//    @Bean
//    public UserDetailsService userDetailsService() {
////        this is for admin
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
////        this is for Seller
//        UserDetails seller = User.builder()
//                .username("seller")
//                .password(passwordEncoder().encode("seller"))
//                .roles("SELLER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,seller);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //object banaya
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); //

        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception {
        return config.getAuthenticationManager();
    }
}
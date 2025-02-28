/*
 * package com.skiply.receipt.receipt.config; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfiguration {
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception {
 * System.out.println("Configuring Security Filter Chain..."); http
 * .csrf().disable() .authorizeHttpRequests(req -> req .anyRequest()
 * .authenticated()) .httpBasic();
 * 
 * System.out.println("Configuring Success ..."); return http.build(); } }
 */
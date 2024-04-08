//package com.blueyonder.shopdataservice.service;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class WebConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.cors(Customizer.withDefaults()).csrf(csrf->csrf.disable())
//                .authorizeHttpRequests(req -> req.requestMatchers(
//                        "/shopdataservice/ecapp/v1/product/addproduct",
//                        "/shopdataservice/ecapp/v1/product/update",
//                        "/shopdataservice/ecapp/v1/product/delete",
//                        "/shopdataservice/ecapp/v1/product/getproduct",
//                        "/shopdataservice/ecapp/v1/product/getallproducts",
//                        "/swagger-ui/index.html").permitAll());
////			.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}

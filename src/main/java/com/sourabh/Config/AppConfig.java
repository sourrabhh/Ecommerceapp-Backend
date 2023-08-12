package com.sourabh.Config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppConfig 
{
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> {
                    try {
                        csrf.disable()
                                .cors(cors -> cors.configurationSource(new CorsConfigurationSource()
                                {
                                    @Override
                                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request)
                                    {
                                        CorsConfiguration cfg = new CorsConfiguration();
                                        cfg.setAllowedOrigins(Arrays.asList(
                                                "http://localhost:3000"
                                        ));

                                        cfg.setAllowedMethods(Collections.singletonList("*"));
                                        cfg.setAllowCredentials(true);
                                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                                        cfg.setMaxAge(3600L);

                                        return cfg;
                                    }
                                }));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).httpBasic(withDefaults()).formLogin(withDefaults());

                //    .and().httpBasic().and().formLogin();
                return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sdm.gcms.config;

/**
 *
 * @author Matthias
 */
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;
import sdm.gcms.filters.ApiKeyAuthFilter;
import sdm.gcms.filters.SessionAuthFilter;
import sdm.gcms.shared.database.Core;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig {

    //@Value("${app.http.auth-token-header-name}")
    private String principalRequestHeader = "X-API-Key";

    //@Value("${app.http.auth-token}")
    private String principalRequestValue = "test";


    private boolean checkKey(Map<String, String> parameters) throws IOException, ClassNotFoundException {
        String key = parameters.get("key") != null ? parameters.get("key") : "";
        String command = parameters.get("command") != null ? parameters.get("command") : "";
        String api = parameters.get("api") != null ? parameters.get("api") : "";
        return Core.isValidApiKey(api, key, command);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ApiKeyAuthFilter apiFilter = new ApiKeyAuthFilter(principalRequestHeader);
        SessionAuthFilter sessionFilter = new SessionAuthFilter(principalRequestHeader);
        apiFilter.setAuthenticationManager(
                authentication -> {
                    String principal = (String) authentication.getPrincipal();

                    if (!Objects.equals(principalRequestValue, principal)) { // ||!checkKey(principal)
                        throw new BadCredentialsException(
                                "The API key was not found or not the expected value.");
                    }
                    authentication.setAuthenticated(true);
                    return authentication;
                });

        http
                .securityMatcher("/api/s/**")
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(apiFilter)
                .securityMatcher("/page/**")
                .csrf()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .addFilterAfter(sessionFilter, ApiKeyAuthFilter.class);
        return http.build();
    }

 
}

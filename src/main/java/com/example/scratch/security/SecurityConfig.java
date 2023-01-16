package com.example.scratch.security;


import com.example.scratch.model.Role;
import com.example.scratch.security.jwt.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

  @Value("${auth.internal.api.key}")
  private String internalApiKey;

  @Autowired
  private CustomUserDetailsService userDetailsService;
  @Autowired
  private PasswordEncoder passwordEncoder;



  @Bean
  public DaoAuthenticationProvider mDaoAuthenticationProvider(){
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);

    return authenticationProvider;
  }

@Bean
  public SecurityFilterChain mFilterChain(HttpSecurity http) throws Exception {
    http.cors();
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeHttpRequests()
      .requestMatchers("/api/auth/**").permitAll()
      .requestMatchers(HttpMethod.GET, "api/book").permitAll()
      .requestMatchers("api/book/**").hasRole(Role.ADMIN.name())
      .requestMatchers("/api/internal/**").hasRole(Role.SYSTEM_MANAGER.name())
      .anyRequest().authenticated();
    http.authenticationProvider(mDaoAuthenticationProvider());
    http.addFilterBefore(mJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(mInternalApiAuthenticationFilter(), JwtAuthenticationFilter.class);

    return http.build();
  }


  @Bean
  public AuthenticationManager mAuthenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }




  @Bean
  public JwtAuthenticationFilter mJwtAuthenticationFilter(){
    return new JwtAuthenticationFilter();
  }

  @Bean
  public InternalApiAuthenticationFilter mInternalApiAuthenticationFilter(){
    return new InternalApiAuthenticationFilter(internalApiKey);
  }

  public WebMvcConfigurer corsConfigurer(){
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**/")
          .allowedOrigins("*")
          .allowedMethods("*");
      }
    };
  }
}

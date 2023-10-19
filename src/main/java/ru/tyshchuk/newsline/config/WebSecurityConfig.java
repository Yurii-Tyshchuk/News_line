//package ru.tyshchuk.newsline.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import ru.tyshchuk.newsline.component.JwtTokenFilter;
//import ru.tyshchuk.newsline.services.UserSecurityService;
//
//import javax.servlet.http.HttpServletResponse;
//
//@Configuration
//@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  private final UserSecurityService userSecurityService;
//  private final PasswordEncoder passwordEncoder;
//  private final JwtTokenFilter jwtTokenFilter;
//  public WebSecurityConfig(UserSecurityService userSecurityService, PasswordEncoder passwordEncoder, JwtTokenFilter jwtTokenFilter) {
//    this.userSecurityService = userSecurityService;
//    this.passwordEncoder = passwordEncoder;
//    this.jwtTokenFilter = jwtTokenFilter;
//  }
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userSecurityService);
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    // Enable CORS and disable CSRF
//    http = http.cors().and().csrf().disable();
//
//    // Set session management to stateless
//    http = http
//      .sessionManagement()
//      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//      .and();
//
//    // Set unauthorized requests exception handler
//    http = http
//      .exceptionHandling()
//      .authenticationEntryPoint(
//        (request, response, ex) -> {
//          response.sendError(
//            HttpServletResponse.SC_UNAUTHORIZED,
//            ex.getMessage()
//          );
//        }
//      )
//      .and();
//
//    // Set permissions on endpoints
//    http.authorizeRequests()
//      // Our public endpoints
//      .antMatchers("/api/public/**").permitAll()
//      .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
//      .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
//      .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
//      .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
//      // Our private endpoints
//      .anyRequest().authenticated();
//
//    // Add JWT token filter
//    http.addFilterBefore(
//      jwtTokenFilter,
//      UsernamePasswordAuthenticationFilter.class
//    );
//  }
//
//  // Used by Spring Security if CORS is enabled.
//  @Bean
//  public CorsFilter corsFilter() {
//    UrlBasedCorsConfigurationSource source =
//      new UrlBasedCorsConfigurationSource();
//    CorsConfiguration config = new CorsConfiguration();
//    config.setAllowCredentials(true);
//    config.addAllowedOrigin("*");
//    config.addAllowedHeader("*");
//    config.addAllowedMethod("*");
//    source.registerCorsConfiguration("/**", config);
//    return new CorsFilter(source);
//  }
//}

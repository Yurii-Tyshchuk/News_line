package ru.tyshchuk.newsline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.tyshchuk.newsline.security.jwt.JwtConfigurer;
import ru.tyshchuk.newsline.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers("/api/message/getAll").permitAll()
                .antMatchers("/api/profile/create").permitAll()
                .antMatchers("/api/tag/**").permitAll()
                .antMatchers("/api/tag/create").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/profile/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/like/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/message/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}

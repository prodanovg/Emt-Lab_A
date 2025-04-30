//package mk.ukim.finki.lab1.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//
//@Profile("test")
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class WebSecurityConfig {
//
//    private final PasswordEncoder passwordEncoder;
//    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
//
//
//    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//        corsConfiguration.setAllowedHeaders(List.of("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(AbstractHttpConfigurer::disable).cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
//                        corsConfigurationSource()));
//
////                .headers((headers) -> headers
////                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
////                )
////                .authorizeHttpRequests((requests) -> requests
////                        .requestMatchers("/")
////                        .permitAll()
////                        .anyRequest()
////                        .authenticated()
////                )
////                .formLogin((form) -> form
////                        .permitAll()
////                        .failureUrl("/login?error=BadCredentials")
////                        .defaultSuccessUrl("/swagger-ui/index.html", true)
////                )
////                .logout((logout) -> logout
////                        .logoutUrl("/logout")
////                        .clearAuthentication(true)
////                        .invalidateHttpSession(true)
////                        .deleteCookies("JSESSIONID")
////                        .logoutSuccessUrl("/login")
////                )
////                .exceptionHandling((ex) -> ex
////                        .accessDeniedPage("/access_denied")
////                );
//
//        return http.build();
//    }
//
//    // In Memory Authentication
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password(passwordEncoder.encode("admin"))
////                .roles("ADMIN")
////                .build();
////
////        return new InMemoryUserDetailsManager(admin);
////    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
//                AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
//        return authenticationManagerBuilder.build();
//    }
//
//}
//
//

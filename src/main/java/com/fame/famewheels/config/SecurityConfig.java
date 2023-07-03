package com.fame.famewheels.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	public static final String[] PUBLIC_URLS = {
			"/fame/users/new",
			"/fame/authenticate",
			"/fame/posts/{pageNo}/{pageSize}",
			"/fame/categories/getAll",
			"/fame/posts/{postId}",
			"/fame/posts/image/{postId}",
			"/fame/images/{postId}",
			"/fame/path",
			"/fame/posts/category-posts/{categoryId}",
			 "/fame/posts/condition/{condition}/{pageNo}/{pageSize}",
			 "/fame/posts/filters/{vehicleCondition}/{city}/{make}/{minPrice}/{maxPrice}/{category}/{search}/{pageNo}/{pageSize}",
			 "/fame/cities",
			 "/fame/getMake",
			 "/fame/getModel",
			 "/fame/getByMakeId",
			 "/fame/getModelYear",
			 "/fame/searchpost/{searchValue}",
			 "/fame/posts/type",
			 "/fame/getCurrentPost",
			 "/fame/getAuctionPostById",
			 "/fame/getBranch",
			 "/fame/createAuctionAppointment",
			 "/fame/getUpcomingPost",
			 "/fame/getCurrentPost",
			 "/fame/getBidsByPostId",
			 "/fame/getAuctionPostCount"
	};
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable().
		authorizeHttpRequests()
		.requestMatchers("/error").permitAll()
		.requestMatchers(PUBLIC_URLS).permitAll()
		.requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**", "/webjars/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.cors()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider())
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		DefaultSecurityFilterChain defaultSecurityFilterChain = httpSecurity.build();
		return defaultSecurityFilterChain;
	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://192.168.18.246:3000"));
//		configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://192.168.18.246:3000"); 
        config.addAllowedOrigin("*");// Add the allowed origin(s)
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all HTTP methods
        source.registerCorsConfiguration("/**", config);
        return source;
    }
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean (AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {


        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
	
	
}

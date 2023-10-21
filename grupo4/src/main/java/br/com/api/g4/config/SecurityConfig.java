package br.com.api.g4.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.api.g4.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

    @Autowired
    UsuarioRepository userRepo;

    @Autowired
    JWTFilter filter;

    @Autowired
    UserDetailsServiceImpl uds;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // Metodo encarregado de configurar a seguranca da API
        http
		        .cors()
		        .and()
        		.csrf().disable()
                .httpBasic().disable()
                .authorizeHttpRequests()
                .antMatchers("/categoria/{id}","/categoria/listar","/produto/count","/usuario/salvar","/usuario/login").permitAll()
                .antMatchers("/pedido/salvar","/usuario/{id}","/usuario/listar").hasRole("COMPRADOR")
                .antMatchers("/categoria/count","/categoria/salvar","/categoria/deletarLogico/{id}","/categoria/atualizar/{id}","/pedido/atualizar/{id}","/produto/salvar","/produto/deletar/{id}","/produto/atualizar/{id}","/produto/promocao","/usuario/salvar").hasRole("VENDEDOR")
                .antMatchers("/endereco/count","/endereco/salvar","/endereco/{id}","/endereco/listar","/endereco/deletarLogico/{id}","/endereco/atualizar/{id}","/pedido/count","/pedido/{id}","/pedido/listar","/pedido/deletar/{id}","/produto/{id}","/produto/listar","/usuario/deletarLogico/{id}","/usuario/atualizar/{id}","/usuario/listarEndereco/{id}","/usuario/recuperarSenha/{id}","/usuario/recuperarConta/{id}","/usuario/registro").hasAnyRole("COMPRADOR","VENDEDOR")
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().permitAll().and().csrf(csrf -> csrf.disable());
//		return http.build();
//	}
	
}
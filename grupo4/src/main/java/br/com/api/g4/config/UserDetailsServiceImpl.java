package br.com.api.g4.config;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.api.g4.entities.Usuario;
import br.com.api.g4.repositories.RoleRepository;
import br.com.api.g4.repositories.UserRepository;
import br.com.api.g4.services.UsuarioService;

	@Component
	public class UserDetailsServiceImpl implements UserDetailsService {

	    @Autowired 
	    UserRepository userRepo;

	    @Autowired
	    UsuarioService userService;

	    @Autowired 
	    RoleRepository roleRepo;

		@Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Optional<Usuario> userRes = userRepo.findByEmail(email);
	        if(userRes.isEmpty()) {
	            throw new UsernameNotFoundException("Não foi possível encontrar usuário com o email = " + email);
	        }
		return new org.springframework.security.core.userdetails.User(
	                email,
	                userRes.get().getPassword(),
	                roleRepo.roles(email).stream()
					.map(role -> new SimpleGrantedAuthority(role.getName()
							.name())).collect(Collectors.toList())); // Define, de forma estatica, o perfil do usuario encontrado
	    }
}

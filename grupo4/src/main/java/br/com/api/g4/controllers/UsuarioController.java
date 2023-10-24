package br.com.api.g4.controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.g4.config.JWTUtil;
import br.com.api.g4.dto.LoginDTO;
import br.com.api.g4.dto.MessageResponseDTO;
import br.com.api.g4.dto.UsuarioDTO;
import br.com.api.g4.entities.Endereco;
import br.com.api.g4.entities.Role;
import br.com.api.g4.entities.Usuario;
import br.com.api.g4.enums.TipoRoleEnum;
import br.com.api.g4.repositories.EnderecoRepository;
import br.com.api.g4.repositories.RoleRepository;
import br.com.api.g4.repositories.UsuarioRepository;
import br.com.api.g4.services.EmailService;
import br.com.api.g4.services.EnderecoService;
import br.com.api.g4.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EnderecoService enderecoService;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	EnderecoRepository enderecoRepository;

	private EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@GetMapping("/count")
	public Integer getCount() {

		return usuarioService.getCount();
	}

	@GetMapping("/{id}")
	public Usuario acharId(@PathVariable Integer id) {
		return usuarioService.acharId(id);
	}

	@GetMapping("/listar")
	public List<Usuario> listar() {
		return usuarioService.listar();
	}

	@DeleteMapping("/deletarLogico/{id}")
	public void deletarLogico(@PathVariable Integer id) {
		emailService.envioEmailDelete(acharId(id));
		usuarioService.deletarLogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Usuario atualizar(@PathVariable Integer id, @RequestBody UsuarioDTO objetousuario) {
		return usuarioService.atualizar(id, objetousuario);
	}

	@GetMapping("/listarEndereco/{id}")
	public List<Endereco> getEndereco(@PathVariable Integer id) {
		return usuarioService.listarEndereco();
	}

	// TODO verificar a mensagem de recuperaçao de senha é valida
	@PutMapping("/recuperarSenha/{id}")
	public void recuperarSenha(@PathVariable Integer id, @RequestParam String senha) {
		usuarioService.recuperarSenha(id, senha);
		emailService.envioEmailRecuperacaoSenha(acharId(id));
	}

	@PutMapping("/recuperarConta/{id}")
	public void recuperarConta(@PathVariable Integer id) {
		usuarioService.recuperarConta(id);
		emailService.envioEmailRecuperacaoConta(acharId(id));
	}

	// Registro de usuario
	@PostMapping("/registro")
	public Usuario cadastro(@RequestParam String email, @RequestBody UsuarioDTO usuario) {

		// usuario = usuarioService.save(usuario);

		// Gerando o token JWT a partir do e-mail do Usuario
		// String token = jwtUtil.generateToken(usuario.getEmail());

		Set<String> strRoles = usuario.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			// Se não houver roles especificadas, atribui o papel de USUÁRIO
			Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(usuarioRole);
		} else {
			// Mapeando roles especificadas para objetos Role
			strRoles.forEach(role -> {
				switch (role) {
				case "VENDEDOR":
					Role adminRole = roleRepository.findByName(TipoRoleEnum.ROLE_VENDEDOR)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);
					break;
				case "COMPRADOR":
					Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(usuarioRole);
				}
			});
		}

		Endereco viaCep = enderecoService.pesquisarEndereco(usuario.getCep());
		Endereco endereco = new Endereco();
		endereco.setCep(usuario.getCep());
		endereco.setComplemento(usuario.getComplemento());
		endereco.setNumero(usuario.getNumero());
		endereco.setBairro(viaCep.getBairro());
		endereco.setLocalidade(viaCep.getLocalidade());
		endereco.setLogradouro(viaCep.getLogradouro());
		endereco.setUf(viaCep.getUf());
		endereco.setAtivo(true);
		enderecoRepository.save(endereco);

		Usuario usuarioResumido = new Usuario();
		usuarioResumido.setAtivo(true);
		usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
		usuarioResumido.setEmail(usuario.getEmail());
		usuarioResumido.setRoles(roles);
		usuarioResumido.setCpf(usuario.getCpf());
		usuarioResumido.setDataNascimento(usuario.getDataNascimento());
		usuarioResumido.setNome(usuario.getNome());
		// Encriptando a senha usando o Bcrypt
		String encodedPass = passwordEncoder.encode(usuario.getPassword());
		usuarioResumido.setPassword(encodedPass);
//        String token = jwtUtil.generateTokenWithUsuarioData(usuarioResumido);
//        Collections.singletonMap("jwt-token", token);

		emailService.envioEmailCadastro(usuario);
		return usuarioRepository.save(usuarioResumido);
	}

	// Login de usuario
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginDTO body) {
		try {
			// Criando o token que sera usado no processo de autenticacao
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());

			// Autenticando as credenciais de login
			authManager.authenticate(authInputToken);

			// Se o processo de autenticacao foi concluido com sucesso - etapa anterior,
			// eh gerado o JWT
//            String token = jwtUtil.generateToken(body.getEmail());

			Usuario usuario = usuarioService.findByEmail(body.getEmail());
			Usuario usuarioResumido = new Usuario();
			usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
			usuarioResumido.setEmail(usuario.getEmail());
			usuarioResumido.setId(usuario.getId());
			usuarioResumido.setRoles(usuario.getRoles());
			// Gerando o token JWT a partir dos dados do Usuario
			String token = jwtUtil.generateTokenWithUsuarioData(usuarioResumido);

			// Responde com o JWT
			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}

}
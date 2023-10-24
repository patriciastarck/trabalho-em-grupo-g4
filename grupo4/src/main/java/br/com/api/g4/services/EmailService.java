package br.com.api.g4.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.api.g4.dto.PedidoDeProdutoDTO;
import br.com.api.g4.dto.PromocaoDTO;
import br.com.api.g4.dto.UsuarioDTO;
import br.com.api.g4.entities.Usuario;

@Configuration
@Service
public class EmailService {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	PedidoService pedidoService;

	private JavaMailSender emailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

//	@Autowired
//	Pedido pedido;

	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private Integer port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();
		emailSender.setHost(host);
		emailSender.setPort(port);
		emailSender.setUsername(username);
		emailSender.setPassword(password);
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		emailSender.setJavaMailProperties(prop);
		return emailSender;
	}

//	 TODO terminar metodo e botar no pedido
	public void envioEmailPedido(PedidoDeProdutoDTO pedidon) {
//		Pedido pedido = pedidoService.parsePedidoDeProduto(pedidon);
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo("oliveiraagall@gmail.com");
			helper.setSubject("seu pedido esta sendo enviado");

			LocalDate localDate = LocalDate.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataEntrega = localDate.plusDays(7).format(format);
			double valorTotal = 0;
			List<String> nomes = new ArrayList<>();
			List<Double> valores = new ArrayList<>();

//			for (int i = 0; i < pedido.getProdutos().size(); i++) {
//				
//				valores.add(pedido.getProdutos().get(i).getValorUnitario());
//				nomes.add(pedido.getProdutos().get(i).getNome());
//				valorTotal += pedido.getProdutos().get(i).getValorUnitario();
//			}

			DecimalFormat df = new DecimalFormat("R$ ,##0.00");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("<body>\r\n");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<h1>Seu pedido foi finalizado com sucesso</h1>\r\n");
			builder.append("</div>\r\n");
			builder.append("<br> \r\n");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<img src=\"cid:logo\">");
			builder.append("</div>\r\n");
			builder.append("");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<p>Parabéns! Seu pedido foi realizado com sucesso</p>\r\n");
			builder.append("<p></p>");
			builder.append(
					"<a href=http:\"//localhost:8080/api/swagger-ui/index.html#/\"\"\">Clique aqui para voltar ao site </a>\r\n");
			builder.append("<p>Atenciosamente Grupo 4.\r\n</p>");
			builder.append("");
			builder.append("</div>\r\n");

//			builder.append("<center>");
//			builder.append("<table border='2' cellpadding='4'> \r\n");
//			builder.append("<tr> <th> Nome </th> <\r\n");
//			builder.append("<tr> <th> Nome</th> <th> Email</th> <th>Perfis</th><th> Data de entrega</th> </tr>\r\n");
//
//			for (int i = 0; i< nomes.size();i++) {
//				builder.append(dataEntrega);
//				builder.append(" <tr>\r\n");
//				builder.append(" <td>\r\n");
//				builder.append(nomes.get(i));
//				builder.append(" </td>\r\n");
//				builder.append(" <td>\r\n");
//				builder.append(df.format(valores.get(i)));
//				builder.append(" </td>\r\n");
//				builder.append(" <td>\r\n");
//				builder.append(" </td>\r\n");
//				builder.append(" <td>\r\n");
//				builder.append(" </td>\r\n");
//			}
//			
//			builder.append(" </table>\r\n");
//			builder.append(" </center>\r\n");
//			builder.append(" <table border='1' cellpadding='1' >\r\n");
//			builder.append("<tr><th>Valor Total</th></tr>\r\n");
//			builder.append(" <td>\r\n");
//			builder.append(df.format(valorTotal));
//			builder.append(" </td>\r\n");
//			builder.append(" </table>\r\n");
			builder.append(" </body>\r\n");
			builder.append("</html>");

			helper.setText(builder.toString(), true);
			ClassPathResource imageResource = new ClassPathResource("img/logo.png");
			helper.addInline("logo", imageResource);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailCadastro(UsuarioDTO objetousuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(objetousuario.getEmail());
			String nome = objetousuario.getNome();
			helper.setSubject("Olá " + nome + " sua conta foi criada com sucesso.");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("<body>\r\n");
			builder.append("");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<h1>Conta criada com sucesso</h1>\r\n");
			builder.append("</div>\r\n");
			builder.append("");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<img src=\"cid:logo\">");
			builder.append("</div>\r\n");
			builder.append("");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<p>Parabéns " + nome + " por agora fazer parte do melhor marketplace do mundo!!!</p>");
			builder.append("<p>Esperamos que tenha uma boa experiência conosco.</p>");
			builder.append(
					"<a href=http:\"//localhost:8080/api/swagger-ui/index.html#/\"\"\">Clique aqui para voltar ao site </a>\r\n");
			builder.append("<p>Atenciosamente Grupo 4.\r\n</p>");
			builder.append("");
			builder.append("</div>\r\n");
			builder.append("</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/logo.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailDelete(Usuario usuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(usuario.getEmail());
			String nome = usuario.getNome();
			helper.setSubject("Olá " + nome + " sua conta foi apagada com sucesso.");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + "<body>\r\n" + "" + "<div align=\"center\">\r\n"
					+ "<h1>Conta apagada com sucesso</h1>\r\n" + "</div>\r\n" + "<br/>\r\n" + ""
					+ "<div align=\"center\">\r\n" + "<img src=\"cid:logo\">" + "</div>\r\n" + ""
					+ "<div align=\"center\">\r\n" + ""
					+ "<p>Agradecemos por utilizar nossos serviços, sua conta foi finalizada.</p>"
					+ "<p>Esperamos que você tenha tido uma boa experiência conosco! Até a próxima ;)</p>"
					+ "<p>Atenciosamente Grupo 4.</p>" + "</div>" + "</body>\r\n" + "</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/logo.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailPromo() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo("oliveiraagall@gmail.com");
			helper.setSubject("Olá, veja os produtos em promoção essa semana.");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + "<body>\r\n" + "" + "<div align=\"center\">\r\n"
					+ "<h1>Os 5 produtos da semana são</h1>\r\n" + "</div>\r\n" + "<br/>\r\n" + ""
					+ "<div align=\"center\">\r\n" + "<img src=\"cid:logo\">" + "</div>\r\n"
					+ "<div align=\"center\">\r\n");
			List<PromocaoDTO> promo = produtoService.promocao();
			for (int i = 0; i < promo.size(); i++) {
				builder.append("<p> produto:" + promo.get(i).getNome() + " - \r\n");
				builder.append("valor: R$" + promo.get(i).getValorUnitario() + "</p> \r\n");
			}

			builder.append("</div>" + "<div align=\"center\">\r\n" + "<p>Não perca essas ofertas que estão no site.</p>"
					+ "<p>é por tempo limitado! Não vai perder essa chance, ein?!</p>"
					+ "<a href=\"http://localhost:8080/api/swagger-ui/index.html#/\">Clique aqui para ver essas ofertas </a>"
					+ "<p>Atenciosamente Grupo 4.</p>" + "</div>" + "</body>\r\n" + "</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/logo.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailRecuperacaoSenha(Usuario usuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(usuario.getEmail());
			String nome = usuario.getNome();
			helper.setSubject("Olá " + nome + " vamos recuperar a sua senha");
			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + "<body>\r\n" + "" + "<div align=\"center\">\r\n"
					+ "<h1>Recuperação de senha</h1>\r\n" + "</div>\r\n" + "<br/>\r\n" + ""
					+ "<div align=\"center\">\r\n" + "<img src=\"cid:logo\">" + "</div>\r\n" + ""
					+ "<div align=\"center\">\r\n"
					+ "<p>Se vocé pediu a redefinição de senha<a href=\"http://localhost:8080/api/swagger-ui/index.html#/\"> clique aqui</a>.</p>"
					+ "<p>Se vocé não reconhece essa requisição ignore esse email.</p>"
					+ "<p>Atenciosamente Grupo 4.</p>" + "</div>" + "</body>\r\n" + "</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/logo.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void envioEmailRecuperacaoConta(Usuario usuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo(usuario.getEmail());
			String nome = usuario.getNome();
			helper.setSubject("Olá " + nome + " vamos recuperar o sua conta");
			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n" + "<body>\r\n" + "" + "<div align=\"center\">\r\n"
					+ "<h1>Recuperação de conta</h1>\r\n" + "</div>\r\n" + "<br/>\r\n" + ""
					+ "<div align=\"center\">\r\n" + "<img src=\"cid:logo\">" + "</div>\r\n" + ""
					+ "<div align=\"center\">\r\n"
					+ "<p>Se vocé estáá tentando recuperar sua conta, <a href=\"http://localhost:8080/api/swagger-ui/index.html#/\">clique aqui</a> para ver o e-mail cadastrado.</p>"
					+ "<p>Se vocé não reconhece essa requisição ignore esse email.</p>"
					+ "<p>Atenciosamente Grupo 4.</p>" + "</div>" + "</body>\r\n" + "</html>\r\n");

			helper.setText(builder.toString(), true);

			ClassPathResource imageResource = new ClassPathResource("img/logo.png");
			helper.addInline("logo", imageResource);

			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
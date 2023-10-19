package br.com.api.g4.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.api.g4.entities.Usuario;

@Configuration
@Service
public class EmailService {
	
	@Autowired 
	UsuarioService usuarioService;

	private JavaMailSender emailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
	
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
	
	public void envioEmailCadastro(Usuario usuario) {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo("oliveiraagall@gmail.com");
			String nome = usuario.getNome();
			helper.setSubject("Olá "+nome+" sua conta foi criada com sucesso.");

			StringBuilder builder = new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("	<body>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<h1>Conta criada com sucesso</h1>\r\n");
			builder.append("		</div>\r\n");
			builder.append("		<br/>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("<img src=\"https://lh3.googleusercontent.com/pw/ADCreHeK7nopTeqtz5XA2c5d_fgKIYWNCZDyndFxE3txdvhun_26n1Afq5kRq8FvKbOG9PHC-bTqFr3nagNjDmm0SlMfAQRm7zAGmrtLXsn3HGMum3FwNsL1tPi4gg_fhL4SJ1tmq9juKg0TFjyn_AIUwou7ECOyx5ezNIHyoDA_WUTPDiRuXFxssPLUE-EXgtnmR_dj4wIAPDid6dq6Cfz6JLGIHvYsCr5pM5aYdsQR1TKUJXAVw4aX66ADSIObZueHFuKyqwLmeGlI4BybmBnTZWp4EZcYzN83NDjQTDYGKcS5JBuZDSQIZiMElA0DMGIvxQ6RznVFuyhBDAx3oIZWuTjUwpmfR1Twf50j0Ot7qIHjoLqkbineL6waT-HzodA3c5mMkRMibGfWxJ2QBd2ZKp_FdV8wMdN1-nnx-60t1qPhq-bwQONT-Iq0RlIiaIKiNIlwNGazM2w3DuiGH-NvzImS-w0YkqKtD6rA_nBtLwa5-AmnvU0_A0Xg6Yn7vfFAN3Q-B98Hdu60cwpA8ANEsOyg1UnlhVkWdM6VZ4Z02kDHM_6NiwQFyHNEIE3YmG4Cx7BIr_xUWZmeyrh58nyIpyr0tf_LKvC06kkZjtr-kEX0gZYfm9S-K8BRNNfkjiexs7b_7nHFVxi-_20zEiavDt1zEgWlSWvgt_B9y0lToF257x424KPEic01wUdQTEhUZET73W-h0-EWPp1wHHoplZRMmiXXvvHKhseStDgR4JrhmZg_MwnfTI_HVj5hcHEefl1zX3zcdePhOjfVpYgoNiwAnTQVMGxayTTyHRn7_2Vz5vibv1ayVSuzW1zm50PDWfRufwccZkVznu-lmMrmcED75MUkywjzid_dOl0FXyKYUyIks4D3i9SgEeIqHDRrR13RoHwEzZ4oLpI03jXWMQ=w362-h303-no?authuser=0\">");
			builder.append("		</div>\r\n");
			builder.append("		<div align=\"center\">\r\n");
			builder.append("			<p>Parabéns "+nome+" por agora fazer parte do melhor marketplace do mundo!!!</p>");
			builder.append("<p>Esperamos que tenha uma boa experiência conosco.</p>");
			builder.append("<a href=http:\"//localhost:8080/api/swagger-ui/index.html#/\"\"\">Clique aqui para voltar ao site </a>\r\n");
			builder.append("<p>Atenciosamente Grupo 4.\r\n</p>");
			builder.append("		</div>\r\n");
			builder.append("	</body>\r\n");
			builder.append("</html>\r\n");

			helper.setText(builder.toString(), true);
			emailSender.send(mensagemCadastro);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	public void envioEmailTeste() {
		MimeMessage mensagemCadastro = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mensagemCadastro, true);
			helper.setFrom("gp4api.serratec@gmail.com");
			helper.setTo("patriciastarck@gmail.com");
			helper.setSubject("Me chama que eu vou!!");	
			
			LocalDate localDate=LocalDate.now();
			DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy");			
			String dataEntrega=localDate.plusDays(7).format(format);
			
			Double valor=15.9;
			DecimalFormat df=new DecimalFormat("R$ ,##0.00");
			
			StringBuilder builder=new StringBuilder();
			builder.append("<html>\r\n");
			builder.append("<body>\r\n");
			builder.append("<div align=\"center\">\r\n");
			builder.append("<h1>Convite</h1>\r\n");
			builder.append("</div>\r\n");
			builder.append("<br/> \r\n");
			
			builder.append("<center>");
			builder.append("<table border='2' cellpadding='4'> \r\n");
			builder.append("<tr> <th> Nome </th> <\r\n");
			builder.append("<tr> <th> Nome</th> <th> Email</th> <th>Perfis</th><th> Data de entrega</th> </tr>\r\n");
			
			List<Usuario>listaUsuarios=usuarioService.listar();
			for(Usuario usuario : listaUsuarios) {
			builder.append(" <tr>\r\n"); 
			builder.append(" <td>\r\n"); 
			builder.append(usuario.getNomeUsuario()); 
			builder.append(" </td>\r\n"); 
			builder.append(" <td>\r\n"); 
			builder.append(usuario.getEmail()); 
			builder.append(" </td>\r\n"); 
			builder.append(" <td>\r\n"); 
//			builder.append(usuario.getRoles()); 
			builder.append(" </td>\r\n"); 
			builder.append(" <td>\r\n"); 
			builder.append(dataEntrega); 
			builder.append(" </td>\r\n");
		
		}
			builder.append(" </table>\r\n"); 
			builder.append(" </center>\r\n"); 
			builder.append(" <table border='1' cellpadding='1' >\r\n"); 
			builder.append("<tr><th>Valor Total</th></tr>\r\n"); 
			builder.append(" <td>\r\n"); 
			builder.append(df.format(valor)); 
			builder.append(" </td>\r\n"); 
			builder.append(" </table>\r\n"); 
			builder.append(" </body>\r\n"); 
			builder.append("</html>");

			helper.setText(builder.toString(), true); 
			emailSender.send(mensagemCadastro);						

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	
	}
}
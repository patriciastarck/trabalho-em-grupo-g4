package br.com.api.g4.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration 
public class SwaggerConfig {
	
	/*Esta constante define o cabeçalho de autorização que será usado para autenticar usuários 
	 * na documentação gerada pelo Swagger.*/
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	/*Este método define uma chave de API que será usada para proteger a documentação do Swagger.*/
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
	
	/* Este método define um contexto de segurança que será usado para exigir autenticação para 
	 * acessar a documentação do Swagger.*/
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}
	
	/*Este método define uma lista de referências de segurança que serão usadas para autenticar 
	 * usuários para acessar a documentação do Swagger.*/
	private List<SecurityReference> defaultAuth() {
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add(new SecurityReference("JWT", new AuthorizationScope[0]));
		return securityReferences;
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()) 
				.securityContexts(Collections.singletonList(securityContext())).securitySchemes(Arrays.asList(apiKey()))

				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.api.g4.controllers")) 
				.paths(PathSelectors.any()) 
				.build();
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("Documentação da api de teste")
				.description("Descrição projeto de aula").license("Apache license version 2.0").version("14.22.1")
				.build();
		return apiInfo;
	}
}

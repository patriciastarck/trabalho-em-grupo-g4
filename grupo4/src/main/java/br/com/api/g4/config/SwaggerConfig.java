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
	public static final String AUTHORIZATION_HEADER = "Authorization";//Cria uma constante contendo a chamada de
	//uma classe. Essa classe é responsável por enviar credenciais autentificação em uma solicitação http.

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");//cria um metodo que retorna um autentificação 
		//por chave no swagger
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

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
				.description("Descrição projeto de aula").license("Apache license version 2.0").version("14.4.0")
				.build();
		return apiInfo;
	}
}

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

@Configuration // anotacao para poder startar primeiro, antes que os outros
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add(new SecurityReference("JWT", new AuthorizationScope[0]));
		return securityReferences;
	}

	@Bean // vai ser chamado pelo spring
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()) // 1- metodo de config do docket, o 2 é o
				.securityContexts(Collections.singletonList(securityContext())).securitySchemes(Arrays.asList(apiKey()))

				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.api.g4.controllers")) 
				.paths(PathSelectors.any()) // seleciona todas as urls
				.build();
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("Documentação da api de teste")
				.description("Descrição projeto de aula").license("Apache license version 2.0").version("13.0.0")
				.build();
		return apiInfo;
	}
}

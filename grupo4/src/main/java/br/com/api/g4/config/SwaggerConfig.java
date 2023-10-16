package br.com.api.g4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration // anotacao para poder startar primeiro, antes que os outros
public class SwaggerConfig {

	@Bean // vai ser chamado pelo spring
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()) // 1- metodo de config do docket, o 2 é o
																			// metodo q criaremos agr, q sao infos
																			// especificas
				.select().apis(RequestHandlerSelectors.any()) // seleciona todas as apis
				.paths(PathSelectors.any()) // seleciona todas as urls
				.build();
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("Documentação da api de teste")
				.description("Descrição projeto de aula").license("Apache license version 2.0").version("12.0.0")
				.build();
		return apiInfo;
	}
}

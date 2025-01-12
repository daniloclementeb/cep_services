package com.desafio.apilocalizacao.ApiLocalizacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class ApiLocalizacaoApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ApiLocalizacaoApplication.class, args);
	}

}

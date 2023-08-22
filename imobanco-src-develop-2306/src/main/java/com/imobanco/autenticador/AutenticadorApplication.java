package com.imobanco.autenticador;

import com.imobanco.autenticador.infrastructure.CustomJpaRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
@EnableFeignClients
public class AutenticadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticadorApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
	   ModelMapper modelMapper = new ModelMapper();
	   return modelMapper;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

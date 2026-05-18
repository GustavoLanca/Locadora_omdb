package com.example.locadora.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
import com.example.locadora.annotations.ApiKeyRequired;
import com.example.locadora.interceptors.ApiKeyInterceptor;
 
@Configuration
public class WebConfig implements WebMvcConfigurer {
	//registrando o interceptador
    @Autowired
    private ApiKeyInterceptor apiKeyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyInterceptor)
                .addPathPatterns("/filmes") // Aplica a todas as rotas /aluno/** e /batimentos/**
                .excludePathPatterns("/filmes/public/**"); // Exclui rotas públicas
    }

}
 
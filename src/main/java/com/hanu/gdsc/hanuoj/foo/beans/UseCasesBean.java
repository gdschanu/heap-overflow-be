package com.hanu.gdsc.hanuoj.foo.beans;

import com.hanu.gdsc.hanuoj.foo.repositories.FooCrudRepository;
import com.hanu.gdsc.hanuoj.foo.usecases.FooCrudUseCases;
import com.hanu.gdsc.hanuoj.foo.usecases.FooCrudUseCasesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesBean {
    @Autowired
    private FooCrudRepository fooCrudRepository;

    @Bean
    public FooCrudUseCases fooCrudUseCases() {
        return new FooCrudUseCasesImpl(fooCrudRepository);
    }
}

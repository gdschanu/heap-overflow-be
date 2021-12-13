package com.hanu.gdsc.hanuoj.foo.beans;

import com.hanu.gdsc.hanuoj.foo.repositories.FooCrudRepository;
import com.hanu.gdsc.hanuoj.foo.repositories.FooCrudRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryBeans {
    @Bean
    public FooCrudRepository fooCrudRepository() {
        return new FooCrudRepositoryImpl();
    }
}

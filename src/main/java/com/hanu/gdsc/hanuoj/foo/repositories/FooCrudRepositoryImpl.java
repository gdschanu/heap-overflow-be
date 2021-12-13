package com.hanu.gdsc.hanuoj.foo.repositories;

import com.hanu.gdsc.hanuoj.foo.domains.Foo;
import com.hanu.gdsc.hanuoj.foo.usecases.FooCrudUseCases;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public class FooCrudRepositoryImpl implements FooCrudRepository {
    @Override
    public Foo createFoo(Foo foo) {
        return foo;
    }

    @Override
    public FooCrudUseCases.ListFooOutput listFoo(ListFooInput input) {
        return FooCrudUseCases.ListFooOutput.builder()
                .total(0)
                .data(new ArrayList<>())
                .build();
    }
}

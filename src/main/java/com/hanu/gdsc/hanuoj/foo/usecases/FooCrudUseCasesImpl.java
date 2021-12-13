package com.hanu.gdsc.hanuoj.foo.usecases;

import com.hanu.gdsc.hanuoj.foo.domains.Foo;
import com.hanu.gdsc.hanuoj.foo.repositories.FooCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FooCrudUseCasesImpl implements FooCrudUseCases {

    private final FooCrudRepository fooCrudRepository;

    public FooCrudUseCasesImpl(FooCrudRepository fooCrudRepository) {
        this.fooCrudRepository = fooCrudRepository;
    }

    @Override
    public Foo createFoo(Foo foo) {
        return fooCrudRepository.createFoo(foo);
    }

    @Override
    public ListFooOutput listFoo(ListFooInput input) {
        return fooCrudRepository.listFoo(
                FooCrudRepository.ListFooInput.builder()
                        .limit(input.perPage)
                        .skip(input.perPage * (input.page - 1))
                        .build()
        );
    }
}

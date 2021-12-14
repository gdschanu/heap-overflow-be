package com.hanu.gdsc.hanuoj.foo.usecases;

import com.hanu.gdsc.hanuoj.foo.domains.Foo;
import com.hanu.gdsc.hanuoj.foo.repositories.FooCrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Foo> foos = fooCrudRepository.listFoo(FooCrudRepository.ListFooInput.builder()
                .skip(input.perPage * (input.page - 1))
                .limit(input.perPage)
                .build());
        int total = fooCrudRepository.countTotalFoo();
        return ListFooOutput.builder()
                .data(foos)
                .total(total)
                .build();
    }
}

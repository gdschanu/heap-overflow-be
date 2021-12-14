package com.hanu.gdsc.hanuoj.foo.repositories;

import com.hanu.gdsc.hanuoj.foo.domains.Foo;
import com.hanu.gdsc.hanuoj.foo.usecases.FooCrudUseCases;
import lombok.Builder;

import java.util.List;

public interface FooCrudRepository {
    public Foo createFoo(Foo foo);

    @Builder
    public static class ListFooInput {
        public int limit;
        public int skip;
        public int sort;
    }

    public List<Foo> listFoo(ListFooInput input);

    public int countTotalFoo();
}

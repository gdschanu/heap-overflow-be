package com.hanu.gdsc.hanuoj.foo.usecases;

import com.hanu.gdsc.hanuoj.foo.domains.Foo;
import lombok.Builder;

import java.util.List;

public interface FooCrudUseCases {
    public Foo createFoo(Foo foo);

    @Builder
    public static class ListFooInput {
        public int perPage;
        public int page;
        public int sort;
    }

    @Builder
    public static class ListFooOutput {
        public List<Foo> data;
        public int total;
    }

    public ListFooOutput listFoo(ListFooInput input);
}

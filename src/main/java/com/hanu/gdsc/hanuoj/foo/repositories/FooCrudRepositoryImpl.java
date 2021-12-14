package com.hanu.gdsc.hanuoj.foo.repositories;

import com.hanu.gdsc.hanuoj.foo.domains.Foo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class FooCrudRepositoryImpl implements FooCrudRepository {
    @Override
    public Foo createFoo(Foo foo) {
        return foo;
    }

    @Override
    public List<Foo> listFoo(ListFooInput input) {
        return new ArrayList<>();
    }

    @Override
    public int countTotalFoo() {
        return 0;
    }
}

package com.hanu.gdsc.hanuoj.foo.controllers;

import com.hanu.gdsc.hanuoj.foo.domains.Foo;
import com.hanu.gdsc.hanuoj.foo.usecases.FooCrudUseCases;
import org.springframework.web.bind.annotation.*;

@RestController
public class FooCrudController {
    private final FooCrudUseCases fooCrudUseCases;

    public FooCrudController(FooCrudUseCases fooCrudUseCases) {
        this.fooCrudUseCases = fooCrudUseCases;
    }

    @PostMapping("/foo")
    public Foo createFoo(@RequestBody Foo foo) {
        return fooCrudUseCases.createFoo(foo);
    }

    @GetMapping("/foo")
    public FooCrudUseCases.ListFooOutput listFoo(@RequestParam int page, @RequestParam int perPage) {
        return fooCrudUseCases.listFoo(FooCrudUseCases.ListFooInput.builder()
                .page(page)
                .perPage(perPage)
                .build());
    }
}

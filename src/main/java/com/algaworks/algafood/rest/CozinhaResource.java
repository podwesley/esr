package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.CozinhaService;
import com.algaworks.algafood.entity.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cozinhas")
public class CozinhaResource {

    @Autowired
    private CozinhaService service;

    @GetMapping
    public List<Cozinha> listar() {
        return service.todas();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("{id}")
    public Cozinha buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Cozinha salvar(@RequestBody Cozinha cozinha){
        return service.salvar(cozinha);
    }

}
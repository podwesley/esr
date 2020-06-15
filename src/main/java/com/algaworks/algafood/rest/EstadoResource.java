package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.EstadoService;
import com.algaworks.algafood.entity.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @GetMapping
    public List<Estado> listar() {
        return service.todos();
    }

    @GetMapping("{id}")
    public Estado buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }
}

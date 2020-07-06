package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.CozinhaService;
import com.algaworks.algafood.entity.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("{id}")
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {

        Cozinha cozinha = service.buscarPorId(id);

        if (cozinha == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha cozinha){
        return service.salvar(cozinha);
    }

}
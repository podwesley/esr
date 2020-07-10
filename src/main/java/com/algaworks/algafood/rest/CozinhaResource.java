package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.CozinhaService;
import com.algaworks.algafood.entity.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return service.salvar(cozinha);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cozinha> alterar(@RequestBody Cozinha cozinha, @PathVariable Long id) {

        Cozinha findCozinha = service.buscarPorId(id);

        if (findCozinha != null) {
            findCozinha.setNome(cozinha.getNome());
            service.alterar(findCozinha);

            return ResponseEntity.ok(findCozinha);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Cozinha> delete(@PathVariable Long id) {

        try {
            Cozinha findCozinha = service.buscarPorId(id);

            if (findCozinha != null) {
                service.apagar(id); //Caso ao deletar uma entidade tenha erro de constraint.
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();

        } catch (DataIntegrityViolationException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
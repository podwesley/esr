package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.EstadoService;
import com.algaworks.algafood.Service.EstadoService;
import com.algaworks.algafood.entity.Estado;
import com.algaworks.algafood.entity.Estado;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estados")
public class EstadoResource {


    @Autowired
    private EstadoService service;

    @GetMapping
    public List<Estado> listar() {
        return service.todas();
    }

    @GetMapping("{id}")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {

        Estado estado = service.buscarPorId(id);

        if (estado == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Estado estado) {

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(estado));

        } catch (AlgaFoodResultadoVazioException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterar(@RequestBody Estado estado, @PathVariable Long id) {

        try {

            Estado findEstado = service.buscarPorId(id);

            findEstado.setNome(estado.getNome());
            service.alterar(findEstado);

            return ResponseEntity.ok(findEstado);

        } catch (AlgaFoodResultadoVazioException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Estado> delete(@PathVariable Long id) {

        try {
            service.apagar(id); //Caso ao deletar uma entidade tenha erro de constraint
            return ResponseEntity.noContent().build();
        } catch (AlgaFoodRestricaoException | AlgaFoodResultadoVazioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

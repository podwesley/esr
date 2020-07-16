package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.RestauranteService;
import com.algaworks.algafood.entity.Restaurante;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurantes")
public class RestauranteResource {


    @Autowired
    private RestauranteService service;

    @GetMapping
    public List<Restaurante> listar() {
        return service.todas();
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {

        Restaurante restaurante = service.buscarPorId(id);

        if (restaurante == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(restaurante));

        } catch (AlgaFoodResultadoVazioException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterar(@RequestBody Restaurante restaurante, @PathVariable Long id) {

        try {

            Restaurante findRestaurante = service.buscarPorId(id);

            findRestaurante.setNome(restaurante.getNome());
            findRestaurante.setTaxaFrete(restaurante.getTaxaFrete());
            findRestaurante.setCozinha(restaurante.getCozinha());
            service.alterar(findRestaurante);

            return ResponseEntity.ok(findRestaurante);

        } catch (AlgaFoodResultadoVazioException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Restaurante> delete(@PathVariable Long id) {

        try {
            service.apagar(id); //Caso ao deletar uma entidade tenha erro de constraint
            return ResponseEntity.noContent().build();
        } catch (AlgaFoodRestricaoException | AlgaFoodResultadoVazioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}

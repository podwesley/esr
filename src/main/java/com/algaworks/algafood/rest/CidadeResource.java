package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.CidadeService;
import com.algaworks.algafood.entity.Cidade;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cidades")
public class CidadeResource {

    @Autowired
    private CidadeService service;

    @GetMapping
    public List<Cidade> listar() {
        return service.todas();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id) {

        Cidade cidade = service.buscarPorId(id);

        if (cidade == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(cidade));

        } catch (AlgaFoodResultadoVazioException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> alterar(@RequestBody Cidade cidade, @PathVariable Long id) {

        try {

            Cidade findCidade = service.buscarPorId(id);

            findCidade.setNome(cidade.getNome());
            findCidade.setEstado(cidade.getEstado());
            service.alterar(findCidade);

            return ResponseEntity.ok(findCidade);

        } catch (AlgaFoodResultadoVazioException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cidade> delete(@PathVariable Long id) {

        try {
            service.apagar(id); //Caso ao deletar uma entidade tenha erro de constraint
            return ResponseEntity.noContent().build();
        } catch (AlgaFoodRestricaoException | AlgaFoodResultadoVazioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

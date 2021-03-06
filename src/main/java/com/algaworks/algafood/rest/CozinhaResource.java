package com.algaworks.algafood.rest;

import com.algaworks.algafood.Service.CozinhaService;
import com.algaworks.algafood.entity.Cozinha;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("consultar-nome")
    public List<Cozinha> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("consultar-parte-nome")
    public List<Cozinha> buscarPorParteNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
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
            service.apagar(id); //Caso ao deletar uma entidade tenha erro de constraint
            return ResponseEntity.noContent().build();
        } catch (AlgaFoodRestricaoException | AlgaFoodResultadoVazioException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("verificar")
    public boolean existehhhhh(String cozinhaNome) {
        return service.verificarSeCozinhaExiste(cozinhaNome);
    }

    @GetMapping("buscarPrimeiro")
    public Optional<Cozinha> buscarPrimeiro() {
        return service.buscarPrimeiro();
    }

}

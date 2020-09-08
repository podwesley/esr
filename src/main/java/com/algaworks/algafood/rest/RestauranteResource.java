package com.algaworks.algafood.rest;

import com.algaworks.algafood.Repository.RestauranteRepository;
import com.algaworks.algafood.Service.RestauranteService;
import com.algaworks.algafood.entity.Restaurante;
import com.algaworks.algafood.exception.AlgaFoodRestricaoException;
import com.algaworks.algafood.exception.AlgaFoodResultadoVazioException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.algaworks.algafood.Repository.specification.RestauranteSpecification.comFreteGratis;
import static com.algaworks.algafood.Repository.specification.RestauranteSpecification.comNomeSemelhante;

@RestController
@RequestMapping("restaurantes")
public class RestauranteResource {

    @Autowired
    private RestauranteService service;

    @Autowired
    private RestauranteRepository repository;

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

    /**
     * Atualiza de forma parcial os recursos.
     * @param campos
     * @param id
     * @return
     */
    @PatchMapping("{id}")
    public ResponseEntity<?> atualizarParcial(@RequestBody Map<String, Object> campos, @PathVariable Long id) {

        Restaurante findRestaurante = service.buscarPorId(id);

        if (findRestaurante == null)
            return ResponseEntity.notFound().build();

        //***********************************

        ObjectMapper objectMapper = new ObjectMapper();

        //Converte todos os valores dos campos para o seu devido tipo.
        Restaurante novo = objectMapper.convertValue(campos, Restaurante.class);

        //Usaremos a API de reflection para atualizar os rescursos com o patch.
        campos.forEach((key, value) -> {

            //Pega um campo dentro de restaurante e pega o valor que foi passando na request.
            Field campo = ReflectionUtils.findField(Restaurante.class, key);

            //Acessar uma propriedade privada da classe lah. gato da poah.
            campo.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(campo, novo);

            //Seta o valor exatamente no campo do restaurante que foi buscado na base.
            ReflectionUtils.setField(campo, findRestaurante, novoValor);

        });

        return alterar(findRestaurante, id);
    }

    @GetMapping("/consultas")
    public List<Restaurante> buscarPorNome(@RequestParam("nome") String nome) {
        return service.buscarPorNome(nome);
    }

    @GetMapping("/parteString")
    public List<Restaurante> buscarPorParteDoNome(@RequestParam("nome") String nome) {
        return service.buscarPorParteDoNome(nome);
    }

    @GetMapping("/restaurantes-por-taxa-frete")
    public List<Restaurante> buscarRestaurantesPorIntervaloDeTaxaFrete(@RequestParam("txInicial") BigDecimal txInicial,
                                                                       @RequestParam("txFinal") BigDecimal txFinal) {
        return service.buscarRestaurantesPorIntervaloDeTaxaFrete(txInicial, txFinal);
    }

    @GetMapping("/nome-id")
    public List<Restaurante> buscarPorParteDoNome(String nome, Long cozinhaId) {
        return service.buscarPorNomeRestauranteIdCozinhaa(nome, cozinhaId);
    }

    @GetMapping("/buscar-top")
    public Optional<Restaurante> buscarPrimeiroRestaurante(String nome) {
        return service.buscarPrimeiroRestaurantePesquisa(nome);
    }

    @GetMapping("/buscar-top2")
    public List<Restaurante> buscarPrimeiros2Restaurantes(String nome) {
        return service.buscarPrimeiros2RestaurantesQueContem(nome);
    }

    @GetMapping("quantidade-restaurantes-cozinha-id")
    public int restaurantesPorCozinhaId(Long cozinhaId) {
        return service.restaurantesPorCozinhaId(cozinhaId);
    }

    @GetMapping("quantidade-restaurantes-cozinha-id-query")
    public List<Restaurante> restaurantesPorCozinhaId(String nome, Long cozinhaId) {
        return service.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("quantidade-restaurantes-cozinha-id-query2")
    public List<Restaurante> restaurantesPorCozinhaId2(String nome, Long cozinhaId) {
        return service.consultarPorNome2(nome, cozinhaId);
    }

    @GetMapping("quantidade-restaurantes-cozinha-id-query3")
    public List<Restaurante> buscar(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return service.buscar(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("quantidade-restaurantes-cozinha-id-query4")
    public List<Restaurante> buscarDinamico(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return service.buscarDinamico(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome) {
        return service.buscarFreteGratis(comFreteGratis(), (comNomeSemelhante(nome)));
    }
}
package com.algaworks.algafood.Service;

import com.algaworks.algafood.Repository.CidadeRepository;
import com.algaworks.algafood.entity.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;


    public List<Cidade> listarTodas() {
        return repository.findAll();
    }

    public Cidade buscarPorId(Long id) {

        Optional<Cidade> byId = repository.findById(id);

        Cidade cidade = byId.get();

        return cidade;
    }

    public Cidade alterar(Cidade cidade) {

        Optional<Cidade> byId = repository.findById(cidade.getId());

        Cidade busca = byId.get();

        if (busca != null) {
            busca.setEstado(cidade.getEstado());
            busca.setNome(cidade.getNome());
            salvar(busca);
            return busca;
        }
        return null;
    }

    public Cidade salvar(Cidade cidade) {
        Cidade save = repository.save(cidade);
        return save;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


}

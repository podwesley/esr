package com.algaworks.algafood.Repository;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.entity.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaTemp {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

        CadastroCozinha bean = context.getBean(CadastroCozinha.class);

        Cozinha cozinhaFind = bean.buscarPeloID(1L);

        System.out.println("Find Cozinha: " +cozinhaFind.getNome());

    }
}

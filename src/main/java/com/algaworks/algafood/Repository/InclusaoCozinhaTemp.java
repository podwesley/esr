package com.algaworks.algafood.Repository;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.entity.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaTemp {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

        CadastroCozinha bean = context.getBean(CadastroCozinha.class);

        Cozinha cozinha1 = new Cozinha();
        Cozinha cozinha2 = new Cozinha();

        cozinha1.setNome("BRASILEIRA");
        cozinha2.setNome("JAPONESA");

        cozinha1 = bean.adicionar(cozinha1);
        cozinha2 = bean.adicionar(cozinha2);

        System.out.printf("%d - %s \n" , cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d - %s \n" , cozinha2.getId(), cozinha2.getNome());

    }
}

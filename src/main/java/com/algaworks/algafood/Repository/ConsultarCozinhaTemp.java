package com.algaworks.algafood.Repository;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.entity.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ConsultarCozinhaTemp {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class).web(WebApplicationType.NONE).run(args);

        CadastroCozinha bean = context.getBean(CadastroCozinha.class);

        for(Cozinha cozinha: bean.listar()){
            System.out.println(cozinha.getNome());
        }
    }
}

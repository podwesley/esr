package com.algarworks.algarfood.notificacao;

import com.algarworks.algarfood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@TipoNotificador(NivelNotificacao.EMAIL)
@Component
public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;

    @Autowired
    private NotificadorProperties properties;

    @Value("${notificador.email.host-servidor}")
    private String servidor;

    @Value("${notificador.email.porta-servidor}")
    private Integer porta;

    @Override
    public void notificar(Cliente cliente, String mensagem) {

        System.out.printf("Host: %s porta: %s",properties.getHostServidor() , properties.getPortaServidor());
        System.out.printf("\nHostTeste: %s portaTeste: %s\n",servidor , porta);

        if (this.caixaAlta)
            mensagem = mensagem.toUpperCase();

        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}

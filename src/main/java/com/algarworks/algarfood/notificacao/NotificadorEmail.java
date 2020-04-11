package com.algarworks.algarfood.notificacao;

import com.algarworks.algarfood.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoNotificador(NivelNotificacao.EMAIL)
@Component
public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (this.caixaAlta)
            mensagem = mensagem.toUpperCase();

        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}

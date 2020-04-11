package com.algarworks.algarfood.service;

import com.algarworks.algarfood.modelo.Cliente;
import com.algarworks.algarfood.notificacao.NivelNotificacao;
import com.algarworks.algarfood.notificacao.Notificador;
import com.algarworks.algarfood.notificacao.TipoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @TipoNotificador(NivelNotificacao.EMAIL)
    @Autowired
    private Notificador notificador;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }
}

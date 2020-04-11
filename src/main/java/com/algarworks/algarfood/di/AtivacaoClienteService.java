package com.algarworks.algarfood.di;

import com.algarworks.algarfood.modelo.Cliente;
import com.algarworks.algarfood.notificacao.NivelNotificacao;
import com.algarworks.algarfood.notificacao.Notificador;
import com.algarworks.algarfood.notificacao.TipoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {

    @TipoNotificador(NivelNotificacao.EMAIL)
    @Autowired
    private Notificador notificador;


    public void ativar(Cliente cliente) {
        cliente.ativar();

        this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}

package com.algarworks.algarfood.listerner;

import com.algarworks.algarfood.notificacao.NivelNotificacao;
import com.algarworks.algarfood.notificacao.Notificador;
import com.algarworks.algarfood.notificacao.TipoNotificador;
import com.algarworks.algarfood.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoNotificador(NivelNotificacao.EMAIL)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListerner(ClienteAtivadoEvent event){
        System.out.println("Cliente " + event.getCliente().getNome() + " Agora está ativo. ");
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está concluído. ");
    }
}

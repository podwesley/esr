package com.algarworks.algarfood.listerner;

import com.algarworks.algarfood.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event){
        System.out.println("Emitido nota fiscal para o cliente " + event.getCliente().getNome());
    }
}

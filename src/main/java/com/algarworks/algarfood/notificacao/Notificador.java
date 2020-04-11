package com.algarworks.algarfood.notificacao;

import com.algarworks.algarfood.modelo.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}

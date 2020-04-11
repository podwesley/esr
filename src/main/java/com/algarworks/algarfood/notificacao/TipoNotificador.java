package com.algarworks.algarfood.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //Lida em tempo de execução.
@Qualifier
public @interface TipoNotificador {

    NivelNotificacao value();
}

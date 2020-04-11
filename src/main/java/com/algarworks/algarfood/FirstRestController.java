package com.algarworks.algarfood;

import com.algarworks.algarfood.di.AtivacaoClienteService;
import com.algarworks.algarfood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstRestController {

    @Autowired
    private AtivacaoClienteService ativacaoClienteService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {

        Cliente cliente = new Cliente("Wesley R", "podwesley@gmail.com", "(61) 99191-9633");

        ativacaoClienteService.ativar(cliente);

        return cliente.toString();

    }
}

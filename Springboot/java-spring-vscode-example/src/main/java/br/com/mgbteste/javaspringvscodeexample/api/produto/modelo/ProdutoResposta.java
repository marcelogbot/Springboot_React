package br.com.mgbteste.javaspringvscodeexample.api.produto.modelo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ProdutoResposta {
    
    private String mensagem;
}

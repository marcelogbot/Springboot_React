package br.com.mgbteste.javaspringvscodeexample.api.produto.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mgbteste.javaspringvscodeexample.api.produto.modelo.ProdutoModelo;
import br.com.mgbteste.javaspringvscodeexample.api.produto.servico.ProdutoServico;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoControle {

    @Autowired
    private ProdutoServico ps;

    @GetMapping("/produto")
    public String rota(){
        return "API de produtos funcionando!";
    }

    @PostMapping("/produto/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModelo pm){
        return ps.update(pm,"cadastrar");
    }

    @PutMapping("/produto/alterar")
    public ResponseEntity<?> alterar(@RequestBody ProdutoModelo pm){
        return ps.update(pm,"alterar");
    }

    @DeleteMapping("/produto/removerProduto")
    public ResponseEntity<?> removerProduto(@RequestBody ProdutoModelo pm){
        return ps.removerProduto(pm.getCodigo());
    }

    @GetMapping("/produto/listar")
    public Iterable<ProdutoModelo> listar() {
        return ps.listar();
    }

   
    
}

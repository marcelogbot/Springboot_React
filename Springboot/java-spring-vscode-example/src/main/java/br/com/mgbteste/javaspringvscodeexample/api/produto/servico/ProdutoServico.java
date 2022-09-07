package br.com.mgbteste.javaspringvscodeexample.api.produto.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mgbteste.javaspringvscodeexample.api.produto.modelo.ProdutoModelo;
import br.com.mgbteste.javaspringvscodeexample.api.produto.modelo.ProdutoResposta;
import br.com.mgbteste.javaspringvscodeexample.api.produto.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private ProdutoResposta rm;


    //Método de list produtos
    public Iterable<ProdutoModelo> listar() {
        return pr.findAll();
    }

    //Método para cadastrar ou alterar produtos
    public ResponseEntity<?> update(ProdutoModelo pm, String action) {
        if(pm.getNome().equals("")){

            rm.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<ProdutoResposta>(rm, HttpStatus.BAD_REQUEST);

        } else if (pm.getMarca().equals("")) {

            rm.setMensagem("A marca do produto é obrigatória!");
            return new ResponseEntity<ProdutoResposta>(rm, HttpStatus.BAD_REQUEST);

        } else {
            if (action.equals("cadastrar")) {

                return new ResponseEntity<ProdutoModelo>(pr.save(pm),HttpStatus.CREATED);

            } else {
                if (pr.findById(pm.getCodigo()).isEmpty()) {
                    rm.setMensagem("Esse produto não existe para ser alterado");
                    return new ResponseEntity<ProdutoResposta>(rm, HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<ProdutoModelo>(pr.save(pm),HttpStatus.OK);
                }
                
            }
        }
    }

    public ResponseEntity<?> removerProduto(Long codigo) {
        
        try {

            pr.deleteById(codigo);
            rm.setMensagem("O produto de código "+codigo+" foi removido com sucesso");
            return new ResponseEntity<ProdutoResposta>(rm, HttpStatus.OK);
            
        } catch (Exception e) {

            rm.setMensagem("Não Existe produto com o código: "+codigo);
            return new ResponseEntity<ProdutoResposta>(rm, HttpStatus.BAD_REQUEST);

        }
        
        
       /* 
        if (pr.findById(codigo).isEmpty()) {

            rm.setMensagem("Não Existe produto com o código: "+codigo);
            return new ResponseEntity<ProdutoResposta>(rm, HttpStatus.BAD_REQUEST);

        } else {

            pr.deleteById(codigo);
            rm.setMensagem("O produto de código "+codigo+" foi removido com sucesso");
            return new ResponseEntity<ProdutoResposta>(rm, HttpStatus.OK);
            
        }
        */ 
        
    }
    
}

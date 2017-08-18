package carromesa.com.br.bottombar.model;

import java.util.List;

/**
 * Created by carlosrodolfosantos on 03/08/17.
 */

public class Grupos {
    private int id;
    private String nome;
    private List<Produtos> produtos;

    public Grupos(int id, String nome, List<Produtos> produtos){
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
    }

    public String getNome(){
        return this.nome;
    }

    public List<Produtos> getProdutos(){
        return produtos;
    }

    public Produtos getProduto(int id){
        return this.produtos.get(id);
    }
}

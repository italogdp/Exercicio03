package main;

import static spark.Spark.*;
import dao.ProdutoDAO;
import model.Produto;

public class Aplicacao {
    public static void main(String[] args) { 
        port(4567);

        staticFiles.location("/public");

        
        post("/inserir", (request, response) -> {
            
            String nome = request.queryParams("nome");
            String precoStr = request.queryParams("preco");
            double preco = Double.parseDouble(precoStr);

            
            ProdutoDAO dao = new ProdutoDAO();
            dao.inserir(new Produto(0, nome, preco));

            
            return "<html><body><h2>Produto '" + nome + "' cadastrado com sucesso!</h2>" +
                   "<a href='/index.html'>Voltar ao formulário</a></body></html>";
        });

        System.out.println("Servidor iniciado! Acesse: http://localhost:4567/index.html");
    }
}
package main;

import java.util.Scanner;
import model.Produto;
import dao.ProdutoDAO;

public class GerenciadorProdutos {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        ProdutoDAO dao = new ProdutoDAO(); 
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n--- SISTEMA DE PRODUTOS (POSTGRESQL) ---");
            System.out.println("1. Inserir Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Remover Produto");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                scanner.nextLine(); 
                continue;
            }

            switch (opcao) {
                case 1: 
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine(); 

                    Produto novo = new Produto(0, nome, preco);
                    dao.inserir(novo);
                    break;

                case 2: 
                    System.out.println("\n--- PRODUTOS NO BANCO ---");
                    
                    if (dao.listar() != null) {
                        for (Produto p : dao.listar()) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 3: 
                    System.out.print("ID do produto para atualizar: ");
                    int idAtu = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo Preço: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine(); 
                    
                    Produto pAtu = new Produto(idAtu, novoNome, novoPreco);
                    dao.atualizar(pAtu); 
                    break;

                case 4: 
                    System.out.print("ID do produto para remover: ");
                    int idRem = scanner.nextInt();
                    scanner.nextLine(); 
                    dao.remover(idRem); 
                    break;
            }
        }
        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}
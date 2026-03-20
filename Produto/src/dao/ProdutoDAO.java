package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.Conexao;
import model.Produto;

public class ProdutoDAO {

    public void inserir(Produto p) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.executeUpdate();
            System.out.println("Inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos ORDER BY id";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco")));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Produto p) {
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao remover: " + e.getMessage());
        }
    }
}
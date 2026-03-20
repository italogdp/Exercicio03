package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    
	private static final String URL = "jdbc:postgresql://localhost:5432/controle_produtos";";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123"; 

    public static Connection conectar() {
        try {
            
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }
}

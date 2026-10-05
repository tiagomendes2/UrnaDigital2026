package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Votacao {

    public static boolean salvar(String cpf, String candidato) {

        String sql = "INSERT INTO votos (cpf, candidato) VALUES (?, ?)";

        try {
            Connection conexao = Conexao.conectar();

            PreparedStatement comando = conexao.prepareStatement(sql);

            comando.setString(1, cpf);
            comando.setString(2, candidato);

            comando.executeUpdate();

            comando.close();
            conexao.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar votação: " + e.getMessage());
            return false;
        }
    }
}
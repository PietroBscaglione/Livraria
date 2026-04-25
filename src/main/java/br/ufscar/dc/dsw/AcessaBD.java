package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD {

    public static void main(String[] args) {
        try {
            // 1. Carrega o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Configura a URL (ajustada para evitar erros de conexão comuns)
            String url = "jdbc:mysql://localhost:3306/Livraria?serverTimezone=America/Sao_Paulo";

            // 3. Tenta conectar (Verifique se sua senha é 'root' mesmo!)
            Connection con = DriverManager.getConnection(url, "root", "root");

            System.out.println("Conexão estabelecida com sucesso!");

            // 4. Executa a Query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Livro");

            while (rs.next()) {
                System.out.print(rs.getString("titulo")); // Note o 't' minúsculo se falhar
                System.out.print(", " + rs.getString("autor"));
                System.out.print(", " + rs.getInt("ano"));
                System.out.println(" (R$ " + rs.getFloat("preco") + ")");
            }

            // 5. Fecha tudo
            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("ERRO: O Driver do MySQL não foi encontrado no projeto!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("ERRO NO SQL/CONEXÃO: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
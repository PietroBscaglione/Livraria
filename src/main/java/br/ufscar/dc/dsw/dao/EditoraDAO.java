package br.ufscar.dc.dsw.dao;

import java.sql.*;
import java.util.*;
import br.ufscar.dc.dsw.domain.Editora;

// 1. HERDE de GenericDAO para usar a conexão que já funciona
public class EditoraDAO extends GenericDAO {

    public List<Editora> getAll() {
        List<Editora> lista = new ArrayList<>();
        String sql = "SELECT * FROM Editora";

        // 2. Use o método getConnection() da classe pai (GenericDAO)
        try (Connection con = this.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Editora(
                        rs.getLong("id"),
                        rs.getString("cnpj"),
                        rs.getString("nome")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void insert(Editora editora) {
        String sql = "INSERT INTO Editora(cnpj, nome) VALUES (?, ?)";
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, editora.getCnpj());
            stmt.setString(2, editora.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Editora get(Long id) {
        Editora editora = null;
        String sql = "SELECT * FROM Editora WHERE id = ?";
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                editora = new Editora(
                        rs.getLong("id"),
                        rs.getString("cnpj"),
                        rs.getString("nome")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return editora;
    }

    public void update(Editora editora) {
        String sql = "UPDATE Editora SET cnpj=?, nome=? WHERE id=?";
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, editora.getCnpj());
            stmt.setString(2, editora.getNome());
            stmt.setLong(3, editora.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Editora editora) {
        String sql = "DELETE FROM Editora WHERE id=?";
        try (Connection con = this.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setLong(1, editora.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
package uniguia.model.dao;

import uniguia.model.Instituicao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstituicaoDAO {

    public boolean cadastrar(Instituicao inst) {
        String sql = "INSERT INTO instituicao (nome, endereco, tipo, nota_enade, descricao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, inst.getNome());
            stmt.setString(2, inst.getEndereco());
            stmt.setString(3, inst.getTipo());
            stmt.setDouble(4, inst.getNotaEnade());
            stmt.setString(5, inst.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Instituicao> listarTodas() {
        List<Instituicao> lista = new ArrayList<>();
        String sql = "SELECT * FROM instituicao ORDER BY id DESC";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Instituicao(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("tipo"),
                    rs.getDouble("nota_enade"),
                    rs.getString("descricao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean atualizar(Instituicao inst) {
        String sql = "UPDATE instituicao SET nome=?, endereco=?, tipo=?, nota_enade=?, descricao=? WHERE id=?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, inst.getNome());
            stmt.setString(2, inst.getEndereco());
            stmt.setString(3, inst.getTipo());
            stmt.setDouble(4, inst.getNotaEnade());
            stmt.setString(5, inst.getDescricao());
            stmt.setInt(6, inst.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM instituicao WHERE id=?";
        try (Connection conn = ConexaoDAO.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
package com.gamardo.clientes.dao;

import com.gamardo.clientes.db.ConnectionFactory;
import com.gamardo.clientes.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserir(Cliente c) {
        String sql = "INSERT INTO clientes (nome, email, telefone, documento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getDocumento());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage(), e);
        }
    }

    public void atualizar(Cliente c) {
        if (c.getId() == null) throw new IllegalArgumentException("ID do cliente não pode ser nulo para atualização.");
        String sql = "UPDATE clientes SET nome=?, email=?, telefone=?, documento=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getTelefone());
            ps.setString(4, c.getDocumento());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage(), e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente: " + e.getMessage(), e);
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT id, nome, email, telefone, documento FROM clientes ORDER BY id DESC";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("documento")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
        return lista;
    }
}

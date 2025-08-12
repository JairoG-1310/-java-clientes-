package com.gamardo.clientes.ui;

import com.gamardo.clientes.dao.ClienteDAO;
import com.gamardo.clientes.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteForm extends JFrame {

    private JTextField txtId = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTelefone = new JTextField();
    private JTextField txtDocumento = new JTextField();

    private DefaultTableModel tableModel = new DefaultTableModel(
            new Object[]{"ID", "Nome", "Email", "Telefone", "Documento"}, 0
    );
    private JTable tabela = new JTable(tableModel);

    private ClienteDAO dao = new ClienteDAO();

    public ClienteForm() {
        setTitle("Cadastro de Clientes - Java + MySQL");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initLayout();
        carregarTabela();

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() >= 0) {
                int row = tabela.getSelectedRow();
                txtId.setText(String.valueOf(tabela.getValueAt(row, 0)));
                txtNome.setText(String.valueOf(tabela.getValueAt(row, 1)));
                txtEmail.setText(String.valueOf(tabela.getValueAt(row, 2)));
                txtTelefone.setText(String.valueOf(tabela.getValueAt(row, 3)));
                txtDocumento.setText(String.valueOf(tabela.getValueAt(row, 4)));
            }
        });
    }

    private void initLayout() {
        JPanel form = new JPanel(new GridLayout(5, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtId.setEditable(false);

        form.add(new JLabel("ID:"));
        form.add(txtId);
        form.add(new JLabel("Nome:"));
        form.add(txtNome);
        form.add(new JLabel("Email:"));
        form.add(txtEmail);
        form.add(new JLabel("Telefone:"));
        form.add(txtTelefone);
        form.add(new JLabel("Documento:"));
        form.add(txtDocumento);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnNovo = new JButton("Novo");
        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnRecarregar = new JButton("Recarregar");

        btnNovo.addActionListener(e -> limparCampos());
        btnSalvar.addActionListener(e -> salvarCliente());
        btnAtualizar.addActionListener(e -> atualizarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());
        btnRecarregar.addActionListener(e -> carregarTabela());

        botoes.add(btnNovo);
        botoes.add(btnSalvar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);
        botoes.add(btnRecarregar);

        JScrollPane scroll = new JScrollPane(tabela);
        tabela.setFillsViewportHeight(true);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.NORTH);
        getContentPane().add(botoes, BorderLayout.CENTER);
        getContentPane().add(scroll, BorderLayout.SOUTH);
    }

    private void carregarTabela() {
        tableModel.setRowCount(0);
        try {
            List<Cliente> clientes = dao.listar();
            for (Cliente c : clientes) {
                tableModel.addRow(new Object[]{
                        c.getId(), c.getNome(), c.getEmail(), c.getTelefone(), c.getDocumento()
                });
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao carregar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtDocumento.setText("");
        tabela.clearSelection();
    }

    private void salvarCliente() {
        try {
            Cliente c = new Cliente(null, txtNome.getText(), txtEmail.getText(), txtTelefone.getText(), txtDocumento.getText());
            dao.inserir(c);
            carregarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao salvar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarCliente() {
        if (txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para atualizar.");
            return;
        }
        try {
            Integer id = Integer.parseInt(txtId.getText());
            Cliente c = new Cliente(id, txtNome.getText(), txtEmail.getText(), txtTelefone.getText(), txtDocumento.getText());
            dao.atualizar(c);
            carregarTabela();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao atualizar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirCliente() {
        if (txtId.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela para excluir.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Confirma excluir?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText());
                dao.excluir(id);
                carregarTabela();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro ao excluir", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

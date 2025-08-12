package com.gamardo.clientes;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.gamardo.clientes.ui.ClienteForm;

public class App {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new ClienteForm().setVisible(true));
    }
}

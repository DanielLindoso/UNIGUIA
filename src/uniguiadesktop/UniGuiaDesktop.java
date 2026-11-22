package uniguiadesktop;

import javax.swing.UIManager;
import uniguia.view.TelaLogin;

public class UniGuiaDesktop {

    public static void main(String[] args) {
        // Tenta aplicar o visual nativo do sistema operacional (Windows/Linux)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Não foi possível carregar o Look and Feel nativo: " + e.getMessage());
        }

        // Inicializa a interface Swing de forma assíncrona abrindo a TelaLogin
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
}
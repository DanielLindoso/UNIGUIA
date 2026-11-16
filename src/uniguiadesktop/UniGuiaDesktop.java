package uniguiadesktop;

import uniguia.view.TelaLogin;

public class UniGuiaDesktop {

    public static void main(String[] args) {
        // Define o visual padronizado do sistema e abre a tela de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
}
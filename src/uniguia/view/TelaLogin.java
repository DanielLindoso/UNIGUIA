
package uniguia.view;

import uniguia.model.Usuario;
import uniguia.model.dao.UsuarioDAO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class TelaLogin extends JFrame {

    private JTextField campoEmail;
    private JPasswordField campoSenha;

    private final Color azulEscuro = new Color(10, 31, 62);
    private final Color azul = new Color(25, 105, 210);
    private final Color ciano = new Color(0, 180, 210);

    public TelaLogin() {
        configurarTela();
        criarComponentes();
    }

    private void configurarTela() {
        setTitle("UniGuia - Acesse sua conta");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void criarComponentes() {

        JPanel principal = new JPanel(new GridLayout(1, 2));
        principal.setBackground(Color.WHITE);

        // PAINEL ESQUERDO
        JPanel painelEsquerdo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gradiente = new GradientPaint(
                    0, 0, azulEscuro,
                    getWidth(), getHeight(), new Color(20, 80, 145)
                );

                g2.setPaint(gradiente);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        painelEsquerdo.setLayout(new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS));
        painelEsquerdo.setBorder(new EmptyBorder(55, 35, 40, 35));

        // LOGOTIPO
        
        // LOGOTIPO
        URL caminhoLogo = getClass().getClassLoader()
            .getResource("uniguia/images/logo_uniguia.png");

        if (caminhoLogo == null) {
            caminhoLogo = getClass().getClassLoader()
                .getResource("uniguia.images/logo_uniguia.png");
        }

        if (caminhoLogo != null) {

            ImageIcon imagemOriginal = new ImageIcon(caminhoLogo);

            int larguraOriginal = imagemOriginal.getIconWidth();
            int alturaOriginal = imagemOriginal.getIconHeight();

            double proporcao = Math.min(
                220.0 / larguraOriginal,
                190.0 / alturaOriginal
            );

            int novaLargura = (int) (larguraOriginal * proporcao);
            int novaAltura = (int) (alturaOriginal * proporcao);

            Image imagemRedimensionada = imagemOriginal.getImage()
                .getScaledInstance(
                    novaLargura,
                    novaAltura,
                    Image.SCALE_SMOOTH
                );

            JLabel logo = new JLabel(new ImageIcon(imagemRedimensionada));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);

            painelEsquerdo.add(logo);

        } else {
            JLabel logoTexto = new JLabel("UniGuia");
            logoTexto.setFont(new Font("Arial", Font.BOLD, 38));
            logoTexto.setForeground(Color.WHITE);
            logoTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelEsquerdo.add(logoTexto);
        }

        painelEsquerdo.add(Box.createVerticalStrut(45));


        JLabel chamada = new JLabel(
            "<html><div style='text-align:center;'>"
            + "Encontre a faculdade ideal<br>"
            + "para o seu futuro."
            + "</div></html>"
        );

        chamada.setFont(new Font("Arial", Font.BOLD, 23));
        chamada.setForeground(Color.WHITE);
        chamada.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelEsquerdo.add(chamada);

        painelEsquerdo.add(Box.createVerticalStrut(18));

        JLabel descricao = new JLabel(
            "<html><div style='text-align:center;'>"
            + "Conheça instituições de ensino e descubra<br>"
            + "novas oportunidades para sua formação."
            + "</div></html>"
        );

        descricao.setFont(new Font("Arial", Font.PLAIN, 14));
        descricao.setForeground(new Color(205, 220, 240));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelEsquerdo.add(descricao);

        // PAINEL DIREITO
        JPanel painelDireito = new JPanel(new GridBagLayout());
        painelDireito.setBackground(new Color(245, 248, 252));

        JPanel formulario = new JPanel();
        formulario.setLayout(new BoxLayout(formulario, BoxLayout.Y_AXIS));
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(new EmptyBorder(40, 40, 40, 40));
        formulario.setPreferredSize(new Dimension(380, 440));

        JLabel tituloLogin = new JLabel("Bem-vindo de volta!");
        tituloLogin.setFont(new Font("Arial", Font.BOLD, 27));
        tituloLogin.setForeground(azulEscuro);
        tituloLogin.setAlignmentX(Component.LEFT_ALIGNMENT);
        formulario.add(tituloLogin);

        formulario.add(Box.createVerticalStrut(10));

        JLabel subtitulo = new JLabel("Entre na sua conta para continuar.");
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(110, 120, 135));
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        formulario.add(subtitulo);

        formulario.add(Box.createVerticalStrut(35));

        JLabel labelEmail = new JLabel("E-mail");
        estilizarLabel(labelEmail);
        formulario.add(labelEmail);

        formulario.add(Box.createVerticalStrut(8));

        campoEmail = new JTextField();
        estilizarCampo(campoEmail);
        formulario.add(campoEmail);

        formulario.add(Box.createVerticalStrut(22));

        JLabel labelSenha = new JLabel("Senha");
        estilizarLabel(labelSenha);
        formulario.add(labelSenha);

        formulario.add(Box.createVerticalStrut(8));

        campoSenha = new JPasswordField();
        estilizarCampo(campoSenha);
        formulario.add(campoSenha);

        formulario.add(Box.createVerticalStrut(30));

        JButton botaoEntrar = new JButton("ENTRAR NA CONTA");
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setBackground(azul);
        botaoEntrar.setFocusPainted(false);
        botaoEntrar.setBorderPainted(false);
        botaoEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoEntrar.setAlignmentX(Component.LEFT_ALIGNMENT);
        botaoEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        botaoEntrar.setPreferredSize(new Dimension(300, 46));

        botaoEntrar.addActionListener(e -> realizarLogin());

        formulario.add(botaoEntrar);

        getRootPane().setDefaultButton(botaoEntrar);

        formulario.add(Box.createVerticalStrut(25));

        JLabel rodape = new JLabel("UniGuia - Seu futuro começa aqui.");
        rodape.setFont(new Font("Arial", Font.PLAIN, 12));
        rodape.setForeground(new Color(140, 150, 165));
        rodape.setAlignmentX(Component.CENTER_ALIGNMENT);
        formulario.add(rodape);

        painelDireito.add(formulario);

        principal.add(painelEsquerdo);
        principal.add(painelDireito);

        setContentPane(principal);
    }

    private void estilizarLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(azulEscuro);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Arial", Font.PLAIN, 15));
        campo.setPreferredSize(new Dimension(300, 43));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 43));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(215, 225, 238)),
            new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void realizarLogin() {

        String email = campoEmail.getText().trim();
        String senha = new String(campoSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Preencha o e-mail e a senha.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar(email, senha);

        if (usuario != null) {

            JOptionPane.showMessageDialog(
                this,
                "Bem-vindo ao UniGuia, " + usuario.getNome() + "!"
            );

            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(
                this,
                "E-mail ou senha incorretos.",
                "Falha no login",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(
            () -> new TelaLogin().setVisible(true)
        );
    }
}




package uniguia.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class TelaPrincipal extends JFrame {

    private final Color azulEscuro = new Color(10, 31, 62);
    private final Color azul = new Color(25, 105, 210);
    private final Color ciano = new Color(0, 180, 210);
    private final Color fundo = new Color(244, 247, 252);

    public TelaPrincipal() {
        configurarTela();
        criarComponentes();
    }

    private void configurarTela() {
        setTitle("UniGuia - Página Inicial");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void criarComponentes() {

        JPanel principal = new JPanel(new BorderLayout());
        principal.setBackground(fundo);

        // MENU LATERAL
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(220, 650));
        menu.setBackground(azulEscuro);
        menu.setLayout(null);

        // LOGOTIPO
        JLabel logo = criarImagem(
            "logo_uniguia.png", 150, 120
        );

        logo.setBounds(35, 35, 150, 120);
        menu.add(logo);

        JLabel textoMenu = new JLabel("MENU PRINCIPAL");
        textoMenu.setFont(new Font("Arial", Font.BOLD, 12));
        textoMenu.setForeground(new Color(160, 185, 215));
        textoMenu.setBounds(25, 190, 170, 25);
        menu.add(textoMenu);

        // BOTÃO INSTITUIÇÕES
        JButton botaoInstituicoes = criarBotaoMenu(
            "Instituições de Ensino"
        );

        botaoInstituicoes.setBounds(15, 230, 190, 45);

        botaoInstituicoes.addActionListener(e -> {
            TelaInstituicao tela = new TelaInstituicao();
            tela.setVisible(true);
            dispose();
        });

        menu.add(botaoInstituicoes);

        // BOTÃO SAIR
        JButton botaoSair = criarBotaoMenu("Sair");
        botaoSair.setBounds(15, 560, 190, 45);
        botaoSair.setForeground(new Color(255, 190, 190));

        botaoSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        menu.add(botaoSair);

        // ÁREA PRINCIPAL
        JPanel conteudo = new JPanel(null);
        conteudo.setBackground(fundo);

        // TÍTULO
        JLabel titulo = new JLabel("Bem-vindo ao UniGuia!");
        titulo.setFont(new Font("Arial", Font.BOLD, 29));
        titulo.setForeground(azulEscuro);
        titulo.setBounds(35, 25, 650, 45);
        conteudo.add(titulo);

        JLabel descricao = new JLabel(
            "Descubra instituições de ensino e encontre novas oportunidades."
        );

        descricao.setFont(new Font("Arial", Font.PLAIN, 14));
        descricao.setForeground(new Color(105, 115, 130));
        descricao.setBounds(37, 72, 680, 30);
        conteudo.add(descricao);

        // BANNER
        JPanel painelBanner = new JPanel(new BorderLayout());
        painelBanner.setBackground(azulEscuro);
        painelBanner.setBounds(35, 120, 690, 220);

        JLabel banner = criarImagem(
            "banner_home.png", 680, 210
        );

        banner.setHorizontalAlignment(SwingConstants.CENTER);
        banner.setVerticalAlignment(SwingConstants.CENTER);

        painelBanner.add(banner, BorderLayout.CENTER);
        conteudo.add(painelBanner);

        // CARTÃO DE INSTITUIÇÕES
        JPanel cartao = new JPanel(null);
        cartao.setBackground(Color.WHITE);
        cartao.setBounds(35, 375, 690, 190);
        cartao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(225, 232, 242)
            ),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel tituloCartao = new JLabel(
            "Instituições de Ensino"
        );

        tituloCartao.setFont(new Font("Arial", Font.BOLD, 22));
        tituloCartao.setForeground(azulEscuro);
        tituloCartao.setBounds(25, 20, 500, 35);
        cartao.add(tituloCartao);

        JLabel textoCartao = new JLabel(
            "<html>Consulte e gerencie as informações das instituições<br>"
            + "de ensino disponíveis no UniGuia.</html>"
        );

        textoCartao.setFont(new Font("Arial", Font.PLAIN, 14));
        textoCartao.setForeground(new Color(110, 120, 135));
        textoCartao.setBounds(25, 65, 450, 50);
        cartao.add(textoCartao);

        JButton botaoAcessar = new JButton(
            "ACESSAR INSTITUIÇÕES"
        );

        botaoAcessar.setBounds(25, 125, 230, 42);
        botaoAcessar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoAcessar.setForeground(Color.WHITE);
        botaoAcessar.setBackground(azul);
        botaoAcessar.setFocusPainted(false);
        botaoAcessar.setBorderPainted(false);
        botaoAcessar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botaoAcessar.addActionListener(e -> {
            TelaInstituicao tela = new TelaInstituicao();
            tela.setVisible(true);
            dispose();
        });

        cartao.add(botaoAcessar);

        conteudo.add(cartao);

        // RODAPÉ
        JLabel rodape = new JLabel(
            "UniGuia - Seu futuro começa aqui."
        );

        rodape.setFont(new Font("Arial", Font.PLAIN, 11));
        rodape.setForeground(new Color(145, 155, 170));
        rodape.setBounds(35, 585, 400, 20);
        conteudo.add(rodape);

        principal.add(menu, BorderLayout.WEST);
        principal.add(conteudo, BorderLayout.CENTER);

        setContentPane(principal);
    }

    // CARREGAR IMAGENS DO PROJETO
    private JLabel criarImagem(
        String nomeArquivo,
        int largura,
        int altura
    ) {

        URL caminho = getClass().getClassLoader()
            .getResource("uniguia/images/" + nomeArquivo);

        if (caminho == null) {
            caminho = getClass().getClassLoader()
                .getResource("uniguia.images/" + nomeArquivo);
        }

        if (caminho != null) {

            ImageIcon original = new ImageIcon(caminho);

            int larguraOriginal = original.getIconWidth();
            int alturaOriginal = original.getIconHeight();

            double proporcao = Math.min(
                (double) largura / larguraOriginal,
                (double) altura / alturaOriginal
            );

            int novaLargura = (int) (larguraOriginal * proporcao);
            int novaAltura = (int) (alturaOriginal * proporcao);

            Image imagem = original.getImage().getScaledInstance(
                novaLargura,
                novaAltura,
                Image.SCALE_SMOOTH
            );

            return new JLabel(new ImageIcon(imagem));

        } else {

            System.out.println(
                "Imagem não encontrada: " + nomeArquivo
            );

            return new JLabel("");
        }
    }

    // ESTILO DOS BOTÕES DO MENU
    private JButton criarBotaoMenu(String texto) {

        JButton botao = new JButton(texto);

        botao.setFont(new Font("Arial", Font.BOLD, 13));
        botao.setForeground(Color.WHITE);
        botao.setBackground(azulEscuro);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setBorder(
            BorderFactory.createEmptyBorder(0, 15, 0, 0)
        );
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botao.setBackground(new Color(28, 62, 105));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                botao.setBackground(azulEscuro);
            }
        });

        return botao;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(
            () -> new TelaPrincipal().setVisible(true)
        );
    }
}


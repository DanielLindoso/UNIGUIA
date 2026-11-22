
package uniguia.view;

import uniguia.model.Instituicao;
import uniguia.model.dao.InstituicaoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class TelaInstituicao extends JFrame {

    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoEndereco;
    private JComboBox<String> campoTipo;
    private JTextField campoNota;
    private JTextArea campoDescricao;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    private final InstituicaoDAO instituicaoDAO = new InstituicaoDAO();

    private final Color AZUL_ESCURO = new Color(15, 35, 65);
    private final Color AZUL = new Color(0, 150, 195);
    private final Color FUNDO = new Color(240, 245, 250);
    private final Color CINZA = new Color(95, 105, 120);

    public TelaInstituicao() {
        configurarTela();
        criarComponentes();
        listarInstituicoes();
    }

    private void configurarTela() {
        setTitle("UniGuia - Instituições de Ensino");
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(FUNDO);
    }

    private void criarComponentes() {

        // CABEÇALHO
        JPanel cabecalho = new JPanel(null);
        cabecalho.setBackground(AZUL_ESCURO);
        cabecalho.setBounds(0, 0, 1100, 80);
        add(cabecalho);

        // ÍCONE DO UNIGUIA
        JLabel icone = criarImagem("icon_institution.png", 45, 45);
        icone.setBounds(30, 17, 45, 45);
        cabecalho.add(icone);

        // NOME DO UNIGUIA
        JLabel logo = new JLabel("UniGuia");
        logo.setFont(new Font("Arial", Font.BOLD, 27));
        logo.setForeground(Color.WHITE);
        logo.setBounds(85, 15, 170, 45);
        cabecalho.add(logo);

        // SUBTÍTULO
        JLabel subtitulo = new JLabel("INSTITUIÇÕES DE ENSINO");
        subtitulo.setFont(new Font("Arial", Font.BOLD, 12));
        subtitulo.setForeground(new Color(0, 190, 220));
        subtitulo.setBounds(270, 25, 250, 30);
        cabecalho.add(subtitulo);

        // BOTÃO VOLTAR
        JButton botaoVoltar = new JButton("VOLTAR");
        botaoVoltar.setBounds(940, 20, 120, 38);
        estilizarBotao(botaoVoltar, AZUL_ESCURO);
        botaoVoltar.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        botaoVoltar.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            dispose();
        });

        cabecalho.add(botaoVoltar);

        // TÍTULO DA PÁGINA
        JLabel tituloPagina = new JLabel("Instituições de Ensino");
        tituloPagina.setFont(new Font("Arial", Font.BOLD, 27));
        tituloPagina.setForeground(AZUL_ESCURO);
        tituloPagina.setBounds(35, 100, 600, 38);
        add(tituloPagina);

        JLabel descricaoPagina = new JLabel(
            "Consulte e gerencie as informações das instituições de ensino."
        );
        descricaoPagina.setFont(new Font("Arial", Font.PLAIN, 14));
        descricaoPagina.setForeground(CINZA);
        descricaoPagina.setBounds(35, 140, 700, 25);
        add(descricaoPagina);

        // PAINEL DO FORMULÁRIO
        JPanel painelFormulario = new JPanel(null);
        painelFormulario.setBackground(Color.WHITE);
        painelFormulario.setBounds(35, 180, 1015, 245);
        painelFormulario.setBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 232))
        );
        add(painelFormulario);

        JLabel tituloFormulario = new JLabel("Dados da instituição");
        tituloFormulario.setFont(new Font("Arial", Font.BOLD, 19));
        tituloFormulario.setForeground(AZUL_ESCURO);
        tituloFormulario.setBounds(22, 12, 350, 30);
        painelFormulario.add(tituloFormulario);

        // ID
        JLabel labelId = criarLabel("ID:", 22, 55, 80);
        painelFormulario.add(labelId);

        campoId = new JTextField();
        campoId.setBounds(22, 80, 75, 32);
        campoId.setEditable(false);
        estilizarCampo(campoId);
        painelFormulario.add(campoId);

        // NOME
        JLabel labelNome = criarLabel("Nome da instituição:", 120, 55, 250);
        painelFormulario.add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(120, 80, 390, 32);
        estilizarCampo(campoNome);
        painelFormulario.add(campoNome);

        // ENDEREÇO
        JLabel labelEndereco = criarLabel("Endereço:", 535, 55, 200);
        painelFormulario.add(labelEndereco);

        campoEndereco = new JTextField();
        campoEndereco.setBounds(535, 80, 455, 32);
        estilizarCampo(campoEndereco);
        painelFormulario.add(campoEndereco);

        // TIPO
        JLabel labelTipo = criarLabel("Tipo de instituição:", 22, 130, 200);
        painelFormulario.add(labelTipo);

        campoTipo = new JComboBox<>(
            new String[]{"Pública", "Privada", "Comunitária"}
        );
        campoTipo.setBounds(22, 155, 220, 32);
        campoTipo.setFont(new Font("Arial", Font.PLAIN, 13));
        painelFormulario.add(campoTipo);

        // NOTA ENADE
        JLabel labelNota = criarLabel("Nota ENADE:", 265, 130, 150);
        painelFormulario.add(labelNota);

        campoNota = new JTextField();
        campoNota.setBounds(265, 155, 150, 32);
        estilizarCampo(campoNota);
        painelFormulario.add(campoNota);

        // DESCRIÇÃO
        JLabel labelDescricao = criarLabel("Descrição:", 440, 130, 150);
        painelFormulario.add(labelDescricao);

        campoDescricao = new JTextArea();
        campoDescricao.setFont(new Font("Arial", Font.PLAIN, 13));
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true);

        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setBounds(440, 155, 550, 55);
        painelFormulario.add(scrollDescricao);

        // BOTÕES DE AÇÃO
        JButton botaoCadastrar = criarBotao("CADASTRAR", 35, 445);
        botaoCadastrar.addActionListener(e -> cadastrarInstituicao());
        add(botaoCadastrar);

        JButton botaoAtualizar = criarBotao("ATUALIZAR", 205, 445);
        botaoAtualizar.addActionListener(e -> atualizarInstituicao());
        add(botaoAtualizar);

        JButton botaoExcluir = criarBotao("EXCLUIR", 375, 445);
        botaoExcluir.setBackground(new Color(190, 55, 55));
        botaoExcluir.addActionListener(e -> excluirInstituicao());
        add(botaoExcluir);

        JButton botaoLimpar = criarBotao("LIMPAR", 545, 445);
        botaoLimpar.setBackground(new Color(100, 115, 130));
        botaoLimpar.addActionListener(e -> limparCampos());
        add(botaoLimpar);

        // PAINEL DA TABELA
        JPanel painelTabela = new JPanel(null);
        painelTabela.setBackground(Color.WHITE);
        painelTabela.setBounds(35, 505, 1015, 145);
        painelTabela.setBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 232))
        );
        add(painelTabela);

        JLabel tituloTabela = new JLabel("Instituições cadastradas");
        tituloTabela.setFont(new Font("Arial", Font.BOLD, 18));
        tituloTabela.setForeground(AZUL_ESCURO);
        tituloTabela.setBounds(18, 8, 400, 28);
        painelTabela.add(tituloTabela);

        // MODELO DA TABELA
        modeloTabela = new DefaultTableModel(
            new Object[]{
                "ID", "Nome", "Tipo", "Nota ENADE", "Endereço"
            },
            0
        ) {
            @Override
            public boolean isCellEditable(int linha, int coluna) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));
        tabela.setRowHeight(25);
        tabela.setSelectionBackground(new Color(205, 230, 245));
        tabela.setSelectionForeground(AZUL_ESCURO);
        tabela.setGridColor(new Color(230, 235, 240));

        JTableHeader cabecalhoTabela = tabela.getTableHeader();
        cabecalhoTabela.setFont(new Font("Arial", Font.BOLD, 12));
        cabecalhoTabela.setBackground(AZUL_ESCURO);
        cabecalhoTabela.setForeground(Color.WHITE);
        cabecalhoTabela.setPreferredSize(new Dimension(0, 28));

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                preencherCampos();
            }
        });

        JScrollPane scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBounds(15, 42, 985, 90);
        painelTabela.add(scrollTabela);
    }

    // CARREGAR IMAGEM
    private JLabel criarImagem(String nomeArquivo, int largura, int altura) {

        java.net.URL caminho = getClass().getClassLoader()
            .getResource("uniguia/images/" + nomeArquivo);

        if (caminho == null) {
            caminho = getClass().getClassLoader()
                .getResource("uniguia.images/" + nomeArquivo);
        }

        if (caminho == null) {
            System.out.println("Imagem não encontrada: " + nomeArquivo);
            return new JLabel();
        }

        ImageIcon imagem = new ImageIcon(caminho);

        Image imagemRedimensionada = imagem.getImage().getScaledInstance(
            largura,
            altura,
            Image.SCALE_SMOOTH
        );

        return new JLabel(new ImageIcon(imagemRedimensionada));
    }

    // CRIAÇÃO DE LABELS
    private JLabel criarLabel(String texto, int x, int y, int largura) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(AZUL_ESCURO);
        label.setBounds(x, y, largura, 22);
        return label;
    }

    // ESTILO DOS CAMPOS
    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        campo.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(205, 215, 225)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
            )
        );
    }

    // ESTILO DOS BOTÕES
    private JButton criarBotao(String texto, int x, int y) {
        JButton botao = new JButton(texto);
        botao.setBounds(x, y, 150, 40);
        estilizarBotao(botao, AZUL);
        return botao;
    }

    private void estilizarBotao(JButton botao, Color cor) {
        botao.setFont(new Font("Arial", Font.BOLD, 12));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBorderPainted(false);
    }

    // CADASTRAR
    private void cadastrarInstituicao() {
        try {
            if (campoNome.getText().trim().isEmpty()
                    || campoEndereco.getText().trim().isEmpty()
                    || campoNota.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Preencha o nome, endereço e nota ENADE."
                );
                return;
            }

            double nota = Double.parseDouble(
                campoNota.getText().trim().replace(",", ".")
            );

            if (nota < 0 || nota > 5) {
                JOptionPane.showMessageDialog(
                    this,
                    "A nota ENADE deve estar entre 0 e 5."
                );
                return;
            }

            Instituicao instituicao = new Instituicao(
                0,
                campoNome.getText().trim(),
                campoEndereco.getText().trim(),
                campoTipo.getSelectedItem().toString(),
                nota,
                campoDescricao.getText().trim()
            );

            if (instituicaoDAO.cadastrar(instituicao)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Instituição cadastrada com sucesso!"
                );

                limparCampos();
                listarInstituicoes();

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível cadastrar a instituição."
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Digite uma nota ENADE válida."
            );
        }
    }

    // LISTAR
    private void listarInstituicoes() {
        modeloTabela.setRowCount(0);

        List<Instituicao> lista = instituicaoDAO.listarTodas();

        for (Instituicao inst : lista) {
            modeloTabela.addRow(new Object[]{
                inst.getId(),
                inst.getNome(),
                inst.getTipo(),
                inst.getNotaEnade(),
                inst.getEndereco()
            });
        }
    }

    // PREENCHER CAMPOS AO SELECIONAR UMA LINHA
    private void preencherCampos() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            return;
        }

        campoId.setText(
            modeloTabela.getValueAt(linha, 0).toString()
        );

        campoNome.setText(
            modeloTabela.getValueAt(linha, 1).toString()
        );

        campoTipo.setSelectedItem(
            modeloTabela.getValueAt(linha, 2).toString()
        );

        campoNota.setText(
            modeloTabela.getValueAt(linha, 3).toString()
        );

        campoEndereco.setText(
            modeloTabela.getValueAt(linha, 4).toString()
        );

        int id = Integer.parseInt(campoId.getText());

        for (Instituicao inst : instituicaoDAO.listarTodas()) {
            if (inst.getId() == id) {
                campoDescricao.setText(inst.getDescricao());
                break;
            }
        }
    }

    // ATUALIZAR
    private void atualizarInstituicao() {
        try {
            if (campoId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma instituição na tabela."
                );
                return;
            }

            if (campoNome.getText().trim().isEmpty()
                    || campoEndereco.getText().trim().isEmpty()
                    || campoNota.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Preencha o nome, endereço e nota ENADE."
                );
                return;
            }

            double nota = Double.parseDouble(
                campoNota.getText().trim().replace(",", ".")
            );

            if (nota < 0 || nota > 5) {
                JOptionPane.showMessageDialog(
                    this,
                    "A nota ENADE deve estar entre 0 e 5."
                );
                return;
            }

            Instituicao instituicao = new Instituicao(
                Integer.parseInt(campoId.getText()),
                campoNome.getText().trim(),
                campoEndereco.getText().trim(),
                campoTipo.getSelectedItem().toString(),
                nota,
                campoDescricao.getText().trim()
            );

            if (instituicaoDAO.atualizar(instituicao)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Instituição atualizada com sucesso!"
                );

                limparCampos();
                listarInstituicoes();

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível atualizar a instituição."
                );
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                this,
                "Verifique os dados informados."
            );
        }
    }

    // EXCLUIR
    private void excluirInstituicao() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione uma instituição na tabela."
            );
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir esta instituição?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(campoId.getText());

            if (instituicaoDAO.excluir(id)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Instituição excluída com sucesso!"
                );

                limparCampos();
                listarInstituicoes();

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível excluir a instituição."
                );
            }
        }
    }

    // LIMPAR CAMPOS
    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoEndereco.setText("");
        campoTipo.setSelectedIndex(0);
        campoNota.setText("");
        campoDescricao.setText("");
        tabela.clearSelection();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(
            () -> new TelaInstituicao().setVisible(true)
        );
    }
}

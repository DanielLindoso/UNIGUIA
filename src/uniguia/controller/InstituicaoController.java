package uniguia.controller;

import uniguia.model.Instituicao;
import uniguia.model.dao.InstituicaoDAO;
import java.util.List;

public class InstituicaoController {
    private final InstituicaoDAO dao = new InstituicaoDAO();

    public boolean salvar(String idStr, String nome, String endereco, String tipo, String notaStr, String descricao) {
        if (nome.trim().isEmpty() || endereco.trim().isEmpty()) {
            return false;
        }
        
        double nota = 0.0;
        try {
            if (!notaStr.trim().isEmpty()) {
                nota = Double.parseDouble(notaStr.replace(",", "."));
            }
        } catch (NumberFormatException e) {
            return false;
        }

        if (idStr == null || idStr.trim().isEmpty()) {
            Instituicao inst = new Instituicao(0, nome, endereco, tipo, nota, descricao);
            return dao.cadastrar(inst);
        } else {
            int id = Integer.parseInt(idStr);
            Instituicao inst = new Instituicao(id, nome, endereco, tipo, nota, descricao);
            return dao.atualizar(inst);
        }
    }

    public List<Instituicao> listar() {
        return dao.listarTodas();
    }

    public boolean remover(int id) {
        return dao.excluir(id);
    }
}
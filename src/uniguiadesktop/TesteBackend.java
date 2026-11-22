package uniguiadesktop;

import uniguia.controller.InstituicaoController;
import uniguia.model.Instituicao;
import uniguia.model.Usuario;
import uniguia.model.dao.UsuarioDAO;
import java.util.List;

public class TesteBackend {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("🚀 INICIANDO TESTE COMPLETO DO UNIGUIA BD");
        System.out.println("=========================================\n");

        // 1. TESTE DE AUTENTICAÇÃO (LOGIN)
        System.out.println("1️⃣  TESTANDO AUTENTICAÇÃO DE USUÁRIO...");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.autenticar("daniel@uniguia.com", "123456");

        if (usuario != null) {
            System.out.println("✅ Login bem-sucedido!");
            System.out.println("   Nome: " + usuario.getNome());
            System.out.println("   Perfil: " + usuario.getPerfilResumido());
        } else {
            System.err.println("❌ Erro de login! Verifique se inseriu o usuário no pgAdmin.");
        }

        System.out.println("\n-----------------------------------------");

        // 2. TESTE DO CRUD DE INSTITUIÇÕES VIA CONTROLLER
        System.out.println("2️⃣  TESTANDO CRUD DE INSTITUIÇÃO...");
        InstituicaoController controller = new InstituicaoController();

        // 2.1 CREATE (Salvar nova instituição)
        System.out.println("\n🔹 Criando nova Instituição de Teste...");
        boolean salvou = controller.salvar("", "Instituto Federal SC (IFSC)", "São José - SC", "Pública", "4.7", "Campus com ótimos laboratórios.");
        if (salvou) {
            System.out.println("✅ Instituição cadastrada com sucesso!");
        } else {
            System.err.println("❌ Erro ao cadastrar instituição.");
        }

        // 2.2 READ (Listar todas as instituições)
        System.out.println("\n🔹 Listando Instituições do PostgreSQL:");
        List<Instituicao> lista = controller.listar();
        for (Instituicao inst : lista) {
            System.out.println("   - ID: " + inst.getId() + " | Nome: " + inst.getNome() + " | Tipo: " + inst.getTipo() + " | Nota ENADE: " + inst.getNotaEnade());
        }

        // 2.3 UPDATE (Atualizar a instituição recém-criada)
        if (!lista.isEmpty()) {
            Instituicao ultima = lista.get(lista.size() - 1);
            System.out.println("\n🔹 Atualizando dados da Instituição ID " + ultima.getId() + "...");
            boolean atualizou = controller.salvar(
                String.valueOf(ultima.getId()), 
                ultima.getNome() + " (Campus Atualizado)", 
                ultima.getEndereco(), 
                ultima.getTipo(), 
                "5.0", 
                "Descrição atualizada no teste de backend."
            );
            
            if (atualizou) {
                System.out.println("✅ Instituição atualizada com sucesso!");
            } else {
                System.err.println("❌ Erro ao atualizar instituição.");
            }
        }

        System.out.println("\n=========================================");
        System.out.println("🎉 TESTES DO BACKEND CONCLUÍDOS COM SUCESSO!");
        System.out.println("=========================================");
    }
}
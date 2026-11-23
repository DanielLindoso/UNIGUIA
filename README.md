# 🎓 UniGuia Desktop — Sistema de Orientação Universitária e Gestão de IES

> **Projeto Acadêmico de Programação Orientada a Objetos (Java Desktop + PostgreSQL)**  
> **Avaliação AV2 — Prática de Linguagem de Programação I**  
> **Curso:** Tecnologia em Análise e Desenvolvimento de Sistemas (ADS) — FMP (Faculdade Municipal de Palhoça)  

---

## 📌 Sobre o Projeto

O **UniGuia Desktop** é uma aplicação Java Desktop desenvolvida para centralizar, organizar e gerenciar dados de Instituições de Ensino Superior (IES), seus cursos e métricas institucionais (como a nota ENADE). 

O sistema foi construído aplicando rigorosamente a arquitetura **MVC (Model-View-Controller)** integrada à camada **DAO (Data Access Object)**, com persistência em banco de dados relacional **PostgreSQL** via JDBC e interface gráfica desenvolvida em **Java Swing**.

---

## 🚀 Funcionalidades Principais

* 🔐 **Autenticação de Usuários:** Sistema de login seguro com validação de credenciais diretamente no banco relacional.
* 🏛️ **CRUD Completo de Instituições:**
  * **Create:** Cadastro de novas instituições com nome, endereço, tipo de IES (Pública/Privada), nota ENADE e descrição.
  * **Read:** Listagem dinâmica e atualizada de registros em componente `JTable`.
  * **Update:** Edição e atualização de dados de instituições cadastradas.
  * **Delete:** Exclusão de registros com caixa de diálogo de confirmação via `JOptionPane`.
* 🎨 **Interface Intuitiva:** Telas gráficas responsivas em Java Swing com validação de campos obrigatórios e tratamento amigável de erros.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem de Programação:** Java (JDK 17/26)
* **Interface Gráfica:** Java Swing / NetBeans GUI Builder
* **Banco de Dados:** PostgreSQL (`uniguia_db`)
* **Driver JDBC:** `postgresql-42.7.14.jar`
* **IDE Recomendada:** NetBeans IDE
* **Controle de Versão:** Git / GitHub

---

## 🧱 Arquitetura e Engenharia de Software

O projeto segue a divisão estrita de responsabilidades em camadas:

```text
uniguia/
├── controller/
│   └── InstituicaoController.java      # Mediação de regras de negócio e eventos das telas
├── model/
│   ├── Usuario.java                    # Superclasse Abstrata base
│   ├── Aluno.java                      # Subclasse herdada de Usuario (Herança e Polimorfismo)
│   ├── Instituicao.java                # Entidade central de domínio do CRUD
│   └── dao/
│       ├── ConexaoDAO.java             # Fábrica de conexões JDBC com PostgreSQL
│       ├── UsuarioDAO.java             # Persistência e autenticação de usuários
│       └── InstituicaoDAO.java         # Operações CRUD relacionais com o PostgreSQL
└── view/
    ├── TelaLogin.java                  # Tela gráfica de autenticação em Swing
    ├── TelaPrincipal.java              # Dashboard principal do sistema
    └── TelaInstituicao.java            # Formulario e JTable para gestão de IES

💎 Pilares de POO Demonstrados no Código
 * Herança (extends): A classe Aluno estende a superclasse abstrata Usuario, reaproveitando atributos de acesso (id, nome, email, senha) e adicionando atributos específicos de estudantes.
 * Encapsulamento: Atributos declarados com visibilidade private e protected, com leitura e modificação restritas através de métodos Getters e Setters.
 * Polimorfismo & Abstração (@Override): A superclasse Usuario declara o método abstrato getPerfilResumido(), que é obrigatoriamente sobrescrito pela subclasse Aluno com implementação personalizada.
 * Tratamento de Exceções & Segurança: Manipulação de exceções de banco com catch (SQLException e) e fechamento seguro de conexões através da estrutura try-with-resources.
🗄️ Estrutura do Banco de Dados (SQL)
Para rodar o projeto localmente, crie o banco de dados uniguia_db no PostgreSQL e execute o script abaixo:
CREATE DATABASE uniguia_db;

-- Tabela de Usuários
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(100) NOT NULL
);

-- Tabela de Instituições (CRUD Central)
CREATE TABLE instituicao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    nota_enade DOUBLE PRECISION,
    descricao TEXT
);

-- Tabela de Cursos
CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    modalidade VARCHAR(50),
    duracao VARCHAR(50),
    id_instituicao INT REFERENCES instituicao(id) ON DELETE CASCADE
);

⚙️ Como Executar o Projeto
 * Clonar o Repositório:
   git clone [https://github.com/DanielLindoso/UNIGUIA.git](https://github.com/DanielLindoso/UNIGUIA.git)

 * Configurar o Banco de Dados:
   * Certifique-se de que o PostgreSQL está rodando localmente na porta 5432.
   * Crie o banco uniguia_db e rode o script SQL fornecido acima.
   * Se necessário, ajuste as credenciais de usuário e senha na classe ConexaoDAO.java.
 * Abrir na IDE:
   * Abra a IDE NetBeans.
   * Vá em File > Open Project e selecione a pasta do projeto UNIGUIA.
   * Verifique se a biblioteca postgresql-42.7.14.jar está adicionada nas dependências do projeto em Libraries.
 * Executar:
   * Clique com o botão direito na classe TelaLogin.java (ou UNIGuiaDesktop.java) e escolha Run File (ou pressione Shift + F6).
👥 Integrantes da Equipe
 * Daniel Lindoso de Jesus Lacerda
 * Vitor Vieira
 * Samuel Morais
 * Isaque Morais

package uniguia.model;

public class Instituicao {
    private int id;
    private String nome;
    private String endereco;
    private String tipo;
    private double notaEnade;
    private String descricao;

    public Instituicao() {}

    public Instituicao(int id, String nome, String endereco, String tipo, double notaEnade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.notaEnade = notaEnade;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getNotaEnade() { return notaEnade; }
    public void setNotaEnade(double notaEnade) { this.notaEnade = notaEnade; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
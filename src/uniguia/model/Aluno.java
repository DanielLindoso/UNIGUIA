package uniguia.model;

public class Aluno extends Usuario {
    private String matricula;
    private String cursoMatriculado;
    private int semestre;

    public Aluno() {}

    public Aluno(int id, String nome, String email, String senha, String matricula, String cursoMatriculado, int semestre) {
        super(id, nome, email, senha);
        this.matricula = matricula;
        this.cursoMatriculado = cursoMatriculado;
        this.semestre = semestre;
    }

    @Override
    public String getPerfilResumido() {
        return "Aluno: " + nome + " | Curso: " + cursoMatriculado;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getCursoMatriculado() { return cursoMatriculado; }
    public void setCursoMatriculado(String cursoMatriculado) { this.cursoMatriculado = cursoMatriculado; }
    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }
}
package model;

/**
 * Padrão COMPOSITE - Folha.
 *
 * O morador é o elemento mais simples da hierarquia: não possui filhos.
 */
public class Morador implements ComponenteCondominio {

    private String nome;
    private String cpf;
    private String rg;
    private char sexo;
    private int idade;
    private double renda;

    public Morador(String nome, String cpf, String rg, char sexo, int idade, double renda) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.sexo = Character.toUpperCase(sexo);
        this.idade = idade;
        this.renda = renda;
    }

    @Override
    public void exibir(int nivel) {
        System.out.printf("%s- Morador: %s | CPF: %s | RG: %s | Sexo: %c | Idade: %d | Renda: R$ %.2f%n",
                ComponenteCondominio.recuo(nivel), nome, cpf, rg, sexo, idade, renda);
    }

    @Override
    public int getQuantidadeMoradores() {
        return 1;
    }

    @Override
    public double getRendaTotal() {
        return renda;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getRg() { return rg; }
    public char getSexo() { return sexo; }
    public int getIdade() { return idade; }
    public double getRenda() { return renda; }

    public void setNome(String nome) { this.nome = nome; }
    public void setRg(String rg) { this.rg = rg; }
    public void setSexo(char sexo) { this.sexo = Character.toUpperCase(sexo); }
    public void setIdade(int idade) { this.idade = idade; }
    public void setRenda(double renda) { this.renda = renda; }
}

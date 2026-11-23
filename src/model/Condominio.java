package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Padrão COMPOSITE - Composto raiz (contém edifícios).
 */
public class Condominio implements ComponenteCondominio {

    private int id;
    private String nome;
    private String cidade;
    private String bairro;
    private final List<Edificio> edificios = new ArrayList<>();

    public Condominio(int id, String nome, String cidade, String bairro) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.bairro = bairro;
    }

    public void adicionarEdificio(Edificio edificio) {
        if (buscarEdificio(edificio.getNumero()) != null) {
            throw new IllegalArgumentException("Já existe o edifício " + edificio.getNumero() + " neste condomínio.");
        }
        edificios.add(edificio);
    }

    /** Remove o edifício (e, por composição, seus apartamentos e moradores). */
    public boolean removerEdificio(int numero) {
        return edificios.removeIf(e -> e.getNumero() == numero);
    }

    /** Busca um edifício pela sua identificação: o número. */
    public Edificio buscarEdificio(int numero) {
        for (Edificio e : edificios) {
            if (e.getNumero() == numero) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void exibir(int nivel) {
        System.out.printf("%s- Condomínio %d: %s | %s, %s | Edifícios: %d | Moradores: %d%n",
                ComponenteCondominio.recuo(nivel), id, nome, bairro, cidade,
                edificios.size(), getQuantidadeMoradores());
        for (Edificio e : edificios) {
            e.exibir(nivel + 1);
        }
    }

    @Override
    public int getQuantidadeMoradores() {
        int total = 0;
        for (Edificio e : edificios) {
            total += e.getQuantidadeMoradores();
        }
        return total;
    }

    @Override
    public double getRendaTotal() {
        double total = 0;
        for (Edificio e : edificios) {
            total += e.getRendaTotal();
        }
        return total;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public List<Edificio> getEdificios() { return edificios; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setBairro(String bairro) { this.bairro = bairro; }
}

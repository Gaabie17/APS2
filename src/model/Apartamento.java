package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Padrão COMPOSITE - Composto (contém moradores).
 *
 * Classe abstrata: só existem apartamentos Padrão ou Luxo.
 * Um apartamento é identificado, dentro do edifício, pelo número e pelo andar.
 */
public abstract class Apartamento implements ComponenteCondominio {

    private int numero;
    private int andar;
    private final List<Morador> moradores = new ArrayList<>();

    protected Apartamento(int numero, int andar) {
        this.numero = numero;
        this.andar = andar;
    }

    /** Nome do tipo do apartamento ("Padrão" ou "Luxo"). */
    public abstract String getTipo();

    /** Detalhes específicos de cada tipo de apartamento. */
    protected abstract String getDetalhes();

    public void adicionarMorador(Morador morador) {
        for (Morador m : moradores) {
            if (m.getCpf().equals(morador.getCpf())) {
                throw new IllegalArgumentException("Já existe um morador com o CPF " + morador.getCpf() + " neste apartamento.");
            }
        }
        moradores.add(morador);
    }

    public Morador buscarMorador(String cpf) {
        for (Morador m : moradores) {
            if (m.getCpf().equals(cpf)) {
                return m;
            }
        }
        return null;
    }

    public boolean removerMorador(String cpf) {
        return moradores.removeIf(m -> m.getCpf().equals(cpf));
    }

    @Override
    public void exibir(int nivel) {
        System.out.printf("%s- Apartamento %d (andar %d) | %s | %s | Renda média: R$ %.2f%n",
                ComponenteCondominio.recuo(nivel), numero, andar, getTipo(), getDetalhes(), getRendaMedia());
        if (moradores.isEmpty()) {
            System.out.println(ComponenteCondominio.recuo(nivel + 1) + "(sem moradores)");
        }
        for (Morador m : moradores) {
            m.exibir(nivel + 1);
        }
    }

    @Override
    public int getQuantidadeMoradores() {
        int total = 0;
        for (Morador m : moradores) {
            total += m.getQuantidadeMoradores();
        }
        return total;
    }

    @Override
    public double getRendaTotal() {
        double total = 0;
        for (Morador m : moradores) {
            total += m.getRendaTotal();
        }
        return total;
    }

    public int getNumero() { return numero; }
    public int getAndar() { return andar; }
    public List<Morador> getMoradores() { return moradores; }
}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Padrão COMPOSITE - Composto (contém apartamentos).
 *
 * Um edifício é identificado, dentro do condomínio, pelo seu número.
 */
public class Edificio implements ComponenteCondominio {

    private int numero;
    private final List<String> coresPintura;
    private int quantidadeAndares;
    private final List<Apartamento> apartamentos = new ArrayList<>();

    public Edificio(int numero, List<String> coresPintura, int quantidadeAndares) {
        this.numero = numero;
        this.coresPintura = new ArrayList<>(coresPintura);
        this.quantidadeAndares = quantidadeAndares;
    }

    public void adicionarApartamento(Apartamento apartamento) {
        if (apartamento.getAndar() < 1 || apartamento.getAndar() > quantidadeAndares) {
            throw new IllegalArgumentException("O edifício " + numero + " tem apenas " + quantidadeAndares + " andares.");
        }
        if (buscarApartamento(apartamento.getNumero(), apartamento.getAndar()) != null) {
            throw new IllegalArgumentException("Já existe o apartamento " + apartamento.getNumero()
                    + " no andar " + apartamento.getAndar() + " deste edifício.");
        }
        apartamentos.add(apartamento);
    }

    /** Remove o apartamento (e, por composição, seus moradores). */
    public boolean removerApartamento(int numero, int andar) {
        return apartamentos.removeIf(a -> a.getNumero() == numero && a.getAndar() == andar);
    }

    /** Busca um apartamento pela sua identificação: número + andar. */
    public Apartamento buscarApartamento(int numero, int andar) {
        for (Apartamento a : apartamentos) {
            if (a.getNumero() == numero && a.getAndar() == andar) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void exibir(int nivel) {
        System.out.printf("%s- Edifício %d | Cores: %s | Andares: %d | Apartamentos: %d%n",
                ComponenteCondominio.recuo(nivel), numero, String.join(", ", coresPintura),
                quantidadeAndares, apartamentos.size());
        for (Apartamento a : apartamentos) {
            a.exibir(nivel + 1);
        }
    }

    @Override
    public int getQuantidadeMoradores() {
        int total = 0;
        for (Apartamento a : apartamentos) {
            total += a.getQuantidadeMoradores();
        }
        return total;
    }

    @Override
    public double getRendaTotal() {
        double total = 0;
        for (Apartamento a : apartamentos) {
            total += a.getRendaTotal();
        }
        return total;
    }

    public int getNumero() { return numero; }
    public List<String> getCoresPintura() { return coresPintura; }
    public int getQuantidadeAndares() { return quantidadeAndares; }
    public List<Apartamento> getApartamentos() { return apartamentos; }

    public void setCoresPintura(List<String> cores) {
        coresPintura.clear();
        coresPintura.addAll(cores);
    }

    /** Altera a quantidade de andares sem deixar apartamentos acima do último andar. */
    public void setQuantidadeAndares(int quantidadeAndares) {
        if (quantidadeAndares < 1) {
            throw new IllegalArgumentException("O edifício precisa ter ao menos 1 andar.");
        }
        for (Apartamento a : apartamentos) {
            if (a.getAndar() > quantidadeAndares) {
                throw new IllegalArgumentException("Há o apartamento " + a.getNumero() + " no andar "
                        + a.getAndar() + "; o edifício não pode ter menos andares que isso.");
            }
        }
        this.quantidadeAndares = quantidadeAndares;
    }
}

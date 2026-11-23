package model;

/**
 * Padrão COMPOSITE - Componente.
 *
 * Interface comum a todos os elementos da hierarquia
 * Condomínio -> Edifício -> Apartamento -> Morador.
 * Permite tratar um elemento simples (Morador) e um elemento composto
 * (Condomínio, Edifício, Apartamento) da mesma forma.
 */
public interface ComponenteCondominio {

    /** Mostra o componente (e seus filhos) no terminal, com recuo pelo nível. */
    void exibir(int nivel);

    /** Quantidade de moradores contidos neste componente. */
    int getQuantidadeMoradores();

    /** Soma das rendas dos moradores contidos neste componente. */
    double getRendaTotal();

    /** Renda média dos moradores contidos neste componente. */
    default double getRendaMedia() {
        int quantidade = getQuantidadeMoradores();
        return quantidade == 0 ? 0 : getRendaTotal() / quantidade;
    }

    /** Monta o recuo usado na exibição em árvore. */
    static String recuo(int nivel) {
        return "    ".repeat(nivel);
    }
}

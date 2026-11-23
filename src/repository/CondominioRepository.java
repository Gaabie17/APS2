package repository;

import model.Condominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Padrão SINGLETON.
 *
 * Repositório único, em memória, de todos os condomínios do sistema.
 * Garante que todas as telas do sistema trabalhem sobre os mesmos dados.
 */
public final class CondominioRepository {

    private static CondominioRepository instancia;

    private final List<Condominio> condominios = new ArrayList<>();

    /** Construtor privado: ninguém fora da classe consegue criar outra instância. */
    private CondominioRepository() {
    }

    /** Ponto de acesso global à única instância. */
    public static synchronized CondominioRepository getInstancia() {
        if (instancia == null) {
            instancia = new CondominioRepository();
        }
        return instancia;
    }

    public void adicionar(Condominio condominio) {
        if (buscarPorId(condominio.getId()) != null) {
            throw new IllegalArgumentException("Já existe um condomínio com o número " + condominio.getId() + ".");
        }
        condominios.add(condominio);
    }

    /** Remove o condomínio (e, por composição, toda a sua estrutura). */
    public boolean remover(int id) {
        return condominios.removeIf(c -> c.getId() == id);
    }

    public Condominio buscarPorId(int id) {
        for (Condominio c : condominios) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Condominio> listarTodos() {
        return Collections.unmodifiableList(condominios);
    }
}

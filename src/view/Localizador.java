package view;

import model.Apartamento;
import model.Condominio;
import model.Edificio;
import repository.CondominioRepository;

/**
 * Casos de uso incluídos (<<include>>):
 *   UC07 Localizar condomínio
 *   UC08 Localizar edifício    (inclui UC07)
 *   UC09 Localizar apartamento (inclui UC08)
 *
 * Lança IllegalArgumentException quando o elemento não existe.
 */
public class Localizador {

    private final CondominioRepository repositorio = CondominioRepository.getInstancia();
    private final Entrada entrada;

    public Localizador(Entrada entrada) {
        this.entrada = entrada;
    }

    // UC07 - Localizar condomínio
    public Condominio localizarCondominio() {
        int id = entrada.lerInteiro("Número do condomínio: ");
        Condominio condominio = repositorio.buscarPorId(id);
        if (condominio == null) {
            throw new IllegalArgumentException("Condomínio " + id + " não encontrado.");
        }
        return condominio;
    }

    // UC08 - Localizar edifício (inclui UC07)
    public Edificio localizarEdificio() {
        return localizarEdificio(localizarCondominio());
    }

    /** Localiza o edifício dentro de um condomínio já escolhido. */
    public Edificio localizarEdificio(Condominio condominio) {
        int numero = entrada.lerInteiro("Número do edifício: ");
        Edificio edificio = condominio.buscarEdificio(numero);
        if (edificio == null) {
            throw new IllegalArgumentException("Edifício " + numero + " não encontrado.");
        }
        return edificio;
    }

    // UC09 - Localizar apartamento (inclui UC08)
    public Apartamento localizarApartamento() {
        return localizarApartamento(localizarEdificio());
    }

    /** Localiza o apartamento dentro de um edifício já escolhido. */
    public Apartamento localizarApartamento(Edificio edificio) {
        int numero = entrada.lerInteiro("Número do apartamento: ");
        int andar = entrada.lerInteiro("Andar do apartamento: ");
        Apartamento apartamento = edificio.buscarApartamento(numero, andar);
        if (apartamento == null) {
            throw new IllegalArgumentException("Apartamento " + numero + " (andar " + andar + ") não encontrado.");
        }
        return apartamento;
    }
}

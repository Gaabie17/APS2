package view;

import model.Condominio;
import model.Edificio;

/**
 * UC02 - Manter edifício (inclui UC07 Localizar condomínio).
 */
public class MenuEdificio extends MenuCadastro {

    public MenuEdificio(Entrada entrada, Localizador localizador) {
        super("Manter edifício", entrada, localizador);
    }

    @Override
    protected void incluir() {
        Condominio condominio = localizador.localizarCondominio();
        int numero = entrada.lerInteiro("Número do edifício: ");
        String cores = entrada.lerTexto("Cores da pintura (separadas por vírgula): ");
        int andares = entrada.lerInteiro("Quantidade de andares: ");
        if (andares < 1) {
            throw new IllegalArgumentException("O edifício precisa ter ao menos 1 andar.");
        }
        condominio.adicionarEdificio(new Edificio(numero, separarLista(cores), andares));
        System.out.println("Edifício incluído.");
    }

    @Override
    protected void consultar() {
        localizador.localizarEdificio().exibir(0);
    }

    @Override
    protected void alterar() {
        Edificio edificio = localizador.localizarEdificio();
        System.out.println("(Enter mantém o valor atual)");
        String cores = entrada.lerTexto("Cores da pintura", String.join(", ", edificio.getCoresPintura()));
        int andares = entrada.lerInteiro("Quantidade de andares", edificio.getQuantidadeAndares());
        edificio.setQuantidadeAndares(andares);
        edificio.setCoresPintura(separarLista(cores));
        System.out.println("Edifício alterado.");
    }

    @Override
    protected void excluir() {
        Condominio condominio = localizador.localizarCondominio();
        Edificio edificio = localizador.localizarEdificio(condominio);
        if (confirmar("Excluir o edifício " + edificio.getNumero() + " com todos os seus apartamentos e moradores?")) {
            condominio.removerEdificio(edificio.getNumero());
            System.out.println("Edifício excluído.");
        }
    }
}

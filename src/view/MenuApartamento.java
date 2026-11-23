package view;

import factory.ApartamentoFactory;
import factory.TipoApartamento;
import model.Apartamento;
import model.ApartamentoLuxo;
import model.ApartamentoPadrao;
import model.Edificio;

/**
 * UC03 - Manter apartamento (inclui UC08 Localizar edifício).
 * A inclusão usa a FACTORY para criar o apartamento Padrão ou Luxo.
 */
public class MenuApartamento extends MenuCadastro {

    public MenuApartamento(Entrada entrada, Localizador localizador) {
        super("Manter apartamento", entrada, localizador);
    }

    @Override
    protected void incluir() {
        Edificio edificio = localizador.localizarEdificio();
        int numero = entrada.lerInteiro("Número do apartamento: ");
        int andar = entrada.lerInteiro("Andar: ");
        int tipo = entrada.lerInteiro("Tipo (1 - Padrão, 2 - Luxo): ");

        Apartamento apartamento;
        if (tipo == 1) {
            String armarios = entrada.lerTexto("Tipo de armários: ");
            String pisos = entrada.lerTexto("Tipo de pisos: ");
            apartamento = ApartamentoFactory.criar(TipoApartamento.PADRAO, numero, andar, armarios, pisos);
        } else if (tipo == 2) {
            String luminarias = entrada.lerTexto("Modelos de luminárias (separados por vírgula): ");
            boolean geladeira = entrada.lerSimNao("Geladeira embutida? (s/n): ");
            boolean fogao = entrada.lerSimNao("Fogão embutido? (s/n): ");
            apartamento = ApartamentoFactory.criar(TipoApartamento.LUXO, numero, andar,
                    luminarias, geladeira ? "s" : "n", fogao ? "s" : "n");
        } else {
            throw new IllegalArgumentException("Tipo inválido.");
        }
        edificio.adicionarApartamento(apartamento);
        System.out.println("Apartamento " + apartamento.getTipo() + " incluído.");
    }

    @Override
    protected void consultar() {
        localizador.localizarApartamento().exibir(0);
    }

    /** Altera os dados próprios do tipo; número e andar identificam o apartamento e não mudam. */
    @Override
    protected void alterar() {
        Apartamento apartamento = localizador.localizarApartamento();
        System.out.println("(Enter mantém o valor atual)");
        if (apartamento instanceof ApartamentoPadrao) {
            ApartamentoPadrao padrao = (ApartamentoPadrao) apartamento;
            padrao.setTipoArmarios(entrada.lerTexto("Tipo de armários", padrao.getTipoArmarios()));
            padrao.setTipoPisos(entrada.lerTexto("Tipo de pisos", padrao.getTipoPisos()));
        } else if (apartamento instanceof ApartamentoLuxo) {
            ApartamentoLuxo luxo = (ApartamentoLuxo) apartamento;
            String luminarias = entrada.lerTexto("Modelos de luminárias", String.join(", ", luxo.getModelosLuminarias()));
            luxo.setModelosLuminarias(separarLista(luminarias));
            luxo.setGeladeiraEmbutida(entrada.lerSimNao("Geladeira embutida? (s/n)", luxo.isGeladeiraEmbutida()));
            luxo.setFogaoEmbutido(entrada.lerSimNao("Fogão embutido? (s/n)", luxo.isFogaoEmbutido()));
        }
        System.out.println("Apartamento alterado.");
    }

    @Override
    protected void excluir() {
        Edificio edificio = localizador.localizarEdificio();
        Apartamento apartamento = localizador.localizarApartamento(edificio);
        if (confirmar("Excluir o apartamento " + apartamento.getNumero() + " com todos os seus moradores?")) {
            edificio.removerApartamento(apartamento.getNumero(), apartamento.getAndar());
            System.out.println("Apartamento excluído.");
        }
    }
}

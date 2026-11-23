package factory;

import model.Apartamento;
import model.ApartamentoLuxo;
import model.ApartamentoPadrao;

import java.util.List;

/**
 * Padrão FACTORY (Simple Factory / Factory Method estático).
 *
 * Centraliza a criação dos apartamentos. Quem cadastra um apartamento
 * informa apenas o tipo desejado e os dados; não precisa conhecer as
 * classes concretas ApartamentoPadrao e ApartamentoLuxo.
 */
public final class ApartamentoFactory {

    private ApartamentoFactory() {
        // classe utilitária: não deve ser instanciada
    }

    /**
     * Cria um apartamento do tipo informado.
     *
     * @param tipo    PADRAO ou LUXO
     * @param numero  número do apartamento
     * @param andar   andar do apartamento
     * @param dados   dados específicos do tipo:
     *                PADRAO -> [tipoArmarios, tipoPisos]
     *                LUXO   -> [luminárias separadas por vírgula, geladeira (s/n), fogão (s/n)]
     */
    public static Apartamento criar(TipoApartamento tipo, int numero, int andar, String... dados) {
        switch (tipo) {
            case PADRAO:
                exigirDados(dados, 2, "tipo de armários e tipo de pisos");
                return new ApartamentoPadrao(numero, andar, dados[0], dados[1]);
            case LUXO:
                exigirDados(dados, 3, "luminárias, geladeira embutida e fogão embutido");
                List<String> luminarias = List.of(dados[0].split("\\s*,\\s*"));
                boolean geladeira = dados[1].trim().equalsIgnoreCase("s");
                boolean fogao = dados[2].trim().equalsIgnoreCase("s");
                return new ApartamentoLuxo(numero, andar, luminarias, geladeira, fogao);
            default:
                throw new IllegalArgumentException("Tipo de apartamento desconhecido: " + tipo);
        }
    }

    private static void exigirDados(String[] dados, int quantidade, String descricao) {
        if (dados.length < quantidade) {
            throw new IllegalArgumentException("Informe: " + descricao + ".");
        }
    }
}

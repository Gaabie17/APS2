package view;

import model.Condominio;
import repository.CondominioRepository;

/**
 * UC01 - Manter condomínio.
 */
public class MenuCondominio extends MenuCadastro {

    private final CondominioRepository repositorio = CondominioRepository.getInstancia();

    public MenuCondominio(Entrada entrada, Localizador localizador) {
        super("Manter condomínio", entrada, localizador);
    }

    @Override
    protected void incluir() {
        int id = entrada.lerInteiro("Número de identificação: ");
        String nome = entrada.lerTexto("Nome: ");
        String cidade = entrada.lerTexto("Cidade: ");
        String bairro = entrada.lerTexto("Bairro: ");
        repositorio.adicionar(new Condominio(id, nome, cidade, bairro));
        System.out.println("Condomínio incluído.");
    }

    @Override
    protected void consultar() {
        localizador.localizarCondominio().exibir(0);
    }

    @Override
    protected void alterar() {
        Condominio condominio = localizador.localizarCondominio();
        System.out.println("(Enter mantém o valor atual)");
        condominio.setNome(entrada.lerTexto("Nome", condominio.getNome()));
        condominio.setCidade(entrada.lerTexto("Cidade", condominio.getCidade()));
        condominio.setBairro(entrada.lerTexto("Bairro", condominio.getBairro()));
        System.out.println("Condomínio alterado.");
    }

    @Override
    protected void excluir() {
        Condominio condominio = localizador.localizarCondominio();
        if (confirmar("Excluir o condomínio " + condominio.getNome()
                + " com todos os seus edifícios, apartamentos e moradores?")) {
            repositorio.remover(condominio.getId());
            System.out.println("Condomínio excluído.");
        }
    }
}

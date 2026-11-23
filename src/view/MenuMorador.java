package view;

import model.Apartamento;
import model.Morador;

/**
 * UC04 - Manter morador (inclui UC09 Localizar apartamento).
 * O morador é identificado, dentro do apartamento, pelo CPF.
 */
public class MenuMorador extends MenuCadastro {

    public MenuMorador(Entrada entrada, Localizador localizador) {
        super("Manter morador", entrada, localizador);
    }

    @Override
    protected void incluir() {
        Apartamento apartamento = localizador.localizarApartamento();
        String nome = entrada.lerTexto("Nome: ");
        String cpf = entrada.lerTexto("CPF: ");
        String rg = entrada.lerTexto("RG: ");
        char sexo = entrada.lerSexo("Sexo (M/F): ");
        int idade = entrada.lerInteiro("Idade: ");
        double renda = entrada.lerDecimal("Renda (R$): ");
        apartamento.adicionarMorador(new Morador(nome, cpf, rg, sexo, idade, renda));
        System.out.println("Morador incluído.");
    }

    @Override
    protected void consultar() {
        localizarMorador(localizador.localizarApartamento()).exibir(0);
    }

    /** Altera os dados do morador; o CPF identifica o morador e não muda. */
    @Override
    protected void alterar() {
        Morador morador = localizarMorador(localizador.localizarApartamento());
        System.out.println("(Enter mantém o valor atual)");
        morador.setNome(entrada.lerTexto("Nome", morador.getNome()));
        morador.setRg(entrada.lerTexto("RG", morador.getRg()));
        morador.setSexo(entrada.lerSexo("Sexo (M/F)", morador.getSexo()));
        morador.setIdade(entrada.lerInteiro("Idade", morador.getIdade()));
        morador.setRenda(entrada.lerDecimal("Renda (R$)", morador.getRenda()));
        System.out.println("Morador alterado.");
    }

    @Override
    protected void excluir() {
        Apartamento apartamento = localizador.localizarApartamento();
        Morador morador = localizarMorador(apartamento);
        if (confirmar("Excluir o morador " + morador.getNome() + "?")) {
            apartamento.removerMorador(morador.getCpf());
            System.out.println("Morador excluído.");
        }
    }

    private Morador localizarMorador(Apartamento apartamento) {
        String cpf = entrada.lerTexto("CPF do morador: ");
        Morador morador = apartamento.buscarMorador(cpf);
        if (morador == null) {
            throw new IllegalArgumentException("Nenhum morador com o CPF " + cpf + " neste apartamento.");
        }
        return morador;
    }
}

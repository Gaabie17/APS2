package view;

import model.Apartamento;
import model.Condominio;
import repository.CondominioRepository;

/**
 * Menu principal do sistema. Cada opção corresponde a um caso de uso:
 *   1 a 4 abrem os submenus "Manter" (UC01 a UC04);
 *   5 e 6 são consultas (UC05 e UC06).
 */
public class Menu {

    private final CondominioRepository repositorio = CondominioRepository.getInstancia();
    private final Entrada entrada = new Entrada();
    private final Localizador localizador = new Localizador(entrada);

    private final MenuCadastro menuCondominio = new MenuCondominio(entrada, localizador);
    private final MenuCadastro menuEdificio = new MenuEdificio(entrada, localizador);
    private final MenuCadastro menuApartamento = new MenuApartamento(entrada, localizador);
    private final MenuCadastro menuMorador = new MenuMorador(entrada, localizador);

    public void iniciar() {
        int opcao;
        do {
            mostrarOpcoes();
            opcao = entrada.lerInteiro("Escolha uma opção: ");
            System.out.println();
            try {
                executar(opcao);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println();
            }
        } while (opcao != 0);
    }

    private void mostrarOpcoes() {
        System.out.println("===== CONTROLE DE CONDOMÍNIOS =====");
        System.out.println("1 - Manter condomínios");
        System.out.println("2 - Manter edifícios");
        System.out.println("3 - Manter apartamentos");
        System.out.println("4 - Manter moradores");
        System.out.println("5 - Consultar renda média de um apartamento");
        System.out.println("6 - Listar condomínios");
        System.out.println("0 - Sair");
    }

    private void executar(int opcao) {
        switch (opcao) {
            case 1: menuCondominio.executar(); break;
            case 2: menuEdificio.executar(); break;
            case 3: menuApartamento.executar(); break;
            case 4: menuMorador.executar(); break;
            case 5: consultarRendaMedia(); break;
            case 6: listarCondominios(); break;
            case 0: System.out.println("Encerrando o sistema."); break;
            default: System.out.println("Opção inválida.\n");
        }
    }

    // UC05 - Consultar renda média do apartamento (inclui UC09; usa o COMPOSITE)
    private void consultarRendaMedia() {
        System.out.println("--- Renda média do apartamento ---");
        Apartamento apartamento = localizador.localizarApartamento();
        System.out.printf("Moradores: %d | Renda total: R$ %.2f | Renda média: R$ %.2f%n%n",
                apartamento.getQuantidadeMoradores(), apartamento.getRendaTotal(), apartamento.getRendaMedia());
    }

    // UC06 - Listar condomínios (usa o COMPOSITE para percorrer a árvore)
    private void listarCondominios() {
        System.out.println("--- Condomínios cadastrados ---");
        if (repositorio.listarTodos().isEmpty()) {
            System.out.println("Nenhum condomínio cadastrado.");
        }
        for (Condominio c : repositorio.listarTodos()) {
            c.exibir(0);
        }
        System.out.println();
    }
}

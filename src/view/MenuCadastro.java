package view;

/**
 * Base dos casos de uso "Manter": todo cadastro oferece
 * incluir, consultar, alterar e excluir.
 */
public abstract class MenuCadastro {

    protected final Entrada entrada;
    protected final Localizador localizador;
    private final String titulo;

    protected MenuCadastro(String titulo, Entrada entrada, Localizador localizador) {
        this.titulo = titulo;
        this.entrada = entrada;
        this.localizador = localizador;
    }

    /** Mostra o submenu até o usuário escolher "Voltar". */
    public void executar() {
        int opcao;
        do {
            System.out.println("--- " + titulo + " ---");
            System.out.println("1 - Incluir");
            System.out.println("2 - Consultar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Voltar");
            opcao = entrada.lerInteiro("Escolha uma opção: ");
            System.out.println();
            try {
                switch (opcao) {
                    case 1: incluir(); break;
                    case 2: consultar(); break;
                    case 3: alterar(); break;
                    case 4: excluir(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        } while (opcao != 0);
    }

    protected abstract void incluir();

    protected abstract void consultar();

    protected abstract void alterar();

    protected abstract void excluir();

    /** Pede confirmação antes de uma exclusão. */
    protected boolean confirmar(String mensagem) {
        boolean confirmado = entrada.lerSimNao(mensagem + " (s/n): ");
        if (!confirmado) {
            System.out.println("Exclusão cancelada.");
        }
        return confirmado;
    }

    /** Converte "Branco, Azul" em uma lista de itens. */
    protected static java.util.List<String> separarLista(String texto) {
        return java.util.List.of(texto.split("\\s*,\\s*"));
    }
}

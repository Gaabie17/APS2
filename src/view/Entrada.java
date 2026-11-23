package view;

import java.util.Scanner;

/**
 * Leitura de dados do teclado com validação simples.
 */
public class Entrada {

    private final Scanner scanner = new Scanner(System.in);

    public String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  Valor obrigatório.");
        }
    }

    public int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("  Digite um número inteiro.");
            }
        }
    }

    public double lerDecimal(String mensagem) {
        while (true) {
            try {
                return Double.parseDouble(lerTexto(mensagem).replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("  Digite um valor numérico (ex.: 2500,00).");
            }
        }
    }

    public char lerSexo(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem).toUpperCase();
            if (texto.equals("M") || texto.equals("F")) {
                return texto.charAt(0);
            }
            System.out.println("  Digite M ou F.");
        }
    }

    public boolean lerSimNao(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem).toLowerCase();
            if (texto.equals("s")) return true;
            if (texto.equals("n")) return false;
            System.out.println("  Digite s ou n.");
        }
    }

    // ----- leituras para ALTERAR: Enter vazio mantém o valor atual -----

    /** Mostra o valor atual entre colchetes; devolve null se o usuário só apertar Enter. */
    private String lerOpcional(String mensagem, Object atual) {
        System.out.print(mensagem + " [" + atual + "]: ");
        String texto = scanner.nextLine().trim();
        return texto.isEmpty() ? null : texto;
    }

    public String lerTexto(String mensagem, String atual) {
        String texto = lerOpcional(mensagem, atual);
        return texto == null ? atual : texto;
    }

    public int lerInteiro(String mensagem, int atual) {
        while (true) {
            String texto = lerOpcional(mensagem, atual);
            if (texto == null) return atual;
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("  Digite um número inteiro.");
            }
        }
    }

    public double lerDecimal(String mensagem, double atual) {
        while (true) {
            String texto = lerOpcional(mensagem, String.format("%.2f", atual));
            if (texto == null) return atual;
            try {
                return Double.parseDouble(texto.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("  Digite um valor numérico (ex.: 2500,00).");
            }
        }
    }

    public char lerSexo(String mensagem, char atual) {
        while (true) {
            String texto = lerOpcional(mensagem, atual);
            if (texto == null) return atual;
            texto = texto.toUpperCase();
            if (texto.equals("M") || texto.equals("F")) return texto.charAt(0);
            System.out.println("  Digite M ou F.");
        }
    }

    public boolean lerSimNao(String mensagem, boolean atual) {
        while (true) {
            String texto = lerOpcional(mensagem, atual ? "s" : "n");
            if (texto == null) return atual;
            texto = texto.toLowerCase();
            if (texto.equals("s")) return true;
            if (texto.equals("n")) return false;
            System.out.println("  Digite s ou n.");
        }
    }
}

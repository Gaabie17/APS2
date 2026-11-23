package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Apartamento luxo: possui modelos de luminárias e pode ou não ter
 * geladeira e fogão embutidos nos armários.
 */
public class ApartamentoLuxo extends Apartamento {

    private final List<String> modelosLuminarias;
    private boolean geladeiraEmbutida;
    private boolean fogaoEmbutido;

    public ApartamentoLuxo(int numero, int andar, List<String> modelosLuminarias,
                           boolean geladeiraEmbutida, boolean fogaoEmbutido) {
        super(numero, andar);
        this.modelosLuminarias = new ArrayList<>(modelosLuminarias);
        this.geladeiraEmbutida = geladeiraEmbutida;
        this.fogaoEmbutido = fogaoEmbutido;
    }

    @Override
    public String getTipo() {
        return "Luxo";
    }

    @Override
    protected String getDetalhes() {
        return "Luminárias: " + String.join(", ", modelosLuminarias)
                + " | Geladeira embutida: " + (geladeiraEmbutida ? "sim" : "não")
                + " | Fogão embutido: " + (fogaoEmbutido ? "sim" : "não");
    }

    public List<String> getModelosLuminarias() { return modelosLuminarias; }
    public boolean isGeladeiraEmbutida() { return geladeiraEmbutida; }
    public boolean isFogaoEmbutido() { return fogaoEmbutido; }

    public void setModelosLuminarias(List<String> modelos) {
        modelosLuminarias.clear();
        modelosLuminarias.addAll(modelos);
    }
    public void setGeladeiraEmbutida(boolean geladeiraEmbutida) { this.geladeiraEmbutida = geladeiraEmbutida; }
    public void setFogaoEmbutido(boolean fogaoEmbutido) { this.fogaoEmbutido = fogaoEmbutido; }
}

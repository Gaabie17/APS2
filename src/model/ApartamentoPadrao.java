package model;

/**
 * Apartamento padrão: guarda o tipo de armários e o tipo de pisos.
 */
public class ApartamentoPadrao extends Apartamento {

    private String tipoArmarios;
    private String tipoPisos;

    public ApartamentoPadrao(int numero, int andar, String tipoArmarios, String tipoPisos) {
        super(numero, andar);
        this.tipoArmarios = tipoArmarios;
        this.tipoPisos = tipoPisos;
    }

    @Override
    public String getTipo() {
        return "Padrão";
    }

    @Override
    protected String getDetalhes() {
        return "Armários: " + tipoArmarios + " | Pisos: " + tipoPisos;
    }

    public String getTipoArmarios() { return tipoArmarios; }
    public String getTipoPisos() { return tipoPisos; }

    public void setTipoArmarios(String tipoArmarios) { this.tipoArmarios = tipoArmarios; }
    public void setTipoPisos(String tipoPisos) { this.tipoPisos = tipoPisos; }
}

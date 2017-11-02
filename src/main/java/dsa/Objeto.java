package dsa;

public class Objeto {

    public String nombreObjeto;
    public String tipoObjeto;
    public String descripcionObjeto;
    public int valorObjeto;
    public int costeObjeto;

    public Objeto (String nombreObjeto, String tipoObjeto, String descripcionObjeto, int valorObjeto, int costeObjeto){
        this.nombreObjeto = nombreObjeto;
        this.tipoObjeto = tipoObjeto;
        this.descripcionObjeto = descripcionObjeto;
        this.valorObjeto = valorObjeto;
        this.costeObjeto = costeObjeto;
    }

    public Objeto(){}


    public String getNombreObjeto() { return nombreObjeto; }

    public void setNombreObjeto(String nombreObjeto) { this.nombreObjeto = nombreObjeto; }

    public String getTipoObjeto() { return tipoObjeto; }

    public void setTipoObjeto(String tipoObjeto) { this.tipoObjeto = tipoObjeto; }

    public String getDescripcionObjeto() { return descripcionObjeto; }

    public void setDescripcionObjeto(String descripcionObjeto) { this.descripcionObjeto = descripcionObjeto; }

    public int getValorObjeto() { return valorObjeto; }

    public void setValorObjeto(int valorObjeto) { this.valorObjeto = valorObjeto; }

    public int getCosteObjeto() { return costeObjeto; }

    public void setCosteObjeto(int costeObjeto) { this.costeObjeto = costeObjeto; }



    public boolean objetoEsIgual(Objeto o) {

        boolean resp = false;

        if (o.nombreObjeto == this.nombreObjeto && o.tipoObjeto == this.tipoObjeto && o.descripcionObjeto == this.descripcionObjeto && o.valorObjeto == this.valorObjeto && o.costeObjeto == this.costeObjeto) {
            resp = true;
        }

        return resp;
    }



}

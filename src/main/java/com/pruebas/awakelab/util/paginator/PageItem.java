package com.pruebas.awakelab.util.paginator;

public class PageItem {

    private Integer numero;
    private Boolean actual;

    public PageItem(Integer numero, Boolean actual) {
        this.numero = numero;
        this.actual = actual;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }
}

package com.pruebas.awakelab.util.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int numElemPorPaginas;
    private int paginaActual;
    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page){
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<PageItem>();

        totalPaginas = page.getTotalPages();
        numElemPorPaginas = page.getSize();
        paginaActual = page.getNumber() + 1;

        int desde,hasta;
        if (totalPaginas <= numElemPorPaginas){
            desde = 1;
            hasta = totalPaginas;
        }else {
            if (paginaActual <= numElemPorPaginas/2){
                desde = 1;
                hasta = numElemPorPaginas;

            }else if (paginaActual >= totalPaginas - numElemPorPaginas/2){
                desde = totalPaginas - numElemPorPaginas + 1;
                hasta = numElemPorPaginas;

            }else {
                desde = paginaActual - numElemPorPaginas/2;
                hasta = numElemPorPaginas;
            }
        }

        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde + i, paginaActual == desde + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirst(){
        return  page.isFirst();
    }

    public boolean isLast(){
        return  page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }

}


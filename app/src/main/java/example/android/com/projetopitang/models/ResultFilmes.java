package example.android.com.projetopitang.models;

import java.util.List;

public class ResultFilmes {

    public String total_pages;
    public List<Filme> results;

    public List<Filme> getFilmes() {
        return results;
    }

    public void setListFilmes(List<Filme> listFilmes) {
        this.results = listFilmes;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public List<Filme> getResults() {
        return results;
    }

    public void setResults(List<Filme> results) {
        this.results = results;
    }
}
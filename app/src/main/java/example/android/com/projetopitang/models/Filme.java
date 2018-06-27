package example.android.com.projetopitang.models;

import java.util.List;

public class Filme {

    public String id, title, original_title, poster_path, release_date, overview, runtime;

    public Filme(String id, String title, String original_title, String poster_path,
                 String release_date, String overview, String runtime){
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.overview = overview;
        this.runtime = runtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /*
    public void formataData(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse("23/11/2015", formato);
        System.out.println(data);
    } */

}

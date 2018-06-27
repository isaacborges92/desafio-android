package example.android.com.projetopitang.comunicacao;

import example.android.com.projetopitang.models.Filme;
import example.android.com.projetopitang.models.ResultFilmes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestApi {

    @GET("top_rated")
    Call<ResultFilmes> getTopRated(@Query("api_key") String api_key,
                                   @Query("language") String language,
                                   @Query("page") String page);

    @GET("{id}")
    Call<Filme> getDetails (@Path("id") String idFilme,
                            @Query("api_key") String api_key,
                            @Query("language") String language);

}

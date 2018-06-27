package example.android.com.projetopitang.comunicacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import example.android.com.projetopitang.models.Filme;
import retrofit2.Response;

public class RetrofitAsyncTaskDetalhes extends AsyncTask<ParamRequest, Void, Filme> {

    private GetDetailsFilme getDetailsFilme;
    private Context context;

    public RetrofitAsyncTaskDetalhes(RetrofitAsyncTaskDetalhes.GetDetailsFilme listener, Context context){
        this.getDetailsFilme = listener;
        this.context = context;
    }

    @Override
    protected Filme doInBackground(ParamRequest... param) {

        ParamRequest parameter = param[0];
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        RequestApi api = retrofitConfig.getRequestApi();

        try {
            Response<Filme> response =
                    api.getDetails( parameter.idFilme,
                            parameter.api_key,
                            parameter.language).execute();

            if (response.body() != null) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Filme filme) {
        super.onPostExecute(filme);

        if (getDetailsFilme != null) {
            getDetailsFilme.onDetailsResult(filme);
        }

    }

    public interface GetDetailsFilme{
        void onDetailsResult(Filme filme);
    }
}

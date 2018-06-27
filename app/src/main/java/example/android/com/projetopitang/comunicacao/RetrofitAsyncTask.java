package example.android.com.projetopitang.comunicacao;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import example.android.com.projetopitang.MainActivity;
import example.android.com.projetopitang.models.Filme;
import example.android.com.projetopitang.models.ResultFilmes;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAsyncTask extends AsyncTask<ParamRequest, Void, List<Filme>> {

    private Context context;
    private GetFilmesListener getFilmesListener;

   public RetrofitAsyncTask(GetFilmesListener listener, Context context){
        this.getFilmesListener = listener;
        this.context = context;
   }

    @Override
    protected List<Filme> doInBackground(ParamRequest... param) {

        RetrofitConfig retrofitConfig = new RetrofitConfig();
        RequestApi api = retrofitConfig.getRequestApi();
        ParamRequest parameter = param[0];

        try {
            Response<ResultFilmes> response =
                    api.getTopRated(parameter.api_key,
                            parameter.language,
                            parameter.page).execute();

            if (response.body() != null) {
                MainActivity.total_paginas = response.body().getTotal_pages();
                return response.body().getFilmes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Filme> filmes){
        super.onPostExecute(filmes);

        //Tratar retorno nulo

        //Retornar a lista de artigos
        if (getFilmesListener != null) {
            getFilmesListener.onFilmesResult(filmes);
        }

    }

    public interface GetFilmesListener {
        void onFilmesResult(List<Filme> filmes);
    }
}

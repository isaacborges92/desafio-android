package example.android.com.projetopitang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.android.com.projetopitang.comunicacao.ParamRequest;
import example.android.com.projetopitang.comunicacao.RetrofitAsyncTask;
import example.android.com.projetopitang.models.Filme;
import example.android.com.projetopitang.models.ResultFilmes;

public class MainActivity extends AppCompatActivity implements RetrofitAsyncTask.GetFilmesListener {

    public static String total_paginas;
    private RecyclerView recyclerView;
    public static ResultFilmes resultFilmes;
    private Adapter adapterFilmes;
    private ParamRequest paramRequest;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paramRequest = new ParamRequest(this);
        paramRequest.page = String.valueOf(1);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler); //Pegando referência da recyclerView
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        recyclerView.addOnItemTouchListener(
                new RecyclerViewItemClickListener(getApplicationContext(), new RecyclerViewItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
                        intent.putExtra("idFilme", resultFilmes.getFilmes().get(position).getId());

                        startActivity(intent);
                    }
                })
        );


        recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener() {
            @Override
            public void onLoadMore() {
                addDataToList();
            }
        });

        if(resultFilmes == null) {
            progressBar.setVisibility(View.VISIBLE);
            RetrofitAsyncTask task = new RetrofitAsyncTask(this, this);
            task.execute(paramRequest);
        }else{
            progressBar.setVisibility(View.GONE);
            adapterFilmes = new Adapter(resultFilmes.getFilmes(), this);
            recyclerView.setAdapter(adapterFilmes);
        }
    }

    private void addDataToList() {

        paramRequest.page = String.valueOf(Integer.valueOf(paramRequest.page) + 1); //Atualiza o valor da página nos parâmetros

        progressBar.setVisibility(View.VISIBLE);
        RetrofitAsyncTask task = new RetrofitAsyncTask(this, this);
        task.execute(paramRequest);
    }


    @Override
    public void onFilmesResult(List<Filme> filmes) {
        if (filmes != null) {

            if(resultFilmes == null) {
                resultFilmes = new ResultFilmes();
                resultFilmes.setListFilmes(filmes);
                adapterFilmes = new Adapter(resultFilmes.getFilmes(), this);
                recyclerView.setAdapter(adapterFilmes);
            }else{
                resultFilmes.results.addAll(filmes);
                adapterFilmes.notifyDataSetChanged();
            }
        }

        progressBar.setVisibility(View.GONE);
    }

}

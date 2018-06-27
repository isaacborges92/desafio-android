package example.android.com.projetopitang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import example.android.com.projetopitang.comunicacao.ParamRequest;
import example.android.com.projetopitang.comunicacao.RetrofitAsyncTaskDetalhes;
import example.android.com.projetopitang.models.Filme;

public class DetalhesActivity extends AppCompatActivity implements RetrofitAsyncTaskDetalhes.GetDetailsFilme {

    private String idFilme;
    private ImageView imgFilme;
    private TextView txtTitulo, txtDescricao;
    private Filme filme;
    private ParamRequest paramRequest;

    private static final String url_base_imagem = "http://image.tmdb.org/t/p/w342/";

    private void configuraActivity(Filme filme) {
        String posterPath = filme.getPoster_path();

        Picasso.get()
                .load(url_base_imagem + posterPath)
                .fit()
                .into(imgFilme);

        txtTitulo.setText(filme.getTitle());
        txtDescricao.setText(filme.getOverview());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        getSupportActionBar().setTitle(getResources().getString(R.string.detalhes_filme));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idFilme = extras.getString("idFilme");
        }

        imgFilme = (ImageView) findViewById(R.id.imgFilme);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);

        paramRequest = new ParamRequest(this);
        paramRequest.idFilme = getIntent().getStringExtra("idFilme");

        RetrofitAsyncTaskDetalhes task = new RetrofitAsyncTaskDetalhes(this, this);
        task.execute(paramRequest);
    }

    @Override
    public void onDetailsResult(Filme filme) {
        if (filme != null) {
            this.filme = filme;
            configuraActivity(filme);
        }
    }

    private String preparaInformacoes(){
       return filme.title + "\n\n" + filme.overview;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, filme.getTitle());
                sendIntent.putExtra(Intent.EXTRA_TEXT, preparaInformacoes());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.compartilhar)));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

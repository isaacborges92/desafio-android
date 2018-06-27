package example.android.com.projetopitang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.android.com.projetopitang.models.Filme;

public class Adapter extends RecyclerView.Adapter {

    private List<Filme> filmes;
    private Context context;

    public Adapter(List<Filme> filmes, Context context) {
        this.filmes = filmes;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_filme, parent, false); //Infla a view

        MeuViewHolder holder = new MeuViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        MeuViewHolder holder = (MeuViewHolder) viewHolder;
        Filme filme  = filmes.get(position) ;

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w300/" + filme.getPoster_path())
                .error(R.drawable.ic_error_black_24dp)
                .fit()
                .centerCrop()
                .into(holder.imgFilme);

        holder.titulo.setText(filme.getTitle());
        holder.data.setText(context.getResources().getString(R.string.lancamento) + " " + filme.getRelease_date());
    }

    @Override
    public int getItemCount() { //Quantidade de itens
        return filmes.size();
    }
}


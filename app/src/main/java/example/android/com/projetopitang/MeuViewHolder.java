package example.android.com.projetopitang;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MeuViewHolder extends RecyclerView.ViewHolder {

    public final ImageView imgFilme;
    public TextView titulo, data;

    public MeuViewHolder(View view) {
        super(view);

        imgFilme = (ImageView) view.findViewById(R.id.imgFilme);
        titulo = (TextView) view.findViewById(R.id.txtTitulo);
        data = (TextView) view.findViewById(R.id.txtData);
    }

}
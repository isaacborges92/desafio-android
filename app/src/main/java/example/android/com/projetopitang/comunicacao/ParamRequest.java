package example.android.com.projetopitang.comunicacao;

import android.content.Context;

import example.android.com.projetopitang.R;

public class ParamRequest {
    public String idFilme;
    public String language;
    public String page;
    public String api_key;

    public ParamRequest(Context context){
        this.language = context.getResources().getString(R.string.linguagem);
        this.api_key  = "e087efa69ec3f733e1c3c8cb21c4c442";
    }

}

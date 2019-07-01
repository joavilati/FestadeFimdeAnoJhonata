package com.example.festadefimdeano.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPrefences {
    // Pode ser pensar nele como se fosse um banco de dados, porém não é um banco de dados sómente é similar, é usado para armazenar dados simples que são dados que têm que ser de fácil acesso na aplicação.

    private SharedPreferences mSharedPreferences;

    public SecurityPrefences(Context mContext){

        // Pega uma instancia, Context.MODE_PRIVATE diz que nenhum app de fora da aplicação pode pedar estes dados
        this.mSharedPreferences = mContext.getSharedPreferences("FestaFimDeAno",Context.MODE_PRIVATE);

    }
    public  void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key, value ).apply();
    }

    public  String getStoredString(String key){
        return this.mSharedPreferences.getString(key,"");
    }
}

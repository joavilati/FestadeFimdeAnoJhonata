package com.example.festadefimdeano.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festadefimdeano.R;
import com.example.festadefimdeano.constant.FimDeAnoConstants;
import com.example.festadefimdeano.data.SecurityPrefences;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

        private ViewHolder mViewHolder = new ViewHolder(); //criada instanância da classe ViewHoder
        private SecurityPrefences mSecurityPreferences; //criada instancia da classe SecurityPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mSecurityPreferences = new SecurityPrefences(this); // Atribuído dentro do Oncreate por que ele da o Contexto, caso seja feito fora ocorrerá um erro, que só se mostrará durante a aplicação

        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);

        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.LoadDataFromActivity();


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_participate) {

            if (this.mViewHolder.checkParticipate.isChecked()) {
                // Salvar  a presença
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY,FimDeAnoConstants.CONFIRMATION_YES);

            } else{
               //Salvar a ausência
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY,FimDeAnoConstants.CONFIRMATION_NO);
            }
        }
    }

    // Puxa e testa os Extras para que a confirmação de presença seja preenchida automaticamente
    private void LoadDataFromActivity(){

        // O getExtras retorna uma variável do tipo Bundle
        Bundle extras = getIntent().getExtras();

        // Se extra diferente de null
        if(extras!= null){

            // A variavel presenca recebe a string retornada por extras.getString("Aqui vai a chave pertencente a String buscada")
            String presenca = extras.getString(FimDeAnoConstants.PRESENCE_KEY);

            // Se presenca for diferende de nulo E presenca for igual a 'CONFIRMATION_YES'
            if(presenca!=null && presenca.equals(FimDeAnoConstants.CONFIRMATION_YES)){

                // CheckBox preenchido
                this.mViewHolder.checkParticipate.setChecked(true);

            } else {

                //checkBox vazio
                this.mViewHolder.checkParticipate.setChecked(false);
            }

        }

    }

    private static class ViewHolder{

        CheckBox checkParticipate;

    }

}

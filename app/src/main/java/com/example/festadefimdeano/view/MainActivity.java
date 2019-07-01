package com.example.festadefimdeano.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festadefimdeano.R;
import com.example.festadefimdeano.constant.FimDeAnoConstants;
import com.example.festadefimdeano.data.SecurityPrefences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Cria instânncia da Classe ViewHolder(criada dentro da classe MainActivity
    private ViewHolder mViewHolder = new ViewHolder();

    // Pega formatação de Data "dd/MM//YYYY"
    private static SimpleDateFormat SIMPLE_DATE_FORMAT =  new SimpleDateFormat("dd/MM/yyyy");

    // Cria instância da Classe SecurityPreferences
    private SecurityPrefences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Método que inicia o app
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Preenche o objeto textToday(criado na classe ViewHolder) com o objeto do xml referente a Id text_today
        this.mViewHolder.textToday = findViewById(R.id.text_today);

        // Preenche o objeto textDaysLeftView(criado na classe ViewHolder) com o objeto do xml referente a Id text_today
        this.mViewHolder.textDaysLeftView = findViewById(R.id.text_days_left);

        // Preenche o objeto buttonConfirm(criado na classe ViewHolder) com o objeto do xml referente a Id button_confirm
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        // Criado dentro do Oncreate por que ele da o Contexto, caso seja feito fora ocorrerá um erro, que só mostrará durante a aplicação
        mSecurityPreferences = new SecurityPrefences(this);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        // Datas
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        this.mViewHolder.textDaysLeftView.setText(String.valueOf(getDaysLeft()));




    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_confirm) {

            // Pegando a string dentro da SharedPreferences onde a chave é PRESENCE_KEY, e colocando dentro da String presenca
            String presenca = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);

            // Criando um intenção para um componente expecífico nesse caso a Classe Details(Especificar se for classe com .class)
            Intent intent = new Intent(this,DetailsActivity.class);

            // Insere na tranferencia de Activity informações extras
            intent.putExtra(FimDeAnoConstants.CONFIRMATION_YES, presenca);

            // Inicia a classe DetailsAcvitivity
            startActivity(intent);
        }
    }

    private void verifyPresence(){ // Não confirmado, sim, não


        // atribui à presença a string guardada(StoredString) pelo SharedPreferences(SecurityPreferences)
        String presenca = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.PRESENCE_KEY);

        // Se a String presença esteja vazia nao_confirmado
        if (presenca.equals("")) {
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);

        } else if(presenca.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirm.setText(R.string.sim);

        } else {
            this.mViewHolder.buttonConfirm.setText(R.string.não);

        }
    }


    private int getDaysLeft(){
        // Data de hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);
        // Dia máximo do ano
        // [1-365]
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return (dayMax - today);
    }

    public static class ViewHolder{
        TextView textToday;
        TextView textDaysLeftView;
        Button buttonConfirm;

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}

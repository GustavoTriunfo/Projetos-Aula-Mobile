package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
Button btnValidar, btnLimpar;
RadioGroup opcoesPerguntaUm, opcoesPerguntaDois, opcoesPerguntaTres;
Integer pontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnValidar = (Button)findViewById(R.id.botaoValidar);
        btnLimpar = (Button)findViewById(R.id.botaoLimpar);

        opcoesPerguntaUm = findViewById(R.id.opcoesPrimeiraPergunta);
        opcoesPerguntaDois = findViewById(R.id.opcoesSegundaPergunta);
        opcoesPerguntaTres = findViewById(R.id.opcoesTerceiraPergunta);

        btnLimpar.setOnClickListener(view -> {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Atenção");
            alert.setMessage("Deseja remover os itens selecionados?");
            alert.setIcon(R.mipmap.ic_launcher);


            alert.setNegativeButton("Não", null);


            alert.setPositiveButton("Sim", (dialog, which) -> {

                opcoesPerguntaUm.clearCheck();
                opcoesPerguntaDois.clearCheck();
                opcoesPerguntaTres.clearCheck();

                Toast.makeText(this, "Seleções removidas!", Toast.LENGTH_SHORT).show();
            });

            alert.show();
        });

        btnValidar.setOnClickListener(v -> {
            this.verificarRespostas();
            Toast.makeText(this, "Sua pontuação foi: " + pontuacao.toString(), Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void verificarRespostas() {

        int selectedId1 = opcoesPerguntaUm.getCheckedRadioButtonId();
        int selectedId2 = opcoesPerguntaDois.getCheckedRadioButtonId();
        int selectedId3 = opcoesPerguntaTres.getCheckedRadioButtonId();


        if (selectedId1 == -1 || selectedId2 == -1 || selectedId3 == -1) {

            Toast.makeText(this, "Você acertou " + pontuacao + " de 3 perguntas!", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRadioButton1 = findViewById(selectedId1);
        RadioButton selectedRadioButton2 = findViewById(selectedId2);
        RadioButton selectedRadioButton3 = findViewById(selectedId3);

        boolean resposta1Correta = selectedRadioButton1.getId() == R.id.radioButtonSteve;
        boolean resposta2Correta = selectedRadioButton2.getId() == R.id.radioButtonOvelha;
        boolean resposta3Correta = selectedRadioButton3.getId() == R.id.radioButtonVillager;

        int acertos = 0;
        if (resposta1Correta) acertos++;
        if (resposta2Correta) acertos++;
        if (resposta3Correta) acertos++;
        pontuacao = acertos;
        if(pontuacao != 3){
            if(!resposta1Correta){
                Toast.makeText(this, "Você errou a primeira pergunta... é outro nome!", Toast.LENGTH_SHORT).show();
            }if(!resposta2Correta){
                Toast.makeText(this, "Você errou a segunda pergunta... é outro nome!", Toast.LENGTH_SHORT).show();
            }if(!resposta3Correta){
                Toast.makeText(this, "Você errou a terceira pergunta... é outro nome!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


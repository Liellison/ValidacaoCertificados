package com.caatuern.liellison;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.caatuern.liellison.myapplication.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etMatricula;
    TextView tvInforme;
    Button btVerificacao;
    String suporte, crip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMatricula = (EditText) findViewById(R.id.etMatricula);
        tvInforme = (TextView) findViewById(R.id.tvInforme);
        btVerificacao = (Button) findViewById(R.id.btVerificacao);
        btVerificacao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        suporte = etMatricula.getText().toString();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(suporte.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte aByte : bytes) {
                stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            crip = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        tvInforme.setText(crip);

    }
}

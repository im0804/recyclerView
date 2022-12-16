package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements recyclerViewInterface {
    Button btnUp;
    TextView tvN, tvSn;
    EditText etD, etX;
    Switch sw;
    RecyclerView recyView;
    String [] listtext = new String[20];
    double num1, numD, numX, sum;
    String str;
    recyclerAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnUp = (Button) findViewById(R.id.btnUp);
        tvN = (TextView) findViewById(R.id.tvN);
        tvSn = (TextView) findViewById(R.id.tvSN);
        etD = (EditText) findViewById(R.id.etD);
        etX = (EditText) findViewById(R.id.etX);
        sw = findViewById(R.id.sw);
        recyView = (RecyclerView) findViewById(R.id.recyView);
        etD.setHint("sequence");
        etX.setHint("first number");

        }


    public void update(View view) {
        if (etD.getText().toString().trim().equals("")){
            Toast.makeText(this, "please insert a sequence", Toast.LENGTH_LONG).show();
        }
        else
            if (etX.getText().toString().trim().equals("")){
            Toast.makeText(this, "please insert a number", Toast.LENGTH_LONG).show();
        }
            else {
                tvSn.setText("");
                tvN.setText("");
                numD = Double.parseDouble(etD.getText().toString());
                numX = Double.parseDouble(etX.getText().toString());

                if (sw.isChecked()) {
                    for (int i = 0; i < 20; i++) {
                        num1 = numX * Math.pow(numD, i);
                        str = String.valueOf(num1);
                        if (str.contains("E")) {
                            NumberFormat num = new DecimalFormat();
                            num = new DecimalFormat("0.####E0");
                            listtext[i] = num.format(num1);
                        } else {
                            listtext[i] = str;
                        }
                    }
                } else {
                    for (int i = 0; i < 20; i++) {
                        num1 = numX + (i) * numD;
                        str = String.valueOf(num1);
                        if (str.contains("E")) {
                            NumberFormat num = new DecimalFormat();
                            num = new DecimalFormat("0.####E0");
                            listtext[i] = num.format(num1);
                        } else {
                            listtext[i] = str;
                        }
                    }
                }

                adp = new recyclerAdapter(getApplicationContext(), listtext, this);
                recyView.setAdapter(adp);
            }
    }

    @Override
    public void onItemClick(int position) {
        tvN.setText("" + (position+1));
        sum = 0;
        if (sw.isChecked()){
            for (int i = 0; i <= position; i++) {
                sum += Double.parseDouble(listtext[i]);
            }
        }
        else {
            for (int i = 0; i <= position; i++) {
                sum += Double.parseDouble(listtext[i]);
            }
        }
        str = String.valueOf(sum);
        if (str.contains("E")){
            NumberFormat num = new DecimalFormat();
            num = new DecimalFormat("0.####E0");
             tvSn.setText(num.format(sum));
        }
        else{
            tvSn.setText(str);
        }
    }
}
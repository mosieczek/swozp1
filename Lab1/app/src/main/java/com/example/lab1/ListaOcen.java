package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaOcen extends AppCompatActivity {
    ArrayList<ModelOceny> listaOcen = new ArrayList<ModelOceny>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ocen);

        Bundle bundleIn = getIntent().getExtras();
        final int ileOcen = bundleIn.getInt("ileOcen");

        for(int i = 0; i < ileOcen; i++) {
            ModelOceny ocena = new ModelOceny("ocena " + (i + 1));
            ocena.setOcena(2);
            listaOcen.add(ocena);
        }

        final InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, listaOcen);
        ListView listaOcen = findViewById(R.id.listaOcen);
        listaOcen.setAdapter(adapter);

        findViewById(R.id.buttonOut).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundleOut = new Bundle();
                        bundleOut.putFloat("srednia", getAverage());
                        Intent intentOut = new Intent();
                        intentOut.putExtras(bundleOut);
                        setResult(RESULT_OK, intentOut);

                        finish();
                    }
                }
        );

    }
    //przy obróceniu urządzenia
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int[] tablicaOcen = new int[listaOcen.size()];
        for (int i=0; i < listaOcen.size(); i++) {
            tablicaOcen[i] = listaOcen.get(i).getOcena();
        }
        //zapisz tablice
        outState.putIntArray("oceny", tablicaOcen);
        super.onSaveInstanceState(outState);
    }
    //po obróceniu urządzenia
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //pobierz tablice z ocenami
        int[] tablicaOcen = savedInstanceState.getIntArray("oceny");
        for (int i = 0; i < listaOcen.size(); i++) {
            listaOcen.get(i).setOcena(tablicaOcen[i]);
        }
        //wywołaj adapter
        final InteraktywnyAdapterTablicy adapter = new InteraktywnyAdapterTablicy(this, listaOcen);
        ListView listaOcen1 = findViewById(R.id.listaOcen);
        listaOcen1.setAdapter(adapter);
    }
    //oblicz średnią z ocen zawaratych w ArrayList z obiektami modelu
    protected float getAverage() {
        float avg = 0, gradeSum = 0;
        for (int i = 0; i < listaOcen.size(); i++) {
            gradeSum += listaOcen.get(i).getOcena();

        }
        avg = gradeSum / (float) listaOcen.size();

        return avg;
    }
}

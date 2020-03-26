package com.example.lab1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class InteraktywnyAdapterTablicy extends ArrayAdapter<ModelOceny> {
    private List<ModelOceny> listaOcen;
    private Activity kontekst;

    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen){
        super(kontekst, R.layout.oceny, listaOcen);

        this.listaOcen = listaOcen;
        this.kontekst = kontekst;
    }

    @NonNull
    @Override
    public View getView(final int numerWiersza, View widok2, ViewGroup parent){
        View widok = null;
        if (widok2 == null) {
            LayoutInflater layIF = this.kontekst.getLayoutInflater();
            widok = layIF.inflate(R.layout.oceny, null);
            widok.setVisibility(View.VISIBLE);

            RadioGroup grupaOceny = widok.findViewById(R.id.ocenyRadioGroup);
            final View finalWidok = widok;
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            //odczytanie z etykiety który obiekt modelu przechowuje dane o zmienionej ocenie
                            ModelOceny element = (ModelOceny) group.getTag();
                            //zapisanie zmienionej oceny
                            RadioButton checked = finalWidok.findViewById(group.getCheckedRadioButtonId());
                            element.setOcena(Integer.valueOf(checked.getText().toString()));

                        }
                    }
            );
            //powiązanie grupy przycisków z obiektem w modelu
            grupaOceny.setTag(listaOcen.get(numerWiersza));


        }
        //aktualizacja istniejącego wiersza
        else {
            widok = widok2;
            RadioGroup grupaOceny = widok.findViewById(R.id.ocenyRadioGroup);
            //powiązanie grupy przycisków z obiektem w modelu
            grupaOceny.setTag(listaOcen.get(numerWiersza));
            ModelOceny element = (ModelOceny) grupaOceny.getTag();
        }

        TextView etykieta = widok.findViewById(R.id.etykieta);
        //ustawienie tekstu etykiety na podstawie modelu
        etykieta.setText(listaOcen.get(numerWiersza).getNazwa());

        RadioGroup grupaOceny = widok.findViewById(R.id.ocenyRadioGroup);
        //zaznaczenie odpowiedniego przycisku na podstawie modelu
        switch (listaOcen.get(numerWiersza).getOcena()) {
            case 2:
                grupaOceny.check(R.id.ocena2Przycisk);
                break;

            case 3:
                grupaOceny.check(R.id.ocena3Przycisk);
                break;

            case 4:
                grupaOceny.check(R.id.ocena4Przycisk);
                break;

            case 5:
                grupaOceny.check(R.id.ocena5Przycisk);
                break;
        }
        return widok;
    }
}

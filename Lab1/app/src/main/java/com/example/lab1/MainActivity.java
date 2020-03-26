package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    float srednia = 0;
     EditText editText1, editText2, liczbaOcen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        walidacja();
        generateOnClickListener();
    }
    //wyświetlenie toasta Stringiem
    public void showToast(String toast_message) {
        Toast toast = Toast.makeText(this, toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }
    //walidacja pól editText1, editText2 i liczbaOcenInput
    public void walidacja() {
        generateNameListeners();
        generateLiczbaOcenListener();
    }
    //sprawdzenie czy editText1, editText2 i liczbaOcenInput są poprawne
    public boolean isDataOk() {
        editText1 = findViewById(R.id.imieInput);
        editText2 = findViewById(R.id.nazwiskoInput);
        liczbaOcen = findViewById(R.id.liczbaOcenInput);
        Pattern pattern = Pattern.compile("[A-ZŁŚĄĘÓŻŹĆŃ][a-ząęółśżźćń]+");

        if (liczbaOcen.getText().toString().isEmpty()) {
        } else if (Integer.valueOf(liczbaOcen.getText().toString())< 5 || Integer.valueOf(liczbaOcen.getText().toString()) >15 ){
        } else {
            liczbaOcen.setBackgroundColor(0x00FF00);
            if(!editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()) {
                if(pattern.matcher(editText1.getText().toString()).matches() && pattern.matcher(editText2.getText().toString()).matches() ){
                    findViewById(R.id.przyciskOceny).setVisibility(View.VISIBLE);
                    return true;
                }
            }
        }
        return false;
    }
    //listenery dla pól editText1 i editText2 (imię i nazwisko)
    //imię i nazwisko zaczynają się wielką literą, co najmniej jedna wielka i jedna mała, tylko litery
    public void generateNameListeners() {
        editText1 = findViewById(R.id.imieInput);
        editText2 = findViewById(R.id.nazwiskoInput);

        editText1.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Pattern pattern = Pattern.compile("[A-ZŁŚĄĘÓŻŹĆŃ][a-ząęółśżźćń]+");
                        if(editText1.getText().toString().isEmpty()){
                            showToast("Proszę wpisać imię");
                            editText1.setBackgroundColor(Color.RED);
                            findViewById(R.id.przyciskOceny).setVisibility(View.INVISIBLE);
                        }else if(!pattern.matcher(editText1.getText().toString()).matches()) {
                            showToast("Imię powinno rozpoczynać się wielką literą i zawierać co najmniej 2 litery");
                            editText1.setBackgroundColor(Color.RED);
                            findViewById(R.id.przyciskOceny).setVisibility(View.INVISIBLE);
                        }else {
                            editText1.setBackgroundColor(0x00FF00);
                            if (isDataOk()) {
                                findViewById(R.id.liczbaOcenInput).setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
        );
        editText2.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Pattern pattern = Pattern.compile("[A-ZŁŚĄĘÓŻŹĆŃ][a-ząęółśżźćń]+");
                        if(editText2.getText().toString().isEmpty()){
                            showToast("Proszę wpisać nazwisko");
                            editText2.setBackgroundColor(Color.RED);
                            findViewById(R.id.przyciskOceny).setVisibility(View.INVISIBLE);
                        } else if(!pattern.matcher(editText2.getText().toString()).matches()) {
                            showToast("Nazwisko powinno rozpoczynać się wielką literą i zawierać co najmniej 2 litery");
                            editText2.setBackgroundColor(Color.RED);
                            findViewById(R.id.przyciskOceny).setVisibility(View.INVISIBLE);
                        }else {
                            editText2.setBackgroundColor(0x00FF00);
                            if (isDataOk()) {
                                findViewById(R.id.liczbaOcenInput).setVisibility(View.VISIBLE);
                            }
                        }

                    }
                }
        );

    }
    //listenery dla pola z liczbą ocen
    //liczba ocen musi być z przedziału od 5 do 15
    public void generateLiczbaOcenListener() {
        final EditText liczbaOcen = findViewById(R.id.liczbaOcenInput);
        liczbaOcen.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(liczbaOcen.getText().toString().isEmpty()) {
                            showToast("Wprowadź liczbę ocen");
                            liczbaOcen.setBackgroundColor(Color.RED);
                            findViewById(R.id.przyciskOceny).setVisibility(View.INVISIBLE);
                        } else if (Integer.valueOf(liczbaOcen.getText().toString()) < 5 || Integer.valueOf(liczbaOcen.getText().toString()) > 15  ) {
                            showToast("Ilość ocen musi być z przedziału (5,15)");
                            liczbaOcen.setBackgroundColor(Color.RED);
                            findViewById(R.id.przyciskOceny).setVisibility(View.INVISIBLE);
                        } else {
                            liczbaOcen.setBackgroundColor(0x00FF00);
                            if (isDataOk()) {
                                findViewById(R.id.przyciskOceny).setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
        );
    }
    //listener dla przycisku
    public void generateOnClickListener () {
       findViewById(R.id.przyciskOceny).setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       EditText liczbaOcen = findViewById(R.id.liczbaOcenInput);
                       if(!isDataOk()) {
                           showToast("Wprowadź poprawne wartości!");
                       }
                       if(!liczbaOcen.getText().toString().isEmpty() && isDataOk()) {
                           Intent listaOcen = new Intent(MainActivity.this, ListaOcen.class);
                           int ileOcen = Integer.valueOf(liczbaOcen.getText().toString());
                           listaOcen.putExtra("ileOcen", ileOcen);
                           int kodZadania = 1;
                           startActivityForResult(listaOcen, kodZadania);
                       }
                   }
               }
       );
    }
    //wyświetlenie średniej i komunikaty końcowe
    @Override
    protected void onActivityResult(int kodZadania, int kodWyniku, @Nullable Intent dane) {
        super.onActivityResult(kodZadania, kodWyniku, dane);
        super.onActivityResult(kodZadania, kodWyniku, dane);
        if (kodWyniku == RESULT_OK) {
            //pobranie sredniej ListaOcen
            Bundle bundle = dane.getExtras();
            this.srednia = bundle.getFloat("srednia");
            TextView sredOcen = findViewById(R.id.sredniaOcen);
            sredOcen.setText("Twoja średnia to: " + srednia);
            sredOcen.setVisibility(View.VISIBLE);
            Button przyciskWyjscia = findViewById(R.id.przyciskOceny);
            findViewById(R.id.przyciskOceny).setVisibility(View.VISIBLE);
            //wyłączenie textEditów
            findViewById(R.id.imieInput).setEnabled(false);
            findViewById(R.id.nazwiskoInput).setEnabled(false);
            findViewById(R.id.liczbaOcenInput).setEnabled(false);

            if (srednia >= 3.0) {
                przyciskWyjscia.setText("Super :)");
                przyciskWyjscia.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                showToast("Gratulacje! otrzymujesz zaliczenie!");
                            }
                        }
                );
            } else {
                przyciskWyjscia.setText("Tym razem mi nie poszło.");
                przyciskWyjscia.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                showToast("Wysyłam podanie o zaliczenie warunkowe");
                            }
                        }
                );
            }
        }
    }
    //obrót urządzenia
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putFloat("srednia", this.srednia);
        super.onSaveInstanceState(outState);
    }
    //po obrocie urządzenia
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.srednia = savedInstanceState.getFloat("srednia");
        walidacja();
        Button przyciskWyjscia = findViewById(R.id.przyciskOceny);

        findViewById(R.id.przyciskOceny).setVisibility(View.VISIBLE);
        findViewById(R.id.imieInput).setEnabled(false);
        findViewById(R.id.nazwiskoInput).setEnabled(false);
        findViewById(R.id.liczbaOcenInput).setEnabled(false);

        if (srednia >= 3.0) {
            przyciskWyjscia.setText("Super :)");
            przyciskWyjscia.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            showToast("Gratulacje! otrzymujesz zaliczenie!");
                        }
                    }
            );
        } else {
            przyciskWyjscia.setText("Tym razem mi nie poszło.");
            przyciskWyjscia.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                            showToast("Wysyłam podanie o zaliczenie warunkowe");
                        }
                    }
            );
        }

        }

}


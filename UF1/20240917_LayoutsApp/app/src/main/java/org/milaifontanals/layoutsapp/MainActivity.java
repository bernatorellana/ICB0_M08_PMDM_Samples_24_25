package org.milaifontanals.layoutsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.milaifontanals.layoutsapp.model.Persona;
import org.milaifontanals.layoutsapp.model.Provincia;
import org.milaifontanals.layoutsapp.model.Sexe;
import org.milaifontanals.layoutsapp.utils.MyTextWatcher;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

// Views del layout
    private ImageView imgPhoto;

    private EditText edtNom;
    private EditText edtCognoms;
    private EditText edtNIF;
    private RadioGroup rdgSexe;

    private RadioButton rdoH;
    private RadioButton rdoD;
    private RadioButton rdoA;
    private Spinner spnProvincies;
    private LinearLayout llyNext;
    private LinearLayout llyPrev;



    private int indexPersonatgeActual = 0;
    private ArrayAdapter<Provincia> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creacioIds();
        showCurrentUser();
        programarListeners();
        omplirSpinner();
    }

    private void omplirSpinner() {

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,Provincia.getProvincies());
        spnProvincies.setAdapter(adapter);
    }

    private void programarListeners() {
    /*llyNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // codi del click
        }
    });*/
        llyNext.setOnClickListener(view -> {
            //codi del click
            this.indexPersonatgeActual++;
            this.indexPersonatgeActual =
                    this.indexPersonatgeActual%Persona.getPersones().size();
            showCurrentUser();
        });
        llyPrev.setOnClickListener(view -> {
            //codi del click
            this.indexPersonatgeActual--;
            if(this.indexPersonatgeActual<0) {
                this.indexPersonatgeActual = Persona.getPersones().size() - 1;
            }
            showCurrentUser();
        });

        edtNIF.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                    NIF_TextChangedListener(editable);
            }
        });
        edtNom.addTextChangedListener( new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Nom_TextChangedListener(editable);
            }
        });
        edtCognoms.addTextChangedListener( new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Cognom_TextChangedListener(editable);
            }
        });

        rdgSexe.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            Persona actual = Persona.getPersones().get(indexPersonatgeActual);

            HashMap<Integer, Sexe> mapaSexe = new HashMap<>();
            mapaSexe.put(R.id.rdoA, Sexe.ALTRES);
            mapaSexe.put(R.id.rdoH, Sexe.HOME);
            mapaSexe.put(R.id.rdoD, Sexe.DONA);

            actual.setSexe(mapaSexe.get(checkedId));
        });
    }

    private void Cognom_TextChangedListener(Editable editable) {
        Persona actual = Persona.getPersones().get(indexPersonatgeActual);
        actual.setCognoms(editable.toString());
    }

    private void Nom_TextChangedListener(Editable editable) {
        Persona actual = Persona.getPersones().get(indexPersonatgeActual);
        actual.setNom(editable.toString());
    }

    private void NIF_TextChangedListener(Editable editable) {
        Persona actual = Persona.getPersones().get(indexPersonatgeActual);
        actual.setNIF(editable.toString());
    }

    private void creacioIds() {
        //-----------------------------------------------------------
        // Enllaçar els elements de la interfíce gràfica
        imgPhoto = findViewById(R.id.imgPhoto);
        edtNom = findViewById(R.id.edtNom);
        edtCognoms = findViewById(R.id.edtCognoms);
        edtNIF = findViewById(R.id.edtNIF);
        rdgSexe = findViewById(R.id.rdgSexe);
        rdoH = findViewById(R.id.rdoH);
        rdoD = findViewById(R.id.rdoD);
        rdoA = findViewById(R.id.rdoA);
        spnProvincies = findViewById(R.id.spnProvincies);
        llyNext = findViewById(R.id.llyNext);
        llyPrev = findViewById(R.id.llyPrev);
    }

    private void showCurrentUser() {
       Persona actual = Persona.getPersones().get(indexPersonatgeActual);
       int idx = Provincia.getProvincies().indexOf(actual.getProvincia());
       spnProvincies.setSelection(idx);

       imgPhoto.setImageResource(actual.getImatge());
       edtNom.setText(actual.getNom());
       edtCognoms.setText(actual.getCognoms());
       edtNIF.setText(actual.getNIF());
        /*switch (actual.getSexe()){
            case DONA: rdoD.setChecked(true);break;
            case HOME: rdoH.setChecked(true);break;
            case ALTRES: rdoA.setChecked(true);break;
        }*/
        //rdgSexe.check();
       RadioButton rb = (RadioButton)rdgSexe.getChildAt(actual.getSexe().ordinal());
       rb.setChecked(true);

        boolean prevActivat = (indexPersonatgeActual>0);
        boolean nextActivat = indexPersonatgeActual<Persona.getPersones().size()-1;
        activa(llyPrev, prevActivat);
        activa(llyNext, nextActivat);
    }
    private void activa(Button b, boolean prevActivat) {
        b.setEnabled(prevActivat);
    }
    private void activa(LinearLayout llyPrev, boolean prevActivat) {

        //android:backgroundTint="#88888800"
        //android:backgroundTintMode="multiply"
        llyPrev.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        if(!prevActivat) {
            llyPrev.setBackgroundTintList(ColorStateList.valueOf(Color.argb(100,200,200,200)));
        } else {
            llyPrev.setBackgroundTintList(null);
        }
    }
}
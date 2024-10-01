package org.milaifontanals.layoutsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.service.voice.VoiceInteractionSession;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.milaifontanals.layoutsapp.databinding.ActivityMainBinding;
import org.milaifontanals.layoutsapp.model.Persona;
import org.milaifontanals.layoutsapp.model.Provincia;
import org.milaifontanals.layoutsapp.model.Sexe;
import org.milaifontanals.layoutsapp.utils.MyTextWatcher;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private int indexPersonatgeActual = 0;
    private ArrayAdapter<Provincia> adapter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        // Incialització del programa

        showCurrentUser();
        programarListeners();
        omplirSpinner();
    }

    private void omplirSpinner() {

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,Provincia.getProvincies());
        binding.lyoForm.spnProvincies.setAdapter(adapter);
    }

    private void programarListeners() {
    /*llyNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // codi del click
        }
    });*/
        binding.lyoForm.llyNext.setOnClickListener(view -> {
            //codi del click
            this.indexPersonatgeActual++;
            this.indexPersonatgeActual =
                    this.indexPersonatgeActual%Persona.getPersones().size();
            showCurrentUser();
        });
        binding.lyoForm.llyPrev.setOnClickListener(view -> {
            //codi del click
            this.indexPersonatgeActual--;
            if(this.indexPersonatgeActual<0) {
                this.indexPersonatgeActual = Persona.getPersones().size() - 1;
            }
            showCurrentUser();
        });

        binding.lyoForm.edtNIF.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                    NIF_TextChangedListener(editable);
            }
        });
        binding.lyoForm.edtNom.addTextChangedListener( new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Nom_TextChangedListener(editable);
            }
        });
        binding.lyoForm.edtCognoms.addTextChangedListener( new MyTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                Cognom_TextChangedListener(editable);
            }
        });

        binding.lyoForm.rdgSexe.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            Persona actual = getPersonaActual();

            HashMap<Integer, Sexe> mapaSexe = new HashMap<>();
            mapaSexe.put(R.id.rdoA, Sexe.ALTRES);
            mapaSexe.put(R.id.rdoH, Sexe.HOME);
            mapaSexe.put(R.id.rdoD, Sexe.DONA);

            actual.setSexe(mapaSexe.get(checkedId));
        });

        binding.lyoForm.spnProvincies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Persona actual = getPersonaActual();
                actual.setProvincia(Provincia.getProvincies().get(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Persona actual = getPersonaActual();
                actual.setProvincia(null);
            }
        });
    }

    private Persona getPersonaActual() {
        return Persona.getPersones().get(indexPersonatgeActual);
    }

    private void Cognom_TextChangedListener(Editable editable) {
        Persona actual = getPersonaActual();
        actual.setCognoms(editable.toString());
    }

    private void Nom_TextChangedListener(Editable editable) {
        Persona actual = getPersonaActual();
        actual.setNom(editable.toString());
    }

    private void NIF_TextChangedListener(Editable editable) {
        Persona actual = getPersonaActual();
        actual.setNIF(editable.toString());
    }


    private void showCurrentUser() {
       Persona actual = getPersonaActual();
       int idx = Provincia.getProvincies().indexOf(actual.getProvincia());
        binding.lyoForm.spnProvincies.setSelection(idx);

        binding.imgPhoto.setImageResource(actual.getImatge());
        binding.lyoForm.edtNom.setText(actual.getNom());
        binding.lyoForm.edtCognoms.setText(actual.getCognoms());
        binding.lyoForm.edtNIF.setText(actual.getNIF());
        /*switch (actual.getSexe()){
            case DONA: rdoD.setChecked(true);break;
            case HOME: rdoH.setChecked(true);break;
            case ALTRES: rdoA.setChecked(true);break;
        }*/
        //rdgSexe.check();
       RadioButton rb = (RadioButton)binding.lyoForm.rdgSexe.getChildAt(actual.getSexe().ordinal());
       rb.setChecked(true);

        boolean prevActivat = (indexPersonatgeActual>0);
        boolean nextActivat = indexPersonatgeActual<Persona.getPersones().size()-1;
        activa(binding.lyoForm.llyPrev, prevActivat);
        activa(binding.lyoForm.llyNext, nextActivat);
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
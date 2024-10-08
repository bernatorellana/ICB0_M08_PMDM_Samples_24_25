package org.milaifontanals.apprecyclerview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.milaifontanals.apprecyclerview.R;
import org.milaifontanals.apprecyclerview.adapters.CardAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcyList;
    CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //====================================================================
        // Configuració del RecyclerView
        rcyList = findViewById(R.id.rcyList);
        // indiquem que és una llista tipus "linial"
        rcyList.setLayoutManager(new LinearLayoutManager(this));
        rcyList.setHasFixedSize(true);
        //====================================================================
        adapter = new CardAdapter();
        rcyList.setAdapter(adapter);

    }
}
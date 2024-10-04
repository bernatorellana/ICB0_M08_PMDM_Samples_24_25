package org.milaifontanals.apprecyclerview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.milaifontanals.apprecyclerview.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuració del RecyclerView
        rcyList = findViewById(R.id.rcyList);
        rcyList.setLayoutManager(new LinearLayoutManager());


    }
}
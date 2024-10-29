package org.milaifontanals.a20241029_shit;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.milaifontanals.a20241029_shit.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    int scoreLocal=0, scoreVisitant=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.txvLocal.setText(""+scoreLocal);
        binding.txvVisitant.setText(""+scoreVisitant);

        binding.btnVisitant.setOnClickListener(view -> incVisitant(+1) );

        binding.btnVisitant.setOnLongClickListener(view -> {
            incVisitant(-1);return true;
        } );

        binding.btnLocal.setOnClickListener(view -> incLocal(+1)
        );
        binding.btnLocal.setOnLongClickListener(view -> {
            incLocal(-1);return true;
        } );


        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void incVisitant(int inc) {
        scoreVisitant+=inc;
        binding.txvVisitant.setText(""+scoreVisitant);
    }

    private void incLocal(int inc) {
        scoreLocal+=inc;
        binding.txvLocal.setText(""+scoreLocal);
    }
}
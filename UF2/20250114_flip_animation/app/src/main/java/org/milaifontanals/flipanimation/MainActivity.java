package org.milaifontanals.flipanimation;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.milaifontanals.flipanimation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CardView current, other;
    int signe = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        int distance = 4000;
        float scale = getResources().getDisplayMetrics().density;

        current = binding.cardFront;
        other = binding.cardBack;

        current.setCameraDistance(distance * scale);
        other.setCameraDistance(distance * scale);


        binding.imgFront.setOnClickListener(v -> {
            Log.d("XXX", "onCreate: click");
            current.animate().rotationY(signe*180).alpha(0).setDuration(500).start();
            other.animate().rotationY(0).alpha(1).setDuration(500).start();
            CardView tmp = other;
            other = current;
            current = tmp;
            signe *=-1;

        });





    }
}
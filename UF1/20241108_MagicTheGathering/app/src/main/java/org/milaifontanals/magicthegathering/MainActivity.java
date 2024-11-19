package org.milaifontanals.magicthegathering;

import org.milaifontanals.magicthegathering.adapter.CardAdapter;
import org.milaifontanals.magicthegathering.databinding.ActivityMainBinding;
import org.milaifontanals.magicthegathering.viewmodel.MainActivityViewModel;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;


public class MainActivity extends AppCompatActivity {


    private MainActivityViewModel viewModel;
    private CardAdapter adapter;


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recuperem el binding amb la classe "màgica" d'Android DataBindingUtil
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //prgLoading = findViewById(R.id.prgLoading);
        //.setVisibility(RecyclerView.VISIBLE);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getCardList().observe(this,cards -> {
            Toast.makeText(this, cards.get(0).getName(), Toast.LENGTH_SHORT).show();
           // prgLoading.setVisibility(RecyclerView.INVISIBLE);

            adapter = new CardAdapter(cards, this);
           binding.rcyCardList.setAdapter(adapter);
        });

        binding.rcyCardList.setHasFixedSize(true);
        binding.rcyCardList.setLayoutManager(new GridLayoutManager(this,2));
    }
}
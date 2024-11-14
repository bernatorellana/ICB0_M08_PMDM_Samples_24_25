package org.milaifontanals.magicthegathering;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.milaifontanals.magicthegathering.adapter.CardAdapter;
import org.milaifontanals.magicthegathering.model.Card;
import org.milaifontanals.magicthegathering.model.Example;
import org.milaifontanals.magicthegathering.utils.NetworkUtils;
import org.milaifontanals.magicthegathering.viewmodel.MainActivityViewModel;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class MainActivity extends AppCompatActivity {


    private MainActivityViewModel viewModel;
    private CardAdapter adapter;
    private RecyclerView rcyCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.getCardList().observe(this,cards -> {
            Toast.makeText(this, cards.get(0).getName(), Toast.LENGTH_SHORT).show();

            adapter = new CardAdapter(cards, this);
            rcyCardList.setAdapter(adapter);
        });

        rcyCardList = findViewById(R.id.rcyCardList);
        rcyCardList.setHasFixedSize(true);
        rcyCardList.setLayoutManager(new GridLayoutManager(this,2));
    }
}
package org.milaifontanals.apprecyclerview.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import org.milaifontanals.apprecyclerview.R;
import org.milaifontanals.apprecyclerview.databinding.ActivityCardBinding;
import org.milaifontanals.apprecyclerview.model.Card;
import org.milaifontanals.apprecyclerview.model.Rarity;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends AppCompatActivity {

    public static final String PARAM_CARD = "CARD";
    private Card card;

    private ActivityCardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        card = (Card)this.getIntent().getSerializableExtra(PARAM_CARD);

       binding.edtName.setText(card.getName());
       binding.edtDesc.setText(card.getDesc());
       Glide.with(this).load(card.getImageURL()).into(binding.imvPhoto);

       int idx = 0, rarityIdx = 0;
       List<String> rarityList = new ArrayList<>();
       for(Rarity r:Rarity.values()){
           rarityList.add(r.name());
           if(r==card.getRarity()){
               rarityIdx = idx;
           }
           idx++;
       }
        ArrayAdapter<String> rarityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                rarityList);
        binding.spnRarity.setAdapter(rarityAdapter);
        binding.spnRarity.setSelection(rarityIdx);
    }
}
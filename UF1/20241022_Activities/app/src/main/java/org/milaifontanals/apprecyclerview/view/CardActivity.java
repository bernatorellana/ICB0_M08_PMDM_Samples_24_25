package org.milaifontanals.apprecyclerview.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
       if(card.getBitmap(this)!=null){
           binding.imvPhoto.setImageBitmap(card.getBitmap(this));
       } else {
           Glide.with(this).load(card.getImageURL()).into(binding.imvPhoto);
       }
       int idx = 0, rarityIdx = 0;
       List<String> rarityList = new ArrayList<>();
       for(Rarity r:Rarity.values()){
           rarityList.add(r.name());
           if(r==card.getRarity()){
               rarityIdx = idx;
           }
           idx++;
       }
        // ================== Spinner de rarity ============================
        ArrayAdapter<String> rarityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                rarityList);
        binding.spnRarity.setAdapter(rarityAdapter);
        binding.spnRarity.setSelection(rarityIdx);

        // ================== Spinner de cost ============================
        Integer elixirCost[] = {1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> costAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Arrays.asList(elixirCost));
        binding.spnElixirCost.setAdapter(costAdapter);
        binding.spnElixirCost.setSelection(card.getElixirCost()-1);

        // ================== Botons ============================
        binding.btnSave.setOnClickListener(view -> {
            Optional<Card> c = Card.getCartes().stream().filter(x -> x.getId()==card.getId()).findFirst();
            Card cardActual =  c.get();
            cardActual.setName(binding.edtName.getText().toString());
            cardActual.setDesc(binding.edtDesc.getText().toString());
            cardActual.setRarity( Rarity.values()[ binding.spnRarity.getSelectedItemPosition()]);
            cardActual.setElixirCost((Integer) binding.spnElixirCost.getSelectedItem());
            //cardActual.setElixirCost();
            if(imageBitmap!=null) {
                cardActual.setBitmap(imageBitmap, this);
            }
            finish();
         });

        binding.imvPhoto.setOnClickListener(view -> {
            ferFoto();
        });
    }




    //*********************************************
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void ferFoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }


private Bitmap imageBitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            binding.imvPhoto.setImageBitmap(imageBitmap);
            card.setBitmap(imageBitmap,this);

        }
    }
}
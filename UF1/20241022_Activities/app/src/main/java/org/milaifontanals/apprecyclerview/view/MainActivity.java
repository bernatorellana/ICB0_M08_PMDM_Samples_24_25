package org.milaifontanals.apprecyclerview.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.milaifontanals.apprecyclerview.R;
import org.milaifontanals.apprecyclerview.adapters.CardAdapter;
import org.milaifontanals.apprecyclerview.model.Card;

public class MainActivity extends AppCompatActivity implements CardAdapter.CardAdapterListener {

    private static final int REQUEST_CODE = 1;
    private RecyclerView rcyList;
    private CardAdapter adapter;
    private Card selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //====================================================================
        // Configuració del RecyclerView
        rcyList = findViewById(R.id.rcyList);
        // indiquem que és una llista tipus "linial"
        rcyList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        rcyList.setHasFixedSize(true);
        //====================================================================
        adapter = new CardAdapter(this, this);
        rcyList.setAdapter(adapter);

    }



    @Override
    public void onCardSelected(Card selected) {
        this.selected = selected;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuDelete) {
            this.adapter.deleteSelectedItem();
        } else if (item.getItemId() == R.id.mnuUp) {
            this.adapter.moveSelectedItem(-1);
        } else if (item.getItemId() == R.id.mnuDown) {
            this.adapter.moveSelectedItem(+1);
        } else if(item.getItemId() == R.id.mnuEdit) {
            if(selected!=null) {
                // Codi per llançar una activity
                Intent i = new Intent(this, CardActivity.class);

                i.putExtra(CardActivity.PARAM_CARD, selected);
                startActivityForResult(i,REQUEST_CODE);
            }
        }
        return true;
    }
}
package org.milaifontanals.apprecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.milaifontanals.apprecyclerview.R;
import org.milaifontanals.apprecyclerview.model.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Card> cartes;

    public CardAdapter(){
        cartes = Card.getCartes();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila =  LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new ViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card current = cartes.get(position);
        holder.txvName.setText(current.getName());
        holder.txvDesc.setText(current.getDesc());
        holder.imvPhoto.setImageResource(current.getDrawable());
        holder.txvCost.setText(current.getElixirCost());
    }

    @Override
    public int getItemCount() {
        return cartes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llyRow;
        ImageView imvPhoto;
        TextView txvCost;
        TextView txvName;
        TextView txvDesc;

        public ViewHolder(@NonNull View fila) {
            super(fila);
            llyRow = fila.findViewById(R.id.llyRow);
            imvPhoto = fila.findViewById(R.id.imvPhoto);
            txvCost = fila.findViewById(R.id.txvCost);
            txvName = fila.findViewById(R.id.txvName);
            txvDesc = fila.findViewById(R.id.txvDesc);
        }
    }
}

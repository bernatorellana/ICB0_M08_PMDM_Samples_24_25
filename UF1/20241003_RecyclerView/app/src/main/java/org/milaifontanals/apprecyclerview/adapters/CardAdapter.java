package org.milaifontanals.apprecyclerview.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.milaifontanals.apprecyclerview.R;
import org.milaifontanals.apprecyclerview.model.Card;
import org.milaifontanals.apprecyclerview.model.Rarity;
import org.milaifontanals.apprecyclerview.view.MainActivity;

import java.util.HashMap;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {


    public void deleteSelectedItem() {
        if(posicioSeleccionada!=NO_SELECCIONAT){
            int pos = posicioSeleccionada;
            cartes.remove(pos);
            posicioSeleccionada = NO_SELECCIONAT;
            notifyItemRemoved(pos);
        }
    }

    public void moveSelectedItem(int i) {

        int currentPos = this.posicioSeleccionada;
        int nextPos = currentPos+i;
        if(nextPos>=0 && nextPos<cartes.size()) { // Estem dins del rang vàlid d'índexos
            Card c = cartes.remove(currentPos);
            cartes.add(nextPos, c);
            posicioSeleccionada = nextPos;
            notifyItemMoved(currentPos, nextPos);
        }
    }

    public interface CardAdapterListener{
        void onCardSelected(Card selected);
    }

    private List<Card> cartes;
    private Context context;
    private HashMap<Rarity, Integer> colors_per_rarity = new HashMap<>();

    private static final int NO_SELECCIONAT = -1;

    /**
     * Posició del caràcter seleccionat. Serà -1 si no n'hi ha cap
     */
    private int posicioSeleccionada =  NO_SELECCIONAT;

    private CardAdapterListener listener;

    public CardAdapter(
            Context context, CardAdapterListener listener
    ){
        this.listener = listener;
        cartes = Card.getCartes();
        this.context = context; // guardem el context
        colors_per_rarity.put(Rarity.COMMON, R.color.common_color);
        colors_per_rarity.put(Rarity.RARE,   R.color.rare_color);
        colors_per_rarity.put(Rarity.EPIC,   R.color.epic_color);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila =  LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new ViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int XXX_DO_NOT_USE_ME) {
        Card current = cartes.get(holder.getAdapterPosition());
        holder.txvName.setText(current.getName());
        holder.txvDesc.setText(current.getDesc());
        //holder.imvPhoto.setImageResource(current.getDrawable());
        Glide.with(context).load(current.getImageURL()).into(holder.imvPhoto);

        holder.txvCost.setText(""+current.getElixirCost());

        Log.d("CLASH_ROYALE", "Estic passant per onBind");

        Rarity r = current.getRarity();
        holder.llyRowConstraint.setBackgroundColor(
                context.getColor(colors_per_rarity.get(r))
        );
        if(holder.getAdapterPosition()==posicioSeleccionada) {
            holder.llyRow.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.selected)));
            holder.llyRowConstraint.setBackgroundTintList(ColorStateList.valueOf(context.getColor(R.color.selected)));
        } else {
            holder.llyRow.setBackgroundTintList(null);// anul·lem el Tint
            holder.llyRowConstraint.setBackgroundTintList(null);

        }

        // Programació de l'event Click sobre la fila sencera

        holder.llyRow.setOnClickListener(view ->{
            int position = holder.getAdapterPosition();
            if(this.posicioSeleccionada==position) {
                // estic desmarcant la posició seleccionada
                this.posicioSeleccionada = NO_SELECCIONAT;
                this.notifyItemChanged(position);
            } else {
                // Hi ha un canvi d'element seleccionat
                int posicioAnteriormentSeleccionada = this.posicioSeleccionada;
                this.posicioSeleccionada = position;
                this.notifyItemChanged(posicioSeleccionada); // refresco el nou seleccionat
                this.notifyItemChanged(posicioAnteriormentSeleccionada); // refresquem l'element anteriorment seleccionat per deseleccionar-lo
                if(listener!=null) {
                    listener.onCardSelected(current);
                }
            }
        } );
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
        ConstraintLayout llyRowConstraint;

        public ViewHolder(@NonNull View fila) {
            super(fila);
            llyRow = fila.findViewById(R.id.llyRow);
            imvPhoto = fila.findViewById(R.id.imvPhoto);
            txvCost = fila.findViewById(R.id.txvCost);
            txvName = fila.findViewById(R.id.txvName);
            txvDesc = fila.findViewById(R.id.txvDesc);
            llyRowConstraint = fila.findViewById(R.id.llyRowConstraint);
        }
    }
}

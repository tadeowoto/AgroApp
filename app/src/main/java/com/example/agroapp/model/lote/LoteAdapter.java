package com.example.agroapp.model.lote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agroapp.R;

import java.util.List;

public class LoteAdapter extends RecyclerView.Adapter<LoteAdapter.ViewHolderLote> {

    private List<Lote> lista;
    private LayoutInflater li;

    public LoteAdapter(List<Lote> lista, LayoutInflater li) {
        this.lista = lista;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderLote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = li.inflate(R.layout.item_lote, parent, false);
        return new ViewHolderLote(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLote holder, int position) {
        Lote l = lista.get(position);
        holder.nombre.setText(l.getNombre());
        holder.cultivo.setText("Cultivo: " + l.getCultivo());
        holder.fecha.setText("Fecha de creación: " + l.getFecha_creacion());
        holder.superficie.setText("Superficie: " + l.getSuperficie_ha() + " ha");
        holder.card.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("lote", l);
            Navigation.findNavController( v).navigate(R.id.action_detalleLoteFragment_to_informacionLoteFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public class ViewHolderLote extends RecyclerView.ViewHolder {

        TextView nombre, cultivo, fecha, superficie;
        CardView card;
        public ViewHolderLote(@NonNull View itemView) {
            super(itemView);
            superficie = itemView.findViewById(R.id.tvSuperficie);
            card = itemView.findViewById(R.id.item_campo);
            nombre = itemView.findViewById(R.id.tvNombreLote);
            cultivo = itemView.findViewById(R.id.tvCultivo);
            fecha = itemView.findViewById(R.id.tvFechaCreacion);
        }
    }
}

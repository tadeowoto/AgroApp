package com.example.agroapp.model.campo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.agroapp.R;
import java.util.List;

public class CampoAdapter extends RecyclerView.Adapter<CampoAdapter.ViewHolderCampo> {

    private List<Campo> lista;
    private LayoutInflater li;
    private OnCampoClickListener listener;

    public interface OnCampoClickListener {
        void onCampoClick(Campo campo, View view);
    }

    public CampoAdapter(List<Campo> lista, LayoutInflater li, OnCampoClickListener listener) {
        this.lista = lista;
        this.li = li;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderCampo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = li.inflate(R.layout.item_campo, parent, false);
        return new ViewHolderCampo(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCampo holder, int position) {
        Campo c = lista.get(position);
        holder.nombre.setText(c.getNombre());
        holder.ubicacion.setText(c.getUbicacion());
        holder.area.setText("Área: " + c.getExtension_ha() + " ha");
        holder.card.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCampoClick(c, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public class ViewHolderCampo extends RecyclerView.ViewHolder {
        TextView nombre, ubicacion, area;
        CardView card;

        public ViewHolderCampo(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreCampo);
            ubicacion = itemView.findViewById(R.id.tvUbicacionCampo);
            area = itemView.findViewById(R.id.tvAreaCampo);
            card = itemView.findViewById(R.id.item_campo);
        }
    }
}





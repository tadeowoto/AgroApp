package com.example.agroapp.model.campo;

import android.content.Context;
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

public class CampoAdapter extends RecyclerView.Adapter<CampoAdapter.ViewHolderCampo> {
    private List<Campo> lista;
    private Context context;
    private LayoutInflater li;
    public CampoAdapter(List<Campo> lista, Context context, LayoutInflater li) {
        this.lista = lista;
        this.context = context;
        this.li = li;
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
        holder.card.setOnClickListener( v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("campo", c);
            Navigation.findNavController(v).navigate(R.id.action_campoFragment_to_detalleCampoFragment, bundle);
        });

    }

    @Override
    public int getItemCount() {
        return lista.size() == 0 ? 0 : lista.size();
    }

    public class ViewHolderCampo extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView ubicacion;
        TextView area;

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





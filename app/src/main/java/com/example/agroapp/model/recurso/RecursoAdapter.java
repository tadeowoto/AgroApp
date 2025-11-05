package com.example.agroapp.model.recurso;

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

public class RecursoAdapter extends RecyclerView.Adapter<RecursoAdapter.ViewHolderRecurso> {

    private List<Recurso> lista;
    private LayoutInflater li;

    public RecursoAdapter(List<Recurso> lista, LayoutInflater li) {
        this.lista = lista;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderRecurso onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = li.inflate(R.layout.item_recurso, parent, false);
        return new ViewHolderRecurso(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecurso holder, int position) {
        Recurso r = lista.get(position);
        holder.nombre.setText(r.getNombre());
        holder.tipo.setText("Tipo: " + r.getTipo());
        holder.marca.setText("Marca: " + r.getMarca());
        holder.modelo.setText("Modelo: " + r.getModelo());
        holder.estado.setText("Estado: " + r.getEstado());
        holder.card.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("recurso", r);
            Navigation.findNavController(v).navigate(R.id.action_recursoFragment_to_editarRecursoFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public class ViewHolderRecurso extends RecyclerView.ViewHolder {

        TextView nombre, tipo, marca, modelo, estado;
        CardView card;


        public ViewHolderRecurso(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreRecurso);
            tipo = itemView.findViewById(R.id.tvTipoRecurso);
            marca = itemView.findViewById(R.id.tvMarcaRecurso);
            modelo = itemView.findViewById(R.id.tvModeloRecurso);
            estado = itemView.findViewById(R.id.tvEstadoRecurso);
            card = itemView.findViewById(R.id.item_recurso);

        }
    }
}

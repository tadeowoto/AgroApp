package com.example.agroapp.model.actividad;

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

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ViewHolderActividad> {

    private List<Actividad> lista;
    private LayoutInflater li;

    public ActividadAdapter(List<Actividad> lista, LayoutInflater li) {
        this.lista = lista;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderActividad onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = li.inflate(R.layout.item_actividad, parent, false);
        return new ViewHolderActividad(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderActividad holder, int position) {
        Actividad a = lista.get(position);
        Locale locale = new Locale("es", "AR");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        holder.descripcion.setText(a.getDescripcion());
        holder.cantidad.setText("Cantidad insumo: " + a.getCantidad_insumo());
        holder.costo.setText("Costo: " + format.format(a.getCosto()));


        holder.card.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("actividad", a);
            Navigation.findNavController(v).navigate(R.id.action_actividadFragment_to_detalleActividadFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public static class ViewHolderActividad extends RecyclerView.ViewHolder {

        TextView descripcion, cantidad, costo;
        CardView card;

        public ViewHolderActividad(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
            cantidad = itemView.findViewById(R.id.tvCantidad);
            costo = itemView.findViewById(R.id.tvCosto);
            card = itemView.findViewById(R.id.cardActividad);
        }
    }
}

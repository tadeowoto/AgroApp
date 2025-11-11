package com.example.agroapp.model.insumo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agroapp.R;
import com.example.agroapp.lib.Services;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class InsumoAdapter extends RecyclerView.Adapter<InsumoAdapter.ViewHolderInsumo> {

    private List<Insumo> lista;
    private LayoutInflater li;

    public InsumoAdapter(List<Insumo> lista, LayoutInflater li) {
        this.lista = lista;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderInsumo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = li.inflate(R.layout.item_insumo, parent, false);
        return new ViewHolderInsumo(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInsumo holder, int position) {
        Insumo i = lista.get(position);


        holder.tvNombre.setText(i.getNombre());
        holder.tvTipo.setText(i.getTipo());
        holder.tvUnidad.setText(i.getUnidad());
        holder.tvStock.setText("Stock actual: " + i.getStock_actual());
        holder.tvVencimiento.setText("Vence: " + Services.formatFechaDisplay(i.getFecha_vencimiento()));

        holder.card.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("insumo", i);
            Navigation.findNavController(v).navigate(R.id.action_insumoFragment_to_detalleInsumoFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public class ViewHolderInsumo extends RecyclerView.ViewHolder {

        TextView tvNombre, tvTipo, tvUnidad, tvStock, tvVencimiento;
        MaterialCardView card;

        public ViewHolderInsumo(@NonNull View itemView) {
            super(itemView);

            card = (MaterialCardView) itemView;
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvUnidad = itemView.findViewById(R.id.tvUnidad);
            tvStock = itemView.findViewById(R.id.tvStock);
            tvVencimiento = itemView.findViewById(R.id.tvVencimiento);
        }
    }
}
package com.example.agroapp.model.cosecha;

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

public class CosechaAdapter extends RecyclerView.Adapter<CosechaAdapter.ViewHolderCosecha> {

    private List<Cosecha> lista;
    private LayoutInflater li;

    public CosechaAdapter(List<Cosecha> lista, LayoutInflater li) {
        this.lista = lista;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderCosecha onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = li.inflate(R.layout.item_cosecha, parent, false);
        return new ViewHolderCosecha(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCosecha holder, int position) {
        Cosecha c = lista.get(position);

        holder.tvFechaInicio.setText("Fecha inicio: " + c.getFecha_inicio());
        holder.tvFechaFin.setText("Fecha fin: " + c.getFecha_fin());
        holder.tvRendimiento.setText("Rendimiento: " + c.getRendimiento() + " t/ha");

        holder.card.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("cosecha", c);
            Navigation.findNavController(v).navigate(R.id.action_cosechasDeUnLoteFragment_to_detalleCosechaFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return lista != null ? lista.size() : 0;
    }

    public static class ViewHolderCosecha extends RecyclerView.ViewHolder {

        TextView tvFechaInicio, tvFechaFin, tvRendimiento;
        CardView card;

        public ViewHolderCosecha(@NonNull View itemView) {
            super(itemView);
            tvFechaInicio = itemView.findViewById(R.id.tvFechaInicio);
            tvFechaFin = itemView.findViewById(R.id.tvFechaFin);
            tvRendimiento = itemView.findViewById(R.id.tvRendimientoCosecha);
            card = (CardView) itemView;
        }
    }
}

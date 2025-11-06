package com.example.agroapp.model.lote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agroapp.R;

import java.util.List;

public class LoteAdapter extends RecyclerView.Adapter<LoteAdapter.ViewHolder> {

    private List<Lote> lotes;
    private LayoutInflater inflater;
    private OnLoteClickListener listener;

    public interface OnLoteClickListener {
        void onLoteClick(Lote lote);
    }

    public LoteAdapter(List<Lote> lotes, LayoutInflater inflater, OnLoteClickListener listener) {
        this.lotes = lotes;
        this.inflater = inflater;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_lote, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Lote lote = lotes.get(position);
        holder.bind(lote, listener);
    }

    @Override
    public int getItemCount() {
        return lotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvCultivo, tvSuperficie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreLote);
            tvCultivo = itemView.findViewById(R.id.tvCultivo);
            tvSuperficie = itemView.findViewById(R.id.tvSuperficie);
        }

        public void bind(Lote lote, OnLoteClickListener listener) {
            tvNombre.setText(lote.getNombre());
            tvCultivo.setText("Cultivo: " + lote.getCultivo());
            tvSuperficie.setText("Superficie: " + lote.getSuperficie_ha() + " ha");

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onLoteClick(lote);
                }
            });
        }
    }
}

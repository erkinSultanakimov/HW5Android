package com.geekchtech.hw5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekchtech.hw5.databinding.FragmentHomeBinding;
import com.geekchtech.hw5.databinding.FragmentHomeItemsBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Model> list = new ArrayList<>();

    public void addItems(Model model){
        list.add(model);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentHomeItemsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FragmentHomeItemsBinding binding;
        public ViewHolder( @NonNull FragmentHomeItemsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void onBind(Model model) {
            binding.name.setText(model.getName());
            binding.number.setText(model.getNumber());
        }
    }
}

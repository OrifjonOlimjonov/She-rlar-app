package uz.orifjon.sherlarinjava.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.orifjon.sherlarinjava.databinding.ItemLikeBinding;
import uz.orifjon.sherlarinjava.models.Poems;

public class RecyclerViewLikesAdapter extends RecyclerView.Adapter<RecyclerViewLikesAdapter.MyViewHolder> {
    private List<Poems> list;
    private OnClickListenerItem onClickListenerItem;

    public RecyclerViewLikesAdapter(List<Poems> list, OnClickListenerItem onClickListenerItem) {
        this.list = list;
        this.onClickListenerItem = onClickListenerItem;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemLikeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.binding.poemName.setText(list.get(position).getName());
            holder.binding.poemText.setText(list.get(position).getText());
            holder.itemView.setOnClickListener(view -> {
                onClickListenerItem.onClick(list.get(position));
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ItemLikeBinding binding;
        public MyViewHolder(@NonNull ItemLikeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void change(List<Poems> list){
        notifyDataSetChanged();
    }
    public interface OnClickListenerItem{
        void onClick(Poems poem);
    }
}

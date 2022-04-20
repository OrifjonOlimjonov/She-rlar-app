package uz.orifjon.sherlarinjava.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.orifjon.sherlarinjava.databinding.ItemBinding;
import uz.orifjon.sherlarinjava.databinding.ItemLikeBinding;
import uz.orifjon.sherlarinjava.models.Poems;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Poems> list;
    private OnClickListenerItem onClickListenerItem;

    public RecyclerViewAdapter(List<Poems> list, OnClickListenerItem onClickListenerItem) {
        this.list = list;
        this.onClickListenerItem = onClickListenerItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return (viewType == 1 ? new LikeMyViewHolder(ItemLikeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)) : new MyViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(list.get(position).isLike()){
                LikeMyViewHolder likeMyViewHolder = (LikeMyViewHolder) holder;
                likeMyViewHolder.binding.poemName.setText(list.get(position).getName());
                likeMyViewHolder.binding.poemText.setText(list.get(position).getText());
            }else{
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.binding.poemName.setText(list.get(position).getName());
                myViewHolder.binding.poemText.setText(list.get(position).getText());
            }
        holder.itemView.setOnClickListener(view -> {
            onClickListenerItem.onClick(list.get(position));
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).isLike()) {
            return 1;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;

        public MyViewHolder(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class LikeMyViewHolder extends RecyclerView.ViewHolder {
        ItemLikeBinding binding;

        public LikeMyViewHolder(@NonNull ItemLikeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickListenerItem {
        void onClick(Poems poem);
    }
}

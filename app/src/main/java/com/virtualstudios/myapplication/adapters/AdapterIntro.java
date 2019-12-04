package com.virtualstudios.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.virtualstudios.myapplication.R;
import com.virtualstudios.myapplication.model.ModelIntro;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterIntro extends RecyclerView.Adapter<AdapterIntro.ViewHolder> {

    private List<ModelIntro> mItems;

    public AdapterIntro(List<ModelIntro> mItems) {
        this.mItems = mItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_intro, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.text_title)
        TextView textViewTitle;
        @BindView(R.id.text_description)
        TextView textViewDescription;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void setData(ModelIntro data){
            imageView.setImageResource(data.getImage());
            textViewTitle.setText(data.getTitle());
            textViewDescription.setText(data.getDescription());
        }
    }
}

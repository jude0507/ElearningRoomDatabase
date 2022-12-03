package com.example.elearningapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapplication.Model.PDFModel;

import java.util.ArrayList;

public class RecyclerAdapterQ4 extends RecyclerView.Adapter<RecyclerAdapterQ4.MyViewHolder>{
    private ArrayList<PDFModel> mymodelQ4;
    private ItemClickListener listener;

    public RecyclerAdapterQ4(ArrayList<PDFModel> mymodelQ4, ItemClickListener listener){
        this.mymodelQ4 = mymodelQ4;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mytitle;
        public MyViewHolder(@NonNull View view) {
            super(view);
            mytitle = view.findViewById(R.id.txt_title);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            listener.onItemClickLister(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterQ4.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecyclerviewrow, parent, false);
        return new RecyclerAdapterQ4.MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterQ4.MyViewHolder holder, int position) {
        String nametitle = mymodelQ4.get(position).getPdftitle();
        holder.mytitle.setText(nametitle);
    }

    @Override
    public int getItemCount() {
        return mymodelQ4.size();
    }


}

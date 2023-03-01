package com.example.elearningapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapplication.ItemClickListener;
import com.example.elearningapplication.Model.PDFModel;
import com.example.elearningapplication.R;

import java.util.ArrayList;

public class RecyclerAdapterQ3 extends RecyclerView.Adapter<RecyclerAdapterQ3.MyViewHolder>{

    private ArrayList<PDFModel> mymodelQ3;
    private ItemClickListener listener;

    public RecyclerAdapterQ3(ArrayList<PDFModel> mymodelQ3, ItemClickListener listener){
        this.mymodelQ3 = mymodelQ3;
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
    public RecyclerAdapterQ3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecyclerviewrow, parent, false);
        return new RecyclerAdapterQ3.MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterQ3.MyViewHolder holder, int position) {
        String nametitle = mymodelQ3.get(position).getPdftitle();
        holder.mytitle.setText(nametitle);
    }

    @Override
    public int getItemCount() {
        return mymodelQ3.size();
    }

}

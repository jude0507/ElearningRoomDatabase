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

public class RecyclerAdapterQ2 extends RecyclerView.Adapter<RecyclerAdapterQ2.MyViewHolder> {
    private ArrayList<PDFModel> mymodelQ2;
    private ItemClickListener listener;

    public RecyclerAdapterQ2(ArrayList<PDFModel> mymodelQ2, ItemClickListener listener){
        this.mymodelQ2 = mymodelQ2;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mytitle;

        public MyViewHolder(final View view){
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
    public RecyclerAdapterQ2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecyclerviewrow, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterQ2.MyViewHolder holder, int position) {
        String nametitle = mymodelQ2.get(position).getPdftitle();
        holder.mytitle.setText(nametitle);
    }

    @Override
    public int getItemCount() {
        return mymodelQ2.size();
    }
}


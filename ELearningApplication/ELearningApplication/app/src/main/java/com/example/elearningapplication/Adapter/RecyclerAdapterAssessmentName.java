package com.example.elearningapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapplication.ItemClickListener;
import com.example.elearningapplication.Model.AssessmentWeekName;
import com.example.elearningapplication.Model.PDFModel;
import com.example.elearningapplication.R;

import java.util.ArrayList;

public class RecyclerAdapterAssessmentName extends RecyclerView.Adapter<RecyclerAdapterAssessmentName.MyViewHolder> {
    private ArrayList<AssessmentWeekName> mymodel;
    private ItemClickListener listener;

    public RecyclerAdapterAssessmentName(ArrayList<AssessmentWeekName> mymodel, ItemClickListener listener){
        this.mymodel = mymodel;
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
    public RecyclerAdapterAssessmentName.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrecyclerviewrow, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterAssessmentName.MyViewHolder holder, int position) {

        String nametitle = mymodel.get(position).getPdftitle();
        holder.mytitle.setText(nametitle);

    }

    @Override
    public int getItemCount() {
        return mymodel.size();
    }
}

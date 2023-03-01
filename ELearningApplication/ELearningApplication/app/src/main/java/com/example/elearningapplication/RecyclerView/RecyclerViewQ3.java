package com.example.elearningapplication.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapplication.ActivityPDF.ActivityPDFViewQ3;
import com.example.elearningapplication.ItemClickListener;
import com.example.elearningapplication.Model.PDFModel;
import com.example.elearningapplication.Quarter.QuarterThree;
import com.example.elearningapplication.R;
import com.example.elearningapplication.Adapter.RecyclerAdapterQ3;

import java.util.ArrayList;

public class RecyclerViewQ3 extends AppCompatActivity {

    private ArrayList<PDFModel> mymodelQ3;
    private RecyclerView myRecyclerView;
    private ItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        myRecyclerView = findViewById(R.id.recyclerView);
        mymodelQ3 = new ArrayList<>();
        setPDFTitle();
        setAdapter();
    }

    private void setAdapter() {
        setOnclickListener();
        RecyclerAdapterQ3 myadapter = new RecyclerAdapterQ3(mymodelQ3,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myadapter);
    }
    private void setOnclickListener() {
        listener = new ItemClickListener(){
            @Override
            public void onItemClickLister(View view, int position) {
                String item = mymodelQ3.get(position).getPdftitle().toString();
                Intent intent = new Intent(getApplicationContext(), ActivityPDFViewQ3.class);
                intent.putExtra("pdfFileNameQ3",item);
                startActivity(intent);

            }
        };
    }

    private void setPDFTitle() {
        mymodelQ3.add(new PDFModel("G9-CSS-9-MODULE-3RD-QUARTER"));
    }

    public void gotolearningmodule(View view) {
        startActivity(new Intent(RecyclerViewQ3.this, QuarterThree.class));
    }

}

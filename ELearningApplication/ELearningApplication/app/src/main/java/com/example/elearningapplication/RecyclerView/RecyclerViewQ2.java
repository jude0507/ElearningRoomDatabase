package com.example.elearningapplication.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elearningapplication.ActivityPDF.ActivityPDFViewQ2;
import com.example.elearningapplication.ItemClickListener;
import com.example.elearningapplication.Model.PDFModel;
import com.example.elearningapplication.Quarter.QuarterTwo;
import com.example.elearningapplication.R;
import com.example.elearningapplication.Adapter.RecyclerAdapterQ2;

import java.util.ArrayList;

public class RecyclerViewQ2 extends AppCompatActivity {

    private ArrayList<PDFModel> mymodelQ2;
    private RecyclerView myRecyclerView;
    private ItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        myRecyclerView = findViewById(R.id.recyclerView);
        mymodelQ2 = new ArrayList<>();
        setPDFTitle();
        setAdapter();
    }

    private void setAdapter() {
        setOnclickListener();
        RecyclerAdapterQ2 myadapter = new RecyclerAdapterQ2(mymodelQ2,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myadapter);
    }
    private void setOnclickListener() {
        listener = new ItemClickListener(){
            @Override
            public void onItemClickLister(View view, int position) {
                String item = mymodelQ2.get(position).getPdftitle().toString();
                Intent intent = new Intent(getApplicationContext(), ActivityPDFViewQ2.class);
                intent.putExtra("pdfFileNameQ2",item);
                startActivity(intent);

            }
        };

    }
    private void setPDFTitle() {
        mymodelQ2.add(new PDFModel("TLE-GRADE-9-CSS-Q2-WEEK1"));
        mymodelQ2.add(new PDFModel("TLE-GRADE-9-CSS-Q2-WEEK2"));
        mymodelQ2.add(new PDFModel("TLE-GRADE-9-CSS-Q2-WEEK3"));
        mymodelQ2.add(new PDFModel("TLE-GRADE-9-CSS-Q2-WEEK4"));
        mymodelQ2.add(new PDFModel("TLE-GRADE-9-CSS-Q2-WEEK5"));
        mymodelQ2.add(new PDFModel("TLE-GRADE-9-CSS-Q2-WEEK6"));
        mymodelQ2.add(new PDFModel("TLE-GRADE-9-CSS-Q2-WEEK7"));
    }
    public void gotolearningmodule(View view) {
        startActivity(new Intent(RecyclerViewQ2.this, QuarterTwo.class));
    }
}
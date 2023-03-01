package com.example.elearningapplication.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elearningapplication.ActivityPDF.ActivityPDFViewQ1;
import com.example.elearningapplication.ItemClickListener;
import com.example.elearningapplication.Model.PDFModel;
import com.example.elearningapplication.R;
import com.example.elearningapplication.Adapter.RecyclerAdapterQ1;
import com.example.elearningapplication.View.LearningMaterials;

import java.util.ArrayList;


public class RecyclerViewQ1 extends AppCompatActivity {

    private ArrayList<PDFModel> mymodel;
    private RecyclerView myRecyclerView;
    private ItemClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        myRecyclerView = findViewById(R.id.recyclerView);
        mymodel = new ArrayList<>();

        setPDFTitle();
        setAdapter();

    }

    private void setAdapter() {
        setOnclickListener();
        RecyclerAdapterQ1 myadapter = new RecyclerAdapterQ1(mymodel,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myadapter);
    }

    private void setOnclickListener() {
        listener = new ItemClickListener() {
            @Override
            public void onItemClickLister(View view, int position) {
                String item = mymodel.get(position).getPdftitle().toString();
                Intent intent = new Intent(getApplicationContext(), ActivityPDFViewQ1.class);
                intent.putExtra("pdfFileNameQ1",item);
                startActivity(intent);

            }
        };
    }

    private void setPDFTitle() {
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D1"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D2"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D3"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D4"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D5"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D6"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D7"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D8"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D9"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D10"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D11"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D12"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D13"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D14"));
        mymodel.add(new PDFModel("GRADE 9-CSS-Q1-D15"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W1-D1-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W1-D2-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W1-D3-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W1-D4-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W2-D1-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W2-D2-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W2-D3-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W2-D4-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W3-D1-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W3-D2-Rosario"));
        mymodel.add(new PDFModel("PASAY-G9-Q1-W4-D1-Rosario"));
    }

    public void gotolearningmodule(View view) {
        startActivity(new Intent(RecyclerViewQ1.this, LearningMaterials.class));
    }
}
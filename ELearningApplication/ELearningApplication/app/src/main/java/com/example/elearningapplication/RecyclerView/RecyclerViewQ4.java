package com.example.elearningapplication.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapplication.ActivityPDF.ActivityPDFViewQ4;
import com.example.elearningapplication.ItemClickListener;
import com.example.elearningapplication.Model.PDFModel;
import com.example.elearningapplication.Quarter.QuarterFour;
import com.example.elearningapplication.R;
import com.example.elearningapplication.Adapter.RecyclerAdapterQ4;

import java.util.ArrayList;

public class RecyclerViewQ4 extends AppCompatActivity {
    private ArrayList<PDFModel> mymodelQ4;
    private RecyclerView myRecyclerView;
    private ItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        myRecyclerView = findViewById(R.id.recyclerView);
        mymodelQ4 = new ArrayList<>();
        setPDFTitle();
        setAdapter();
    }

    private void setAdapter() {
        setOnclickListener();
        RecyclerAdapterQ4 myadapter = new RecyclerAdapterQ4(mymodelQ4,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myadapter);
    }
    private void setOnclickListener() {
        listener = new ItemClickListener(){
            @Override
            public void onItemClickLister(View view, int position) {
                String item = mymodelQ4.get(position).getPdftitle().toString();
                Intent intent = new Intent(getApplicationContext(), ActivityPDFViewQ4.class);
                intent.putExtra("pdfFileNameQ4",item);
                startActivity(intent);

            }
        };
    }

    private void setPDFTitle() {
        mymodelQ4.add(new PDFModel("G9-CSS-SLEM-4thQUARTER"));
    }

    public void gotolearningmodule(View view) {
        startActivity(new Intent(RecyclerViewQ4.this, QuarterFour.class));
    }

}

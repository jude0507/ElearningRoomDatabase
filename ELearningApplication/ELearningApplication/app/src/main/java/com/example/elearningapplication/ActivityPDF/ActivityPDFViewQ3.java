package com.example.elearningapplication.ActivityPDF;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elearningapplication.R;
import com.example.elearningapplication.RecyclerView.RecyclerViewQ3;
import com.github.barteksc.pdfviewer.PDFView;

public class ActivityPDFViewQ3 extends AppCompatActivity {

    PDFView pdfviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview_q3);

        pdfviewer = findViewById(R.id.pdfView);

        String getItem = getIntent().getStringExtra("pdfFileNameQ3");

        if (getItem.equals("G9-CSS-9-MODULE-3RD-QUARTER")){
            pdfviewer.fromAsset("G9-CSS-9-MODULE-3RD-QUARTER.pdf").load();
        }
    }
    public void gotorecylerview(View view) {
        startActivity(new Intent(ActivityPDFViewQ3.this, RecyclerViewQ3.class));
    }
}
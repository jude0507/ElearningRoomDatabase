package com.example.elearningapplication.ActivityPDF;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elearningapplication.R;
import com.example.elearningapplication.RecyclerView.RecyclerViewQ2;
import com.github.barteksc.pdfviewer.PDFView;

public class ActivityPDFViewQ2 extends AppCompatActivity {

    PDFView pdfviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview_q2);

        pdfviewer = findViewById(R.id.pdfView);

        String getItem = getIntent().getStringExtra("pdfFileNameQ2");

        if (getItem.equals("TLE-GRADE-9-CSS-Q2-WEEK1")){
            pdfviewer.fromAsset("TLE-GRADE-9-CSS-Q2-WEEK1.pdf").load();
        }
        if (getItem.equals("TLE-GRADE-9-CSS-Q2-WEEK2")){
            pdfviewer.fromAsset("TLE-GRADE-9-CSS-Q2-WEEK2.pdf").load();
        }
        if (getItem.equals("TLE-GRADE-9-CSS-Q2-WEEK3")){
            pdfviewer.fromAsset("TLE-GRADE-9-CSS-Q2-WEEK3.pdf").load();
        }
        if (getItem.equals("TLE-GRADE-9-CSS-Q2-WEEK4")){
            pdfviewer.fromAsset("TLE-GRADE-9-CSS-Q2-WEEK4.pdf").load();
        }
        if (getItem.equals("TLE-GRADE-9-CSS-Q2-WEEK5")){
            pdfviewer.fromAsset("TLE-GRADE-9-CSS-Q2-WEEK5.pdf").load();
        }
        if (getItem.equals("TLE-GRADE-9-CSS-Q2-WEEK6")){
            pdfviewer.fromAsset("TLE-GRADE-9-CSS-Q2-WEEK6.pdf").load();
        }
        if (getItem.equals("TLE-GRADE-9-CSS-Q2-WEEK7")){
            pdfviewer.fromAsset("TLE-GRADE-9-CSS-Q2-WEEK7.pdf").load();
        }
    }
    public void gotorecylerview(View view) {
        startActivity(new Intent(ActivityPDFViewQ2.this, RecyclerViewQ2.class));
    }
}
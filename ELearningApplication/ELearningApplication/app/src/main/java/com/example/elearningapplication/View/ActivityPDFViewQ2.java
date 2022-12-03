package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elearningapplication.R;
import com.github.barteksc.pdfviewer.PDFView;

public class ActivityPDFViewQ2 extends AppCompatActivity {

    PDFView pdfviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview_q2);

        pdfviewer = findViewById(R.id.pdfView);

        String getItem = getIntent().getStringExtra("pdfFileNameQ2");

        if (getItem.equals("G9_ICT_Q2_W1andW2")){
            pdfviewer.fromAsset("G9_ICT_Q2_W1andW2.pdf").load();
        }
        if (getItem.equals("G9_ICT_Q2_W3andW4")){
            pdfviewer.fromAsset("G9_ICT_Q2_W3andW4.pdf").load();
        }
        if (getItem.equals("G9_ICT_Q2_W5andW6")){
            pdfviewer.fromAsset("G9_ICT_Q2_W5andW6.pdf").load();
        }
    }
    public void gotorecylerview(View view) {
        startActivity(new Intent(ActivityPDFViewQ2.this, RecyclerViewQ2.class));
    }
}
package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elearningapplication.R;
import com.github.barteksc.pdfviewer.PDFView;

public class ActivityPDFViewQ4 extends AppCompatActivity {

    PDFView pdfviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview_q4);

        pdfviewer = findViewById(R.id.pdfView);

        String getItem = getIntent().getStringExtra("pdfFileNameQ4");

        if (getItem.equals("G9-CSS-SLEM-4thQUARTER")){
            pdfviewer.fromAsset("G9-CSS-SLEM-4thQUARTER.pdf").load();
        }
    }
    public void gotorecylerview(View view) {
        startActivity(new Intent(ActivityPDFViewQ4.this, RecyclerViewQ4.class));
    }
}
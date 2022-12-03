package com.example.elearningapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.elearningapplication.R;
import com.github.barteksc.pdfviewer.PDFView;

public class ActivityPDFViewQ1 extends AppCompatActivity {

    PDFView pdfviewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);

        pdfviewer = findViewById(R.id.pdfView);

        String getItem = getIntent().getStringExtra("pdfFileNameQ1");

        if (getItem.equals("GRADE 9-CSS-Q1-D1")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D1.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D2")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D2.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D3")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D3.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D4")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D4.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D5")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D5.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D6")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D6.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D7")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D7.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D8")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D8.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D9")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D9.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D10")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D10.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D11")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D11.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D12")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D12.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D13")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D13.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D14")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D14.pdf").load();
        }
        if (getItem.equals("GRADE 9-CSS-Q1-D15")){
            pdfviewer.fromAsset("GRADE 9-CSS-Q1-D15.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W1-D1-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W1-D1-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W1-D2-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W1-D2-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W1-D3-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W1-D3-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W1-D4-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W1-D4-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W2-D1-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W2-D1-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W2-D2-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W2-D2-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W2-D3-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W2-D3-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W2-D4-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W2-D4-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W3-D1-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W3-D1-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W3-D2-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W3-D2-Rosario.pdf").load();
        }
        if (getItem.equals("PASAY-G9-Q1-W4-D1-Rosario")){
            pdfviewer.fromAsset("PASAY-G9-Q1-W4-D1-Rosario.pdf").load();
        }


    }

    public void gotorecylerview(View view) {
        startActivity(new Intent(ActivityPDFViewQ1.this, RecyclerViewQ1.class));
    }

}
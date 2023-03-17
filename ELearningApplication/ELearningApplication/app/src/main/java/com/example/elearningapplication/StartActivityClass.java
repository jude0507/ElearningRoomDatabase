package com.example.elearningapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.elearningapplication.ActivityPDF.ActivityPDFViewQ1;
import com.example.elearningapplication.Adapter.RecyclerAdapterAssessmentName;
import com.example.elearningapplication.Adapter.RecyclerAdapterQ1;
import com.example.elearningapplication.Model.AssessmentWeekName;
import com.example.elearningapplication.Model.PDFModel;
import com.example.elearningapplication.Quarter.QuarterTwo;

import java.util.ArrayList;

public class StartActivityClass extends AppCompatActivity {

    private ArrayList<AssessmentWeekName> mymodel;
    private RecyclerView myRecyclerView;
    private ItemClickListener listener;

    private Button okay_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        myRecyclerView = findViewById(R.id.recyclerViewWeekName);
        mymodel = new ArrayList<>();

        setAssessmentName();
        setAdapter();
        DiagramGuideShow();

    }

    private void setAdapter() {
        setOnclickListener();
        RecyclerAdapterAssessmentName myadapter = new RecyclerAdapterAssessmentName(mymodel,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setAdapter(myadapter);
    }

    private void setOnclickListener() {
        listener = new ItemClickListener() {
            @Override
            public void onItemClickLister(View view, int position) {
                //String item = mymodel.get(position).getPdftitle().toString();
//                Intent intent = new Intent(getApplicationContext(), ActivityClass.class);
//                intent.putExtra("AssessmentName",item);
//                startActivity(intent);

//                if (mymodel.get(position).equals(0)){
//                    startActivity(new Intent(StartActivityClass.this, ActivityClass.class));
//                } else if (mymodel.get(position).equals(1)) {
//                    startActivity(new Intent(StartActivityClass.this, ActivityClass2.class));
//
//                } else if (mymodel.get(position).equals(2)) {
//                    startActivity(new Intent(StartActivityClass.this, ActivityClass3.class));
//                }
                switch (position){
                    case 0:
                        startActivity(new Intent(StartActivityClass.this, ActivityClass.class));
                        break;

                    case 1:
                        startActivity(new Intent(StartActivityClass.this, ActivityClass2.class));
                        break;

                    case 2:
                        startActivity(new Intent(StartActivityClass.this, ActivityClass3.class));
                        break;

                    case 3:
                        startActivity(new Intent(StartActivityClass.this, ActivityClass4.class));
                        break;

                    case 4:
                        startActivity(new Intent(StartActivityClass.this, ActivityClass5.class));
                        break;

                    case 5:
                        startActivity(new Intent(StartActivityClass.this, ActivityClass6.class));
                        break;

                    case 6:
                        startActivity(new Intent(StartActivityClass.this, ActivityClass7.class));
                        break;
                }

            }
        };
    }

    private void setAssessmentName() {
        mymodel.add(new AssessmentWeekName("Assessment Week 1"));
        mymodel.add(new AssessmentWeekName("Assessment Week 2"));
        mymodel.add(new AssessmentWeekName("Assessment Week 3"));
        mymodel.add(new AssessmentWeekName("Assessment Week 4"));
        mymodel.add(new AssessmentWeekName("Assessment Week 5"));
        mymodel.add(new AssessmentWeekName("Assessment Week 6"));
        mymodel.add(new AssessmentWeekName("Assessment Week 7"));
    }

    private void DiagramGuideShow(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(StartActivityClass.this);
        LayoutInflater myinflater = getLayoutInflater();
        View mydialogView = myinflater.inflate(R.layout.guide_diagram,null);
        myBuilder.setCancelable(false);
        myBuilder.setView(mydialogView);

        okay_btn = mydialogView.findViewById(R.id.okaybtn);
        final AlertDialog alertDialogmyPicture = myBuilder.create();
        alertDialogmyPicture.show();
        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogmyPicture.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(StartActivityClass.this, QuarterTwo.class));

    }

    public void BacktoQ2(View view) {
        startActivity(new Intent(StartActivityClass.this, QuarterTwo.class));
    }
}
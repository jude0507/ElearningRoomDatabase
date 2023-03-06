package com.example.elearningapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ActivityClass extends AppCompatActivity {

    TextView txtQuestion;
    TextView textViewScore,textViewQuestionCount,textViewCountDownTimer;
    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rbGroup;
    Button buttonNext;
    boolean answered = false;

    List<ActivityQuestions> actQueList;
    ActivityQuestions currentQ;

    private int questionCounter=0,questionTotalCount;

    private ActivityViewModel activityViewModel;

    private ColorStateList textColorofButtons;

    private Handler handler = new Handler();

    private int correctAns = 0,wrongAns =0;

    private int score=0;

    private PlayAudioForAnswers playAudioForAnswers;

    private TimerDialog timerDialog;
    private WrongDialog wrongDialog;
    private CorrectDialog correctDialog;

    //private int totalSizeofQuiz;

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftinMillis;

    private int FLAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        //RegVariable();

        txtQuestion = findViewById(R.id.displayQuestions);
        textViewScore = findViewById(R.id.view_score);
        textViewQuestionCount = findViewById(R.id.number_of_question);
        textViewCountDownTimer = findViewById(R.id.timer);

        rbGroup = findViewById(R.id.radiogrp);
        rb1 = findViewById(R.id.opt1);
        rb2 = findViewById(R.id.opt2);
        rb3 = findViewById(R.id.opt3);
        rb4 = findViewById(R.id.opt4);

        buttonNext = findViewById(R.id.buttonConfirm);

        wrongDialog =  new WrongDialog(this);
        correctDialog = new CorrectDialog(this);
        timerDialog =  new TimerDialog(this);
        playAudioForAnswers = new PlayAudioForAnswers(this);

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel.class);
        activityViewModel.getmAllQuestions().observe(this, new Observer<List<ActivityQuestions>>() {
            @Override
            public void onChanged(List<ActivityQuestions> activityQuestions) {
                Toast.makeText(ActivityClass.this, "Let's Start", Toast.LENGTH_SHORT).show();
                getContent(activityQuestions);
            }
        });

    }
    private void getContent(List<ActivityQuestions> questionsList) {

        actQueList = questionsList;
        start();

    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    public void setQuestions(){

        rbGroup.clearCheck();
        rb1.setTextColor(R.color.activeColor);
        rb2.setTextColor(R.color.activeColor);
        rb3.setTextColor(R.color.activeColor);
        rb4.setTextColor(R.color.activeColor);

        questionTotalCount = actQueList.size();
        Collections.shuffle(actQueList);
        if (questionCounter < questionTotalCount -1){

            currentQ = actQueList.get(questionCounter);

            txtQuestion.setText(currentQ.getQuestions());
            rb1.setText(currentQ.getOption1());
            rb2.setText(currentQ.getOption2());
            rb3.setText(currentQ.getOption3());
            rb4.setText(currentQ.getOption4());
            questionCounter++;

            answered = false;


            buttonNext.setText("Confirm");

            textViewQuestionCount.setText("Questions: " + questionCounter +"/" +(questionTotalCount-1));

            timeLeftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();



        }else {

            Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            buttonNext.setClickable(false);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //resultData();
                    Toast.makeText(ActivityClass.this, "RESULT SHOW", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ActivityClass.this, StartActivityClass.class));

                }
            },2000);
        }

    }

//    void RegVariable() {
//        txtQuestion = findViewById(R.id.txtQuestion);
//        textViewScore = findViewById(R.id.view_score);
//        textViewQuestionCount = findViewById(R.id.number_of_question);
//        textViewCountDownTimer = findViewById(R.id.timer);
//
//        rbGroup = findViewById(R.id.radiogrp);
//        rb1 = findViewById(R.id.opt1);
//        rb2 = findViewById(R.id.opt2);
//        rb3 = findViewById(R.id.opt3);
//        rb4 = findViewById(R.id.opt4);
//
//        buttonNext = findViewById(R.id.buttonConfirm);
//
//    }

    private void start(){

        setQuestions();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId){

                    case R.id.opt1:

                        rb1.setTextColor(R.color.textcolor2);
                        rb2.setTextColor(R.color.activeColor);
                        rb3.setTextColor(R.color.activeColor);
                        rb4.setTextColor(R.color.activeColor);

                        break;

                    case R.id.opt2:

                        rb1.setTextColor(R.color.activeColor);
                        rb2.setTextColor(R.color.textcolor2);
                        rb3.setTextColor(R.color.activeColor);
                        rb4.setTextColor(R.color.activeColor);

                        break;

                    case R.id.opt3:

                        rb1.setTextColor(R.color.activeColor);
                        rb2.setTextColor(R.color.activeColor);
                        rb3.setTextColor(R.color.textcolor2);
                        rb4.setTextColor(R.color.activeColor);

                        break;

                    case R.id.opt4:

                        rb1.setTextColor(R.color.activeColor);
                        rb2.setTextColor(R.color.activeColor);
                        rb3.setTextColor(R.color.activeColor);
                        rb4.setTextColor(R.color.textcolor2);

                        break;

                }

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!answered){

                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        
                        CheckProcess();

                    }else {

                        Toast.makeText(ActivityClass.this, "Please Select Answer", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });

    }

    private void CheckProcess() {
        answered = true;

        countDownTimer.cancel();

        RadioButton rbselected =  findViewById(rbGroup.getCheckedRadioButtonId());

        int answerNr = rbGroup.indexOfChild(rbselected) +1;


        checkSolution(answerNr,rbselected);
    }

    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQ.getAnswer()) {

            case 1:

                if (currentQ.getAnswer() == answerNr) {

                    //rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb1.setTextColor(Color.GREEN);

                    correctAns++;


                    score +=10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));

                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);


                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;


                    final String correctAnswer = (String) rb1.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);


                }
                break;


            case 2:

                if (currentQ.getAnswer() == answerNr) {


                    //rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb2.setTextColor(Color.GREEN);

                    correctAns++;


                    score +=10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));

                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);




                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;


                    final String correctAnswer = (String) rb2.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);


                }
                break;


            case 3:

                if (currentQ.getAnswer() == answerNr) {


                    //rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb3.setTextColor(Color.GREEN);


                    correctAns++;


                    score +=10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));

                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);




                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;


                    final String correctAnswer = (String) rb3.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);



                }
                break;


            case 4:

                if (currentQ.getAnswer() == answerNr) {


                    //rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb4.setTextColor(Color.GREEN);


                    correctAns++;


                    score +=10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));

                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);



                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;


                    final String correctAnswer = (String) rb4.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);



                }
                break;
        }

    }

    private void changetoIncorrectColor(RadioButton rbselected) {
        //rbselected.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_wrong));
        rbselected.setTextColor(Color.RED);
    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftinMillis = 0;
                updateCountDownText();

            }
        }.start();

    }

    private void updateCountDownText() {

        int minutes = (int) (timeLeftinMillis/1000) /60;
        int seconds = (int) (timeLeftinMillis/1000) %60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);
        textViewCountDownTimer.setText(timeFormatted);


        if (timeLeftinMillis <10000){

            textViewCountDownTimer.setTextColor(Color.RED);

            FLAG = 3;

            playAudioForAnswers.setAudioforAnswers(FLAG);

        }else {

            textViewCountDownTimer.setTextColor(ContextCompat.getColor(this,R.color.activeColor));

        }


        if (timeLeftinMillis == 0){

            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    timerDialog.timerDialog();

                }
            },2000);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null){
            countDownTimer.cancel();
        }

        Log.i("DATATA","onDestroy ActivityClass");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DATATA","onStop in ActivityClass");
        finish();
    }


}

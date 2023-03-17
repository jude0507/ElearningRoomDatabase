package com.example.elearningapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class ActivityClass7 extends AppCompatActivity {

    TextView txtQuestion;
    TextView textViewScore,textViewQuestionCount,textViewCountDownTimer;
    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rbGroup;
    Button buttonNext;
    boolean answered = false;

    List<ActivityQuestions7> actQueList;
    ActivityQuestions7 currentQ;

    private int questionCounter=0,questionTotalCount;

    private ActivityViewModel7 activityViewModel;

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
        setContentView(R.layout.activity_class7);

        txtQuestion = findViewById(R.id.displayQuestionscl7);
        textViewScore = findViewById(R.id.view_scorecl7);
        textViewQuestionCount = findViewById(R.id.number_of_questioncl7);
        textViewCountDownTimer = findViewById(R.id.timercl7);

        rbGroup = findViewById(R.id.radiogrpcl7);
        rb1 = findViewById(R.id.opt1cl7);
        rb2 = findViewById(R.id.opt2cl7);
        rb3 = findViewById(R.id.opt3cl7);
        rb4 = findViewById(R.id.opt4cl7);

        buttonNext = findViewById(R.id.buttonConfirmcl7);

        //wrongDialog =  new WrongDialog(this);
        //correctDialog = new CorrectDialog(this);
        timerDialog =  new TimerDialog(this);
        playAudioForAnswers = new PlayAudioForAnswers(this);

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel7.class);
        activityViewModel.getmAllQuestions().observe(this, new Observer<List<ActivityQuestions7>>() {
            @Override
            public void onChanged(List<ActivityQuestions7> activityQuestions) {
                Toast.makeText(ActivityClass7.this, "Let's Start", Toast.LENGTH_SHORT).show();
                getContent(activityQuestions);
            }
        });

    }

    private void getContent(List<ActivityQuestions7> questionsList) {

        actQueList = questionsList;
        start();

    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    public void setQuestions(){

        rbGroup.clearCheck();
        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);

        questionTotalCount = actQueList.size();
        //Collections.shuffle(actQueList);
        if (questionCounter < questionTotalCount){

            currentQ = actQueList.get(questionCounter);

            txtQuestion.setText(currentQ.getQuestions());
            rb1.setText(currentQ.getOption1());
            rb2.setText(currentQ.getOption2());
            rb3.setText(currentQ.getOption3());
            rb4.setText(currentQ.getOption4());
            questionCounter++;

            answered = false;

            buttonNext.setText("Confirm");

            textViewQuestionCount.setText("Questions: " + questionCounter +"/" +(questionTotalCount));

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

                    DisplayDialogResult();
                    //Toast.makeText(ActivityClass.this, "RESULT SHOW", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(ActivityClass.this, StartActivityClass.class));

                }
            },2000);
        }

    }

    private void start(){

        setQuestions();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint({"ResourceAsColor", "NonConstantResourceId"})
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

                        Toast.makeText(ActivityClass7.this, "Please Select Answer", Toast.LENGTH_SHORT).show();

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

                    //correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);


                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;


                    //final String correctAnswer = (String) rb1.getText();
                    //wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);


                }
                break;


            case 2:

                if (currentQ.getAnswer() == answerNr) {


                    //rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb2.setTextColor(Color.GREEN);

                    correctAns++;


                    score +=10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));

                    // correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);


                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;


                    final String correctAnswer = (String) rb2.getText();
                    //wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);


                }
                break;


            case 3:

                if (currentQ.getAnswer() == answerNr) {


                    //rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb3.setTextColor(Color.GREEN);


                    correctAns++;


                    score +=10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));

                    //correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);



                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;

                    final String correctAnswer = (String) rb3.getText();
                    //wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);

                }
                break;


            case 4:

                if (currentQ.getAnswer() == answerNr) {

                    //rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb4.setTextColor(Color.GREEN);

                    correctAns++;

                    score +=10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));

                    //correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);


                } else {

                    changetoIncorrectColor(rbselected);

                    wrongAns++;

                    final String correctAnswer = (String) rb4.getText();
                    //wrongDialog.WrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);

                }
                break;
        }

    }

    @SuppressLint("ResourceAsColor")
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

            //Toast.makeText(this, "Next Q", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //timerDialog.timerDialog();

                    setQuestions();
                    //CheckProcess();

                }
            },2000);

        }

    }

//    private void results(){
//
//        Intent resultofQuiz = new Intent(ActivityClass.this,StartActivityClass.class);
//        resultofQuiz.putExtra("UserScore", score);
//        resultofQuiz.putExtra("TotalQuizQuestions",(questionTotalCount -1));
//        resultofQuiz.putExtra("CorrectQuestions",correctAns);
//        resultofQuiz.putExtra("WrongQuestions",wrongAns);
//        startActivity(resultofQuiz);
//
//    }

    private void DisplayDialogResult(){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(ActivityClass7.this);
        LayoutInflater myinflater = getLayoutInflater();
        View mydialogView = myinflater.inflate(R.layout.activity_result,null);
        myBuilder.setCancelable(false);
        myBuilder.setView(mydialogView);

        TextView Result_totalQ = mydialogView.findViewById(R.id.result_tv_Num_of_Ques);
        TextView Result_Correct = mydialogView.findViewById(R.id.result_tv_correct_Ques);
        TextView Result_Wrong = mydialogView.findViewById(R.id.result_tv_wrong_Ques);
        Button okay = mydialogView.findViewById(R.id.bt_result_main_menu);

        final AlertDialog alertDialogmyPicture = myBuilder.create();
        alertDialogmyPicture.show();

        Result_totalQ.setText("Total Questions: " + String.valueOf(questionTotalCount));
        Result_Correct.setText("Correct Questions: " + String.valueOf(correctAns));
        Result_Wrong.setText("Wrong Questions: " + String.valueOf(wrongAns));

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogmyPicture.dismiss();
                startActivity(new Intent(ActivityClass7.this, StartActivityClass.class));
            }
        });

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
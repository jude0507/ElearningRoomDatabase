package com.example.elearningapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion;
    TextView txtQuestionNum;
    TextView txtScore;
    TextView txtCorrect;
    TextView txtWrong;
    TextView txtTimer;
    RadioButton ansA, ansB, ansC, ansD;
    RadioGroup radioGroup;
    Button nextBtn;
    boolean answered = true;
    List<Questionnaire> questionnaireList;
    Questionnaire currentQuestion;
    private int questionCounter = 0, questionTotalCount;
    private ColorStateList textColorofButtons;
    private Handler handler = new Handler();
    private int correctAns = 0, wrongAns = 0, score = 0;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftinMillis;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setupUI();
        textColorofButtons = ansA.getTextColors();
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        questionViewModel.getmAllQuestions().observe(this, new Observer<List<Questionnaire>>() {
            @Override
            public void onChanged(List<Questionnaire> questionnaire) {

                Toast.makeText(QuizActivity.this, "Get IT :)", Toast.LENGTH_SHORT).show();
                fetchDataBase(questionnaire);
            }
        });
    }


    void setupUI() {
        txtCorrect = findViewById(R.id.txtCorrect);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtWrong = findViewById(R.id.txtWrong);
        txtScore = findViewById(R.id.txtScore);
        txtQuestionNum = findViewById(R.id.txtQuestionNum);
        txtTimer = findViewById(R.id.txtTime);
        radioGroup = findViewById(R.id.radioGroup);
        ansA = findViewById(R.id.ansA);
        ansA = findViewById(R.id.ansB);
        ansA = findViewById(R.id.ansC);
        ansA = findViewById(R.id.ansD);
        nextBtn = findViewById(R.id.nextBtn);
    }

    private void fetchDataBase(List<Questionnaire> questionnaire) {
        questionnaireList = questionnaire;
        startQuiz();

    }


    @SuppressLint("SetTextI18n")
    private void setQuestions() {
        radioGroup.clearCheck();
        ansA.setTextColor(Color.BLACK);
        ansB.setTextColor(Color.BLACK);
        ansC.setTextColor(Color.BLACK);
        ansD.setTextColor(Color.BLACK);
        questionTotalCount = questionnaireList.size();
        Collections.shuffle(questionnaireList);
        if (questionCounter < questionTotalCount - 1) {
            currentQuestion = questionnaireList.get(questionCounter);

            txtQuestion.setText(currentQuestion.getQuestion());
            ansA.setText(currentQuestion.getAnsA());
            ansB.setText(currentQuestion.getAnsB());
            ansC.setText(currentQuestion.getAnsC());
            ansD.setText(currentQuestion.getAnsD());
            questionCounter++;

            answered = false;

            nextBtn.setText("Confirm");

            txtQuestionNum.setText("Questions: " + questionCounter + "/" + (questionTotalCount - 1));

            timeLeftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
            startActivity(intent);
        }
    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftinMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftinMillis = l;
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
        int minutes = (int) (timeLeftinMillis / 1000) / 60;
        int seconds = (int) (timeLeftinMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "", minutes, seconds);
        txtTimer.setText(timeFormatted);

        if (timeLeftinMillis < 10000) {
            txtTimer.setTextColor(Color.RED);
        } else {
            txtTimer.setTextColor(Color.BLACK);
        }

        if (timeLeftinMillis == 0) {
            Toast.makeText(this, "Time's UP", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void startQuiz() {
        setQuestions();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.ansA:
                        ansA.setTextColor(Color.CYAN);
                        ansB.setTextColor(Color.GRAY);
                        ansC.setTextColor(Color.GRAY);
                        ansD.setTextColor(Color.GRAY);
                        break;
                    case R.id.ansB:
                        ansB.setTextColor(Color.CYAN);
                        ansA.setTextColor(Color.GRAY);
                        ansC.setTextColor(Color.GRAY);
                        ansD.setTextColor(Color.GRAY);
                        break;
                    case R.id.ansC:
                        ansC.setTextColor(Color.CYAN);
                        ansB.setTextColor(Color.GRAY);
                        ansA.setTextColor(Color.GRAY);
                        ansD.setTextColor(Color.GRAY);
                        break;
                    case R.id.ansD:
                        ansD.setTextColor(Color.CYAN);
                        ansB.setTextColor(Color.GRAY);
                        ansC.setTextColor(Color.GRAY);
                        ansA.setTextColor(Color.GRAY);
                        break;
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (ansA.isChecked() || ansB.isChecked() || ansC.isChecked() || ansD.isChecked()) {
                        quizOperation();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void quizOperation() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rbSelected) + 1;

        checkSolution(answerNr, rbSelected);
    }

    @SuppressLint("SetTextI18n")
    private void checkSolution(int answerNr, RadioButton rbSelected) {
        switch (currentQuestion.getAnswer()) {
            case 1:
                if (currentQuestion.getAnswer() == answerNr) {
                    ansA.setTextColor(Color.GREEN);
                    correctAns++;
                    score += 10;
                    txtScore.setText("Score: " + score);
                    txtCorrect.setText("Correct: " + correctAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);

                } else {
                    ansA.setTextColor(Color.RED);
                    wrongAns++;
                    txtWrong.setText("Wrong: " + wrongAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);

                }
                break;
            case 2:
                if (currentQuestion.getAnswer() == answerNr) {
                    ansA.setTextColor(Color.GREEN);
                    correctAns++;
                    score += 10;
                    txtScore.setText("Score: " + score);
                    txtCorrect.setText("Correct: " + correctAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);
                } else {
                    ansB.setTextColor(Color.RED);
                    wrongAns++;
                    txtWrong.setText("Wrong: " + wrongAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);

                }
                break;
            case 3:
                if (currentQuestion.getAnswer() == answerNr) {
                    ansA.setTextColor(Color.GREEN);
                    correctAns++;
                    score += 10;
                    txtScore.setText("Score: " + score);
                    txtCorrect.setText("Correct: " + correctAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);
                } else {
                    ansC.setTextColor(Color.RED);
                    wrongAns++;
                    txtWrong.setText("Wrong: " + wrongAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);

                }
                break;
            case 4:
                if (currentQuestion.getAnswer() == answerNr) {
                    ansA.setTextColor(Color.GREEN);
                    correctAns++;
                    score += 10;
                    txtScore.setText("Score: " + score);
                    txtCorrect.setText("Correct: " + correctAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);
                } else {
                    ansD.setTextColor(Color.RED);
                    wrongAns++;
                    txtWrong.setText("Wrong: " + wrongAns);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 2000);

                }
                break;
        }
        if (questionCounter < questionTotalCount) {
            nextBtn.setText("Confirm and Finish");
        }
    }
}
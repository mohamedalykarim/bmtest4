package bmtestv4.android.mohalim.bmtestv4;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bmtestv4.android.mohalim.bmtestv4.Adapters.CorrectAnswerAdapter;
import bmtestv4.android.mohalim.bmtestv4.Adapters.MainRecyclerViewAdapter;
import bmtestv4.android.mohalim.bmtestv4.Result.Circle;
import bmtestv4.android.mohalim.bmtestv4.Result.CircleAngleAnimation;
import bmtestv4.android.mohalim.bmtestv4.Result.CorrectAnswer;

public class ResultActivity extends AppCompatActivity {
    TextView mTvResult;
    Button mAllQuestionBtn, mWrongQuestionBtn;

    ArrayList<CurrentSession> mCurrrentSession;
    int result, correctCount;
    int angle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        mAllQuestionBtn = findViewById(R.id.btn_all_questions);
        mWrongQuestionBtn = findViewById(R.id.btn_wrong_question);





        mTvResult = findViewById(R.id.tv_result);
        Circle circle = (Circle) findViewById(R.id.circle);


        Intent intent = getIntent();
        mCurrrentSession = intent.getParcelableArrayListExtra("current_session");
        result = intent.getIntExtra("result",0);
        correctCount = intent.getIntExtra("correct",0);

        mTvResult.setText(result + "%");




        mAllQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ResultActivity.this,android.R.style.Theme_Light_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.all_question_result);
                dialog.show();
                RecyclerView mRecyclerView = dialog.findViewById(R.id.recyclerview_all_questions);

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(dialog.getContext(), LinearLayoutManager.VERTICAL, false);

                mRecyclerView.setLayoutManager(layoutManager);

                mRecyclerView.setHasFixedSize(true);

                ArrayList<CorrectAnswer> correctAnswers = new ArrayList<>();

                CorrectAnswerAdapter correctAnswerAdapter = new CorrectAnswerAdapter(correctAnswers,ResultActivity.this);
                mRecyclerView.setAdapter(correctAnswerAdapter);

                for (int i=0; i< mCurrrentSession.size(); i++){
                    String[] choices = null;
                    int correctNumber = 0;

                    String question = mCurrrentSession.get(i).getQuestionText();
                    correctNumber = mCurrrentSession.get(i).getCorrectAnswer();

                    choices = mCurrrentSession.get(i).getChoices();

                    if (correctNumber == 1){
                        correctNumber = 0;
                    }else if (correctNumber == 2){
                        correctNumber = 2;
                    }else if (correctNumber == 3){
                        correctNumber = 4;
                    }else if (correctNumber == 4){
                        correctNumber = 6;
                    }else if (correctNumber == 5){
                        correctNumber = 8;
                    }

                    String choicesString = "";
                    if (choices.length == 2){
                        choicesString = choices[0] ;
                    }else if(choices.length == 4){
                        choicesString = choices[0] + "\n";
                        choicesString += choices[2];
                    }else if(choices.length == 6){
                        choicesString = choices[0] + "\n";
                        choicesString += choices[2]+ "\n";
                        choicesString += choices[4];
                    }else if(choices.length == 8){
                        choicesString = choices[0] + "\n";
                        choicesString += choices[2]+ "\n";
                        choicesString += choices[4]+ "\n";
                        choicesString += choices[6];
                    }else if(choices.length == 10){
                        choicesString = choices[0] + "\n";
                        choicesString += choices[2]+ "\n";
                        choicesString += choices[4]+ "\n";
                        choicesString += choices[6]+ "\n";
                        choicesString += choices[8];

                    }




                    String correctChoice = "الاجابة الصحيحة :" + choices[correctNumber];


                    CorrectAnswer correctAnswer = new CorrectAnswer(question,correctChoice,choicesString);
                    correctAnswers.add(correctAnswer);

                }

                correctAnswerAdapter.setCorrectAnswer(correctAnswers);


            }
        });

        mWrongQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(ResultActivity.this,android.R.style.Theme_Light_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.wrong_question_result);
                dialog.show();

                RecyclerView mRecyclerView = dialog.findViewById(R.id.recyclerview_wrong_questions);

                LinearLayoutManager layoutManager
                        = new LinearLayoutManager(dialog.getContext(), LinearLayoutManager.VERTICAL, false);

                mRecyclerView.setLayoutManager(layoutManager);

                mRecyclerView.setHasFixedSize(true);

                ArrayList<CorrectAnswer> correctAnswers = new ArrayList<>();

                CorrectAnswerAdapter correctAnswerAdapter = new CorrectAnswerAdapter(correctAnswers,ResultActivity.this);
                mRecyclerView.setAdapter(correctAnswerAdapter);

                for (int i=0; i< mCurrrentSession.size(); i++){
                    if (mCurrrentSession.get(i).getCorrectAnswer() != mCurrrentSession.get(i).getChosenAnswer()){
                        String[] choices = null;
                        int correctNumber = 0;

                        String question = mCurrrentSession.get(i).getQuestionText();
                        correctNumber = mCurrrentSession.get(i).getCorrectAnswer();

                        choices = mCurrrentSession.get(i).getChoices();

                        if (correctNumber == 1){
                            correctNumber = 0;
                        }else if (correctNumber == 2){
                            correctNumber = 2;
                        }else if (correctNumber == 3){
                            correctNumber = 4;
                        }else if (correctNumber == 4){
                            correctNumber = 6;
                        }else if (correctNumber == 5){
                            correctNumber = 8;

                        }

                        String choicesString = "";
                        if (choices.length == 2){
                            choicesString = choices[0] ;
                        }else if(choices.length == 4){
                            choicesString = choices[0] + "\n";
                            choicesString += choices[2];
                        }else if(choices.length == 6){
                            choicesString = choices[0] + "\n";
                            choicesString += choices[2]+ "\n";
                            choicesString += choices[4];
                        }else if(choices.length == 8){
                            choicesString = choices[0] + "\n";
                            choicesString += choices[2]+ "\n";
                            choicesString += choices[4]+ "\n";
                            choicesString += choices[6];
                        }else if(choices.length == 10){
                            choicesString = choices[0] + "\n";
                            choicesString += choices[2]+ "\n";
                            choicesString += choices[4]+ "\n";
                            choicesString += choices[6]+ "\n";
                            choicesString += choices[8];

                        }

                        String correctChoice = "الاجابة الصحيحة :" + choices[correctNumber];


                        CorrectAnswer correctAnswer = new CorrectAnswer(question,correctChoice,choicesString);
                        correctAnswers.add(correctAnswer);
                    }

                }

                if (correctAnswers.size() == 0){
                    correctAnswers.add(new CorrectAnswer("لا يوجد اسئلة خطأ", "",""));
                }
                correctAnswerAdapter.setCorrectAnswer(correctAnswers);

            }
        });


        angle = 360 * result / 100;





        if (result < 50){
            circle.setColor(Color.parseColor("#a01e0c"));
        }else if (result >= 50 && result < 70){
            circle.setColor(Color.parseColor("#ffb600"));
        }else if (result >= 70){
            circle.setColor(Color.parseColor("#155300"));
        }

        CircleAngleAnimation animation = new CircleAngleAnimation(circle, angle);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTvResult.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        circle.startAnimation(animation);


    }






}

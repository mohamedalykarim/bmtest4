package bmtestv4.android.mohalim.bmtestv4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import bmtestv4.android.mohalim.bmtestv4.Result.Circle;
import bmtestv4.android.mohalim.bmtestv4.Result.CircleAngleAnimation;

public class ResultActivity extends AppCompatActivity {
    TextView mTvResult;
    Button mAllQuestionBtn, mWrongQuestionBtn;
    int[] allQuestionsId , wrongQuestionsId;

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
        int notCorrect = mCurrrentSession.size() - correctCount;

        mTvResult.setText(result + "%");


        allQuestionsId = new int[mCurrrentSession.size()];
        for (int i=0; i<mCurrrentSession.size();i++){

        }

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

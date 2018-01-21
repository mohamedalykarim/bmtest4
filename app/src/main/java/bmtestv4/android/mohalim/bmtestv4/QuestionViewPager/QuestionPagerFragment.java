package bmtestv4.android.mohalim.bmtestv4.QuestionViewPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bmtestv4.android.mohalim.bmtestv4.CurrentSession;
import bmtestv4.android.mohalim.bmtestv4.Database.SessionSQLiteHelper;
import bmtestv4.android.mohalim.bmtestv4.QuizSessionActivity;
import bmtestv4.android.mohalim.bmtestv4.R;

/**
 * Created by Mohamed ALi on 1/13/2018.
 */

public class QuestionPagerFragment extends Fragment {
    public static ArrayList<CurrentSession> mCurrentSession;
    CurrentSession currentSessionItem;
    int pageNumber;

    TextView questionNumber,questionText;
    // Choices
    RadioGroup mRadioGroubChoices;
    int child= 0;
    SessionSQLiteHelper database;


    RadioButton rb[];


    public static QuestionPagerFragment newInstance(ArrayList<CurrentSession> currentSessions, int position){
        QuestionPagerFragment questionPagerFragment = new QuestionPagerFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("current_session",currentSessions);
        args.putInt("position", position);
        questionPagerFragment.setArguments(args);
        return questionPagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentSession = getArguments().getParcelableArrayList("current_session");
        pageNumber      = getArguments().getInt("position");
        rb = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.questions_pager_row, container, false);
        view.setRotation(180);
        questionNumber = view.findViewById(R.id.tv_question_number);
        questionText = view.findViewById(R.id.tv_question_text);
        mRadioGroubChoices = view.findViewById(R.id.rg_choices);
        database = new SessionSQLiteHelper(getContext());




        // The title: "السؤال رقم"
        questionNumber.setText(String.valueOf(pageNumber+1));

        // Question text
        questionText.setText(mCurrentSession.get(pageNumber).getQuestionText());

        currentSessionItem = mCurrentSession.get(pageNumber);










        final int choiceCount = (mCurrentSession.get(pageNumber).getChoices().length)/2;
        rb = new RadioButton[choiceCount];

        // Initiate Radio Buttons
        for(int i=0; i<choiceCount; i++){
            rb[i]  = new RadioButton(getContext());
            mRadioGroubChoices.addView(rb[i]); //the RadioButtons are added to the radioGroup instead of the layout

            //RadioButton Style
            rb[i].setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rb[i].setBackgroundResource(R.drawable.rounded3);
            ViewGroup.MarginLayoutParams rbMargin = (ViewGroup.MarginLayoutParams) rb[i].getLayoutParams();
            rbMargin.setMargins(0,5,0,0);
            rb[i].requestLayout();

            // prepare the answered questions
            for (int z=0; z<mCurrentSession.size(); z++){
                if (mCurrentSession.get(z).getCorrectAnswer() > 0){
                    if (i == mCurrentSession.get(z).getCorrectAnswer() -1){
                        rb[0].setSelected(true);
                    }
                }
            }

            if (i == 0){
                rb[i].setText(mCurrentSession.get(pageNumber).getChoices()[i]);
            }else {
                rb[i].setText(mCurrentSession.get(pageNumber).getChoices()[i*2]);
            }

            rb[i].setId(i);
        }


        int chosenAnswer = currentSessionItem.getChosenAnswer();
        int isAnswered = currentSessionItem.getIsAnswered();

        if (chosenAnswer > 0 && isAnswered == 1){
            rb[chosenAnswer-1].setChecked(true);
            rb[chosenAnswer-1].setBackgroundResource(R.drawable.rounded6);

        }


        mRadioGroubChoices.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                // Style on press radio button
                for(int m=0; m<choiceCount; m++){
                    rb[m].setBackgroundResource(R.drawable.rounded3);
                }
                rb[i].setBackgroundResource(R.drawable.rounded6);

                // update the database
                int chosenAnswer = i+1;
                int questionID = currentSessionItem.getQuestionId();
                int sessionID = currentSessionItem.getSessionId();

                // Update the current session Array list
                currentSessionItem.setIsAnswered(1);
                currentSessionItem.setChosenAnswer(chosenAnswer);

                // Update chosen Answer on the database
                database.updateQuestion(questionID,sessionID,chosenAnswer);


                // change menu icon
                Menu menu = QuizSessionActivity.mNavigationView.getMenu();
                if (mCurrentSession.get(pageNumber).getIsAnswered() == 1){
                    menu.getItem(pageNumber).setIcon(R.drawable.answered);
                }else{
                    menu.getItem(pageNumber).setIcon(R.drawable.not_answered);
                }


            }
        });


        return view;
    }

}

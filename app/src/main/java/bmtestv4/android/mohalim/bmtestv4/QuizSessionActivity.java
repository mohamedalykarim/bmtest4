package bmtestv4.android.mohalim.bmtestv4;




import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import bmtestv4.android.mohalim.bmtestv4.Database.SessionSQLiteHelper;
import bmtestv4.android.mohalim.bmtestv4.QuestionViewPager.QuestionPagerFragment;

public class QuizSessionActivity extends AppCompatActivity {

    Context mContext;

    private DrawerLayout mDrawerLayout;
    public static NavigationView mNavigationView;
    List<MenuItem> menuItems;


    // database variables
    SessionSQLiteHelper database;
    Sessions sessions;
    ArrayList<CurrentSession> currentSessionArrayList;

    // View Pager
    MyPagerAdabter mQuestionAdapter;
    public static ViewPager mQuestionViewPager;

    // Choices
    public static RadioGroup mRadioGroubChoices;

    // End the Session
    Button mEndSessionBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_session);
        mContext = this;



        database = new SessionSQLiteHelper(getApplicationContext());
        sessions = new Sessions(this);
        currentSessionArrayList = new ArrayList<>();


        mQuestionViewPager = findViewById(R.id.question_view_pager);
        mQuestionViewPager.setRotation(180);


        // Navigation Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setItemBackgroundResource(R.drawable.rounded5);
        menuItems=new ArrayList<>();
        doCategory(2001,20);

        //End the session
        mEndSessionBtn = (Button)findViewById(R.id.btn_end_session);
        mEndSessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // if there is a current session
                if (QuestionPagerFragment.mCurrentSession != null){
                    // variable to check if there is unanswered questions
                    ArrayList<Integer> isNotAnsweredArray = new ArrayList<>();
                    //variable for question count
                    final int questionCount = QuestionPagerFragment.mCurrentSession.size();
                    // Variable for correct question count
                    int correctAnswer = 0;

                    // Loop in the question
                    for (int i=0; i < questionCount; i++){

                        CurrentSession currenITem = QuestionPagerFragment.mCurrentSession.get(i);

                        // check if is answered
                        int isAnswered = currenITem.getIsAnswered();
                        if (isAnswered != 1){
                            isNotAnsweredArray.add(i);
                        }

                        // check if is correct
                        if (currenITem.getChosenAnswer() == currenITem.getCorrectAnswer()){
                            correctAnswer++;
                        }

                    }

                    final double result = (double) correctAnswer / (double) questionCount;
                    final int intResult = (int) (result * 100);


                    // check if there is no unanswered question
                    if (isNotAnsweredArray.size()>0){

                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(mContext);
                        }
                        builder.setMessage("رجاء الاجابة على جميع الاسئلة").show();

                    }else{
                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(mContext);
                        }
                        final int finalCorrectAnswer = correctAnswer;
                        builder.setMessage("هل انت متأكد من انهاء الجلسة ؟ ")
                                .setPositiveButton("قم بانهاء الجلسة", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(mContext,ResultActivity.class);
                                        intent.putExtra("current_session",QuestionPagerFragment.mCurrentSession);
                                        intent.putExtra("result", intResult);
                                        intent.putExtra("correct", finalCorrectAnswer);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setNegativeButton("لا تنهي الجلسة", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                        }
                    }



            }
        });

    }

    public void doCategory(int _category, int _questionCount){
        if (database.isCategoryHasSession(_category)){
            // category has session
            if (database.isCategoryHasOpenSession(_category)){
                // Category has Open Session
                // check the questions
                if (!database.isQuestionsForSession(database.getSessionIdFromCategory(_category))){
                    createQuestions(_category,_questionCount);
                }
                CompleteSessionParams completeSessionParams = new CompleteSessionParams(_category,_questionCount);
                new CompleteSessionAsyncTask().execute(completeSessionParams);

            }else{
                // Category has Closed Session
                if (database.isQuestionsForSession(database.getSessionIdFromCategory(_category))){
                    database.deleteQuestionsFromSession(_category);
                }

                Toast.makeText(this, "Category has Closed Session", Toast.LENGTH_SHORT).show();
            }
        }else{
            //category has no session
            if (database.isQuestionsForSession(database.getSessionIdFromCategory(_category))){
                // There are questions for the category
                Toast.makeText(this, "There are questions for the category", Toast.LENGTH_SHORT).show();
            }else{
                //There are no questions for the category
                Toast.makeText(this, "There are no questions for the category", Toast.LENGTH_SHORT).show();
                createSession(_category);
                createQuestions(_category, _questionCount);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        currentSessionArrayList.clear();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        currentSessionArrayList.clear();
    }

    void resumeSession(int _category){
    }

    public void completeTheSession(int _category, int _questionCount){
        final ArrayList<String> questions_id = new ArrayList<>();
        questions_id.addAll(database.getQuestionsFromSession(_category));

        // Get Session ID
        int sessionId = database.getIsSession(_category)[0];



        for (int i=0; i<questions_id.size(); i++){
            int chosenAnswer = database.getChosenAnswer(Integer.valueOf(questions_id.get(i)),sessionId);

            final String question_text = sessions.getQuestionTextForId(
                    Integer.valueOf(questions_id.get(i)),
                    sessions.readQuestionJSONFromAssets(this),
                    _category);



            final ArrayList<String> choices = sessions.getChoicesTextForId(
                    Integer.valueOf(questions_id.get(i)),
                    sessions.readChoicesJSONFromAssets(this),
                    _category
                    );
            int correctAnswer = 0;
            int isAnswered = 0;

            if (chosenAnswer > 0) {
                isAnswered = 1;

                makeCurrentSessionArrayList(
                        sessionId,
                        Integer.valueOf(questions_id.get(i)),
                        isAnswered,
                        chosenAnswer,
                        choices,
                        question_text,
                        correctAnswer);
            }else{
                // if question not answered yet
                makeCurrentSessionArrayList(
                        sessionId,
                        Integer.valueOf(questions_id.get(i)),
                        0,
                        0,
                        choices,
                        question_text,
                        correctAnswer);
            }


        }










        QuizSessionActivity.this.runOnUiThread(new Runnable() {
            public void run() {

                // Navigation View Pager
                Menu menu = mNavigationView.getMenu();
                for (int i = 1; i <= currentSessionArrayList.size(); i++) {
                    menu.add("السؤال رقم "+ i);

                }

                FragmentManager fm = getSupportFragmentManager();


                mQuestionAdapter = new MyPagerAdabter(fm,currentSessionArrayList);
                mQuestionViewPager.setAdapter(mQuestionAdapter);







                //setup Navigation drawer programmatically
                menu=mNavigationView.getMenu();

                // Get the ID of the menu in the navigation view pager
                for(int i=0; i<menu.size(); i++){
                    menuItems.add(menu.getItem(i));
                    if (currentSessionArrayList.get(i).getIsAnswered() == 1){
                        menu.getItem(i).setIcon(R.drawable.answered);
                    }else{
                        menu.getItem(i).setIcon(R.drawable.not_answered);
                    }
                }

                mNavigationView.setNavigationItemSelectedListener(
                        new NavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                int position=menuItems.indexOf(item);
                                mQuestionViewPager.setCurrentItem(position);
                                mDrawerLayout.closeDrawers();
                                return true;
                            }
                        }
                );


            }
        });


    }


    public void createQuestions(int _category, int _questionCount){
        String json = sessions.readQuestionJSONFromAssets(this);
        int[] questions = sessions.getRandomQuestionFromCategory(_category,json,_questionCount);
        int[] limitedQuestions = new int[_questionCount];
        for (int i=0; i<limitedQuestions.length; i++){
            limitedQuestions[i] = questions[i];
        }
        Collections.shuffle(Arrays.asList(limitedQuestions));
        sessions.setQuestionSessionForSpecificCategory(_category,limitedQuestions);
    }

    public void createSession(int category){
        database.createIsSession(category);
    }


    // Initiate the current session Arraylist
    public void makeCurrentSessionArrayList(int sessionID, int questionID, int isAnswered,
                                            int chosenAnswer, ArrayList<String> choices, String question_text, int correctAnswer){
        //If question have one choice
        if (choices.size() == 2){
            String[] allChoices= {choices.get(0),choices.get(1)};
            if (allChoices[1].equals("1")){
                correctAnswer = 1;
            }

            currentSessionArrayList.add(new CurrentSession(question_text,correctAnswer,
                    isAnswered,0, chosenAnswer, sessionID,questionID,allChoices));

            //If question have two choice
        }else if (choices.size() == 4){
            String[] allChoices= {choices.get(0),choices.get(1),choices.get(2),choices.get(3)};
            if (allChoices[1].equals("1")){
                correctAnswer = 1;
            }else if (allChoices[3].equals("1")){
                correctAnswer = 2;
            }

            currentSessionArrayList.add(new CurrentSession(question_text,correctAnswer,
                    isAnswered,0,chosenAnswer, sessionID, questionID, allChoices));

            //If question have three choice
        }else if (choices.size() == 6){
            String[] allChoices= {choices.get(0),choices.get(1),choices.get(2),choices.get(3)
                    ,choices.get(4),choices.get(5)
            };
            if (allChoices[1].equals("1")){
                correctAnswer = 1;
            }else if (allChoices[3].equals("1")){
                correctAnswer = 2;
            }else if (allChoices[5].equals("1")){
                correctAnswer = 3;
            }

            currentSessionArrayList.add(new CurrentSession(question_text,correctAnswer,
                    isAnswered,0,chosenAnswer, sessionID, questionID,allChoices));

            //If question have four choice
        }else if (choices.size() == 8){
            String[] allChoices= {choices.get(0),choices.get(1),choices.get(2),choices.get(3)
                    ,choices.get(4),choices.get(5),choices.get(6),choices.get(7)
            };
            if (allChoices[1].equals("1")){
                correctAnswer = 1;
            }else if (allChoices[3].equals("1")){
                correctAnswer = 2;
            }else if (allChoices[5].equals("1")){
                correctAnswer = 3;
            }else if (allChoices[7].equals("1")){
                correctAnswer = 4;
            }

            currentSessionArrayList.add(new CurrentSession(question_text,correctAnswer,
                    isAnswered,0,chosenAnswer, sessionID, questionID,allChoices));

            //If question have five choice
        }else if (choices.size() == 10){
            String[] allChoices= {choices.get(0),choices.get(1),choices.get(2),choices.get(3)
                    ,choices.get(4),choices.get(5),choices.get(6),choices.get(7)
                    ,choices.get(8),choices.get(9)
            };
            if (allChoices[1].equals("1")){
                correctAnswer = 1;
            }else if (allChoices[3].equals("1")){
                correctAnswer = 2;
            }else if (allChoices[5].equals("1")){
                correctAnswer = 3;
            }else if (allChoices[7].equals("1")){
                correctAnswer = 4;
            }else if (allChoices[9].equals("1")){
                correctAnswer = 5;
            }

            currentSessionArrayList.add(new CurrentSession(question_text,correctAnswer,
                    isAnswered,0,chosenAnswer, sessionID, questionID,allChoices));
        }
    }

    class CompleteSessionAsyncTask extends AsyncTask<CompleteSessionParams,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(CompleteSessionParams... completeSessionParams) {
            completeTheSession(completeSessionParams[0].get_category(),
                    completeSessionParams[0].get_questionCount());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

    class CompleteSessionParams{
        int _category,_questionCount;

        public CompleteSessionParams(int _category, int _questionCount) {
            this._category = _category;
            this._questionCount = _questionCount;
        }

        public int get_category() {
            return _category;
        }

        public void set_category(int _category) {
            this._category = _category;
        }

        public int get_questionCount() {
            return _questionCount;
        }

        public void set_questionCount(int _questionCount) {
            this._questionCount = _questionCount;
        }
    }

    public static class MyPagerAdabter extends FragmentPagerAdapter{
        ArrayList<CurrentSession> mCurrentSession;

        public MyPagerAdabter(FragmentManager fm, ArrayList<CurrentSession> cs) {
            super(fm);
            mCurrentSession = cs;
        }

        @Override
        public Fragment getItem(int position) {
            return QuestionPagerFragment.newInstance(mCurrentSession, position);
        }

        @Override
        public int getCount() {
            return mCurrentSession.size();
        }
    }







}

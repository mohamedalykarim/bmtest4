package bmtestv4.android.mohalim.bmtestv4;




import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

    private static final int lOADER_ID = 654;


    int sessionCategory, questionCount;

    // database variables
    static SessionSQLiteHelper database;
    Sessions sessions;
    ArrayList<CurrentSession> currentSessionArrayList;

    // View Pager
    MyPagerAdabter mQuestionAdapter;
    public static ViewPager mQuestionViewPager;


    // End the Session
    Button mEndSessionBtn;


    // Loading
    boolean shouldAllowBack = false;


    private static final String ARRAYLIST_CALLBACKS_TEXT_JEY = "callbacks";



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


        sessionCategory = getIntent().getIntExtra("category",0);
        if (sessionCategory < 1){
            sessionCategory = 2001;
        }
        questionCount = 10;

        // المشروعات المتناهية الصغر
        if (sessionCategory == 2001) questionCount = 178;
        // ائتمان التجزئة المصرفية والبطاقات الائتمانية
        if (sessionCategory == 2002) questionCount = 40;
        // التوفير والاحوال الشخصية
        if (sessionCategory == 2003) questionCount = 46;
        // القروض الشخصية والتمويل العقاري
        if (sessionCategory == 2004) questionCount = 26;
        // الحسابات العامة
        if (sessionCategory == 2010) questionCount = 45;
        // الخزينة والتللر
        if (sessionCategory == 2011) questionCount = 67;
        // الحسابات الجارية والشيكات
        if (sessionCategory == 2012) questionCount = 125;
        // التأمين البنكي
        if (sessionCategory == 2013) questionCount = 25;
        // التحاويل الداخلية
        if (sessionCategory == 2014) questionCount = 31;
        // الاوراق التجارية
        if (sessionCategory == 2015) questionCount = 23;
        // الالتزام المصرفي
        if (sessionCategory == 2016) questionCount = 29;
        // شهادات الادخار
        if (sessionCategory == 2017) questionCount = 93;
        // خطابات الضمان
        if (sessionCategory == 2018) questionCount = 86;
        // BM VIP
        if (sessionCategory == 2019) questionCount = 30;
        // المشروعات الصغيرة والمتوسطة
        if (sessionCategory == 2020) questionCount = 42;
        // البطاقات
        if (sessionCategory == 2021) questionCount = 47;
        // خزن الامانات
        if (sessionCategory == 2022) questionCount = 38;
        // الات البنك الشخصي
        if (sessionCategory == 2023) questionCount = 46;
        // النواحى النظريه للاعتمادات المستنديه استيراد - تصدير
        if (sessionCategory == 2024) questionCount = 22;
        // الاعتمادات المستندية استيراد
        if (sessionCategory == 2025) questionCount = 20;





        // فريق العمل
        if (sessionCategory == 3001) questionCount = 76;
        // التواصل الفعال
        if (sessionCategory == 3002) questionCount = 88;
        // خدمة العملاء
        if (sessionCategory == 3003) questionCount = 67;
        // ادارة الوقت
        if (sessionCategory == 3004) questionCount = 50;
        // حل المشكلات
        if (sessionCategory == 3005) questionCount = 67;
        // المهارات الاشرافية
        if (sessionCategory == 3005) questionCount = 32;


        // مخاطر التشغيل
        if (sessionCategory == 3201) questionCount = 63;
        // الميثاق
        if (sessionCategory == 3202) questionCount = 40;

        // القروض النقدية بدون ضمان عيني
        if (sessionCategory == 4001) questionCount = 53;
        // القروض النقدية بضمان عيني
        if (sessionCategory == 4002) questionCount = 25;
        // القروض النقدية بضمان عيني
        if (sessionCategory == 4004) questionCount = 35;
        // التمويل العقاري للافراد
        if (sessionCategory == 2525) questionCount = 23;
        // التمويل العقاري تشطيل
        if (sessionCategory == 2527) questionCount = 13;
        // التمويل العقاري بنك مركزي
        if (sessionCategory == 2526) questionCount = 27;

        // مصرفي ممتاز
        if (sessionCategory == 1001) questionCount = 30;



        if (savedInstanceState != null)
        {
            if (savedInstanceState.containsKey(ARRAYLIST_CALLBACKS_TEXT_JEY)){
               currentSessionArrayList = savedInstanceState.getParcelableArrayList(ARRAYLIST_CALLBACKS_TEXT_JEY);
            }

        }


        //End the session
        mEndSessionBtn = (Button)findViewById(R.id.btn_end_session);
        mEndSessionBtn.setClickable(false);
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
                                        database.deleteQuestionsFromSession(sessionCategory);
                                        database.updateIsSession(sessionCategory,0);
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

        doCategory(sessionCategory,questionCount);


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
                database.updateIsSession(_category,1);
                createQuestions(_category, _questionCount);
                CompleteSessionParams completeSessionParams = new CompleteSessionParams(_category,_questionCount);
                new CompleteSessionAsyncTask().execute(completeSessionParams);


            }
        }else{
            //category has no session
            if (database.isQuestionsForSession(database.getSessionIdFromCategory(_category))){
                // There are questions for the category
                database.deleteQuestionsFromSession(_category);
                createSession(_category);
                createQuestions(_category, _questionCount);
                CompleteSessionParams completeSessionParams = new CompleteSessionParams(_category,_questionCount);

                new CompleteSessionAsyncTask().execute(completeSessionParams);

            }else{
                //There are no questions for the category
                createSession(_category);
                createQuestions(_category, _questionCount);
                CompleteSessionParams completeSessionParams = new CompleteSessionParams(_category,_questionCount);

                new CompleteSessionAsyncTask().execute(completeSessionParams);

            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARRAYLIST_CALLBACKS_TEXT_JEY,currentSessionArrayList);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

        if (!shouldAllowBack) {
            return;
        } else {
            super.onBackPressed();
            currentSessionArrayList.clear();
        }

    }


    public void completeTheSession(int _category, int _questionCount){

        int defaultCategory = _category;

        final ArrayList<String> questions_id = new ArrayList<>();
        questions_id.addAll(database.getQuestionsFromSession(_category));

        // Get Session ID
        int sessionId = database.getIsSession(_category)[0];


        for (int i=0; i<questions_id.size(); i++){

            String qId = questions_id.get(i);


            if (_category == 1001 || _category == 1002 || _category == 1003 || _category == 1004 || _category == 1005 || _category == 1006) {
                _category = Integer.parseInt(qId.substring(0, 4));
            }


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

            _category = defaultCategory;
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
                mQuestionViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });







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
        int[] questions;

        if (sessionCategory == 1001){
            _category = 1001;
            questions = sessions.getRandomForLvl1(json);
            _questionCount = 30;
        }
        else if (sessionCategory == 1002){
            _category = 1002;
            questions = sessions.getRandomForLvl1(json);
            _questionCount = 30;
        }

        else if (sessionCategory == 1003){
            _category = 1003;
            questions = sessions.getRandomForLvl1(json);
            _questionCount = 30;
        }

        else if (sessionCategory == 1004){
            _category = 1004;
            questions = sessions.getRandomForLvl1(json);
            _questionCount = 30;
        }


        else if (sessionCategory == 1005){
            _category = 1005;
            questions = sessions.getRandomForLvl1(json);
            _questionCount = 30;
        }


        else if (sessionCategory == 1006){
            _category = 1006;
            questions = sessions.getRandomForLvl1(json);
            _questionCount = 30;
        }

        else
        {
            questions = sessions.getRandomQuestionFromCategory(_category,json,_questionCount);
        }

        int[] limitedQuestions = new int[_questionCount];

        for (int i=0; i<limitedQuestions.length; i++){
            limitedQuestions[i] = questions[i];
        }
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
            shouldAllowBack = true;
            mEndSessionBtn.setClickable(true);
        }
    }

    class CompleteSessionParams  {
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

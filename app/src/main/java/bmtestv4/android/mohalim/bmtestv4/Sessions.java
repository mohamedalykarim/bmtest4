package bmtestv4.android.mohalim.bmtestv4;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import bmtestv4.android.mohalim.bmtestv4.Database.SessionSQLiteHelper;

/**
 * Created by Mohamed ALi on 1/5/2018.
 */

public class Sessions {
    SessionSQLiteHelper sessionSQLiteHelper;

    public Sessions(Context context){
        sessionSQLiteHelper = new SessionSQLiteHelper(context);
    }

    public boolean setQuestionSessionForSpecificCategory(int category, int[] questions_ids){
        int session_id = sessionSQLiteHelper.getSessionIdFromCategory(category);
        //check is there a session for the category
        if (!sessionSQLiteHelper.isCategoryHasSession(category)){
            // create the session if not exists
            if (sessionSQLiteHelper.createIsSession(category)){
                // check is there and previous questions in the database
                sessionSQLiteHelper.createQuestionsForSession(category,questions_ids);
                return true;
            }
        }
        else{
            // check is there and previous questions in the database
            sessionSQLiteHelper.createQuestionsForSession(category,questions_ids);
            return true;
        }

        return false;
    }

    public String getQuestionTextForId(int questionId, String json, int category){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject allCategoryObject = jsonObject.getJSONObject("questions");
            JSONObject categoryObject = allCategoryObject.getJSONObject("category"+category);
            JSONArray questionsArray = categoryObject.getJSONArray("questions");
            int[] question_ids = new int[questionsArray.length()];


            for (int i=0; i < questionsArray.length(); i++){
                JSONObject questionObject = questionsArray.getJSONObject(i);
                question_ids[i] = Integer.valueOf(questionObject.getString("question_id"));
                if (question_ids[i] == questionId){
                    return questionObject.getString("question_text");
                }
            }

            Collections.shuffle(Arrays.asList(question_ids));

        } catch (JSONException e) {
            return "";
        }
        return "";
    }

    public ArrayList<String> getChoicesTextForId(int questionId, String json, int category){
        JSONObject jsonObject = null;
        ArrayList<String> choicesString = new ArrayList<>();

        try {
            jsonObject = new JSONObject(json);
            JSONObject allCategoryObject = jsonObject.getJSONObject("questions");
            JSONArray categoryArray = allCategoryObject.getJSONArray("category"+category);
            for (int m=0; m<categoryArray.length();m++){
                JSONObject item = categoryArray.getJSONObject(m);
                String question_id = item.getString("question_id");

                // check if json question id = parameter id
                if (Integer.valueOf(question_id) == questionId){

                    JSONArray choices = item.getJSONArray("question_choices");
                    for (int y=0; y<choices.length(); y++){
                        JSONObject choicesItem = choices.getJSONObject(y);
                        String choiceText = choicesItem.getString("choice_text");
                        String isTrue = choicesItem.getString("is_true");
                        choicesString.add(choiceText);
                        choicesString.add(isTrue);
                    }
                }
            }
            return choicesString;

        } catch (JSONException e) {

        }


        return choicesString;
    }

    public int[] getRandomQuestionFromCategory(int category, String json, int questionCount){

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject allCategoryObject = jsonObject.getJSONObject("questions");
            JSONObject categoryObject = allCategoryObject.getJSONObject("category"+category);
            JSONArray questionsArray = categoryObject.getJSONArray("questions");
            int[] question_ids = new int[questionsArray.length()];


            for (int i=0; i < questionsArray.length(); i++){
                JSONObject questionObject = questionsArray.getJSONObject(i);
                question_ids[i] = Integer.valueOf(questionObject.getString("question_id"));
            }

            Collections.shuffle(Arrays.asList(question_ids));
            return question_ids;

        } catch (JSONException e) {
            return null;
        }
    }

    public String readQuestionJSONFromAssets(Context context){
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("questions.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            return null;
        }
        return json;
    }

    public String readChoicesJSONFromAssets(Context context){
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("choices.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            return null;
        }
        return json;
    }

}

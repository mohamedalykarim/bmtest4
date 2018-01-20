package bmtestv4.android.mohalim.bmtestv4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Mohamed ALi on 1/5/2018.
 */

public class SessionSQLiteHelper extends SQLiteOpenHelper {

    // database details (name and version)
    public static final String DATABASE_NAME = "bntest_session.db";
    public static final int DATABASE_VERSION = 1;


    //create table is_session
    public static final String SQL_TABLE_IS_SESSION = "CREATE TABLE "
            + SessionContract.IsSessionTable.TABLE_NAME
            + "("
            + SessionContract.IsSessionTable._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY
            + " INTEGER NOT NULL,"
            + SessionContract.IsSessionTable.COLUMN_NAME_IS_SESSION
            + " INTEGER NOT NULL"
            + ")";

    //create table choices_session
    public static final String SQL_TABLE_CHOICES_SESSION = "CREATE TABLE "
            + SessionContract.questionSessionTable.TABLE_NAME
            + "("
            + SessionContract.questionSessionTable._ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SessionContract.questionSessionTable.COLUMN_NAME_CATEGORY
            + " INTEGER NOT NULL,"
            + SessionContract.questionSessionTable.COLUMN_NAME_QUESTION_ID
            + " INTEGER NOT NULL,"
            + SessionContract.questionSessionTable.COLUMN_NAME_ANSWER
            + " INTEGER NOT NULL,"
            + SessionContract.questionSessionTable.COLUMN_NAME_IS_SESSION_ID
            + " INTEGER NOT NULL"
            + ")";


    public SessionSQLiteHelper (Context context){
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_CHOICES_SESSION);
        sqLiteDatabase.execSQL(SQL_TABLE_IS_SESSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SessionContract.IsSessionTable.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SessionContract.questionSessionTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public int[] getIsSession(int category){
        if (!isCategoryHasSession(category)){
            return null;
        }

        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.IsSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY
                + " = "
                + category;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            //save the cursor in a string array
            int[] isSession =
                    {
                            cursor.getInt(cursor.getColumnIndex(SessionContract.IsSessionTable._ID)),
                            cursor.getInt(cursor.getColumnIndex(SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY)),
                            cursor.getInt(cursor.getColumnIndex(SessionContract.IsSessionTable.COLUMN_NAME_IS_SESSION))
                    };

            return isSession;
        }

        return null;
    }

    public boolean createIsSession(int category){
        if (isCategoryHasSession(category)){
            return false;
        }
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY,category);
        contentValues.put(SessionContract.IsSessionTable.COLUMN_NAME_IS_SESSION,1);
        return database.insert(
                SessionContract.IsSessionTable.TABLE_NAME,
                null,
                contentValues) > 0;
    }

    public boolean updateIsSession(int category, int is_session){
        if (!isCategoryHasSession(category)){
            return false;
        }
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY,category);
        contentValues.put(SessionContract.IsSessionTable.COLUMN_NAME_IS_SESSION,is_session);
        return database.update(
                SessionContract.IsSessionTable.TABLE_NAME,
                contentValues,
                SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY
                        + " = " + category,
                null
                ) > 0;
    }

    public boolean isCategoryHasSession(int category){
        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.IsSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY
                + " = "
                + category;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean isCategoryHasOpenSession(int category){
        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.IsSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.IsSessionTable.COLUMN_NAME_CATEGORY
                + " = "
                + category;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            int is_session = cursor.getInt(cursor.getColumnIndex(SessionContract.IsSessionTable.COLUMN_NAME_IS_SESSION));
            if (is_session == 0){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }



    //check if there is question for specific session
    public boolean isQuestionsForSession(int session_id){
        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.questionSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.questionSessionTable.COLUMN_NAME_IS_SESSION_ID
                + " = "
                + session_id;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        cursor.moveToNext();

        if (cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    //create Questions For Session
    public boolean createQuestionsForSession(int category, int[] questions_id){
        int[] session = getIsSession(category);
        if (null != session){
            int session_id = session[0];

            if (isQuestionsForSession(session_id))
            deleteQuestionsFromSession(category);

            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            for (int i = 0; i<questions_id.length; i++){
                contentValues.put(SessionContract.questionSessionTable.COLUMN_NAME_CATEGORY,category);
                contentValues.put(SessionContract.questionSessionTable.COLUMN_NAME_QUESTION_ID,questions_id[i]);
                contentValues.put(SessionContract.questionSessionTable.COLUMN_NAME_ANSWER,0);
                contentValues.put(SessionContract.questionSessionTable.COLUMN_NAME_IS_SESSION_ID,session_id);
                database.insert(
                        SessionContract.questionSessionTable.TABLE_NAME,
                        null,
                        contentValues);
            }
            return true;

        }
        return false;


    }

    //delete questions from session
    public boolean deleteQuestionsFromSession(int category){
        int[] session = getIsSession(category);
        if (null != session){
            int session_id = session[0];
            if (!isQuestionsForSession(session_id))
                return false;
        }

        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(
                SessionContract.questionSessionTable.TABLE_NAME,
                SessionContract.questionSessionTable.COLUMN_NAME_CATEGORY + " = " + category,
                null
        ) > 0;
    }

    public ArrayList<String> getQuestionsFromSession(int category){
        if (!isQuestionsForSession(getSessionIdFromCategory(category))){
            return null;
        }

        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.questionSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.questionSessionTable.COLUMN_NAME_CATEGORY
                + " = "
                + category;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor != null){
            cursor.moveToFirst();
            //save the cursor in a string array
            ArrayList<String> questions_ids = new ArrayList<>();
            questions_ids.add(cursor.getString(cursor.getColumnIndex(SessionContract.questionSessionTable.COLUMN_NAME_QUESTION_ID)));
            while(cursor.moveToNext()){
                questions_ids.add(cursor.getString(cursor.getColumnIndex(SessionContract.questionSessionTable.COLUMN_NAME_QUESTION_ID)));
            }

            return questions_ids;
        }

        return null;
    }

    public int getQuestionsCount(int category){
        if (!isQuestionsForSession(getSessionIdFromCategory(category))){
            return 0;
        }

        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.questionSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.questionSessionTable.COLUMN_NAME_CATEGORY
                + " = "
                + category;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor.getCount() > 0){
            return cursor.getCount();
        }

        return 0;
    }

    public boolean updateQuestion(int questionId, int sessionID, int answer){
        if (isQuestionbyId(questionId,sessionID)){
            int _id = get_IDforQuestion(questionId,sessionID);

            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SessionContract.questionSessionTable.COLUMN_NAME_ANSWER,answer);
            database.update(SessionContract.questionSessionTable.TABLE_NAME,
                    contentValues,
                    SessionContract.questionSessionTable._ID
                            + " = "   + _id, null);
            return true;
        }
        return false;
    }

    public boolean isQuestionbyId(int questionId, int sessionID){
        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.questionSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.questionSessionTable.COLUMN_NAME_QUESTION_ID
                + " = "
                + questionId
                + " AND "
                + SessionContract.questionSessionTable.COLUMN_NAME_IS_SESSION_ID
                + " = "
                + sessionID;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int get_IDforQuestion(int questionId, int sessionID){
        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.questionSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.questionSessionTable.COLUMN_NAME_QUESTION_ID
                + " = "
                + questionId
                + " AND "
                + SessionContract.questionSessionTable.COLUMN_NAME_IS_SESSION_ID
                + " = "
                + sessionID;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            int _id = cursor.getInt(cursor.getColumnIndex(SessionContract.questionSessionTable._ID));
            return _id;
        }
        else{
            return 0;
        }
    }

    public int getChosenAnswer(int questionId, int sessionID){
        SQLiteDatabase database = this.getReadableDatabase();
        String SQLQuery = "SELECT * FROM "
                + SessionContract.questionSessionTable.TABLE_NAME
                + " WHERE "
                + SessionContract.questionSessionTable.COLUMN_NAME_QUESTION_ID
                + " = "
                + questionId
                + " AND "
                + SessionContract.questionSessionTable.COLUMN_NAME_IS_SESSION_ID
                + " = "
                + sessionID;

        Cursor cursor = database.rawQuery(SQLQuery,null);
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            int chosen = cursor.getInt(cursor.getColumnIndex(SessionContract.questionSessionTable.COLUMN_NAME_ANSWER));
            return chosen;

        }
        else{
            return 0;
        }
    }

    public int getSessionIdFromCategory(int category){
        int[] session = getIsSession(category);
        if (null != session){
            int session_id = session[0];
            return session_id;
        }
        return 0;
    }

}

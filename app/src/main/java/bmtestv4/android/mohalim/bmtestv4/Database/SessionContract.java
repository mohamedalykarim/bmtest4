package bmtestv4.android.mohalim.bmtestv4.Database;

import android.provider.BaseColumns;

import java.security.PublicKey;

/**
 * Created by Mohamed ALi on 1/5/2018.
 */

public class SessionContract {
    private SessionContract() {

    }

    public static class IsSessionTable implements BaseColumns{
        public static final String TABLE_NAME = "is_session";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_IS_SESSION = "is_session";
    }

    public static class questionSessionTable implements BaseColumns{
        public static final String TABLE_NAME = "choices_session";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_QUESTION_ID = "question_id";
        public static final String COLUMN_NAME_ANSWER = "the_answer";
        public static final String COLUMN_NAME_IS_SESSION_ID = "is_session_id";
    }

}

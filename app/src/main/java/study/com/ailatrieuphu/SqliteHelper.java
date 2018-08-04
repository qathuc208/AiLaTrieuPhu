package study.com.ailatrieuphu;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Administrator on 06/03/2018.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String  TABLE_TP ="tbtrieuphu";

    public static final String COLUMN_ID="_ID";
    public static final String COLUMN_Q="Question";
    public static final String COLUMN_A="DAA";
    public static final String COLUMN_B="DAB";
    public static final String COLUMN_C="DAC";
    public static final String COLUMN_D="DAD";
    public static final String COLUMN_True="DATre";

    public static final String Database_Name = "DB_AiLaTrieuPhu";
    public static final int Database_Version = 1;

    //Create database

    private static final String DATABASE_CREATE = "create table " + TABLE_TP
            +"("+COLUMN_ID+ " integer primary key AUTOINCREMENT, "
            +COLUMN_Q+" Text not null,"+ COLUMN_A+" Text not null, "
            + COLUMN_B+" Text not null, "
            + COLUMN_C+" Text not null, "
            + COLUMN_D+" Text not null, "
            + COLUMN_True+" Text not null);";


    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
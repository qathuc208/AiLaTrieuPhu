package study.com.ailatrieuphu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 06/03/2018.
 */

public class DataSouce {

    //Create DP

    private static final String DATABASE_NAME = "DB_AiLaTrieuPhu";
    private static final int    DATABASE_VERSION = 1;

    SQLiteDatabase _database;
    Context mContext;

    public DataSouce (Context context) {
        mContext = context;
    }

    private SQLiteDatabase getReadable() {
        SqliteHelper conector = new SqliteHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        return conector.getReadableDatabase();
    }

    private SQLiteDatabase getWriteable() {
        SqliteHelper conector = new SqliteHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        return conector.getWritableDatabase();
    }

    public void insertQuesTions(TrieuPhu user) {
        _database = this.getWriteable();
        String sql = "Insert into "+SqliteHelper.TABLE_TP +
                "(Question,DAA,DAB,DAC,DAD,DATre) " +
                "values('"+ user.Q +"','"+ user.A +"','" +
                user.B+"','"+user.C+ "','"+user.D+ "','"+user.QT+ "')";
        _database.execSQL(sql);
     }

     public TrieuPhu[] getAllQu() {
         _database = this.getReadable();
         String sql = "Select count(*) from tbtrieuphu";
         TrieuPhu[] listUser = null;
         Cursor resCur = _database.rawQuery(sql, null);
         if(resCur.moveToFirst())
         {
             int len = resCur.getInt(0);
             if(len <= 0)
                 return null;
             listUser = new TrieuPhu[len];
             sql = "Select * from tbtrieuphu";
             resCur = _database.rawQuery(sql, null);
             len = 0;
             if (resCur.moveToFirst())
             {
                 do
                 {
                     listUser[len] = new TrieuPhu();
                     listUser[len].id = resCur.getString(0);
                     listUser[len].Q = resCur.getString(1);
                     listUser[len].A = resCur.getString(2);
                     listUser[len].B = resCur.getString(3);
                     listUser[len].C = resCur.getString(4);
                     listUser[len].D = resCur.getString(5);
                     listUser[len].QT = resCur.getString(6);
                     len++;
                 }while(resCur.moveToNext());
             }
         }
         return listUser;
     }

    public TrieuPhu[] getAllQuDe()
    {
        _database = this.getReadable();
        String sql = "Select count(*) from tbtrieuphu";
        TrieuPhu[] listUser = null;

        Cursor resCur = _database.rawQuery(sql, null);
        if(resCur.moveToFirst())
        {
            int len = resCur.getInt(0);
            if(len <= 0)
                return null;
            listUser = new TrieuPhu[len];
            sql = "Select * from tbtrieuphu";
            resCur = _database.rawQuery(sql, null);
            len = 0;
            if (resCur.moveToFirst())
            {
                do
                {
                    listUser[len] = new TrieuPhu();
                    listUser[len].id = resCur.getString(0);
                    listUser[len].Q = resCur.getString(1);
                    listUser[len].A = resCur.getString(2);
                    listUser[len].B = resCur.getString(3);
                    listUser[len].C = resCur.getString(4);
                    listUser[len].D = resCur.getString(5);
                    listUser[len].QT = resCur.getString(6);
                    len++;
                }while((resCur.moveToNext())&&(len<5));
            }
        }
        return listUser;
    }

    public TrieuPhu[] getAllQuKh()
    {
        _database = this.getReadable();
        String sql = "Select count(*) from tbtrieuphu";
        TrieuPhu[] listUser = null;
        Cursor resCur = _database.rawQuery(sql, null);
        int vitri=0;
        if(resCur.moveToFirst())
        {
            int len = resCur.getInt(0);
            if(len <= 0)
                return null;
            listUser = new TrieuPhu[len];
            sql = "Select * from tbtrieuphu";
            resCur = _database.rawQuery(sql, null);
            len = 0;
            if (resCur.moveToFirst())
            {
                do
                {if(vitri>4){
                    listUser[len] = new TrieuPhu();
                    listUser[len].id = resCur.getString(0);
                    listUser[len].Q = resCur.getString(1);
                    listUser[len].A = resCur.getString(2);
                    listUser[len].B = resCur.getString(3);
                    listUser[len].C = resCur.getString(4);
                    listUser[len].D = resCur.getString(5);
                    listUser[len].QT = resCur.getString(6);
                    len++;
                }
                    vitri++;
                }while(resCur.moveToNext());
            }
        }
        return listUser;
    }
}

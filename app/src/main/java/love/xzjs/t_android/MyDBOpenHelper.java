package love.xzjs.t_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/12/18.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    /**
     * 数据库第一次创建
     */
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE config(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "time INTEGER(1)," +
                "date INTEGER(1)," +
                "week INTEGER(1)," +
                "location VARCHAR(20)," +
                "firstDay INTEGER(1)," +
                "secondDay INTEGER(1)," +
                "thirdDay INTEGER(1)," +
                "num INTEGER(1))");

        ContentValues values=new ContentValues();
        values.put("time",1);
        values.put("date",1);
        values.put("week",1);
        values.put("firstDay",1);
        values.put("secondDay",1);
        values.put("thirdDay",1);
        values.put("num",3);
        values.put("location","");
        sqLiteDatabase.insert("config",null,values);
    }

    @Override
    /**
     * 数据库更新
     */
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

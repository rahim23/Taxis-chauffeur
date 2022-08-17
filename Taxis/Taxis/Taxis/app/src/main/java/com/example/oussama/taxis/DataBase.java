package com.example.oussama.taxis;

/**
 * Created by oussama on 18/11/19.
 */
import  android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.widget.Button;

        import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    public static String DbName="mystudent.db " ;
    public static final int version=1 ;
    private SQLiteDatabase db ;
    public DataBase(Context context) {
        super(context, DbName, null, version) ;


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS client (phone INTEGER primary key , password TEXT , name TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS client") ;
        onCreate(db);
    }
    public void InsertRowAdmin(String name,String mdp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("password", mdp);
        db.insert("client", null, contentValues);



    }
    public int  Testt (String strNom,String strMdp)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery ("select * from client where name='"+strNom+"'and password='"+strMdp+"' " , null  );
        int n=-1;
        if(res.moveToFirst())
            n=1;

        return n;

    }





    public ArrayList getAllrecord()
    {
        ArrayList array_List = new ArrayList() ;
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor res = db.rawQuery("select * from client " , null) ;
        res.moveToFirst() ;
        while (res.isAfterLast() == false) {
            array_List.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext() ;

        }
        return array_List ;


    }




}
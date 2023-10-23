package com.hacktivate8.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String dbName = "DB_TODOLIST";
    private static final int dbVersion = 1;
    private static final String tableList = "TODOLIST";
    //COLOM TABLE
    private static final String KEY_ID = "id";
    private static final String NAMA_KEGIATAN = "nama_kegiatan";

    public DataBaseHandler(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_LIST = "CREATE TABLE " +tableList+ " ( "
                                    +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                    +NAMA_KEGIATAN+ " TEXT ) ";
        System.out.println("Create Table : " +CREATE_TABLE_LIST);
        db.execSQL(CREATE_TABLE_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " +tableList);
        onCreate(db);
    }

    //method CURD
    public void addKegiatan (TodoList todoList) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAMA_KEGIATAN, todoList.getNamaKegiatan());

        db.insert(tableList , null, values);
        db.close();
    }

    public List<TodoList> getAllKegiatan() {
        List<TodoList> todoListList = new ArrayList<>();

        String query = " SELECT * FROM " +tableList;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                TodoList todoList = new TodoList();
                todoList.setIdKegiatan(Integer.parseInt(cursor.getString(0)));
                todoList.setNamaKegiatan(cursor.getString(1));

                todoListList.add(todoList);
            }while (cursor.moveToNext());
        }
        return todoListList;
    }

    public void hapusKegiatan(TodoList todoList){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableList, KEY_ID+ " = ? ", new String[]{String.valueOf(todoList.getIdKegiatan())});
    }
}

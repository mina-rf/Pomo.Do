package com.sharif.PomoDo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mina on 7/13/16.
 */
public class TasksDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Tasks.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TASK_TABLE_NAME = "tasks";
    public static final String TASK_COLUMN_NAME = "name";
    public static final String TASK_COLUMN_DEADLINE_YEAR = "deadline_year";
    public static final String TASK_COLUMN_DEADLINE_MONTH = "deadline_month";
    public static final String TASK_COLUMN_DEADLINE_DAY = "deadline_day";
    public static final String TASK_COLUMN_DEADLINE_HOUR = "deadline_hour";
    public static final String TASK_COLUMN_DEADLINE_MINUTE = "deadline_minute";
    public static final String TASK_COLUMN_WORK_INTERVAL = "work_intrval";
    public static final String TASK_COLUMN_SHORT_BREAK = "short_break";
    public static final String TASK_COLUMN_DESCRIPTION = "description";
    public static final String TASK_COLUMN_TAG = "tag";
    public static final String TASK_COLUMN_TTARGET = "target";
    public static final String TASK_COLUMN_DONE = "done";


    public TasksDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TASK_TABLE_NAME + "(" +
                TASK_COLUMN_NAME + " TEXT PRIMARY KEY, " +
                TASK_COLUMN_DEADLINE_YEAR + " TEXT, " +
                TASK_COLUMN_DEADLINE_MONTH + " TEXT, " +
                TASK_COLUMN_DEADLINE_DAY + " TEXT, " +
                TASK_COLUMN_DEADLINE_HOUR + " TEXT, " +
                TASK_COLUMN_DEADLINE_MINUTE + " TEXT, " +
                TASK_COLUMN_WORK_INTERVAL + " TEXT, " +
                TASK_COLUMN_SHORT_BREAK + " TEXT, " +
                TASK_COLUMN_DESCRIPTION + " TEXT, " +
                TASK_COLUMN_TTARGET + " INTEGER, " +
                TASK_COLUMN_DONE + " INTEGER, " +
                TASK_COLUMN_TAG + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertTask(String name, String deadline_year , String deadline_month , String deadline_day ,
                              String deadline_hour , String deadline_minute , String work_interval ,
                              String short_Break , String description , String tag , Integer target  , Integer done) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TASK_COLUMN_NAME, name);
        contentValues.put(TASK_COLUMN_DEADLINE_YEAR, deadline_year);
        contentValues.put(TASK_COLUMN_DEADLINE_MONTH, deadline_month);
        contentValues.put(TASK_COLUMN_DEADLINE_DAY, deadline_day);
        contentValues.put(TASK_COLUMN_DEADLINE_HOUR, deadline_hour);
        contentValues.put(TASK_COLUMN_DEADLINE_MINUTE, deadline_minute);
        contentValues.put(TASK_COLUMN_WORK_INTERVAL, work_interval);
        contentValues.put(TASK_COLUMN_SHORT_BREAK, short_Break);
        contentValues.put(TASK_COLUMN_DESCRIPTION, description);
        contentValues.put(TASK_COLUMN_TTARGET, target);
        contentValues.put(TASK_COLUMN_DONE, 0);
        contentValues.put(TASK_COLUMN_TAG, tag);

        db.insert(TASK_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateTask (String name, String deadline_year , String deadline_month , String deadline_day ,
                               String deadline_hour , String deadline_minute , String work_interval ,
                               String short_Break , String description ,String tag , Integer target  , Integer done) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_COLUMN_NAME, name);
        contentValues.put(TASK_COLUMN_DEADLINE_YEAR, deadline_year);
        contentValues.put(TASK_COLUMN_DEADLINE_MONTH, deadline_month);
        contentValues.put(TASK_COLUMN_DEADLINE_DAY, deadline_day);
        contentValues.put(TASK_COLUMN_DEADLINE_HOUR, deadline_hour);
        contentValues.put(TASK_COLUMN_DEADLINE_MINUTE, deadline_minute);
        contentValues.put(TASK_COLUMN_WORK_INTERVAL, work_interval);
        contentValues.put(TASK_COLUMN_SHORT_BREAK, short_Break);
        contentValues.put(TASK_COLUMN_DESCRIPTION, description);
        contentValues.put(TASK_COLUMN_TTARGET, target);
        contentValues.put(TASK_COLUMN_DONE, done);
        contentValues.put(TASK_COLUMN_TAG, tag);
        db.update(TASK_TABLE_NAME, contentValues, TASK_COLUMN_NAME + " = ? ", new String[] { name } );
        return true;
    }

    public Cursor getPerson(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TASK_TABLE_NAME + " WHERE " +
                TASK_COLUMN_NAME + "=?", new String[] { name } );
        return res;
    }
    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + TASK_TABLE_NAME, null );
        return res;
    }

    public Integer deletePerson(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TASK_TABLE_NAME,
                TASK_COLUMN_NAME + " = ? ",
                new String[] { name });
    }





}

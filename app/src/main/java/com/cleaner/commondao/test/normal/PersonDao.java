package com.cleaner.commondao.test.normal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cleaner.commondao.test.been.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * Created by mjd on 2017/1/7.
 */

public class PersonDao {
    private PersonDbHelper dbHelper;

    public PersonDao(Context context) {
        dbHelper = new PersonDbHelper(context);
    }

    public void add(Person person) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_NAME, person.getName());
        values.put(dbHelper.COLUMN_AGE, person.getAge());
        db.insert(dbHelper.TABLE_NAME, null, values);
        db.close();
    }

    public List<Person> queryAll() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbHelper.TABLE_NAME, null, null, null, null, null, null);
        List<Person> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_AGE));
            list.add(new Person(name, age));
        }
        cursor.close();
        db.close();
        return list;
    }

    public void update(Person person) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_NAME, person.getName());
        values.put(dbHelper.COLUMN_AGE, person.getAge());
        db.update(dbHelper.TABLE_NAME, values, null, null);
        db.close();
    }

    public void delete(Person person) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_NAME, person.getAge() + " = ? ", new String[]{person.getAge() + ""});
        db.close();
    }

}

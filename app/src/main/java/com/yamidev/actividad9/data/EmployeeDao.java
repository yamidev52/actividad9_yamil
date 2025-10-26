package com.yamidev.actividad9.data;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yamidev.actividad9.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private final DBHelper helper;

    public EmployeeDao(Context context) {
        helper = new DBHelper(context.getApplicationContext());
    }

    // INSERT
    public long insert(Employee e) {
        ContentValues cv = new ContentValues();
        cv.put(DBContract.EmployeeEntry.COL_NAME, e.getName());
        cv.put(DBContract.EmployeeEntry.COL_POSITION, e.getPosition());
        cv.put(DBContract.EmployeeEntry.COL_SALARY, e.getSalary());
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            return db.insert(DBContract.EmployeeEntry.TABLE_NAME, null, cv);
        }
    }

    // UPDATE
    public int update(Employee e) {
        if (e.getId() <= 0) return 0;
        ContentValues cv = new ContentValues();
        cv.put(DBContract.EmployeeEntry.COL_NAME, e.getName());
        cv.put(DBContract.EmployeeEntry.COL_POSITION, e.getPosition());
        cv.put(DBContract.EmployeeEntry.COL_SALARY, e.getSalary());

        String where = DBContract.EmployeeEntry._ID + "=?";
        String[] args = { String.valueOf(e.getId()) };

        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            return db.update(DBContract.EmployeeEntry.TABLE_NAME, cv, where, args);
        }
    }

    // DELETE
    public int delete(long id) {
        String where = DBContract.EmployeeEntry._ID + "=?";
        String[] args = { String.valueOf(id) };
        try (SQLiteDatabase db = helper.getWritableDatabase()) {
            return db.delete(DBContract.EmployeeEntry.TABLE_NAME, where, args);
        }
    }

    // SELECT *
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        try (SQLiteDatabase db = helper.getReadableDatabase();
             Cursor c = db.query(
                     DBContract.EmployeeEntry.TABLE_NAME,
                     DBContract.EmployeeEntry.PROJECTION_ALL,
                     null, null, null, null,
                     DBContract.EmployeeEntry.COL_NAME + " ASC")) {

            while (c.moveToNext()) list.add(fromCursor(c));
        }
        return list;
    }

    // SELECT by id (útil)
    public Employee getById(long id) {
        String sel = DBContract.EmployeeEntry._ID + "=?";
        String[] args = { String.valueOf(id) };
        try (SQLiteDatabase db = helper.getReadableDatabase();
             Cursor c = db.query(DBContract.EmployeeEntry.TABLE_NAME,
                     DBContract.EmployeeEntry.PROJECTION_ALL, sel, args,
                     null, null, null)) {
            if (c.moveToFirst()) return fromCursor(c);
        }
        return null;
    }

    private Employee fromCursor(Cursor c) {
        long id = c.getLong(c.getColumnIndexOrThrow(DBContract.EmployeeEntry._ID));
        String name = c.getString(c.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_NAME));
        String position = c.getString(c.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_POSITION));
        double salary = c.getDouble(c.getColumnIndexOrThrow(DBContract.EmployeeEntry.COL_SALARY));
        return new Employee(id, salary, position, name);
    }
}


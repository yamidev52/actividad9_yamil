package com.yamidev.actividad9.data;

import android.provider.BaseColumns;

public final class DBContract {
    private DBContract() {}

    public static final class EmployeeEntry implements BaseColumns {
        public static final String TABLE_NAME   = "employees";
        public static final String COL_NAME     = "name";
        public static final String COL_POSITION = "position";
        public static final String COL_SALARY   = "salary";

        public static final String[] PROJECTION_ALL = {
                _ID, COL_NAME, COL_POSITION, COL_SALARY
        };

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_NAME + " TEXT NOT NULL, " +
                        COL_POSITION + " TEXT, " +
                        COL_SALARY + " REAL NOT NULL DEFAULT 0" +
                        ");";

        public static final String SQL_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
    }
}
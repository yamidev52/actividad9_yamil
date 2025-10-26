package com.yamidev.actividad9.data;

import android.content.Context;

import com.yamidev.actividad9.model.Employee;

import java.util.List;

public class EmployeeDao {
    private final DBHelper helper;

    public EmployeeDao(Context context) { helper = new DBHelper(context); }

    public long insert(Employee e) { /* INSERT con ContentValues */ }
    public int update(Employee e) { /* UPDATE con ContentValues */ }
    public int delete(long id) { /* DELETE */ }
    public List<Employee> getAll() { /* SELECT * FROM employees */ }
}

package com.yamidev.actividad9;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yamidev.actividad9.data.EmployeeDao;
import com.yamidev.actividad9.ui.EmployeeAdapter;

public class MainActivity extends AppCompatActivity {

    private EmployeeDao dao;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new EmployeeDao(this);


    }
}
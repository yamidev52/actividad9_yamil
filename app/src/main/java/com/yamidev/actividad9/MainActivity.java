package com.yamidev.actividad9;

import android.os.Bundle;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yamidev.actividad9.data.EmployeeDao;
import com.yamidev.actividad9.model.Employee;
import com.yamidev.actividad9.ui.EmployeeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvEmployees;
    private FloatingActionButton fabAdd;

    private EmployeeDao dao;
    private EmployeeAdapter adapter;
    private List<Employee> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new EmployeeDao(this);

        rvEmployees = findViewById(R.id.rvEmployees);
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EmployeeAdapter(data, new EmployeeAdapter.OnItemClick() {
            @Override public void onEdit(Employee e) { showEmployeeDialog(e); }

            @Override public void onDelete(Employee e) { confirmDelete(e); }
        });
        rvEmployees.setAdapter(adapter);

        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> showEmployeeDialog(null));

        loadEmployees();
    }

    private void loadEmployees() {
        data.clear();
        data.addAll(dao.getAll());
        adapter.notifyDataSetChanged(); // o adapter.updateData(dao.getAll());
    }

    private void confirmDelete(Employee e) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Eliminar a " + e.getName() + "?")
                .setPositiveButton("Sí", (d, w) -> {
                    dao.delete(e.getId());
                    loadEmployees();
                    Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

    /** Dialogo para crear/editar. Si 'employee' es null -> crear; si no, editar. */
    private void showEmployeeDialog(@Nullable Employee employee) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_employee, null, false);

        EditText etName = view.findViewById(R.id.etName);
        EditText etPosition = view.findViewById(R.id.etPosition);
        EditText etSalary = view.findViewById(R.id.etSalary);
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnCancel = view.findViewById(R.id.btnCancel);

        final boolean isEdit = (employee != null);
        if (isEdit) {
            etName.setText(employee.getName());
            etPosition.setText(employee.getPosition());
            etSalary.setText(String.valueOf(employee.getSalary()));
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(isEdit ? "Editar empleado" : "Nuevo empleado")
                .setView(view)
                .setCancelable(false)
                .create();

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String position = etPosition.getText().toString().trim();
            String salaryStr = etSalary.getText().toString().trim();

            if (TextUtils.isEmpty(name)) { etName.setError("Requerido"); etName.requestFocus(); return; }
            if (TextUtils.isEmpty(salaryStr)) { etSalary.setError("Requerido"); etSalary.requestFocus(); return; }

            double salary;
            try { salary = Double.parseDouble(salaryStr); }
            catch (NumberFormatException ex) { etSalary.setError("Número inválido"); return; }

            if (isEdit) {
                employee.setName(name);
                employee.setPosition(position);
                employee.setSalary(salary);
                int rows = dao.update(employee);
                if (rows > 0) Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
            } else {
                Employee e = new Employee(0, salary, position, name);
                long id = dao.insert(e);
                if (id > 0) Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
            }
            loadEmployees();
            dialog.dismiss();
        });

        dialog.show();
    }
}

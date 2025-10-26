package com.yamidev.actividad9.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.yamidev.actividad9.model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.VH> {
    public interface OnItemClick { void onEdit(Employee e); void onDelete(Employee e); }
    // Implementación de onBindViewHolder, ViewHolder y eventos


}

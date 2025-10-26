package com.yamidev.actividad9.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.yamidev.actividad9.R;
import com.yamidev.actividad9.model.Employee;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.VH> {

    private List<Employee> employeeList;
    private OnItemClick listener;

    public interface OnItemClick {
        void onEdit(Employee e);
        void onDelete(Employee e);
    }

    public EmployeeAdapter(List<Employee> employeeList, OnItemClick listener) {
        this.employeeList = employeeList;
        this.listener = listener;
    }

    public static class VH extends RecyclerView.ViewHolder {
        TextView txtName, txtPosition;
        Button btnEdit, btnDelete;

        public VH(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPosition = itemView.findViewById(R.id.txtPosition);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Employee employee = employeeList.get(position);
        holder.txtName.setText(employee.getName());
        holder.txtPosition.setText(employee.getPosition());

        holder.btnEdit.setOnClickListener(v -> listener.onEdit(employee));
        holder.btnDelete.setOnClickListener(v -> listener.onDelete(employee));
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void updateData(List<Employee> newList) {
        this.employeeList = newList;
        notifyDataSetChanged();
    }
}

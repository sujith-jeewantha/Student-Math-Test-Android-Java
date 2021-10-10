package com.sgstech.studentmathtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.R;
import com.sgstech.studentmathtest.UpdateStudentProfileActivity;

import java.util.List;

public class StudentProfileAdapter extends RecyclerView.Adapter<StudentProfileAdapter.StudentProfilesViewHolder> {



    private Context mCtx;
    private List<Student> studentProfileList;

    public StudentProfileAdapter(Context mCtx, List<Student> studentProfileList) {
        this.mCtx = mCtx;
        this.studentProfileList = studentProfileList;
    }


    @Override
    public StudentProfilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_student_profile, parent, false);
        return new StudentProfilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentProfilesViewHolder holder, int position) {

        Student ss = studentProfileList.get(position);
        holder.textView_student_first_name.setText("First Name :"+ss.getStudent_first_name());
        holder.textView_student_last_name.setText("Last Name :"+ss.getStudent_last_name());
        holder.textView_student_no.setText("Student No :"+ss.getStudent_no());


    }

    @Override
    public int getItemCount() {
        return studentProfileList.size();
    }

    class StudentProfilesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStatus,textView_student_first_name, textView_student_last_name, textView_student_no;

        public StudentProfilesViewHolder(View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textView_student_first_name = itemView.findViewById(R.id.textView_student_first_name);
            textView_student_last_name = itemView.findViewById(R.id.textView_student_last_name);
            textView_student_no = itemView.findViewById(R.id.textView_student_no);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Student student = studentProfileList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateStudentProfileActivity.class);
            intent.putExtra("studentProfile", student);

            mCtx.startActivity(intent);
        }
    }
}
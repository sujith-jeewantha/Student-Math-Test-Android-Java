package com.sgstech.studentmathtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.MainActivity_Math_Test_Sub;
import com.sgstech.studentmathtest.R;

import java.io.File;
import java.util.List;

public class StudentMathTestAdapter extends RecyclerView.Adapter<StudentMathTestAdapter.StudentMathTestsViewHolder> {

    private Context mCtx;
    private List<Student> studentList;

    public StudentMathTestAdapter(Context mCtx, List<Student> studentList) {
        this.mCtx = mCtx;
        this.studentList = studentList;
    }

    @Override
    public StudentMathTestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_student_math_test, parent, false);
        return new StudentMathTestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentMathTestsViewHolder holder, int position) {
        Student ss = studentList.get(position);

        String studentImg = ss.getStudent_profile_img();

        try
        {
            if (!studentImg.equals(""))
            {
                File file = new File(studentImg);
                Uri imageUri = Uri.fromFile(file);

                Glide.with(holder.ivProfilePicMin)
                        .load(imageUri)
                        .circleCrop()
                        .into(holder.ivProfilePicMin);
            }
        }catch (Exception e)
        {
            Toast.makeText(mCtx.getApplicationContext(), "Something wrong", Toast.LENGTH_LONG).show();
        }


        holder.textView_student_first_name.setText("First Name :"+ss.getStudent_first_name());
        holder.textView_student_last_name.setText("Last Name :"+ss.getStudent_last_name());
        holder.textView_student_no.setText("Student No :"+ss.getStudent_no());

        if (ss.isFinished()) {
            holder.textViewStatus.setText("Completed");
            holder.textViewStatus.setBackgroundResource(R.drawable.completed_shape);
        }
        else {
            holder.textViewStatus.setText("Not Completed");
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentMathTestsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStatus,textView_student_first_name, textView_student_last_name, textView_student_no;
        ImageView ivProfilePicMin;

        public StudentMathTestsViewHolder(View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            ivProfilePicMin = itemView.findViewById(R.id.ivProfilePicMin);
            textView_student_first_name = itemView.findViewById(R.id.textView_student_first_name);
            textView_student_last_name = itemView.findViewById(R.id.textView_student_last_name);
            textView_student_no = itemView.findViewById(R.id.textView_student_no);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Student student = studentList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, MainActivity_Math_Test_Sub.class);
            intent.putExtra("studentMathTest", student);

            mCtx.startActivity(intent);
        }
    }
}
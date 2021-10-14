package com.sgstech.studentmathtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sgstech.studentmathtest.Database.model.Student;
import com.sgstech.studentmathtest.R;
import com.sgstech.studentmathtest.UpdateStudentProfileActivity;

import java.io.File;
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

        String studentImg = ss.getStudent_profile_img();

        try
        {
            File file = new File(studentImg);

            if(file.exists())
            {
                Uri imageUri = Uri.fromFile(file);

                Glide.with(holder.ivProfilePicMin)
                        .load(imageUri)
                        .circleCrop()
                        .into(holder.ivProfilePicMin);
            }

        }catch (Exception e){

        }

        holder.textView_student_first_name.setText("First Name :"+ss.getStudent_first_name());
        holder.textView_student_last_name.setText("Last Name :"+ss.getStudent_last_name());
        holder.textView_student_no.setText("Student No :"+ss.getStudent_no());

    }

    @Override
    public int getItemCount() {
        return studentProfileList.size();
    }

    class StudentProfilesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView_student_first_name, textView_student_last_name, textView_student_no;
        ImageView ivProfilePicMin;

        public StudentProfilesViewHolder(View itemView) {
            super(itemView);

            ivProfilePicMin = itemView.findViewById(R.id.ivProfilePicMin);
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
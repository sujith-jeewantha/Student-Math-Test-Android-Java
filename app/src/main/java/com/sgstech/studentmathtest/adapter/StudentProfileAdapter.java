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
import com.sgstech.studentmathtest.UpdateServiceSummaryActivity;

import java.util.List;

public class StudentProfileAdapter extends RecyclerView.Adapter<StudentProfileAdapter.StudentProfilesViewHolder> {



    private Context mCtx;
    private List<Student> studentProfileList;

    public StudentProfileAdapter(Context mCtx, List<Student> serviceSummaryList) {
        this.mCtx = mCtx;
        this.studentProfileList = serviceSummaryList;
    }


    @Override
    public StudentProfilesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_student_profile, parent, false);
        return new StudentProfilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentProfilesViewHolder holder, int position) {

        Student ss = studentProfileList.get(position);
//        holder.textView_service_site_id.setText("Site ID :"+ss.getServiceSiteId());
//        holder.textView_service_site_name.setText("Site Name :"+ss.getServiceSiteName());
//        holder.textView_service_unit_no.setText("Unit No :"+ss.getUnitNo());

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
        return studentProfileList.size();
    }

    class StudentProfilesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStatus,textView_service_site_id, textView_service_site_name, textView_service_unit_no;

        public StudentProfilesViewHolder(View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textView_service_site_id = itemView.findViewById(R.id.textView_service_site_id);
            textView_service_site_name = itemView.findViewById(R.id.textView_service_site_name);
            textView_service_unit_no = itemView.findViewById(R.id.textView_service_unit_no);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Student student = studentProfileList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateServiceSummaryActivity.class);
            intent.putExtra("studentProfile", student);

            mCtx.startActivity(intent);
        }
    }
}
package com.sgstech.studentmathtest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sgstech.studentmathtest.Database.model.MathTest;
import com.sgstech.studentmathtest.UpdateHistoryMathTestSubActivity;
import com.sgstech.studentmathtest.R;

import java.util.List;

public class MathTestSubAdapter extends RecyclerView.Adapter<MathTestSubAdapter.MathTestSubViewHolder> {


    private Context mCtx;
    private List<MathTest> mathTestList;


    public MathTestSubAdapter(Context mCtx, List<MathTest> mathTestList) {
        this.mCtx = mCtx;
        this.mathTestList = mathTestList;
    }


    @Override
    public MathTestSubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_math_test_sub, parent, false);
        return new MathTestSubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MathTestSubViewHolder holder, int position) {

        MathTest mt = mathTestList.get(position);

        holder.textView_test_score.setText("Score :"+mt.getScores());
        holder.textView_test_start_time.setText("Beginning time :"+mt.getTime_of_beginning());
        holder.textView_test_total_time.setText("Total time :"+mt.getTotal_time_of_the_test());


    }

    @Override
    public int getItemCount() {
        return mathTestList.size();
    }

    class MathTestSubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView_test_score, textView_test_start_time, textView_test_total_time;

        public MathTestSubViewHolder(View itemView) {
            super(itemView);

            textView_test_score = itemView.findViewById(R.id.textView_test_score);
            textView_test_start_time = itemView.findViewById(R.id.textView_test_start_time);
            textView_test_total_time = itemView.findViewById(R.id.textView_test_total_time);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            MathTest mathTest = mathTestList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateHistoryMathTestSubActivity.class);
            intent.putExtra("mathTest", mathTest);

            mCtx.startActivity(intent);
        }
    }
}
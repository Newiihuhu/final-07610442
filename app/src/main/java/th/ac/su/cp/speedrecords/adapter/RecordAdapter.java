package th.ac.su.cp.speedrecords.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import th.ac.su.cp.speedrecords.R;
import th.ac.su.cp.speedrecords.model.Record;

public class RecordAdapter{
    private Context mContext;
    private Record[] mRecord;

    public RecordAdapter(Context context, Record[] records) {
        this.mContext = context;
        this.mRecord = records;
    }

    @NonNull
    @Override
    public RecordAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new RecordAdapter.MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.MyViewHolder holder, int position) {
        Record record = mRecord[position];
        Double distance = Double.parseDouble(record.distance);
        Double time = Double.parseDouble(record.duration);
        Double kilo;
        Double hr;
        Double average;
        String speed;
        kilo = distance/1000;
        hr = time/3600;
        average = kilo/hr;
        speed = String.format("%.f",average);

        if(average >= 80){
            holder.cowImageView.setImageResource(R.drawable.red_cow);
        }

        holder.speedTextView.setText(speed+" KM/H");
        holder.dis_dur_TextView.setText(distance+" METERS, "+time+" SECONDS");

    }

    @Override
    public int getItemCount() {
        return mRecord.length;
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView speedTextView;
        TextView dis_dur_TextView;
        ImageView cowImageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.speedTextView = itemView.findViewById(R.id.kilo_text_view);
            this.dis_dur_TextView = itemView.findViewById(R.id.cal_text_view);
            this.cowImageView = itemView.findViewById(R.id.cow_image_view);
        }
    }
}

package th.ac.su.cp.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import th.ac.su.cp.speedrecords.db.AppDatabase;
import th.ac.su.cp.speedrecords.model.Record;
import th.ac.su.cp.speedrecords.util.AppExecutors;

public class AddRecordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);


        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText distanceET = findViewById(R.id.distance_edit_text);
                String str_ditance =  distanceET.getText().toString();

                EditText duraitonET = findViewById(R.id.duration_edit_text);
                String str_duration = duraitonET.getText().toString();


                final Record records = new Record(0,str_ditance,str_duration);
                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(AddRecordActivity.this);
                        db.userDao().addUser(records);
                        finish();
                    }
                });
            }
        });
    }
}
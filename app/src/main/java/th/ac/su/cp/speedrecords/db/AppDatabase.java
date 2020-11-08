package th.ac.su.cp.speedrecords.db;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;

import th.ac.su.cp.speedrecords.model.Record;
import th.ac.su.cp.speedrecords.util.AppExecutors;

@Database(entities = {Record.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    private static final String TAG = "AppDatabase";
    private static final String DB_NAME = "record.db";
    private static AppDatabase sInstance;

    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DB_NAME
            ).addCallback(new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    insertInitialData(context);
                }
            }).build();
        }
        return sInstance;
    }
    private static void insertInitialData(final Context context) {
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() { // worker thread
                AppDatabase db = getInstance(context);
                db.userDao().addUser(new Record(0,"300","50")

                );
            }
        });
    }
}

package th.ac.su.cp.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import th.ac.su.cp.speedrecords.model.Record;
@Dao
public interface UserDao {
    @Query("SELECT * FROM records")
    Record[] getAllUsers();

    @Insert
    void addUser(Record... records);

    @Delete
    void deleteUser(Record record);
}

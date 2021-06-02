package com.example.crewdetails.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;
@Dao
public interface CrewDao {
    @Query("SELECT * FROM crew")
    LiveData<List<CrewEntry>> loadAllTasks();

    @Insert
    void insertTask(ArrayList<CrewEntry>Crewentries);


    @Query("DELETE FROM crew")
    public void nukeTable();

}

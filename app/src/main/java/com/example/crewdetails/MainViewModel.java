package com.example.crewdetails;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.crewdetails.database.AppDataBase;
import com.example.crewdetails.database.CrewEntry;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<CrewEntry>> tasks;

    public MainViewModel(Application application) {
        super(application);
        AppDataBase database = AppDataBase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        tasks = database.CrewDao().loadAllTasks();
    }

    public LiveData<List<CrewEntry>> getTasks() {
        return tasks;
    }
}


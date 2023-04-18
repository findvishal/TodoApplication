package com.example.todoapplication;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapplication.database.AppDatabase;
import com.example.todoapplication.database.TaskEntry;
import com.example.todoapplication.database.TasksRepository;


import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<TaskEntry>> tasks;
    private final TasksRepository tasksRepository;
    public MainViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        tasksRepository = new TasksRepository(database);
        tasks = tasksRepository.getloadAllTasks();
    }

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }

    public void deleteTask(TaskEntry taskEntry) {
        tasksRepository.deleteTasks(taskEntry);
    }
}
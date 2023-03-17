package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActivityQuestions6.class},version = 1)
public abstract class ActivityRoomDb6 extends RoomDatabase {

    private static ActivityRoomDb6 INSTANCE;

    public abstract ActivityDao6 activityDao();

    public static synchronized ActivityRoomDb6 getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ActivityRoomDb6.class, "activity_week6_questions")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();
        }

        return INSTANCE;

    }

    private static Callback RoomDBCallback = new Callback(){


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private ActivityDao6 activityDao;


        private PopulateDbAsyncTask(ActivityRoomDb6 db){

            activityDao = db.activityDao();

        }

        @Override
        protected Void doInBackground(Void... voids){

            activityDao.insert(new ActivityQuestions6("Abigail was starting to create a flowchart. Which symbol should she use at the begging of her flowchart?",
                    "Display Symbol",
                    "Oval Shape",
                    "Circle Shape",
                    "Diamond Shape",2));
            activityDao.insert(new ActivityQuestions6("Cedrick wanted to indicate that a step is connected to another page or part of the Flowchart?",
                    "Display Symbol",
                    "Oval Shape",
                    "Circle Shape",
                    "Diamond Shape",2));
            activityDao.insert(new ActivityQuestions6("Elly process that can answer a decision of \"yes\" or \"no\". Which symbol should she use?",
                    "Display Symbol",
                    "Oval Shape",
                    "Circle Shape",
                    "Diamond Shape",4));
            activityDao.insert(new ActivityQuestions6("Which element of flowchart is represented by a small circle or a connector box and is labeled using letters?",
                    "Arrow Lines",
                    "Connector",
                    "Decision",
                    "Process",2));
            activityDao.insert(new ActivityQuestions6("Which element of flowchart is represented by a rectangle and refers to an action?",
                    "Connector",
                    "Process",
                    "Sub Process",
                    "Terminator"
                    ,2));
            activityDao.insert(new ActivityQuestions6("Which element appears at the start and at the end of a flowchart?",
                    "Connector",
                    "Decision",
                    "Process",
                    "Terminator",
                    4));
            activityDao.insert(new ActivityQuestions6("Which element is represented by a diamond?",
                    "Arrow Lines",
                    "Decision",
                    "Delay",
                    "Sub Process"
                    ,2));
            activityDao.insert(new ActivityQuestions6("Which element is represented by a rectangle and refers to an action in a business process?",
                    "Arrow Lines",
                    "Connector",
                    "Process",
                    "Sub Process"
                    ,2));
            activityDao.insert(new ActivityQuestions6("Which element is represented by a small circle or a connector box and is labelled using letters?",
                    "Connector",
                    "Decision",
                    "Sub Process",
                    "Terminator"
                    ,2));
            activityDao.insert(new ActivityQuestions6("Which symbol represents the input and output?",
                    "Lines",
                    "Parallelogram",
                    "Oval",
                    "Triangle"
                    ,2));
            return null;
        }
    }

}

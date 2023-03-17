package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActivityQuestions7.class},version = 1)
public abstract class ActivityRoomDb7 extends RoomDatabase {

    private static ActivityRoomDb7 INSTANCE;

    public abstract ActivityDao7 activityDao();

    public static synchronized ActivityRoomDb7 getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ActivityRoomDb7.class, "activity_week7_questions")
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

        private ActivityDao7 activityDao;


        private PopulateDbAsyncTask(ActivityRoomDb7 db){

            activityDao = db.activityDao();

        }

        @Override
        protected Void doInBackground(Void... voids){

            activityDao.insert(new ActivityQuestions7("Lucky was planning to create a flowchart. Which key steps in developing a flowchart should he do first?",
                    "Draw the flowchart.",
                    "Assign flowchart symbols.",
                    "Review and title the flowchart.",
                    "Define the process to be flowcharted.",
                    2));
            activityDao.insert(new ActivityQuestions7("Tiffany is taking notes of the actual steps that is happening in the flowchart that she will create. Which key steps in creating a developing a flowchart does she do?",
                    "Establish process boundaries.",
                    "Put the steps in chronological order.",
                    "Assemble the right people to develop the flowchart.",
                    "List the steps, activities, and decisions to be charted.",
                    2));
            activityDao.insert(new ActivityQuestions7("Chelsea is done assigning the flowchart symbols. Which key steps in developing a flowchart should she do next?",
                    "Establish process boundaries.",
                    "Review and Title the flowchart",
                    "Define the process to be flowcharted.",
                    "List the steps, activities, and decisions to be charted.",
                    4));
            activityDao.insert(new ActivityQuestions7("Manny listed the steps, activities, and decisions to be charted. Which should he do next?",
                    "Review and Title the flowchart.",
                    "Put the steps in chronological order.",
                    "Assemble the right people to develop the flowchart.",
                    "List the steps, activities, and decisions to be charted.",
                    2));
            activityDao.insert(new ActivityQuestions7("Joshua arranges the steps exactly as how they have occurred. Which steps in developing a successful flowchart did he do?",
                    "Review and Title the flowchart.",
                    "Put the steps in chronological order.",
                    "Assemble the right people to develop the flowchart.",
                    "List the steps, activities, and decisions to be charted.",
                    2));

            return null;
        }
    }

}

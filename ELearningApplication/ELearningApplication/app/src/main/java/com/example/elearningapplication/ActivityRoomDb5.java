package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActivityQuestions5.class},version = 1)
public abstract class ActivityRoomDb5 extends RoomDatabase {

    private static ActivityRoomDb5 INSTANCE;

    public abstract ActivityDao5 activityDao();

    public static synchronized ActivityRoomDb5 getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ActivityRoomDb5.class, "activity_week5_questions")
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

        private ActivityDao5 activityDao;


        private PopulateDbAsyncTask(ActivityRoomDb5 db){

            activityDao = db.activityDao();

        }

        @Override
        protected Void doInBackground(Void... voids){

            activityDao.insert(new ActivityQuestions5("Which is the act or discipline of creating models or plans to build something through designs?",
                    "Technical Ability",
                    "Technical Assessment ",
                    "Technical Drawing",
                    "Technical Management",
                    2));
            activityDao.insert(new ActivityQuestions5("Joanna wanted to present a layout of her dream house. Which type of technical drawing should she use?",
                    "Chart",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,2));
            activityDao.insert(new ActivityQuestions5("Maria is a computer engineer. She was tasked to create a step-by-step diagram of the process in the input and output of the computer data. Which technical drawing should she use?",
                    "Chart",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,1));
            activityDao.insert(new ActivityQuestions5("A ________ is a graphical representation of data. It allows users to see what the results of data to better understand and predict current and future data.",
                    "Chart",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,1));
            activityDao.insert(new ActivityQuestions5("Kathrine is an electrical engineer and was tasked to present a picture that represents the components of a process, device, or other object using abstract often standardized symbols and lines. Which appropriate technical drawing should she use?",
                    "Block Diagram",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,1));
            activityDao.insert(new ActivityQuestions5("Which technical drawing is the visual representation of how parts of an object relate to each other and work together?",
                    "Block Diagram",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,1));
            activityDao.insert(new ActivityQuestions5("A _________ is a graphical representation of decisions and their results mapped out in individual shapes.",
                    "Block Diagram",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,4));
            activityDao.insert(new ActivityQuestions5("A ____________ is a scaled diagram of a room or building viewed from above.",
                    "Block Diagram",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,4));
            activityDao.insert(new ActivityQuestions5("A ____________ is a picture that represents the components of a process, device, or other object using abstract often standardized symbols and lines",
                    "Block Diagram",
                    "Flowchart",
                    "Schematic Diagram",
                    "Floor Plan"
                    ,2));
            activityDao.insert(new ActivityQuestions5("Technical drawings are used widely throughout many industries by professionals including:",
                    "Architects",
                    "CAD Technicians",
                    "Engineers",
                    "All of the above"
                    ,2));
            return null;
        }
    }

}

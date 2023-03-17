package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActivityQuestions2.class},version = 1)
public abstract class ActivityRoomDb2 extends RoomDatabase {

    private static ActivityRoomDb2 INSTANCE;

    public abstract ActivityDao2 activityDao();

    public static synchronized ActivityRoomDb2 getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ActivityRoomDb2.class, "activity_week2_questions")
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

        private ActivityDao2 activityDao;


        private PopulateDbAsyncTask(ActivityRoomDb2 db){

            activityDao = db.activityDao();

        }

        @Override
        protected Void doInBackground(Void... voids){

            activityDao.insert(new ActivityQuestions2("Computer uses the digits 0 and 1 to store data. It is the smallest unit of data in computing",
                    "Binary",
                    "Circuit",
                    "Software",
                    "Transistor",2));
            activityDao.insert(new ActivityQuestions2("These are sets of instructions that is translated into machine code. It is simple binary codes that activate the CPU.",
                    "Bits",
                    "Computer programs",
                    "Programmers",
                    "Software",
                    1));
            activityDao.insert(new ActivityQuestions2("It is a tiny switch that is activated by the electronic signals it receives.",
                    "Binary",
                    "Circuit",
                    "Software",
                    "Transistor"
                    ,2));
            activityDao.insert(new ActivityQuestions2("It is represented by a 0 or a 1.",
                    "Binary",
                    "Circuit",
                    "Software",
                    "Transistor",4));
            activityDao.insert(new ActivityQuestions2("It indicates how many digits are available within a numerical system.",
                    "Binary",
                    "Decimal",
                    "Denary",
                    "Number base"
                    ,2));
            activityDao.insert(new ActivityQuestions2("To calculate how much data can be stored within a certain capacity you need to understand the following:",
                    "How to convert between the relevant units",
                    "The available space (capacity) for storing the data.",
                    "The size of the data being stored.",
                    "All of the above",
                    2));
            activityDao.insert(new ActivityQuestions2("Convert 35 to binary number",
                    "100011",
                    "100111",
                    "101011",
                    "110011"
                    ,2));
            activityDao.insert(new ActivityQuestions2("Convert 69 to binary number",
                    "1000100",
                    "1000101",
                    "1010011",
                    "1000110"
                    ,2));
            activityDao.insert(new ActivityQuestions2("Convert 11101  to decimal number",
                    "29",
                    "39",
                    "49",
                    "59"
                    ,2));
            activityDao.insert(new ActivityQuestions2("Convert 110001  to decimal number",
                    "45",
                    "49",
                    "50",
                    "59"
                    ,2));
            return null;
        }
    }

}

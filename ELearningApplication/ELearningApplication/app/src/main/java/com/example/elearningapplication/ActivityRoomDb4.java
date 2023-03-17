package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActivityQuestions4.class},version = 1)
public abstract class ActivityRoomDb4 extends RoomDatabase {

    private static ActivityRoomDb4 INSTANCE;

    public abstract ActivityDao4 activityDao();

    public static synchronized ActivityRoomDb4 getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ActivityRoomDb4.class, "activity_week4_questions")
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

        private ActivityDao4 activityDao;


        private PopulateDbAsyncTask(ActivityRoomDb4 db){

            activityDao = db.activityDao();

        }

        @Override
        protected Void doInBackground(Void... voids){

            activityDao.insert(new ActivityQuestions4("It is the most common type of storage used in computers.",
                    "Flash memory devices",
                    "Magnetic storage devices",
                    "Magnetic storage devices",
                    "Storage media devices",
                    2));
            activityDao.insert(new ActivityQuestions4("It is a data-storage medium used transferring data between a personal computer (PC) and other digital devices.",
                    "Flash memory devices",
                    "Magnetic storage devices",
                    "Magnetic storage devices",
                    "Storage media devices",
                    1));
            activityDao.insert(new ActivityQuestions4("It is a portable storage device which connects a computer via USB port.",
                    "Hard drive",
                    "Memory card",
                    "Thumb drive",
                    "SD card",
                    2));
            activityDao.insert(new ActivityQuestions4("It is an internal hard drive is the main storage device in a computer.",
                    "Hard drive",
                    "Memory card",
                    "Thumb drive",
                    "SD card",
                    4));
            activityDao.insert(new ActivityQuestions4("It uses lasers and lights as its method of reading and writing data.",
                    "Flash memory devices",
                    "Magnetic storage devices",
                    "Magnetic storage devices",
                    "Storage media devices",
                    1));
            activityDao.insert(new ActivityQuestions4("It should be pulled out safely from the PC after use because it could result to full damage of the disk.",
                    "Blu-ray disc",
                    "CD-ROM",
                    "Memory Card",
                    "Optical Disks",
                    2));
            activityDao.insert(new ActivityQuestions4("It should be maintained and cleaned on a regular basis to prevent damage to media.",
                    "Drives",
                    "RAM",
                    "ROM",
                    "SD card"
                    ,2));
            activityDao.insert(new ActivityQuestions4("It causes the melting of electronic elements in the storage media.",
                    "Air",
                    "Heat",
                    "Liquid",
                    "Magnetic field"
                    ,2));
            activityDao.insert(new ActivityQuestions4("It is the data-carrying layer of the optical disk which is the most prone to damage.",
                    "Both surface",
                    "Lower surface",
                    "Side surface",
                    "Upper surface"
                    ,2));
            activityDao.insert(new ActivityQuestions4("There should be enough to prevent damaging your devices with moisture or heat.",
                    "Air supply",
                    "Electricity supply",
                    "Heat supply",
                    "Magnetic supply"
                    ,2));
            return null;
        }
    }

}

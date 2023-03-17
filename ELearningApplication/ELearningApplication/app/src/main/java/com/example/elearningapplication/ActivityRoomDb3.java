package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActivityQuestions3.class},version = 1)
public abstract class ActivityRoomDb3 extends RoomDatabase {

    private static ActivityRoomDb3 INSTANCE;

    public abstract ActivityDao3 activityDao();

    public static synchronized ActivityRoomDb3 getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ActivityRoomDb3.class, "activity_week3_questions")
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

        private ActivityDao3 activityDao;


        private PopulateDbAsyncTask(ActivityRoomDb3 db){

            activityDao = db.activityDao();

        }

        @Override
        protected Void doInBackground(Void... voids){

            activityDao.insert(new ActivityQuestions3("If the size of an average digital photograph is 8MB, how many photographs can be stored on a 64GB SD card?",
                    "128 photos",
                    "512 photos",
                    "8192 photos",
                    "65,536 photos",
                    2));
            activityDao.insert(new ActivityQuestions3("Jungkook has a photo that is 2MB. How many of these can he store on his 4GB memory stick?",
                    "1024 photos",
                    "4096 photos",
                    "2048 photos",
                    "8182 photos",
                    1));
            activityDao.insert(new ActivityQuestions3("Huh Joon-ho has 50 small images. Each of which is 400 KB. How much space does he take up overall in MB?",
                    "19.5 MB",
                    "8182 MB",
                    "128 MB",
                    "51,200 MB",
                    2));
            activityDao.insert(new ActivityQuestions3("If the size of an average mp3 is 8 MB, how many mp3 can be stored on a 128GB SD card?",
                    "1024 mp3",
                    "16,384 mp3",
                    "8192 mp3",
                    "131,072 mp3",
                    4));
            activityDao.insert(new ActivityQuestions3("Song Hye-Kyo has a file that is 20MB. How many files can fit in a 6GB Hard drive?",
                    "120 mp3",
                    "3413 mp3",
                    "307 mp3",
                    "6144 mp3",
                    2));
            activityDao.insert(new ActivityQuestions3("1 Terabyte is equivalent to 1024 Gigabytes (GB), then 5TB is equivalent to how many GB?",
                    "204.8 GB",
                    "5120 GB",
                    "5120 MB",
                    "5120 TB",
                    2));
            activityDao.insert(new ActivityQuestions3("To calculate how much data can be stored within a certain capacity you need to understand the following:",
                    "How to convert between the relevant units",
                    "The available space (capacity) for storing the data.",
                    "The size of the data being stored.",
                    "All of the above"
                    ,2));
            activityDao.insert(new ActivityQuestions3("Computer uses the digits 0 and 1 to store data. The smallest unit of data in computing is called a ________. It is presented by a 0 or 1.",
                    "Bit",
                    "Byte",
                    "Kilobyte",
                    "Megabyte"
                    ,2));
            activityDao.insert(new ActivityQuestions3("1 Kilobyte is equivalent to how many Bytes?",
                    "1000 bits",
                    "1023 bytes",
                    "1024 bytes",
                    "1024 mb"
                    ,2));
            activityDao.insert(new ActivityQuestions3("1 Gigabyte is equivalent to how many Megabytes?\t",
                    "1024 Bytes",
                    "1024 Kilobytes",
                    "1024 Megabytes",
                    "1024 Gigabytes"
                    ,3));
            return null;
        }
    }

}

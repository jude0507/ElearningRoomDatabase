package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ActivityQuestions.class},version = 1)
public abstract class ActivityRoomDb extends RoomDatabase {

    private static ActivityRoomDb INSTANCE;

    public abstract ActivityDao activityDao();

    public static synchronized ActivityRoomDb getInstance(final Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ActivityRoomDb.class, "activity_week1_questions")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback(){


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private ActivityDao activityDao;


        private PopulateDbAsyncTask(ActivityRoomDb db){

            activityDao = db.activityDao();

        }

        @Override
        protected Void doInBackground(Void... voids){

            activityDao.insert(new ActivityQuestions("It is any physical device capable of storing information temporarily, like RAM (Random Access Memory), or permanently, like ROM (Read- Only Memory).","Auxiliary Memory","Computer Memory","Random Access Memory","Read Only Memory",2));
            activityDao.insert(new ActivityQuestions("It is also known as the volatile memory and the system's short-term memory.","Auxiliary Memory","Computer Memory","Random Access Memory","Read Only Memory",1));
            activityDao.insert(new ActivityQuestions("It contains instructions that can be directly accessed by the CPU.","Auxiliary Memory","Computer Memory","Random Access Memory","Read Only Memory",2));
            activityDao.insert(new ActivityQuestions("It indicates the memory to retain its contents as long as power is being supplied.","Dynamic Ram or DRAM","EDO RAM or Extended Data Out","SDRAM or Synchronous DRAM","Static RAM or SRAM",4));
            activityDao.insert(new ActivityQuestions("It must be continually refreshed to maintain the data.","Dynamic Ram or DRAM","EDO RAM or Extended Data Out","SDRAM or Synchronous DRAM","Static RAM or SRAM",2));
            activityDao.insert(new ActivityQuestions("It is a memory unit that connects directly to the CPU is the primary memory.","Auxiliary Memory or Secondary Memory","Cache Memory","Computer Memory","Main Memory or Primary Memory",2));
            activityDao.insert(new ActivityQuestions("It is an integrated electronic circuit that performs the calculations that runs a computer.","Data storage capacity","Memory","Processor","Video card",2));
            activityDao.insert(new ActivityQuestions("It represents how much disk space can one or more storage devices provides.","Data storage capacity","Memory","Processor","Video card",2));
            activityDao.insert(new ActivityQuestions("It acts as a buffer between the CPU and the main memory.","Auxiliary Memory or Secondary Memory","Cache Memory","Computer Memory","Main Memory or Primary Memory",2));
            activityDao.insert(new ActivityQuestions("It is used to process images so they can be displayed on your monitor.","Data storage capacity","Memory","Processor","Video card",2));
            return null;
        }
    }

}

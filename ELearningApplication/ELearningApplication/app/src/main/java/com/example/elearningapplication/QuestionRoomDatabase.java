package com.example.elearningapplication;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Questionnaire.class},version = 1)
public abstract class QuestionRoomDatabase  extends RoomDatabase {

    private static QuestionRoomDatabase INSTANCE;

    public abstract QuestionnaireDao questionnaireDao();

    public static synchronized QuestionRoomDatabase getInstance(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionRoomDatabase.class, "question_tables")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallBack)
                    .build();
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback RoomDBCallBack= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    @SuppressWarnings("deprecation")
    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private final QuestionnaireDao questionnaireDao;

        private PopulateDbAsyncTask(QuestionRoomDatabase db){
            questionnaireDao = db.questionnaireDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            questionnaireDao.insert(new Questionnaire("What is Android?","OS","Browser","Software","Hard Drive",1));
            questionnaireDao.insert(new Questionnaire("Ram Stands for what?","Operating System","Browser","Random Access Memory","Hard Drive",3));
            questionnaireDao.insert(new Questionnaire("Chrome is what?","OS","Browser","Software","Hard Drive",2));
            questionnaireDao.insert(new Questionnaire("HTML is what?","Scripting Language","Program Language","Software","Hyper Text Markup Language",4));
            questionnaireDao.insert(new Questionnaire("Unity is used for","Game Development","Web Development","Graphic Design","3-D Modling",2));
            questionnaireDao.insert(new Questionnaire("What is OS?","Operating System","Browser","Random Access Memory","Hard Drive",1));
            questionnaireDao.insert(new Questionnaire("IP stand for what?","Internet Protocol","Language","Graphics","Hard Drive",1));

            return null;
        }
    }



}


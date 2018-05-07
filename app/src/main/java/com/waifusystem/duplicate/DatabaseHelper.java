package com.waifusystem.duplicate;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {


    private int[] order = new int[] {
             2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31
    };

    private static final String DB_name = "BTTC";
    private static final int DB_version = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //initiate the random position item
        randomizeArray();

        sqLiteDatabase.execSQL("CREATE TABLE PERSON (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "POSITION INTEGER, "
                + "NOWCHECKING INTEGER, "
                + "CHECKED INTEGER);"); //1 is done 0 isn't
        for (int i = 0; i <Profile.profiles.length ; i++) {
            ContentValues personValue = new ContentValues();
            if (i == 0) {
                personValue.put("POSITION", 1);
                personValue.put("NOWCHECKING", 0);
                personValue.put("CHECKED", 0);
                sqLiteDatabase.insert("PERSON", null, personValue);
            } else {
                personValue.put("POSITION", order[i - 1]);
                personValue.put("NOWCHECKING", 0);
                personValue.put("CHECKED", 0);
                sqLiteDatabase.insert("PERSON", null, personValue);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void randomizeArray() {
        Random random = new Random();
        int swapper;
        int randomPos;
        for (int i = 0; i < order.length; i++) {
            randomPos = random.nextInt(order.length);
            swapper = order[i];
            order[i] = order[randomPos];
            order[randomPos] = swapper;
        }
    }
}

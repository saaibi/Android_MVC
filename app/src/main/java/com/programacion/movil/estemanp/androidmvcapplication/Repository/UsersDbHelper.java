package com.programacion.movil.estemanp.androidmvcapplication.Repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.programacion.movil.estemanp.androidmvcapplication.Domain.User;

/**
 * Created by estemanp on 5/09/16.
 * Manejador de base de datos
 */
public class UsersDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ("
                + UserContract.UserEntry.USER_NAME + " PRIMARY KEY NOT NULL,"
                + UserContract.UserEntry.NAME + " TEXT NOT NULL,"
                + UserContract.UserEntry.LAST_NAME + " TEXT NOT NULL,"
                + UserContract.UserEntry.PASSWORD + " TEXT NOT NULL,"
                + UserContract.UserEntry.AGE + " INT NOT NULL)");

        //Ingreso datos a la base de datos de prueba.
        mockData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //sin operaciones
    }

    public long mocUser(SQLiteDatabase db, User user) {
        return db.insert(
                UserContract.UserEntry.TABLE_NAME,
                null,
                user.toContentValues());
    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        User person1=new User("Andres","Perez","estemanp","1234",24);
        mocUser(sqLiteDatabase,person1);

        User person2=new User("Jessica","Ramirez","jessica","1234",20);
        mocUser(sqLiteDatabase,person2);

        User person3=new User("Albeiro","Alzate","albeiro","0000",45);
        mocUser(sqLiteDatabase,person3);
    }

    public long addUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                UserContract.UserEntry.TABLE_NAME,
                null,
                user.toContentValues());

    }

    public Cursor getUserById(String username) {
        Cursor c = getReadableDatabase().query(
                UserContract.UserEntry.TABLE_NAME,
                null,
                UserContract.UserEntry.USER_NAME + " LIKE ?",
                new String[]{username},
                null,
                null,
                null);
        return c;
    }

    public int updateUser(User user, String username) {
        return getWritableDatabase().update(
                UserContract.UserEntry.TABLE_NAME,
                user.toContentValues(),
                UserContract.UserEntry.USER_NAME + " LIKE ?",
                new String[]{username}
        );
    }


    public int deleteUser(String username) {
        return getWritableDatabase().delete(
                UserContract.UserEntry.TABLE_NAME,
                UserContract.UserEntry.USER_NAME + " LIKE ?",
                new String[]{username});
    }

    public Cursor getAllUsers() {
        return getReadableDatabase()
                .query(
                        UserContract.UserEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }
}

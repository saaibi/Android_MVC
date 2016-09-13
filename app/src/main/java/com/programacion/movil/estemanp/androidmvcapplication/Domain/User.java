package com.programacion.movil.estemanp.androidmvcapplication.Domain;

import android.content.ContentValues;
import android.database.Cursor;

import com.programacion.movil.estemanp.androidmvcapplication.Repository.UserContract;

/**
 * Created by estemanp on 19/08/16.
 */
public class User {

    private String name;
    private String lastName;
    private String userName;
    private String password;
    private int age;

    public User(String name, String lastName, String userName, String password,int age){
        this.setName(name);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setPassword(password);
        this.setAge(age);
    }

    public User(Cursor cursor) {
        userName = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.USER_NAME));
        name = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.NAME));
        lastName = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.LAST_NAME));
        password = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.PASSWORD));
        age = cursor.getInt(cursor.getColumnIndex(UserContract.UserEntry.AGE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.USER_NAME, getUserName());
        values.put(UserContract.UserEntry.NAME, getName());
        values.put(UserContract.UserEntry.LAST_NAME, getLastName());
        values.put(UserContract.UserEntry.PASSWORD, getPassword());
        values.put(UserContract.UserEntry.AGE, getAge());
        return values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

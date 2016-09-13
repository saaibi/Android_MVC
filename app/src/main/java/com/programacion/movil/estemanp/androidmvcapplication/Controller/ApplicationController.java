package com.programacion.movil.estemanp.androidmvcapplication.Controller;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;

import com.programacion.movil.estemanp.androidmvcapplication.Domain.User;
import com.programacion.movil.estemanp.androidmvcapplication.Repository.UserContract;
import com.programacion.movil.estemanp.androidmvcapplication.Repository.UsersDbHelper;
import com.programacion.movil.estemanp.androidmvcapplication.View.LandingActivity;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by estemanp on 19/08/16.
 */
public class ApplicationController extends Application {

    private List<User> users;
    private UsersDbHelper usersDbHelper;

    public  ApplicationController(){
        users=new ArrayList<User>();
        //fillDateBases();
    }

    private void fillDateBases(){
        //Add users
        fillUsers();
    }

    private void fillUsers(){
        User person1=new User("Andres","Perez","estemanp","1234",24);
        users.add(person1);

        User person2=new User("Jessica","Ramirez","jessica","1234",16);
        users.add(person2);
    }

    private boolean isValidUser(String username, String password){
        Boolean resp=false;
        for (User person:users) {
            if(username.equals(person.getUserName())){
                if(password.equals(person.getPassword())){
                    resp=true;
                }
            }
        }
        return resp;
    }


    public void authenticate(String username, String password){
        usersDbHelper = new UsersDbHelper(this.getApplicationContext());
        new AuthenticTask().execute(username,password);
    }

    private class AuthenticTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            List<User> listUsers= new ArrayList<>();
            Cursor cursor=usersDbHelper.getAllUsers();
            cursor.moveToFirst();
            User userAux;
            while(!cursor.isAfterLast()) {
                /*userAux=new User(cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.NAME)),
                        cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.LAST_NAME)),
                        cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.PASSWORD)),
                        cursor.getInt(cursor.getColumnIndex(UserContract.UserEntry.AGE)));*/
                userAux=new User(cursor);
                listUsers.add(userAux);
                cursor.moveToNext();
            }
            System.out.println("Termino de traer los datos");
            users=listUsers;

            return isValidUser(params[0],params[1]);
        }

        @Override
        protected void onPostExecute(Boolean isAuthenticate) {
            super.onPostExecute(isAuthenticate);
            if(isAuthenticate){
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), LandingActivity.class);
                intent.setAction(LandingActivity.class.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

                getApplicationContext().startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "El usuario y contraseña no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

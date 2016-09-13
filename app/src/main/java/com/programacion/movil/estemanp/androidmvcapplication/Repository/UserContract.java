package com.programacion.movil.estemanp.androidmvcapplication.Repository;

import android.provider.BaseColumns;

/**
 * Created by estemanp on 5/09/16.
 * Esquema de la base de datos de usuarios
 */
public class UserContract {
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME ="user";

        public static final String USER_NAME = "username";
        public static final String NAME = "name";
        public static final String LAST_NAME = "lastname";
        public static final String PASSWORD = "password";
        public static final String AGE = "age";
    }
}

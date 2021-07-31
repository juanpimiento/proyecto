package com.juanpimiento.proyecto.ui.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.juanpimiento.proyecto.ui.database.dao.UserDao;
import com.juanpimiento.proyecto.ui.database.model.User;
import com.juanpimiento.proyecto.util.Constant;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();
    private static  UserDatabase userDB;

    public static UserDatabase getInstance(Context context){
        if (userDB == null){
            userDB = buildDatabaseInstance(context);
        }
        return userDB;
    }

    private static UserDatabase buildDatabaseInstance(Context context){
        return Room.databaseBuilder(context,
                UserDatabase.class,
                Constant.DB_USER).allowMainThreadQueries().build();
    }
    public void cleanUp(){
        userDB = null;
    }
}

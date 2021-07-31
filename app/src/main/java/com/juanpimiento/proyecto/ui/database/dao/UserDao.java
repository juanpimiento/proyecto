package com.juanpimiento.proyecto.ui.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.juanpimiento.proyecto.ui.database.model.User;
import com.juanpimiento.proyecto.util.Constant;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM " + Constant.TABLE_NAME_USER)
    List<User> getUser();

    @Insert
    long insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Delete
    void deleteUser(User... users);
}

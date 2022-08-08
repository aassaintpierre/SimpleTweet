package com.codepath.apps.restclienttemplate;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.codepath.apps.restclienttemplate.models.SampleModel;
import com.codepath.apps.restclienttemplate.models.SampleModelDao;

@Database(entities={SampleModel.class}, version=1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract SampleModelDao sampleModelDao();

    // Database name to be used
    public static final String NAME = "MyDataBase";
}



//// bump version number if your schema changes
//@Database(entities={User.class, Organization.class}, version=1)
//public abstract class MyDatabase extends RoomDatabase {
//  // Declare your data access objects as abstract
//  public abstract UserDao userDao();
//
//  // Database name to be used
//  public static final String NAME = "MyDataBase";
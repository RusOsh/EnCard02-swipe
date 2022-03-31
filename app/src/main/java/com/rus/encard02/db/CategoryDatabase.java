package com.rus.encard02.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rus.encard02.data.model.roomModel.CategoryModel;
import com.rus.encard02.data.model.roomModel.WordModel;

@Database(entities = {CategoryModel.class, WordModel.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {
    public abstract CategoryDao getCategoryDao();
    public abstract WordDao getWordDao();
}

package com.rus.encard02.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rus.encard02.data.model.roomModel.CategoryModel;
import com.rus.encard02.data.model.roomModel.WordModel;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categorymodel")
    LiveData<List<CategoryModel>> getCategory();

    @Insert
    void addCategory(CategoryModel model);

    @Delete
    void delete(CategoryModel model);
}
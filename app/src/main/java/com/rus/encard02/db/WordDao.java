package com.rus.encard02.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rus.encard02.data.model.roomModel.WordModel;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM wordmodel WHERE category=:category")
    LiveData<List<WordModel>> getWord(String category);

    @Insert
    void addWord(WordModel model);

    @Delete
    void delete(WordModel model);

    @Delete
    void deleteAllWords(List<WordModel> wordModels);


    @Query("SELECT * FROM wordmodel WHERE category=:category")
    List<WordModel> wordList(String category);

}
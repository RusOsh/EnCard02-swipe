package com.rus.encard02.ui.fragment.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rus.encard02.data.model.roomModel.CategoryModel;
import com.rus.encard02.data.model.roomModel.WordModel;
import com.rus.encard02.repositories.roomRepository.RoomRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final RoomRepository repository;
    private LiveData<List<CategoryModel>> categoryLiveData;
    private LiveData<List<WordModel>> wordLiveData;

    @Inject
    public HomeViewModel(RoomRepository repository) {
        this.repository = repository;
        categoryLiveData = new MutableLiveData<>();
        wordLiveData = new MutableLiveData<>();
    }

    public void getCategory() {
        categoryLiveData = repository.getCategory();
    }

    public void deleteCategory(CategoryModel model) {
        repository.deleteCategory(model);
    }

    public void deleteAllWord(String category) {
        repository.deleteAllWords(category);
    }
    public LiveData<List<CategoryModel>> getCategoryLiveData() {
        return categoryLiveData;
    }
}
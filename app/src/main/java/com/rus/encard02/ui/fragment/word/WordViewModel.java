package com.rus.encard02.ui.fragment.word;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rus.encard02.common.Resource;
import com.rus.encard02.data.model.pixabayModel.Hit;
import com.rus.encard02.data.model.pixabayModel.PixabayResponse;
import com.rus.encard02.data.model.roomModel.WordModel;
import com.rus.encard02.repositories.pixabayRepository.PixabayRepository;
import com.rus.encard02.repositories.roomRepository.RoomRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WordViewModel extends ViewModel {
    private LiveData<Resource<PixabayResponse<Hit>>> liveData;
    private final PixabayRepository pixabayRepository;
    private final RoomRepository roomRepository;

    @Inject
    public WordViewModel(PixabayRepository pixabayRepository, RoomRepository roomRepository) {
        this.pixabayRepository = pixabayRepository;
        this.roomRepository = roomRepository;
        liveData = new MutableLiveData<>();
    }

    public LiveData<List<WordModel>> getImage(String category) {
        return roomRepository.getWord(category);
    }

    public void loadImage(String word, String category) {
        liveData = pixabayRepository.getImage(word, category);
    }

    public void deleteWord(WordModel model) {
        roomRepository.deleteWord(model);
    }

    public LiveData<Resource<PixabayResponse<Hit>>> getLiveData() {
        return liveData;
    }
}

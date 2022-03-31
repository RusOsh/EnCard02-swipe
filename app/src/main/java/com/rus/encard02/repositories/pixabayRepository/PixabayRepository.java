package com.rus.encard02.repositories.pixabayRepository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.rus.encard02.ui.Constant;
import com.rus.encard02.common.Resource;
import com.rus.encard02.data.model.pixabayModel.Hit;
import com.rus.encard02.data.model.pixabayModel.PixabayResponse;
import com.rus.encard02.data.model.roomModel.WordModel;
import com.rus.encard02.data.network.PixabayApi;
import com.rus.encard02.db.WordDao;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixabayRepository {
    private final PixabayApi api;
    private final WordDao dao;

    @Inject
    public PixabayRepository(PixabayApi api, WordDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public MutableLiveData<Resource<PixabayResponse<Hit>>> getImage(String word, String category) {
        MutableLiveData<Resource<PixabayResponse<Hit>>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getImage(Constant.PIXABAYKEY, word).enqueue(new Callback<PixabayResponse<Hit>>() {
            @Override
            public void onResponse(@NotNull Call<PixabayResponse<Hit>> call,
                                   @NotNull Response<PixabayResponse<Hit>> response) {
                Log.e("img", response.body() + "");
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("isSucc", response.body() + "");
                    liveData.setValue(Resource.success(response.body()));
                    String imgUrl = response.body().getHits().get(0).getLargeImageURL();
                    dao.addWord(new WordModel(imgUrl, word, category));
                } else {
                    liveData.setValue(Resource.error(response.message()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<PixabayResponse<Hit>> call, @NotNull Throwable t) {
                Log.e("img", t.getLocalizedMessage() + "");
                liveData.setValue(Resource.error(t.getLocalizedMessage()));
            }
        });
        return liveData;
    }
}

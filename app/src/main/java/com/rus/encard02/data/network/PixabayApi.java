package com.rus.encard02.data.network;

import com.rus.encard02.data.model.pixabayModel.Hit;
import com.rus.encard02.data.model.pixabayModel.PixabayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {
    @GET("/api")
    Call<PixabayResponse<Hit>> getImage(@Query("key") String apiKey,
                                        @Query("q") String keyWord);
}

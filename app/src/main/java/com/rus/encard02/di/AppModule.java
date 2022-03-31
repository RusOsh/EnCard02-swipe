package com.rus.encard02.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.rus.encard02.data.network.PixabayApi;
import com.rus.encard02.db.CategoryDao;
import com.rus.encard02.db.CategoryDatabase;
import com.rus.encard02.db.WordDao;
import com.rus.encard02.repositories.pixabayRepository.PixabayRepository;
import com.rus.encard02.repositories.roomRepository.RoomRepository;
import com.rus.encard02.ui.Constant;
import com.rus.encard02.ui.Prefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@InstallIn(SingletonComponent.class)
@Module
public class AppModule {

    @Provides
    @Singleton
    public static PixabayApi providerApi(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(PixabayApi.class);
    }

    @Provides
    @Singleton
    public static OkHttpClient providerClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor()
                                .setLevel(
                                        HttpLoggingInterceptor
                                                .Level.BODY)).build();
    }

    @Provides
    @Singleton
    public static CategoryDatabase providerDB(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context,
                CategoryDatabase.class,
                "DB_NAME")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static CategoryDao providerCategoryDao(CategoryDatabase database) {
        return database.getCategoryDao();
    }

    @Provides
    @Singleton
    public static WordDao providerWordDao(CategoryDatabase database) {
        return database.getWordDao();
    }

    @Provides
    @Singleton
    public static PixabayRepository providerPixabayRepository(PixabayApi api, WordDao dao) {
        return new PixabayRepository(api, dao);
    }

    @Provides
    @Singleton
    public static RoomRepository providerRoomRepository(CategoryDao dao, WordDao wordDao) {
        return new RoomRepository(dao, wordDao);
    }

    @Provides
    @Singleton
    public SharedPreferences providerSharedPref(@ApplicationContext Context context) {
        return context
                .getApplicationContext()
                .getSharedPreferences(
                        Constant.SHARED,
                        Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public Prefs providerPrefs(SharedPreferences preferences) {
        return new Prefs(preferences);
    }
}

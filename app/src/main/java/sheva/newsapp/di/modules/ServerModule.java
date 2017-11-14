package sheva.newsapp.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;
import sheva.newsapp.mvp.model.api.ServerAPI;
import sheva.newsapp.mvp.model.repositories.ServerRepository;
import sheva.newsapp.utils.IConstants;

/**
 * Created by shevc on 22.09.2017.
 * Let's GO!
 */
@Module
@Singleton
public class ServerModule {


    @Provides
    @Singleton
    ServerAPI provideServerAPI(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IConstants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ServerAPI.class);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    public ServerRepository provideServerRepository() {
        return new ServerRepository();
    }

}

package sheva.newsapp.mvp.model.repositories;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import sheva.newsapp.App;
import sheva.newsapp.mvp.model.api.ServerAPI;
import sheva.newsapp.mvp.model.entities.NewsUpperEntity;
import sheva.newsapp.utils.IConstants;

/**
 * Created by Никита on 13.11.2017.
 */

public class ServerRepository {
    @Inject
    ServerAPI serverAPI;

    public ServerRepository() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<NewsUpperEntity> getArticles(String source, String sortBy) {
        return serverAPI.getArticles(source, sortBy, IConstants.API_KEY).observeOn(AndroidSchedulers.mainThread());
    }
}

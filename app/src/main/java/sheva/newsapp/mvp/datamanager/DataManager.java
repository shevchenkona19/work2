package sheva.newsapp.mvp.datamanager;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;
import sheva.newsapp.App;
import sheva.newsapp.mvp.model.entities.Article;
import sheva.newsapp.mvp.model.entities.NewsUpperEntity;
import sheva.newsapp.mvp.model.repositories.ServerRepository;

/**
 * Created by Никита on 13.11.2017.
 */

public class DataManager {
    @Inject
    ServerRepository serverRepository;

    public DataManager() {
        App.get().getAppComponent().inject(this);
    }

    public Observable<Article> getArticles(String source, String sortBy) {
        return serverRepository.getArticles(source, sortBy)
                .flatMap(new Func1<NewsUpperEntity, Observable<Article>>() {
                    @Override
                    public Observable<Article> call(NewsUpperEntity newsUpperEntity) {
                        return Observable.from(newsUpperEntity.getArticles());
                    }
                });
    }
}

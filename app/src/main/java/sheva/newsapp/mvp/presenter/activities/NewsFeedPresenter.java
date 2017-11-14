package sheva.newsapp.mvp.presenter.activities;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import sheva.newsapp.App;
import sheva.newsapp.mvp.datamanager.DataManager;
import sheva.newsapp.mvp.model.entities.Article;
import sheva.newsapp.mvp.presenter.base.BasePresenter;
import sheva.newsapp.mvp.presenter.interfaces.INewsFeedPresenter;
import sheva.newsapp.mvp.ui.interfaces.INewsFeedActivityView;

/**
 * Created by Никита on 13.11.2017.
 */

public class NewsFeedPresenter extends BasePresenter<INewsFeedActivityView> implements INewsFeedPresenter {
    @Inject
    DataManager dataManager;

    public NewsFeedPresenter() {
        App.get().getAppComponent().inject(this);
    }

    @Override
    public void getFeed(String source) {
        final List<Article> list = new ArrayList<>();
        dataManager.getArticles(source, "top")
                .subscribe(new Subscriber<Article>() {
                    @Override
                    public void onCompleted() {
                        getView().updateList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().errorInLoadingFeed();
                    }

                    @Override
                    public void onNext(Article article) {
                        list.add(article);
                    }
                });
    }
}

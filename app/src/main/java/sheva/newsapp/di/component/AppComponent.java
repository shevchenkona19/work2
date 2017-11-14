package sheva.newsapp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import sheva.newsapp.di.modules.AppModule;
import sheva.newsapp.di.modules.ServerModule;
import sheva.newsapp.mvp.datamanager.DataManager;
import sheva.newsapp.mvp.model.repositories.ServerRepository;
import sheva.newsapp.mvp.presenter.activities.NewsFeedPresenter;

/**
 * Created by shevc on 22.09.2017.
 * Let's GO!
 */
@Component(modules = {AppModule.class, ServerModule.class})
@Singleton
public interface AppComponent {
    void inject(ServerRepository serverRepository);

    void inject(DataManager dataManager);

    void inject(NewsFeedPresenter newsFeedPresenter);
}

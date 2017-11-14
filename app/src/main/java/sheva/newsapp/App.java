package sheva.newsapp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import sheva.newsapp.di.component.AppComponent;
import sheva.newsapp.di.component.DaggerAppComponent;
import sheva.newsapp.di.modules.AppModule;

/**
 * Created by shevc on 22.09.2017.
 * Let's GO!
 */

public class App extends Application {

    private static App instance;
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}

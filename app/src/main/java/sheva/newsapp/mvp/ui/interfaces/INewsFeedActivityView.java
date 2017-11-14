package sheva.newsapp.mvp.ui.interfaces;

import java.util.List;

import sheva.newsapp.mvp.model.entities.Article;

/**
 * Created by Никита on 13.11.2017.
 */

public interface INewsFeedActivityView extends IActivityView {
    void updateList(List<Article> list);
    void errorInLoadingFeed();
}

package sheva.newsapp.mvp.presenter.base;

import sheva.newsapp.mvp.ui.interfaces.IView;

/**
 * Created by Никита on 13.11.2017.
 */

public class BasePresenter<V extends IView> {
    private V v;

    public void bind(V v1) {
        v = v1;
    }

    public void unbind() {
        v = null;
    }

    public V getView() {
        return v;
    }
}

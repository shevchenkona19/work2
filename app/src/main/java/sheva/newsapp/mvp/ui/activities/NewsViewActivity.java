package sheva.newsapp.mvp.ui.activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsapp.R;
import sheva.newsapp.mvp.model.entities.Article;
import sheva.newsapp.utils.IConstants;

public class NewsViewActivity extends AppCompatActivity {
    @BindView(R.id.tbNewsView)
    Toolbar tbNewsView;
    @BindView(R.id.wvNewsView)
    WebView wvNewsView;
    @BindView(R.id.pbNewsViewLoading)
    ProgressBar pbLoading;

    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);
        ButterKnife.bind(this);
        article = getIntent().getExtras().getParcelable(IConstants.ARTICLE_KEY);
        if (article != null) {
            tbNewsView.setTitle(article.getTitle());
        } else {
            tbNewsView.setTitle(getString(R.string.app_name));
        }
        wvNewsView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvNewsView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress > 90) {
                    pbLoading.setVisibility(View.GONE);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    pbLoading.setProgress(newProgress, true);
                } else {
                    pbLoading.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        wvNewsView.loadUrl(article.getUrl());
    }
}

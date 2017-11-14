package sheva.newsapp.mvp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsapp.R;
import sheva.newsapp.mvp.model.entities.Article;
import sheva.newsapp.mvp.presenter.activities.NewsFeedPresenter;
import sheva.newsapp.mvp.ui.adapters.NewsFeedRecyclerViewAdapter;
import sheva.newsapp.mvp.ui.interfaces.INewsFeedActivityView;
import sheva.newsapp.utils.IConstants;
import sheva.newsapp.utils.ItemClickSupport;

public class NewsFeedActivity extends AppCompatActivity implements INewsFeedActivityView {
    @BindView(R.id.pbNewsFeedLoading)
    ProgressBar pbLoading;
    @BindView(R.id.rvNewsFeedList)
    RecyclerView rvFeed;
    @BindView(R.id.btnNewsFeedTryAgain)
    Button btnTryAgain;
    @BindView(R.id.tvNewsFeedFailedToLoad)
    TextView tvFailedToLoad;
    private final NewsFeedPresenter presenter = new NewsFeedPresenter();
    private NewsFeedRecyclerViewAdapter adapter;
    private String currentSource = IConstants.DEFAULT_SOURCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        ButterKnife.bind(this);
        presenter.bind(this);

        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsFeedRecyclerViewAdapter(this);
        rvFeed.setAdapter(adapter);
        presenter.getFeed(currentSource);
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnTryAgain.setVisibility(View.GONE);
                tvFailedToLoad.setVisibility(View.GONE);
                pbLoading.setVisibility(View.VISIBLE);
                presenter.getFeed(currentSource);
            }
        });
        ItemClickSupport.addTo(rvFeed).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(NewsFeedActivity.this, NewsViewActivity.class);
                intent.putExtra(IConstants.ARTICLE_KEY, adapter.getItem(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void updateList(List<Article> list) {
        pbLoading.setVisibility(View.GONE);
        rvFeed.setVisibility(View.VISIBLE);
        adapter.updateList(list);
    }

    @Override
    public void errorInLoadingFeed() {
        rvFeed.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        btnTryAgain.setVisibility(View.VISIBLE);
        tvFailedToLoad.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        ItemClickSupport.removeFrom(rvFeed);
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String[] sources = getResources().getStringArray(R.array.sources);
        for (String s :
                sources) {
            s = s.replace("-", " ");
            menu.add(s);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        currentSource = item.getTitle().toString().replace(" ", "-");
        rvFeed.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        presenter.getFeed(currentSource);
        return super.onOptionsItemSelected(item);
    }
}

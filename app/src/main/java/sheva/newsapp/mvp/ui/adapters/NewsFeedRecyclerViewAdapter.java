package sheva.newsapp.mvp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheva.newsapp.R;
import sheva.newsapp.mvp.model.entities.Article;

/**
 * Created by Никита on 13.11.2017.
 */

public class NewsFeedRecyclerViewAdapter extends RecyclerView.Adapter<NewsFeedRecyclerViewAdapter.NewsViewHolder> {
    private List<Article> list;
    private LayoutInflater inflater;

    public NewsFeedRecyclerViewAdapter(Context context) {
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(inflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Article article = list.get(position);
        holder.sdvNewsPic.setImageURI(article.getUrlToImage());
        holder.tvNewsText.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Article> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public Article getItem(int position) {
        return list.get(position);
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdvItemNewsPic)
        SimpleDraweeView sdvNewsPic;
        @BindView(R.id.tvItemNewsText)
        TextView tvNewsText;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

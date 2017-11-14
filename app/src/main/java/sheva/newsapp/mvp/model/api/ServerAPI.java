package sheva.newsapp.mvp.model.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import sheva.newsapp.mvp.model.entities.NewsUpperEntity;

/**
 * Created by Никита on 13.11.2017.
 */

public interface ServerAPI {

    @GET("articles")
    Observable<NewsUpperEntity> getArticles(@Query("source") String source,
                                            @Query("sortBy") String sortBy,
                                            @Query("apiKey") String apiKey);
}

package es.faouzi.ua.procweb.data.web;

import es.faouzi.ua.procweb.data.model.Rss;
import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
public interface RssFeedService {
    /**
     * Provides RSS feed data.

     * @return RSS Feed
     */
    @GET("/rss/ityuvidau.xml")
    Flowable<Rss> getFeed();
}

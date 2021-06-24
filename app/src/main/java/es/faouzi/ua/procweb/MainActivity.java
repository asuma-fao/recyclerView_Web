package es.faouzi.ua.procweb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import es.faouzi.ua.procweb.adapter.VideoPlayerAdapter;
import es.faouzi.ua.procweb.adapter.VideoPlayerRecyclerView;
import es.faouzi.ua.procweb.data.model.Item;
import es.faouzi.ua.procweb.data.model.Rss;
import es.faouzi.ua.procweb.data.web.RssFeedService;
import es.faouzi.ua.procweb.util.LoadingDialog;
import es.faouzi.ua.procweb.util.RestAdapterUtil;
import es.faouzi.ua.procweb.util.VerticalSpacingItemDecorator;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    final String baseURL = "https://vertice.cpd.ua.es";
    private CompositeDisposable disposable = new CompositeDisposable();
    private VideoPlayerRecyclerView mRecyclerView;
    List<Item> mediaObjects = new ArrayList<Item>();
    VideoPlayerAdapter adapter;
   private  LoadingDialog loadingDialog ;
    private ViewGroup mEmptyView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        mRecyclerView = findViewById(R.id.recycler_view);
        loadingDialog =new LoadingDialog();

        // inciate recycler
        iniciarRecycler();


        RssFeedService client = RestAdapterUtil.createXmlAdapterFor(RssFeedService.class, baseURL);
        Flowable<Rss> rssObservable = client.getFeed();
        rssObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<Rss>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);

                        mRecyclerView.setVisibility(View.GONE);
                        mEmptyView.setVisibility(View.VISIBLE);
                        loadingDialog.show(getSupportFragmentManager(), "loading_dialog");

                    }

                    @Override
                    public void onNext(@NonNull Rss rss) {
                        Log.d(TAG, "onNext: " + rss.channel.toString());
                        mediaObjects = rss.channel.getItemList();
                        Log.d(TAG, "onNext: size "+mediaObjects.size());
                        Log.d(TAG, "onNext: size "+mediaObjects.toString());
                        ListIterator<Item> iter = rss.channel.getItemList().listIterator();
                        updateData(mediaObjects);
                        while ( iter.hasNext()) {
                            Log.d(TAG, "onNext: autor:   "+iter.next().authoritem);
                           // Log.d(TAG, "onNext: guid:    " + iter.next().guid);
                            Log.d(TAG, "onNext: title :    " + iter.next().title);
                            Log.d(TAG, "onNext: imagen :    " + iter.next().image);
                            Log.d(TAG, "onNext: encloser :    " + iter.next().enclosureUrl);


                           // mediaObjects.add(iter.next());

                        }

                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: "+mediaObjects.size());
                        mRecyclerView .setVisibility(View.VISIBLE);
                        mEmptyView.setVisibility(View.GONE);
                        updateData(mediaObjects);
                        adapter.setData(mediaObjects);
                        loadingDialog.dismiss();

                    }


                });


    }

    private void updateData(List<Item> mediaObjects) {
        adapter.notifyDataSetChanged();
        Toast.makeText(this, " se ha añadido", Toast.LENGTH_SHORT).show();
        mRecyclerView.Actualizarlist(mediaObjects);
    }


    private void iniciarRecycler() {


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);




        mEmptyView = findViewById(R.id.view_empty);

        mRecyclerView.setItemsObject(mediaObjects);
        Log.d(TAG, "iniciarRecycler: "+mediaObjects.size());
        adapter = new VideoPlayerAdapter(mediaObjects, initGlide());
        mRecyclerView.setAdapter(adapter);
    }


    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();

    }



}
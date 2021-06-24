package es.faouzi.ua.procweb.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import es.faouzi.ua.procweb.R;
import es.faouzi.ua.procweb.data.model.Item;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
public class VideoPlayerAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "VideoPlayerAdapter";

    private List<Item> itemsObjects;
    private RequestManager requestManager;

    public VideoPlayerAdapter( List<Item> itemsObjects, RequestManager requestManager) {
        this.itemsObjects = itemsObjects;
        this.requestManager = requestManager;
    }
    public void updateModels(List<Item> newModels) {
        itemsObjects.clear();
       // itemsObjects.adAll(newModels);
       // notifyDataSetChaged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_video_item, parent, false);
        return new VideoPlayerViewHolder(view)  ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VideoPlayerViewHolder)holder).onBind(itemsObjects.get(position), requestManager);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+itemsObjects.size());
        return itemsObjects.size();
    }
    public void setData(List<Item> data) {
        itemsObjects = data;
        notifyDataSetChanged();
    }
    protected void onDataChanged() {}

}

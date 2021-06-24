package es.faouzi.ua.procweb.adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import es.faouzi.ua.procweb.R;
import es.faouzi.ua.procweb.data.model.Item;

/**
 * Created by Faouzi Asmaa on 01/05/2021
 */
public class VideoPlayerViewHolder extends RecyclerView.ViewHolder{

    FrameLayout media_container;
    TextView title,subtitle;
    ImageView thumbnail, volumeControl,imageViewlogo;
    ProgressBar progressBar;
    View parent;
    RequestManager requestManager;

    public VideoPlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        media_container = itemView.findViewById(R.id.media_container);
        thumbnail = itemView.findViewById(R.id.thumbnail_logo);
        title = itemView.findViewById(R.id.title_logo);
        subtitle = itemView.findViewById(R.id.tvsubtitlelogo);
        progressBar = itemView.findViewById(R.id.progressBar);
        volumeControl = itemView.findViewById(R.id.volume_control);
        imageViewlogo= itemView.findViewById(R.id.imageViewlogo);

    }

    public void onBind(Item ItemObject, RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);
        title.setText(ItemObject.getTitle());
        subtitle.setText(ItemObject.authoritem);

        this.requestManager
                .load(ItemObject.image)
                .error(R.drawable.boardwalk)
                .into(thumbnail);
    }

}

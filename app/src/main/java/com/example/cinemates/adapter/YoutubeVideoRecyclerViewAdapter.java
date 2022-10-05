package com.example.cinemates.adapter;

import static com.example.cinemates.util.ConstantsKt.YOUTUBE_COM_WATCH_V;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemates.databinding.ListItemYtVideoBinding;
<<<<<<< Updated upstream
import com.example.cinemates.model.Video;
import com.example.cinemates.util.Constants;
=======
import com.example.cinemates.model.data.Video;
>>>>>>> Stashed changes
import com.example.cinemates.util.RecyclerViewEmptySupport;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

<<<<<<< Updated upstream
import java.util.ArrayList;
=======
import java.util.List;
>>>>>>> Stashed changes

/**
 * @author Antonio Di Nuzzo
 * Created 29/05/2022 at 07:59
 */
public class YoutubeVideoRecyclerViewAdapter extends RecyclerViewEmptySupport.Adapter<YoutubeVideoRecyclerViewAdapter.YoutubeViewHolder> {

    private static final String TAG = YoutubeVideoRecyclerViewAdapter.class.getSimpleName();
    private ArrayList<Video> dataList;

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemYtVideoBinding mBinding = ListItemYtVideoBinding.inflate(layoutInflater, parent, false);
        return new YoutubeViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, final int position) {

        Video video = dataList.get(position);
        holder.mBinding.setVideo(video);
        holder.mBinding.executePendingBindings();
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(YOUTUBE_COM_WATCH_V+video.getKey()));
                try {
                    view.getContext().startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    view.getContext().startActivity(webIntent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public void setDataList(ArrayList<Video> dataList) {
        this.dataList = dataList;
    }

    static class YoutubeViewHolder extends RecyclerView.ViewHolder {
        private final ListItemYtVideoBinding mBinding;

        public YoutubeViewHolder(ListItemYtVideoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;

        }
    }
}

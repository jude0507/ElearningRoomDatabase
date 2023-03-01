package com.example.elearningapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elearningapplication.Model.VideoModel;
import com.example.elearningapplication.R;
import com.example.elearningapplication.RecyclerViewInterface;

import java.util.ArrayList;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<VideoModel> modelArrayList;
    private RecyclerViewInterface recyclerViewInterface;


    public VideoAdapter(Context context, ArrayList<VideoModel> modelArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public VideoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_name_list, parent, false);
        return new VideoAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.MyViewHolder holder, int position) {

        VideoModel videoModel = modelArrayList.get(position);
        String name = videoModel.getVideoName();
        holder.nameOfVideo.setText(name);


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameOfVideo;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            nameOfVideo = itemView.findViewById(R.id.video_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}

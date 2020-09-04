package com.example.android.mediaplayersample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Movie> moviesList;
    private boolean mUserIsSeeking = false;
    private View itemView;
    private boolean isPlaying = false;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        private SeekBar seekbar_audio;
        private Button button_play;
        private Button button_reset;
        private Button button_pause;

        public MyViewHolder(View view) {
            super(view);
            seekbar_audio = (SeekBar) view.findViewById(R.id.seekbar_audio);
            button_play = (Button) view.findViewById(R.id.button_play);
            button_pause = (Button) view.findViewById(R.id.button_pause);
            button_reset = (Button) view.findViewById(R.id.button_reset);
        }
    }

    /*
    public void addAll(List<File> listPages) {
        for (File result : listPages) {
            add(result);
            //notifyDataSetChanged();
        }
    }

    public void add(File file) {
        images.add(file);
        notifyDataSetChanged();
    }
     */


    public Adapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializePlaybackController(holder, holder.seekbar_audio, position, isPlaying);
            }
        });
    }

    private void initializeSeekbar(SeekBar mSeekbarAudio, final PlayerAdapter mPlayerAdapter) {
        mSeekbarAudio.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int userSelectedPosition = 0;

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = true;
                    }

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            userSelectedPosition = progress;
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mUserIsSeeking = false;
                        mPlayerAdapter.seekTo(userSelectedPosition);
                    }
                });
    }

    private void initializePlaybackController(MyViewHolder holder, SeekBar mSeekbarAudio, int position, boolean isPlaying) {
        MediaPlayerHolder mMediaPlayerHolder = new MediaPlayerHolder(holder.itemView.getContext());
        mMediaPlayerHolder.setPlaybackInfoListener(new PlaybackListener(mSeekbarAudio));
        final PlayerAdapter mPlayerAdapter = mMediaPlayerHolder;

        holder.button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayerAdapter.isPlaying()){
                    mPlayerAdapter.pause();
                    mPlayerAdapter.play();
                } else {
                    mPlayerAdapter.play();
                }
            }
        });

        holder.button_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerAdapter.pause();
            }
        });

        holder.button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerAdapter.reset();
            }
        });

        initializeSeekbar(holder.seekbar_audio, mPlayerAdapter);


        mPlayerAdapter.loadMedia(moviesList.get(position).getUrl());
    }

    public class PlaybackListener extends PlaybackInfoListener {
        private SeekBar mSeekbarAudio;
        public PlaybackListener(SeekBar seekBar) {
            mSeekbarAudio = seekBar;
        }

        @Override
        public void onDurationChanged(int duration) {
            mSeekbarAudio.setMax(duration);
        }

        @Override
        public void onPositionChanged(int position) {
            if (!mUserIsSeeking) {
                mSeekbarAudio.setProgress(position);

            }
        }

        @Override
        public void onStateChanged(@State int state) {
        }

        @Override
        public void onPlaybackCompleted() {
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

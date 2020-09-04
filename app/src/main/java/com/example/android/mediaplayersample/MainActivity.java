/*
 * Copyright 2017 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.mediaplayersample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows playback of a single MP3 file via the UI. It contains a {@link MediaPlayerHolder}
 * which implements the {@link PlayerAdapter} interface that the activity uses to control
 * audio playback.
 */
public final class MainActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter mAdapter;

    public static final String TAG = "MainActivity";
    private SeekBar mSeekbarAudio;
    private ScrollView mScrollContainer;
//    private PlayerAdapter mPlayerAdapter;
    private boolean mUserIsSeeking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
//        initializeSeekbar();
//        initializePlaybackController();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new Adapter(movieList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (isChangingConfigurations() && mPlayerAdapter.isPlaying()) {
//            Log.d(TAG, "onStop: don't release MediaPlayer as screen is rotating & playing");
//        } else {
//            mPlayerAdapter.release();
//        }
    }

    private void initializeUI() {
        Button mPlayButton = (Button) findViewById(R.id.button_play);
        Button mPauseButton = (Button) findViewById(R.id.button_pause);
        Button mResetButton = (Button) findViewById(R.id.button_reset);
        mSeekbarAudio = (SeekBar) findViewById(R.id.seekbar_audio);
        mScrollContainer = (ScrollView) findViewById(R.id.scroll_container);
//
//        mPauseButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mPlayerAdapter.pause();
//                    }
//                });
//        mPlayButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mPlayerAdapter.play();
//                    }
//                });
//        mResetButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        mPlayerAdapter.reset();
//                    }
//                });
    }
//
//    private void initializePlaybackController() {
//        MediaPlayerHolder mMediaPlayerHolder = new MediaPlayerHolder(this);
//        mMediaPlayerHolder.setPlaybackInfoListener(new PlaybackListener());
//        mPlayerAdapter = mMediaPlayerHolder;
//    }

//    private void initializeSeekbar() {
//        mSeekbarAudio.setOnSeekBarChangeListener(
//                new SeekBar.OnSeekBarChangeListener() {
//                    int userSelectedPosition = 0;
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//                        mUserIsSeeking = true;
//                    }
//
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                        if (fromUser) {
//                            userSelectedPosition = progress;
//                        }
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//                        mUserIsSeeking = false;
//                        mPlayerAdapter.seekTo(userSelectedPosition);
//                    }
//                });
//    }

//    public class PlaybackListener extends PlaybackInfoListener {
//
//        @Override
//        public void onDurationChanged(int duration) {
//            mSeekbarAudio.setMax(duration);
//        }
//
//        @Override
//        public void onPositionChanged(int position) {
//            if (!mUserIsSeeking) {
//                mSeekbarAudio.setProgress(position);
//
//            }
//        }
//
//        @Override
//        public void onStateChanged(@State int state) {
//        }
//
//        @Override
//        public void onPlaybackCompleted() {
//        }
//    }

    private void prepareMovieData() {
        Movie movie = new Movie("http://34.66.8.61:8000/media/uploads/sounds/The_Christmas_Song_Sentimental.mp3");
        movieList.add(movie);

        movie = new Movie("http://34.66.8.61:8000/media/uploads/sounds/The_Christmas_Song_Sentimental.mp3");
        movieList.add(movie);

        movie = new Movie("http://34.66.8.61:8000/media/uploads/sounds/The_Christmas_Song_Sentimental.mp3");
        movieList.add(movie);

        movie = new Movie("http://34.66.8.61:8000/media/uploads/sounds/The_Christmas_Song_Sentimental.mp3");
        movieList.add(movie);

        movie = new Movie("http://34.66.8.61:8000/media/uploads/sounds/The_Christmas_Song_Sentimental.mp3");
        movieList.add(movie);
        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }
}
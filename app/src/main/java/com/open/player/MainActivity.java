package com.open.player;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.open.player.widget.media.AndroidMediaController;
import com.open.player.widget.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private IjkVideoView mVideoView;
    private final String mVideoPath = "http://ivi.bupt.edu.cn/hls/jstv.m3u8";
    private AndroidMediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        mVideoView = findViewById(R.id.video_view);
        mMediaController = new AndroidMediaController(this, false);
        mVideoView.setMediaController(mMediaController);
//        mVideoView.setHudView(mHudView);
        // prefer mVideoPath
        if (mVideoPath != null) {
            mVideoView.setVideoPath(mVideoPath);
        } else {
            Log.e(TAG, "Null Data Source\n");
            finish();
            return;
        }
        mVideoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
        mVideoView.release(true);
        IjkMediaPlayer.native_profileEnd();
    }
}
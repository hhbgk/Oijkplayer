package com.open.player;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.open.player.browser.BrowseFileDialog;
import com.open.player.widget.media.AndroidMediaController;
import com.open.player.widget.media.IjkVideoView;

import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private IjkVideoView mVideoView;
    private String mVideoPath;
    private static final String DEFAULT_PATH = "http://ivi.bupt.edu.cn/hls/jstv.m3u8";
    private AndroidMediaController mMediaController;
    private EditText etPath;
    private BrowseFileDialog browseFileDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        etPath = findViewById(R.id.et_path);
        etPath.setText(DEFAULT_PATH);
        mVideoView = findViewById(R.id.video_view);
        mMediaController = new AndroidMediaController(this, false);
        mVideoView.setMediaController(mMediaController);
//        mVideoView.setHudView(mHudView);

        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mVideoView.stopPlayback();
        mVideoView.release(true);
        IjkMediaPlayer.native_profileEnd();
    }

    private final PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    public void onClickBrowse(View view) {
        if (browseFileDialog != null && browseFileDialog.isAdded()) {
            Log.e(TAG, "dialog is added");
            return;
        }
        browseFileDialog = new BrowseFileDialog();
        browseFileDialog.setOnSelectedListener(new BrowseFileDialog.OnSelectedListener() {
            @Override
            public void onSelected(String path) {
                mVideoPath = path;
                etPath.setText(path);
            }
        });
        browseFileDialog.show(getSupportFragmentManager(), "browse_file_dialog");

    }

    public void onClickPlay(View view) {
        mVideoPath = etPath.getText().toString().trim();
        if (!TextUtils.isEmpty(mVideoPath)) {
            mVideoView.setVideoPath(mVideoPath);
        } else {
            Log.e(TAG, "Null Data Source\n");
            finish();
            return;
        }
        mVideoView.start();
    }
}
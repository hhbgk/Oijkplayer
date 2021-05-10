package com.open.player.browser;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.open.player.MainApplication;
import com.open.player.R;
import com.open.player.browser.bean.VideoFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BrowseFileDialog extends DialogFragment implements View.OnClickListener,
        AdapterView.OnItemClickListener {
    private final String tag = getClass().getSimpleName();
    private TextView currentPathTv;
    private Button btnConfirm;
    private Button cancelBtn;
    private ListView mListView;
    private VideoFileAdapter mAdapter;

    private List<VideoFile> fileInfoList;
    private String currentPath;
    private static String ROOT_PATH = null;
    private OnSelectedListener onSelectedListener;

    public interface OnSelectedListener{
        void onSelected(String path);
    }

    public void setOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Light_NoTitleBar);
        setCancelable(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_browse_file, container, false);
        currentPathTv = view.findViewById(R.id.dialog_file_path);
        mListView = view.findViewById(R.id.dialog_file_list);
        cancelBtn = view.findViewById(R.id.dialog_file_cancel_btn);
        btnConfirm = view.findViewById(R.id.dialog_file_confirm_btn);

        mListView.setOnItemClickListener(this);
        cancelBtn.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainApplication mApplication = MainApplication.getApplication();
        if (getActivity() == null || getActivity().getWindow() == null || getDialog() == null
                || getDialog().getWindow() == null)
            return;
        final WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(params);

        if (fileInfoList == null) {
            fileInfoList = new ArrayList<>();
        }
        currentPath = ROOT_PATH = getRootPath(mApplication);
        Log.i(tag, "currentPath=" + currentPath);
        if (mAdapter == null) {
            mAdapter = new VideoFileAdapter(fileInfoList);
        }
        mListView.setAdapter(mAdapter);

        viewFiles(currentPath);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        onKeyBack();
                    }
                    return false;
                }
            });
        }
    }

    private String getRootPath(Context context) {
        String path = Environment.getExternalStorageDirectory().getPath();
        if (Build.VERSION.SDK_INT >= 29) {
            if (context != null && context.getExternalFilesDir(null) != null) {
                path = context.getExternalFilesDir(null).getPath();
            }
        }
        return path;
    }

    private static class FileComparator implements Comparator<VideoFile> {
        public int compare(VideoFile file1, VideoFile file2) {
            // 文件夹排在前面
            if (file1.isDirectory() && !file2.isDirectory()) {
                return -1000;
            } else if (!file1.isDirectory() && file2.isDirectory()) {
                return 1000;
            }
            // 相同类型按名称排序
            return file1.getName().compareTo(file2.getName());
        }
    }

    private ArrayList<VideoFile> getFiles(String path) {
        File f = new File(path);
        File[] files = f.listFiles();
        if (files == null) {
            Log.e(tag, "No files");
            return null;
        }

        ArrayList<VideoFile> fileList = new ArrayList<>();
        // 获取文件列表
        for (File file : files) {
            if (file.isHidden()) continue;
            VideoFile fileInfo = new VideoFile();
            fileInfo.setName(file.getName());
            fileInfo.setDirectory(file.isDirectory());
            fileInfo.setPath(file.getPath());
            fileList.add(fileInfo);
        }

        // 排序
        Collections.sort(fileList, new FileComparator());
        return fileList;
    }

    /**
     * 获取该目录下所有文件
     **/
    private void viewFiles(String filePath) {
        ArrayList<VideoFile> tmp = getFiles(filePath);
        if (tmp != null) {
            // 清空数据
            fileInfoList.clear();
            fileInfoList.addAll(tmp);
            tmp.clear();

            // 设置当前目录
            currentPath = filePath;
            currentPathTv.setText(filePath);

            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (getActivity() == null || getActivity().getWindow() == null) return;
        if (v == cancelBtn) {
            dismiss();
        } else if (v == btnConfirm) {

        }
    }

    private void onKeyBack() {
        if (ROOT_PATH.equals(currentPath)) {
            // showToastShort(R.string.current_path_is_root);
            dismiss();
        } else {
            int index = currentPath.lastIndexOf("/");
            if (index != -1) {
                currentPath = currentPath.substring(0, index);
                viewFiles(currentPath);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mAdapter != null && position < mAdapter.getCount()) {
            VideoFile fileInfo = (VideoFile) mAdapter.getItem(position);
            if (fileInfo != null) {
                currentPath = fileInfo.getPath();
                if (!fileInfo.isDirectory()) {
                    if (onSelectedListener != null) onSelectedListener.onSelected(fileInfo.getPath());
                    dismiss();
                } else {
                    viewFiles(currentPath);
                }
            }
        }
    }
}

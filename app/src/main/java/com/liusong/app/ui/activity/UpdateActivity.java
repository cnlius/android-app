package com.liusong.app.ui.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.liusong.app.R;
import com.liusong.app.databinding.ActivityUpdateBinding;
import com.liusong.library.updateapp.UpdateManager;
import com.liusong.library.updateapp.UpdateUtils;
import com.liusong.library.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * apk dowloadManager下载更新
 * Created by liu song on 2017/5/10.
 */

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityUpdateBinding mBinding;
    private static final String apkurl = "https://raw.githubusercontent.com/cnlius/resource/master/apk/collections.apk";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_update);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_simple_update:
                simpleUpdate();
                break;
            case R.id.btn_progressbar_update:
                simpleUpdate();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                long downId = sp.getLong(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
                updateViews(mBinding.pbUpdate,downId);
//                mBinding.pbUpdate.setProgress(15);
                break;
        }
    }

    /**
     * 一般的下载更新方法
     */
    private void simpleUpdate() {
        if(UpdateUtils.checkUpdateByVersionName("1.0.1","2.1.1")<0){
            ToastUtils.showToast(this,"开始下载！");
            UpdateManager.getInstance(this).downloadApk(apkurl);
        }else{
            ToastUtils.showToast(this,"已是最新，无需下载！");
        }
    }

    /**
     * 进度条更新
     * @param downlaodId
     */
    private void updateViews(final ProgressBar progressBar,final long downlaodId) {
        final Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                DownloadManager downloadManager=(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Query query = new DownloadManager.Query().setFilterById(downlaodId);
                Cursor cursor = downloadManager.query(query);
                cursor.moveToFirst();
                int curProgress = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                int totalProgress = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                cursor.close();
                final int progress = (curProgress * 100 /totalProgress);
                if (progress == 100) {
                    myTimer.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                }
            }
        }, 0, 1);

    }
}

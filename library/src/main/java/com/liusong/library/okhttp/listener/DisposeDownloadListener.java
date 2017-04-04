package com.liusong.library.okhttp.listener;

/**
 * @author liusong
 * @function 监听下载进度
 */
public interface DisposeDownloadListener extends DisposeDataListener {
	public void onProgress(int progrss);
}

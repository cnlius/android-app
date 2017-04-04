package com.liusong.library.okhttp.listener;

/**
 * @author liusong
 * @function 回调时的预处理，处理json
 */
public class DisposeDataHandle {
	public DisposeDataListener mListener = null;
	public Class<?> mClass = null;
	public String mSource = null;

	/**
	 * 将服务器返回的json直接返回给应用层
	 *
	 * @param listener
	 */
	public DisposeDataHandle(DisposeDataListener listener) {
		this.mListener = listener;
	}

	/**
	 * 将服务器返回的json转换成指定的实体对象
	 * @param listener
	 * @param clazz
	 */
	public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
		this.mListener = listener;
		this.mClass = clazz;
	}

	public DisposeDataHandle(DisposeDataListener listener, String source) {
		this.mListener = listener;
		this.mSource = source;
	}
}
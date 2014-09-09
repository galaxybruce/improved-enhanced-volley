
package com.android.volley.toolbox;

import java.io.File;

import com.android.volley.CommonFailError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

/**
 * A request for download file request
 * 
 * @param <T>
 *            Response expected
 */
public abstract class DownloadFileRequest<T> extends Request<T> {

	private final Listener<T> mListener;
	
    /**
     * Default connection timeout for download requests
     */
    public static final int TIMEOUT_MS = 30000;

    public DownloadFileRequest(String url, Listener<T> listener, ErrorListener errorlistener) {

        super(Request.Method.GET, url, errorlistener, new DefaultRetryPolicy(TIMEOUT_MS, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mListener = listener;
        
    }

    @Override
    protected void deliverResponse(T response) {

        mListener.onResponse(this, response);
    }

    /**
	 * 创建本地保存文件
	 */
    protected File createFile(String url, long fileLength) throws CommonFailError
    {
//		File tempFile = null;
//		try
//		{
//			String filePath = buildFilePath(url);;
//			if (FileUtil.hasFreeSpace(filePath, fileLength))
//			{
//				tempFile = creatLocalFile(filePath);
//			}
//		}
//		catch (Exception e)
//		{
//			LogX.getInstance().e(TAG, "download file create file error...");
//		}
//		finally
//		{
//			if (tempFile == null)
//			{
//				throw new CommonFailError("sdcard has no enough space");
//			}
//		}
//
//		return tempFile;
    	
    	return null;
    }
    
    private File creatLocalFile(String path)
	{
		try
		{
			File file = new File(path);
			if (file.exists())
			{
				file.delete();
			}
			else if (!file.getParentFile().exists())
			{
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			return file;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
    
    protected abstract String buildFilePath(String url);
    
    /**
	 * 设置进度变化回调函数
	 * 
	 * @param getLength      下载大小
	 * @param totalLength    文件大小
	 */
    protected abstract void transferred(long getLength, long totalLength);
    
	/**
	 * 下载完成回调通知接口
	 */
	public abstract void successDownloadCallback(String url, String filePath);

	/**
	 * 下载失败回调通知接口
	 */
	public abstract void failDownloadCallback(String url, String filePath);
	
}

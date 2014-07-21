/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Modified by Vinay S Shenoy on 19/5/13
 */

package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.text.TextUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley.BasicUrlRewriter;


/******************************************************************
 * 文件名称	: SyncVolley.java
 * 作    者		: GalaxyBruce
 * 创建时间	: 2014年7月12日 下午10:52:22
 * 文件描述	: 构建一个没有线程池的请求
 * 版权声明	: Copyright (C)  江苏钱旺智能系统有限公司
 * 修改历史	: 2014年7月12日 1.00 初始版本
 ******************************************************************/
public class SyncVolley {

	private BasicNetwork mNetwork;
	
	public SyncVolley(Context context)
	{
		this(context, null);
	}
	
	public SyncVolley(Context context, String userAgent)
	{
		if(TextUtils.isEmpty(userAgent))
		{
			userAgent = "volley/0";
	        try {
	            String packageName = context.getPackageName();
	            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
	            userAgent = packageName + "/" + info.versionCode;
	        } catch (NameNotFoundException e) {
	        }
		}
		
		HttpStack stack = null;
        if (Build.VERSION.SDK_INT >= 9) {
            stack = new HurlStack(new BasicUrlRewriter(), userAgent);
        } else {
            stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent), new BasicUrlRewriter());
        }

        mNetwork = new BasicNetwork(stack);
	}
	
	public NetworkResponse performRequest(Request<?> request) throws VolleyError
	{
		return mNetwork.performRequest(request);
	}
	
}

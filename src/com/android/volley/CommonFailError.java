package com.android.volley;


/******************************************************************
 * 文件名称	: CommonFailError.java
 * 作    者		: GalaxyBruce
 * 创建时间	: 2014年8月6日 下午11:10:35
 * 文件描述	: 普通的网络请求错误  code != 200
 * 版权声明	: Copyright (C)  江苏钱旺智能系统有限公司
 * 修改历史	: 2014年8月6日 1.00 初始版本
 ******************************************************************/
@SuppressWarnings("serial")
public class CommonFailError extends VolleyError
{
	public int qbErrorCode;
	
	public CommonFailError(String exceptionMessage)
	{
		super(exceptionMessage);
	}
	
	public CommonFailError(String exceptionMessage, int qbErrorCode)
	{
		super(exceptionMessage);
		this.qbErrorCode = qbErrorCode;
		errorCode = ErrorCode.BAD_REQUEST_ERROR;
	}
}

package com.fute.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * JAVA操作SSL协议，通过Socket访问Https的程序代码例子。
 * 
 * @author Livee
 * 
 * 
 */
public class TestTemp {
	// 默认的HTTPS 端口
	static final int HTTPS_PORT = 443;

	public static void main(String argv[]) throws Exception {
		
	}
}

/**
 * 自定义的认证管理类。
 * 
 * @author Livee
 * 
 */
class J2TrustManager implements X509TrustManager {
	J2TrustManager() {
		// 这里可以进行证书的初始化操作
	}

	// 检查客户端的可信任状态
	public void checkClientTrusted(X509Certificate chain[], String authType)
			throws CertificateException {
		// System.out.println("检查客户端的可信任状态...");
	}

	// 检查服务器的可信任状态
	public void checkServerTrusted(X509Certificate chain[], String authType)
			throws CertificateException {
		// System.out.println("检查服务器的可信任状态");
	}

	// 返回接受的发行商数组
	public X509Certificate[] getAcceptedIssuers() {
		// System.out.println("获取接受的发行商数组...");
		return null;
	}
}
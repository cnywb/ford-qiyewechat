package com.fute.common.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;

import net.spy.memcached.MemcachedClient;

public class MemcacheUtil {
	private static MemcacheUtil m = new MemcacheUtil();
	private static MemcachedClient mc = null;
	private static MemcachedClient vc = null;
	public static int time = 0;

	public static MemcacheUtil getMInstance() {
		if (m == null) {
			m = new MemcacheUtil();
		}
		return m;
	}

	private static MemcachedClient getMC() {
		try {
			if (mc == null || Thread.interrupted()) {
				mc = new MemcachedClient(new InetSocketAddress("114.80.158.17",
						21210));// 114.80.215.22 21210 "10.15.107.52", 11211
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mc;
	}
	public static void main(String [] args){
		Object o = null;
		String key="mo.tvindex.html.tv.1.v1_00"+".20130828";
		mc=null;
		mc = getMC();
		o = mc.get(key);
		System.out.println(key);
		System.out.println(o);
	}
	private static MemcachedClient getVisit() {
		try {
			if (vc == null) {

				vc = new MemcachedClient(new InetSocketAddress("114.80.136.12",
						21210));// 114.80.215.22 21210 "10.15.107.52", 11211
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vc;
	}

	public static Object get(String key) {
		vc = getVisit();
		Object o = null;
		try {

			o = vc.get(key);
		} catch (Exception e) {
			o = null;
			e.printStackTrace();
		}

		return o;
	}

	public Object getValue(String key) {
		mc = getMC();
		Object o = null;
		try {

			o = mc.get(key);
		} catch (Exception e) {
			o = null;
			e.printStackTrace();
		}

		return o;
	}
}

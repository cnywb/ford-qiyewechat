package com.fute.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

 
/**
 * Spring环境工具类
 * 
 * @author xufeng
 */
public class FactoryUtil implements ApplicationContextAware {
	private static Logger LOG = LoggerFactory.getLogger(FactoryUtil.class);
	private static ApplicationContext _factory = null;

	/**
	 * 初始化Spring环境
	 * 
	 * @param config
	 */
	public static void init(String[] locations) {
		if (_factory == null) {
			synchronized (FactoryUtil.class) {
				if (_factory == null) {
					_factory = new FileSystemXmlApplicationContext(locations);
				}
			}
		}
	}

	/**
	 * 取得Bean对象
	 * 
	 * @param id
	 *            Bean对象ID
	 * @param ct
	 *            Bean类型
	 * @return Bean对象
	 */
	public static <T> T getBean(String id, Class<T> ct) {
		T t = null;
		if (id != null && id.length() > 0) {
			if (_factory.containsBean(id)) {
				try {
					Object bean = _factory.getBean(id);
					if (ct.isInstance(bean)) {
						t = (T) bean;
					}
				} catch (Exception e) {
					if (LOG.isDebugEnabled()) {
						LOG.debug("ERROR IN getBean:", e);
					}
				}
			}
		}
		return t;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		_factory = applicationContext;
	}
}

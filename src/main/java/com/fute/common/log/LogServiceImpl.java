package com.fute.common.log;

import com.fute.manage.dao.TSTLogMapper;
import com.fute.manage.pojo.TSTLog;

public class LogServiceImpl implements LogService {
	
	private TSTLogMapper logMapper;
	
	public void setLogMapper(TSTLogMapper logMapper) {
		this.logMapper = logMapper;
	}

	@Override
	public void log(Integer operatorId, String operatorTpye, String content,String ip) {
		try {
			TSTLog log = new TSTLog();
			log.setOperator(operatorId);
			log.setOperatorType(operatorTpye);
			log.setContent(content);
			log.setIp(ip);
			logMapper.saveLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}

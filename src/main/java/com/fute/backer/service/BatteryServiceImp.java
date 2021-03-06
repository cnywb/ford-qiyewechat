package com.fute.backer.service;

import com.fute.backer.dao.BatteryMapper;
import com.fute.reserve.util.Constants;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatteryServiceImp implements BatteryService {
	private BatteryMapper batteryMapper;
	
	private static final Logger LOGGER = Logger.getLogger(BatteryServiceImp.class);

	public BatteryMapper getBatteryMapper() {
		return batteryMapper;
	}

	public void setBatteryMapper(BatteryMapper batteryMapper) {
		this.batteryMapper = batteryMapper;
	}

	// 会员设置
	@Override
	public List<Map<String, Object>> findMemberList(Map<String, Object> pvjMap) {

		List<Map<String, Object>> listMap = this.batteryMapper
				.getMemberList(pvjMap);
		return listMap;
	}

	public int findMemberListNum(Map<String, Object> pvjMap) {
		int num = this.batteryMapper.getMemberListNum(pvjMap);
		return num;
	}

	@Override
	public void deleteMember(Map<String, Object> pvjMap) {
		batteryMapper.deleteMember(pvjMap);

	}

	@Override
	public Map<String, Object> findMember(Map<String, Object> pvMap) {
		Map<String, Object> map = this.batteryMapper.getMember(pvMap);
		return map;
	}

	@Override
	public void updateMember(Map<String, Object> pvMap) {
		batteryMapper.updateMember(pvMap);
	}

	@Override
	public void insertMember(Map<String, Object> pvMap) {
		Map<String, Object> map = this.batteryMapper.getMemberByOpenid(pvMap);
		if (null == map) {
			pvMap.put("ifattention", "1");
			this.batteryMapper.insertMember(pvMap);
		} else {
			this.batteryMapper.attentionMember(pvMap);
		}
		//粉丝开始关注国泰基金，或重新开始关注国泰基金
		//将粉丝来源更新为空
		this.batteryMapper.resetMemberSource(pvMap);
	}

	@Override
	public void updateNickName(Map<String, Object> pvMap) {
		this.batteryMapper.updateNickName(pvMap);
	}

	@Override
	public List<Map<String, Object>> getMemberOpenList() {
		return this.batteryMapper.getMemberOpenList();
	}

	@Override
	public void isValidCode(Map<String, Object> pvMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancalMember(Map<String, Object> pvMap) {
		pvMap.put("canceltime", new Date());
		this.batteryMapper.cancalMember(pvMap);
	}

	public List<Map<String, Object>> getMlBannerList() {
		return this.batteryMapper.getMlBannerList();
	}

	public void updateMlBanner(Map<String, Object> mlMap) {
		boolean insertFlag = false;
		if (null != mlMap) {
			if (!mlMap.containsKey("id") || null == mlMap.get("id")
					|| "".equals(mlMap.get("id").toString())) {
				this.batteryMapper.insertMlBanner(mlMap);
				insertFlag = true;
			}
		}
		if (!insertFlag) {
			this.batteryMapper.updateMlBanner(mlMap);
		}
	}

	@Override
	public Map<String, Object> getMlBanner() {
		// TODO Auto-generated method stub
		return this.batteryMapper.getMlBanner();
	}

	public Integer getMemberNum() {
		return this.batteryMapper.getMemberNum();
	}

	public Integer getBattHistoryNum() {
		return this.batteryMapper.getBattHistoryNum();
	}

	public List<Map<String, Object>> getBattHistoryList(
			Map<String, Object> condition) {
		return this.batteryMapper.getBattHistoryList(condition);
	}

	@Override
	public void insertBattHistory(Map<String, Object> pvMap) {
		this.batteryMapper.insertBattHistory(pvMap);
	}

	@Override
	public Integer getBattHistoryListNum(Map<String, Object> paramMap) {
		int num = this.batteryMapper.getBattHistoryListNum(paramMap);
		return num;
	}

	@Override
	public Map<String, Object> getShenye(Map<String, Object> pvMap) {
		return this.batteryMapper.getShenye(pvMap);
	}

	@Override
	public List<Map<String, Object>> getShenyeList(Map<String, Object> pvMap) {
		return this.batteryMapper.getShenyeList(pvMap);
	}

	@Override
	public int getShenyeListNum(Map<String, Object> pvMap) {
		return this.batteryMapper.getShenyeListNum(pvMap);
	}

	@Override
	public void insertShenye(Map<String, Object> pvMap) {
		this.batteryMapper.insertShenye(pvMap);
	}

	@Override
	public void updateShenye(Map<String, Object> pvMap) {
		this.batteryMapper.updateShenye(pvMap);
	}

	@Override
	public Map<String, Object> getDaning(Map<String, Object> pvMap) {
		return this.batteryMapper.getDaning(pvMap);
	}

	@Override
	public List<Map<String, Object>> getDaningList(Map<String, Object> pvMap) {
		return this.batteryMapper.getDaningList(pvMap);
	}

	@Override
	public int getDaningListNum(Map<String, Object> pvMap) {
		return this.batteryMapper.getDaningListNum(pvMap);
	}

	@Override
	public void insertDaning(Map<String, Object> pvMap) {
		this.batteryMapper.insertDaning(pvMap);
	}

	@Override
	public int getDaningNumByDay(Map<String, Object> pvMap) {
		List<Map<String, Object>> listmap = this.batteryMapper
				.getDaningNumByDay(pvMap);
		return null != listmap ? listmap.size() : 0;
	}

	@Override
	public boolean getDaningSwtich(Map<String, Object> pvMap) {
		pvMap.put("actype", "daning");
		int pcount = this.batteryMapper.getACSwitch(pvMap);
		return 0 != pcount ? true : false;
	}

	@Override
	public Map<String, Object> getCD(Map<String, Object> pvMap) {
		return this.batteryMapper.getCD(pvMap);
	}

	@Override
	public List<Map<String, Object>> getCDList(Map<String, Object> pvMap) {
		return this.batteryMapper.getCDList(pvMap);
	}

	@Override
	public int getCDListNum(Map<String, Object> pvMap) {
		return this.batteryMapper.getCDListNum(pvMap);
	}

	@Override
	public void insertCD(Map<String, Object> pvMap) {
		this.batteryMapper.insertCD(pvMap);
	}

	@Override
	public int getCDNumByDay(Map<String, Object> pvMap) {
		List<Map<String, Object>> listmap = this.batteryMapper
				.getCDNumByDay(pvMap);
		return null != listmap ? listmap.size() : 0;
	}

	@Override
	public boolean getCDSwtich(Map<String, Object> pvMap) {
		pvMap.put("actype", "cd");
		int pcount = this.batteryMapper.getACSwitch(pvMap);
		return 0 != pcount ? true : false;
	}

	@Override
	public void deleteTextKey(Map<String, Object> data) {
		this.batteryMapper.deleteTextKey(data);
	}

	@Override
	public List<Map<String, Object>> getTextKeyList(Map<String, Object> data) {
		return this.batteryMapper.getTextKeyList(data);
	}
	
	@Override
	public List<Map<String, Object>> getWeiXinKeyList(Map<String, Object> data) {
		return this.batteryMapper.getWeiXinKeyList(data);
	}

	@Override
	public void insertTextKey(Map<String, Object> data) {
		this.batteryMapper.insertTextKey(data);
	}

	@Override
	public void updateTextKey(Map<String, Object> data) {
		this.batteryMapper.updateTextKey(data);
	}

	@Override
	public int getTextKeyListNum(Map<String, Object> data) {
		return this.batteryMapper.getTextKeyListNum(data);
	}

	@Override
	public List<Map<String, Object>> getMsgtypeList(String requestType) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("type", requestType);
		return this.batteryMapper.getMsgtypeList(data);
	}

	@Override
	public List<Map<String, Object>> getCityListByProvince(
			Map<String, Object> data) {
		return this.batteryMapper.getCityListByProvince(data);
	}

	@Override
	public List<Map<String, Object>> getProvinceList() {
		return this.batteryMapper.getProvinceList();
	}

	@Override
	public void deleteHelloMessage(Map<String, Object> data) {
		this.batteryMapper.deleteHelloMessage(data);
	}

	@Override
	public void deleteMessage(Map<String, Object> data) {
		this.batteryMapper.deleteMessage(data);
	}

	@Override
	public Map<String, Object> getHelloMessage(Map<String, Object> data) {
		return this.batteryMapper.getHelloMessage(data);
	}

	@Override
	public List<Map<String, Object>> getMessageList(Map<String, Object> data) {
		return this.batteryMapper.getMessageList(data);
	}

	@Override
	public Integer getMessageListNum(Map<String, Object> data) {
		return this.batteryMapper.getMessageListNum(data);
	}

	@Override
	public void insertHelloMessage(Map<String, Object> data) {
		this.batteryMapper.insertHelloMessage(data);
	}

	@Override
	public void insertMessage(Map<String, Object> data) {
		this.batteryMapper.insertMessage(data);
	}

	@Override
	public void updateHelloMessage(Map<String, Object> data) {
		this.batteryMapper.updateHelloMessage(data);
	}

	@Override
	public void updateMessage(Map<String, Object> data) {
		this.batteryMapper.updateMessage(data);
	}

	@Override
	public List<Map<String, Object>> getInteractionList(Map<String, Object> data) {
		return this.batteryMapper.getInteractionList(data);
	}

	@Override
	public Integer getInteractionListNum(Map<String, Object> data) {
		return this.batteryMapper.getInteractionListNum(data);
	}

	@Override
	public void updateMemberSource(String openid,String targetsource) {
		//粉丝数据来源
		//关注时，将粉丝数据来源重置为空
		//关注后，用户充电扫描或积分扫描时，更改粉丝的数据来源
		//充电扫描的时间早于积分扫描，则粉丝的数据来源为充电
		//积分扫描的时间早于充电扫描，则粉丝的数据来源为积分
		Map<String, Object> pvMap = new HashMap<String, Object>();
		pvMap.put("openid", openid);
		Map<String, Object> map = this.batteryMapper.getMemberByOpenid(pvMap);
		if(null!=map && !map.isEmpty() && map.containsKey("source")){
			String tempsource = MapUtils.getString(map, "souce");
			if(null==tempsource || "".equals(tempsource)){
				//用户刚关注，还没有数据来源
				pvMap.put("gaintime",MapUtils.getString(map, "gaintime")); 
				//充电时间
				Map<String, Object> t1 = this.batteryMapper.checkSourceBattHistory(pvMap);
				//积分时间
				Map<String, Object> t2 = this.batteryMapper.checkSourceInteGain(pvMap);
				String source = "" ;
				boolean flag1 = null!=t1 && !t1.isEmpty() ;
				boolean flag2 = null!=t2 && !t2.isEmpty();
				LOGGER.info("flag1="+flag1 + "\tflag2="+flag2);
				if(flag1 && flag2){
					String gaintime1 = MapUtils.getString(t1, "gaintime");
					String gaintime2 = MapUtils.getString(t2, "gaintime");
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						LOGGER.info("gaintime1="+gaintime1+"\tgaintime2="+gaintime2) ;
						if(format.parse(gaintime1).before(format.parse(gaintime2))){
							//gaintime1 早于 gaintime2
							source = Constants.MEMBER_SOURCE_BATT ;
						}else if(format.parse(gaintime1).after(format.parse(gaintime2))){
							source = Constants.MEMBER_SOURCE_INTE ;
						}
					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.info("updateMemberSource");
					}
				}else if(flag1){
					source = Constants.MEMBER_SOURCE_BATT ;
				}else if (flag2) {
					source = Constants.MEMBER_SOURCE_INTE ;
				}else {
					source = targetsource ;
					LOGGER.info("targetsource="+targetsource);
					LOGGER.info("积分扫描的其他可能");
				}
				pvMap.put("source", source);
				LOGGER.info("updateMemberSource..........source="+source);
				this.batteryMapper.updateMemberSource(pvMap);
				
			}
		}
		//=============
	}

	@Override
	public void deleteMark(Map<String, Object> data) {
		this.batteryMapper.deleteMark(data);
	}

	@Override
	public Map<String, Object> getMark(Map<String, Object> data) {
		return this.batteryMapper.getMark(data);
	}

	@Override
	public List<Map<String, Object>> getMarkList(Map<String, Object> data) {
		return this.batteryMapper.getMarkList(data);
	}

	@Override
	public Integer getMarkListNum(Map<String, Object> data) {
		return this.batteryMapper.getMarkListNum(data);
	}

	@Override
	public void insertMark(Map<String, Object> data) {
		this.batteryMapper.insertMark(data);
	}

	@Override
	public void updateMark(Map<String, Object> data) {
		this.batteryMapper.updateMark(data);
	}

	@Override
	public void updateMemberMark(String memberid,
			List<Map<String, Object>> markList) {
//		String mark = "" ;
//		if(null!=markList && markList.size()>0){
//			for (Map<String, Object> map : markList) {
//				if(null!=map && !map.isEmpty()){
//					mark+=(MapUtils.getString(map, "markid")+",");
//				}
//			}
//		}
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("memberid", memberid);
//		data.put("mark", null!=mark && mark.length()>0 ? mark.substring(0,mark.length()-1):mark);
		data.put("markList", markList);
		this.batteryMapper.deleteMemberMark(data);
		this.batteryMapper.insertMemberMark(data);
	}

	@Override
	public boolean updateMemberFirstInteChange(String openid) {
		if(null!=openid && !"".equals(openid)){
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("openid", openid);
			Map<String, Object> data = this.batteryMapper.getMember(dataMap);
			if(null!=data && !data.isEmpty()){
				String firstInteChange = MapUtils.getString(data, "firstInteChange");
				if(!"1".equals(firstInteChange)){
					//未访问过兑换记录
					data.put("firstInteChange", "1");
					this.batteryMapper.updateMemberFirstInteChange(data);
					return true ;
				}
			}
		}
		return false ;
	}

	@Override
	public boolean updateFirstAddressChange(String openid,String addressupdatesource) {
		System.out.println("updateFirstAddressChange");
		if(null==openid || "".equals(openid)){
			return false ;
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("openid", openid);
		Map<String, Object> data = this.batteryMapper.getMember(dataMap);
		if(null == data || data.isEmpty()){
			return false ;
		}
		
		String temp = MapUtils.getString(data, "addressupdatesource");
		if(null==temp || "".equals(temp)){
			dataMap.put("addressupdatesource", addressupdatesource);
			this.batteryMapper.updateFirstAddressChange(dataMap);
			return true ;
		}
		return false ;
	}

	@Override
	public Map<String, Object> getMember(Map<String, Object> pvMap) {
		return this.batteryMapper.getMember(pvMap);
	}

	@Override
	public Map<String, Object> getMlBannerCharg(Map<String, Object> data) {
		return this.batteryMapper.getMlBannerCharg(data);
	}

	@Override
	public List<Map<String, Object>> getMlBannerChargList(
			Map<String, Object> data) {
		return this.batteryMapper.getMlBannerChargList(data);
	}

	@Override
	public void insertMlBannerCharg(Map<String, Object> data) {
		this.batteryMapper.insertMlBannerCharg(data);
	}

	@Override
	public void updateMlBannerCharg(Map<String, Object> data) {
		this.batteryMapper.updateMlBannerCharg(data);
	}

	@Override
	public Integer getMlBannerChargListNum(Map<String, Object> data) {
		return this.batteryMapper.getMlBannerChargListNum(data);
	}

	@Override
	public void deleteMLBannerCharg(Map<String, Object> data) {
		this.batteryMapper.deleteMLBannerCharg(data);
	}

	@Override
	public Map<String, Object> getAreaLike(Map<String, Object> data) {
		return this.batteryMapper.getAreaLike(data);
	}
	
}
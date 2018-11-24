package com.fute.reserve.util;

import java.net.URL;
import java.util.Properties;

public class Constants {
	
	 
//	public static  String APPID = "wxfdb0b4dc0d0bc7d3";
//	public static  String APPSECRET = "928caa39dbab5fea6cabb429c221fc0f";
//	public static  String PROJECT_PATH = "http://www.hoheng.cn/cd/";
//	
	// 正式
	public static  String CORPID = "wxd0677a981b2a0d3e";
	public static  String CORPSECRET = "1RmXsb46G_mJdUHn7Jiw5_dOW6X4vLQ2ZGt5r6k5AsQxt49JkLiI9syuWrbtt_Qp";
	public static  String PROJECT_PATH = "http://www.changanfordqiyehao.com/";
	
	//测试
//	public static  String CORPID = "wx2346f6fa2833d502";
//	public static  String CORPSECRET = "pQoVIr7raBiuPXpqjvPP3hF09xPaiy52lfrWa2S5UvkIcbV4xSckcpEbznV9snkC";
//	public static  String PROJECT_PATH = "http://ad.hoheng.cn/fute/";
	
	public static  String userid = "";
	
	
	//以下字段来源于字典表中的数据
	//当数据发生变更后，这里需要修改
	//获取积分的途径= （问卷调查）
	public static final String INTE_WAY_QUESTION = "2";
	//获取积分的途径= （乘坐出租）
	public static final String INTE_WAY_BUS = "1";
	//获取积分的途径= （Q屏扫描）
	public static final String INTE_WAY_QPing = "3";
	//获取积分的途径= （PB扫码）
	public static final String INTE_WAY_PB= "4";
	//获取积分的途径= （好声音）
	public static final String INTE_WAY_HSY= "HSY";
	
	
	//获取积分的途径(数据字典主表)
	public static final String DATATYPE_INTE_WAY = "10";
	//积分类型(数据字典主表)
	public static final String DATATYPE_INTE_TYPE = "11";
	// 积分有效期设定
	public static final String DATATYPE_INTE_VALIDATIME = "3";
	
	
	//积分类型 = （普通类型）
	public static final String INTE_TYPE_ORDINARY = "17";
	//积分类型 = （特殊类型）
	public static final String INTE_TYPE_SPECIAL = "18";
	//积分类型（typeid）
	public static final String INTE_TYPE = "11";
	
	//积分二维码不正确
	public static final String INTE_SCAN_FAIL = "0" ;
	//二维码已过期
	public static final String INTE_SCAN_TIMEOUT = "1" ;
	//成功
	public static final String INTE_SCAN_SUCCESS = "2" ;
	// 未关注过触动传媒
	public static final String INTE_SCAN_NOFOCUS = "3" ;
	
	
	// 积分兑换部分结果参数
	//没有该优惠券
	public static final String BUY_RESULT_NOCOUPONID = "1";
	//优惠券已售完
	public static final String BUY_RESULT_EMPTY = "2";
	//优惠券数量不足
	public static final String BUY_RESULT_LESSNUM = "3";
	//用户积分不足
	public static final String BUY_RESULT_INTELESS = "4";
	//满足兑换优惠券的所有条件
	public static final String BUY_CONDITION = "5";
	//购买成功
	public static final String BUY_SUCCESS = "6";
	//购买失败
	public static final String BUY_FAIL = "7";
	//不满足购买条件爱你
	public static final String BUY_NOT_CONDITION = "8";
	
	// 微信常量end
	public static final String scope = "snsapi_base";
	private Properties properties = new Properties();
	public static final String LOGINADMIN = "administrator";
	public static final URL URL_ROOT = Constants.class.getResource("/");
	public static final String PATH_CLASS_ROOT = URL_ROOT.getPath();
	public static final String PATH_TEMPLATE = PATH_CLASS_ROOT
			+ "com/dzhqs/web/template/";
	public static final String FTP_DIR = "/wap";
	public static final String FilePath = PATH_CLASS_ROOT.substring(1,
			PATH_CLASS_ROOT.length() - 21)
			+ "/esp/chart/";
	public static final String FilePath_OLD = PATH_CLASS_ROOT.substring(0,
			PATH_CLASS_ROOT.length() - 21)
			+ "/web/wml/";
	public static final String File_Path = PATH_CLASS_ROOT.substring(0,
			PATH_CLASS_ROOT.length() - 16);

	public static final String DEFAULT_PASSWORD = "123456";
	
	// 问卷调查的背景图片的placecode
	public static final String QUESTIONBACKGROUND = "questionbackground";
	// 积分商城的背景图片的placecode
	public static final String INTE_BACK_GROUND = "intebackground";
	// 会员信息的背景图片的placecode
	public static final String MEMBER_BACK_GROUND ="memberbackground";
	// 积分扫描页
	public static final String POINT_SCAN = "PointScan" ;
	// 充电扫描页
	public static final String CHARGING_SCAN = "chargingScan" ;
	
	//微信菜单的点击
	public static final String WEIXIN_MENU_CLICK = "click";
	//微信菜单的view
	public static final String WEIXIN_MENU_VIEW = "view";
	
	//激活
	public static final String IFACTIVE_TRUE = "1" ;
	//未激活
	public static final String IFACTIVE_FALSE = "0" ;
	
	//已被购买
	public static final String IFBUY_TRUE = "1" ;
	//未被购买
	public static final String IFBUY_FALSE = "0" ;
	
	//已使用
	public static final String IFUSE_TRUE = "1" ;
	//未使用
	public static final String IFUSE_FALSE = "0" ;
	
	//已超期
	public static final String IFOVERDUE_TRUE = "1" ;
	//未超期
	public static final String IFOVERDUE_FALSE = "0" ;
	
	
	//微信请求指令类型
	public static final String REQUEST_MSG = "0" ;
	//微信响应指令类型
	public static final String RESPONSE_MSG = "1" ;
	
	
	//粉丝的来源 = 充电
	public static final String MEMBER_SOURCE_BATT = "0" ;
	//粉丝的来源 = 积分
	public static final String MEMBER_SOURCE_INTE = "1" ;
	//粉丝的来源 = PB
	public static final String MEMBER_SOURCE_PB = "2" ;
	
	//微信关键字 = 模糊匹配
	public static final String WEIXIN_KEY_LIKE = "1" ;
	//微信关键字 = 完全匹配
	public static final String WEIXIN_KEY_ALL = "0" ;
	
	//返回文本
	public static final String WEIXIN_RESP_TEXT = "8" ;
	//返回图文
	public static final String WEIXIN_RESP_TEXTIMG = "9" ;
	
	/**
	 * @Fields DEFAULT_PLACE_IMG: 图片素材中的默认图片
	 */
	public static final String DEFAULT_PLACE_IMG = "/activity/shenye/images/nopic.jpg";

	public final static Constants instance = new Constants();

	public static Constants getInstance() {
		return instance;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}

package com.fute.backer.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.fute.backer.service.BatteryService;
import com.fute.backer.service.SystemService;
import com.fute.common.action.BaseActionImpl;
import com.fute.common.ui.StringUtil;
import com.fute.common.util.DateUtil;
import com.fute.common.util.DesService;
import com.fute.common.util.FileDeal;
import com.fute.common.util.ImgCompress;
import com.fute.common.util.UploadFileUtil;
import com.fute.reserve.util.Constants;
import com.fute.reserve.util.WeixinUtil;
import com.fute.util.PageHelper;
import com.opensymphony.xwork2.ActionContext;

public class BatteryAction extends BaseActionImpl {

	private static Logger log = Logger.getLogger(BatteryAction.class);
	private static final long serialVersionUID = 1L;
	private BatteryService batteryService;
	private SystemService systemService;
	private String logo;
	private String cdtitle;
	private String cartitle;
	private String tripid;
	private int currentPage = 1;
	private String ifattention = "";
	private String pagestatus = "";
	private String changci;
	private int ifsuccess = 0;

	private String reurl;
	private String CORPID;
	private String xfield;
	private String yfield;
	private String openid;
	private String point;
	private String ptype;
	private String vcode;
	private String state = "123";

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getCartitle() {
		return cartitle;
	}

	public void setCartitle(String cartitle) {
		this.cartitle = cartitle;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getXfield() {
		return xfield;
	}

	public void setXfield(String xfield) {
		this.xfield = xfield;
	}

	public String getYfield() {
		return yfield;
	}

	public void setYfield(String yfield) {
		this.yfield = yfield;
	}

	public String getReurl() {
		return reurl;
	}

	public void setReurl(String reurl) {
		this.reurl = reurl;
	}
	
	public String getCORPID() {
		return CORPID;
	}

	public void setCORPID(String cORPID) {
		CORPID = cORPID;
	}

	public int getIfsuccess() {
		return ifsuccess;
	}

	public void setIfsuccess(int ifsuccess) {
		this.ifsuccess = ifsuccess;
	}

	public String getChangci() {
		return changci;
	}

	public void setChangci(String changci) {
		this.changci = changci;
	}

	// 压缩图片的宽高
	private static final int IMG_COMPRESS_WIDTH = 400;
	private static final int IMG_COMPRESS_HEIGHT = 400;

	public String getPagestatus() {
		return pagestatus;
	}

	public void setPagestatus(String pagestatus) {
		this.pagestatus = pagestatus;
	}

	public String getIfattention() {
		return ifattention;
	}

	public void setIfattention(String ifattention) {
		this.ifattention = ifattention;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getTripid() {
		return tripid;
	}

	public void setTripid(String tripid) {
		this.tripid = tripid;
	}

	public String getCdtitle() {
		return cdtitle;
	}

	public void setCdtitle(String cdtitle) {
		this.cdtitle = cdtitle;
	}

	private Map<String, Object> pvMap = new HashMap<String, Object>();;

	public BatteryService getBatteryService() {
		return batteryService;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setBatteryService(BatteryService batteryService) {
		this.batteryService = batteryService;
	}

	private String code;
	private String carinfo;
	private String imgtype;

	public String getImgtype() {
		return imgtype;
	}

	public void setImgtype(String imgtype) {
		this.imgtype = imgtype;
	}

	public String getCarinfo() {
		return carinfo;
	}

	public void setCarinfo(String carinfo) {
		this.carinfo = carinfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String qcode;

	public String getQcode() {
		return qcode;
	}

	public void setQcode(String qcode) {
		this.qcode = qcode;
	}

	public Map<String, Object> getPvMap() {
		return pvMap;
	}

	public void setPvMap(Map<String, Object> pvMap) {
		this.pvMap = pvMap;
	}

	private String carinfovalue;

	public String getCarinfovalue() {
		return carinfovalue;
	}

	public void setCarinfovalue(String carinfovalue) {
		this.carinfovalue = carinfovalue;
	}

	private Map<String, Object> backGroundMap;

	public Map<String, Object> getBackGroundMap() {
		return backGroundMap;
	}

	public void setBackGroundMap(Map<String, Object> backGroundMap) {
		this.backGroundMap = backGroundMap;
	}

	private Map<String, Object> getChargBackGround(String imgtype) {

		if (null == imgtype || "".equals(imgtype)) {
			return null;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", imgtype);
		Map<String, Object> result = this.batteryService
				.getMlBannerCharg(param);
		if (null != result && !result.isEmpty()) {
			return result;
		}
		return null;
	}
	public String batteryview2() {

		Map<String, Object> param = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(state)) {
			try {
				System.out.println(state);
				state = DesService.getInstance().decode(state);
				System.out.println(state);

				JSONObject jsonObject = JSONObject.fromObject(state);
				param = (Map<String, Object>) JSONObject.toBean(jsonObject,
						Map.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		FileDeal.parseSimpleForm(pvMap);
		String openid = "";
		if (!pvMap.containsKey("openid")) {
			HttpServletRequest request = ServletActionContext.getRequest();
			String scode = request.getParameter("code");
			String result = new WeixinUtil().getInfo(scode);
			JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
			openid = jsonobj.getString("openid");// 获取字符串。
			pvMap.put("openid", openid);
		} else {
			openid = MapUtils.getString(pvMap, "openid");
			pvMap.put("openid", openid);
			param = new HashMap<String, Object>();
			param.put("imgtype", MapUtils.getString(pvMap, "imgtype"));
		}

		Map<String, Object> map = batteryService.findMember(pvMap);

		Map<String, Object> pmap = batteryService.getMlBanner();
		if (null != pmap) {
			logo = pmap.get("img") + "";
		}
		// 跳转到一个单独的页面
		if (null == backGroundMap) {
			return "batteryviewdefault";
		}
		return "batteryview";
	}

	public String execute() {

		if (StringUtils.isNotBlank(code)) {
			try {
				code = DesService.getInstance().decode(code);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// log.info("execute.code="+code);
		// log.info("execute.tripid="+tripid);
		// log.info("execute.carinfo="+carinfo);
		// log.info("execute.imgtype="+imgtype);

		reurl = Constants.PROJECT_PATH + "battery!batteryview.action&amp;";
		// reurl += "qcode="+code+"&amp;" ;
		// reurl += "tripid="+tripid+"&amp;" ;
		// reurl += "carinfo="+carinfo+"&amp;" ;
		// reurl += "imgtype="+imgtype+"&amp;" ;

		JSONObject json = new JSONObject();
		json.put("qcode", code);
		json.put("tripid", tripid);
		json.put("carinfo", carinfo);
		json.put("imgtype", null != imgtype ? imgtype : "");

		try {
			System.out.println(json.toString());
			log.info("battery.action=" + json.toString());
			state = DesService.getInstance().encode(json.toString());
			System.out.println(state);
		} catch (Exception e) {
			e.printStackTrace();
		}

		CORPID = Constants.CORPID;
		return "getopenid";
	}

	public static void main(String[] args) {
		try {
			String string = DesService
					.getInstance()
					.encode(
							"12312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx2312312asfssdfssddxvx");

			System.out.println(string);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 粉丝列表
	private List<Map<String, Object>> memberList;

	public List<Map<String, Object>> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Map<String, Object>> memberList) {
		this.memberList = memberList;
	}

	// 深夜的士列表
	private List<Map<String, Object>> shenyeList;

	public List<Map<String, Object>> getShenyeList() {
		return shenyeList;
	}

	public void setShenyeList(List<Map<String, Object>> shenyeList) {
		this.shenyeList = shenyeList;
	}

	private List<Map<String, Object>> markList;

	public List<Map<String, Object>> getMarkList() {
		return markList;
	}

	public void setMarkList(List<Map<String, Object>> markList) {
		this.markList = markList;
	}

	/**
	 * 查看粉丝集合
	 * 
	 * @return
	 */

	public void initPage(Map<String, Object> paramMap, PageHelper pageHelper) {
		int rowPage = pageHelper.getRowPage(); // 每页显示的记录
		if (currentPage < 1)
			currentPage = 1;
		if (rowPage < 1)
			rowPage = 1;
		int startnum = (currentPage - 1) * rowPage; // 分页查询开始的序号
		int rownum = rowPage; // 每页显示的条数
		pvMap.put("startnum", startnum);
		pvMap.put("rownum", rownum);
	}

	// 充电列表数据
	private List<Map<String, Object>> battHistoryList;

	public List<Map<String, Object>> getBattHistoryList() {
		return battHistoryList;
	}

	public void setBattHistoryList(List<Map<String, Object>> battHistoryList) {
		this.battHistoryList = battHistoryList;
	}

	public void initTime(Map<String, Object> pvMap) {
		if (null != pvMap) {
			// 将开始日期转换为开始时间
			if (null != pvMap.get("startDate")
					&& !"".equals(pvMap.get("startDate"))) {
				String startDate = null != pvMap.get("startDate") ? pvMap.get(
						"startDate").toString() : "";
				if (null != startDate && !"".equals(startDate)) {
					pvMap.put("startDate", startDate + " 00:00:00");
				}
			}
			// 将结束日期转换为结束时间
			if (null != pvMap.get("endDate")
					&& !"".equals(pvMap.get("endDate"))) {
				String endDate = null != pvMap.get("endDate") ? pvMap.get(
						"endDate").toString() : "";
				if (null != endDate && !"".equals(endDate)) {
					pvMap.put("endDate", endDate + " 23:59:59");
				}
			}

		}

	}

	private Map<String, Object> mlBanner;

	public Map<String, Object> getMlBanner() {
		return mlBanner;
	}

	public void setMlBanner(Map<String, Object> mlBanner) {
		this.mlBanner = mlBanner;
	}

	public String findMlBanner() {
		List<Map<String, Object>> mlList = this.batteryService
				.getMlBannerList();

		if (null != mlList && mlList.size() > 0) {
			mlBanner = mlList.get(0);
		} else {
			mlBanner = new HashMap<String, Object>();
			mlBanner.put("id", "");
			mlBanner.put("img", "images/default.gif");
		}
		return "mlBanner";
	}

	private File uploadImg;
	private String uploadImgFileName;

	public File getUploadImg() {
		return uploadImg;
	}

	public void setUploadImg(File uploadImg) {
		this.uploadImg = uploadImg;
	}

	public String getUploadImgFileName() {
		return uploadImgFileName;
	}

	public void setUploadImgFileName(String uploadImgFileName) {
		this.uploadImgFileName = uploadImgFileName;
	}

	// 提交充电Banner
	public String submitMlBanner() {

		String targetPath = copy(uploadImg);

		// 保存图片
		Map<String, Object> mlBannerForm = new HashMap<String, Object>();
		String id = null != getRequest().getParameter("id") ? getRequest()
				.getParameter("id").toString() : "";
		mlBannerForm.put("img", targetPath);
		if (null != id && !"".equals(id)) {
			mlBannerForm.put("id", id);
		}
		this.batteryService.updateMlBanner(mlBannerForm);

		return findMlBanner();
	}

	private String getExtension() {
		return uploadImgFileName.substring(uploadImgFileName.lastIndexOf("."));
	}

	/**
	 * @function:复制文件到默认的路径
	 * @datetime:2014-9-19 下午08:49:06
	 * @Author: robin
	 * @param: @param src
	 * @param: @return 上传后的文件路径
	 * @return String 上传后的文件路径
	 */
	private String copy(File src) {

		int BUFFER_SIZE = 16 * 1024;
		InputStream in = null;
		OutputStream out = null;
		String realPath = getRequest().getRealPath("upload");
		String temp = "/banner/" + new Random().nextLong() + getExtension();
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new FileOutputStream(realPath + temp);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "upload" + temp;
	}

	/**
	 * 大宁音乐节活动
	 * 
	 * */
	public String daningindex() {
		// 判断该用户是否关注过微信,获得openid
		reurl = Constants.PROJECT_PATH + "/wechat!daning.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	public String daning() {
		// 判断该用户是否关注过微信,获得openid
		String forward = "";
		HttpServletRequest request = ServletActionContext.getRequest();

		String scode = request.getParameter("code");
		String result = new WeixinUtil().getInfo(scode);

		JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
		String openid = jsonobj.getString("openid");// 获取openid
		pvMap.put("openid", openid);
		Map<String, Object> map = batteryService.findMember(pvMap);
		// 获得daning的抽奖开关

		if (map != null && map.size() > 0) {
			pvMap.put("name", null == map.get("name") ? "" : map.get("name"));
			pvMap
					.put("phone", null == map.get("phone") ? "" : map
							.get("phone"));
			forward = "daningyes";
		} else {
			// 未关注
			pvMap.put("name", "");
			pvMap.put("phone", "");
			forward = "daningno";
		}
		// forward = "daningyes";
		return forward;
	}

	/**
	 * 1:831 2:dd 3:thank 5:have 6:jz 7:maik
	 * 
	 * @return
	 */

	public String daninginsert() {

		String forward = "daningsuccess";
		FileDeal.mapArrayToStr(pvMap);
		// 首先判断此人 在当天是否抢过票
		String day = DateUtil.formatDateYMD(new Date());

		pvMap.put("currdate", day);
		Map<String, Object> currPerson = batteryService.getDaning(pvMap);
		pvMap.put("currdate", day);
		int currDayCount = batteryService.getDaningNumByDay(pvMap);

		pvMap.put("createtime", new Date());

		boolean ist = batteryService.getDaningSwtich(pvMap);
		if (ist) {
			if (null != currPerson) {
				// 今天已经抢过票了
				pagestatus = "have";
				pvMap.put("ifsuccess", "0");
				batteryService.insertDaning(pvMap);
			} else {
				if (currDayCount < 5) {
					pagestatus = pvMap.get("changci") + "";
					pvMap.put("ifsuccess", "1");
					batteryService.insertDaning(pvMap);
				} else {
					pagestatus = "thank";
					pvMap.put("ifsuccess", "0");
					batteryService.insertDaning(pvMap);
				}

			}
		} else {
			pagestatus = "thank";
			pvMap.put("ifsuccess", "0");
			batteryService.insertDaning(pvMap);
		}

		// 更新会员表
		batteryService.updateMember(pvMap);

		return forward;
	}

	/**
	 * 大宁音乐节结束
	 * 
	 * @return
	 */
	public String shenyeindex() {
		reurl = Constants.PROJECT_PATH + "/wechat!shenye.action";
		CORPID = Constants.CORPID;
		return "getopenid";

	}

	public String shenye() {
		String forward = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		String scode = request.getParameter("code");
		String result = new WeixinUtil().getInfo(scode);
		JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
		if (!jsonobj.containsKey("openid")) {
			return shenyeindex();
		}
		String openid = jsonobj.getString("openid");// 获取openid
		pvMap.put("openid", openid);
		Map<String, Object> map = batteryService.findMember(pvMap);

		if (map != null && map.size() > 0) {
			pvMap
					.put("story", null != map.get("story") ? map.get("story")
							: "");
			pvMap.put("name", null != map.get("name") ? map.get("name") : "");
			pvMap
					.put("phone", null != map.get("phone") ? map.get("phone")
							: "");
			pvMap.put("shenyeid", null != map.get("shenyeid") ? map
					.get("shenyeid") : "");
			pvMap.put("img", null != map.get("img") ? map.get("img") : "");
			forward = "shenyeyes";
		} else {
			// 未关注
			pvMap.put("shenyeid", "");
			pvMap.put("story", "");
			pvMap.put("name", "");
			pvMap.put("phone", "");
			pvMap.put("img", "");
			forward = "shenyeno";
		}
		// forward = "shenyeyes";
		return forward;
	}

	public String shenyeinsert() {
		String forward = "shenyesuccess";
		FileDeal.mapArrayToStr(pvMap);

		if (pvMap.get("shenyeid") != null && !pvMap.get("shenyeid").equals("")) {
			batteryService.updateShenye(pvMap);
		} else {
			batteryService.insertShenye(pvMap);
		}

		return forward;

	}

	private Map<String, Object> shenyeCondition;

	public Map<String, Object> getShenyeCondition() {
		return shenyeCondition;
	}

	public void setShenyeCondition(Map<String, Object> shenyeCondition) {
		this.shenyeCondition = shenyeCondition;
	}

	public String findShenyeList() {

		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(shenyeCondition);
		// 避免将修改后的时间传递到前台
		Map<String, Object> sourceMap = FileDeal.copyNew(shenyeCondition);
		initTime(shenyeCondition);
		// 处理分页参数
		initPage(pvMap, pageHelper);
		shenyeList = this.batteryService.getShenyeList(shenyeCondition);
		int num = this.batteryService.getShenyeListNum(shenyeCondition);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		// 将查询条件传递到前台
		this.shenyeCondition = sourceMap;
		return "shenyelist";
	}

	private File shenYeImg;
	private String shenYeImgFileName;

	public File getShenYeImg() {
		return shenYeImg;
	}

	public void setShenYeImg(File shenYeImg) {
		this.shenYeImg = shenYeImg;
	}

	public String getShenYeImgFileName() {
		return shenYeImgFileName;
	}

	public void setShenYeImgFileName(String shenYeImgFileName) {
		this.shenYeImgFileName = shenYeImgFileName;
	}

	// 提交ShenYe图片
	public String uploadShenYe() {
		// 上传图片
		String targetPath = copyShenYe(shenYeImg);
		// 返回图片地址
		getRequest().setAttribute("imgPath", targetPath);
		return "showImg";
	}

	private String parsePNGImage(String sourcePath) {
		return sourcePath.substring(0, sourcePath.lastIndexOf(".")) + ".png";
	}

	private String copyShenYe(File src) {

		String realPath = getRequest().getRealPath("upload");
		String extension = shenYeImgFileName.substring(shenYeImgFileName
				.lastIndexOf("."));
		String temp = "/shenye/" + Calendar.getInstance().getTime().getTime()
				+ extension;
		temp = parsePNGImage(temp);

		// 压缩图片
		try {
			ImgCompress imgCom = new ImgCompress(src);
			imgCom.resizeFix(IMG_COMPRESS_WIDTH, IMG_COMPRESS_HEIGHT, realPath
					+ temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload" + temp;
	}

	/**
	 * 抢cd活动:没有关注过微信直接抢CD
	 * 
	 * */
	public String cdindex() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put("changci", changci);
		reurl = Constants.PROJECT_PATH + "/wechat!cd.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	public String cd() {
		// 判断该用户是否关注过微信,获得cd
		String forward = "";
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();

		HttpServletRequest request = ServletActionContext.getRequest();
		String scode = request.getParameter("code");
		String result = new WeixinUtil().getInfo(scode);
		JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
		String openid = jsonobj.containsKey("openid") ? jsonobj
				.getString("openid") : "";// 获取openid
		if (null == openid || "".equals(openid)) {
			reurl = Constants.PROJECT_PATH + "/wechat!cd.action";
			CORPID = Constants.CORPID;
			return "getopenid";
		}

		session.put("openid", openid);
		pvMap.put("openid", openid);
		Map<String, Object> map = batteryService.findMember(pvMap);
		if (map != null && map.size() > 0) {
			// 假如是会员,奖项开关开,每场次不大于5个,即可中奖

			// 获得当前的抢票
			String currchangci = "" + session.get("changci");

			// 首先判断此人 在是否抢过票
			pvMap.put("changci", currchangci);
			Map<String, Object> currPerson = batteryService.getCD(pvMap);
			int currDayCount = batteryService.getCDNumByDay(pvMap);
			boolean ist = batteryService.getCDSwtich(pvMap);
			if (ist) {
				if (null != currPerson) {
					String ifsucc = "" + currPerson.get("ifsuccess");
					pagestatus = null != currPerson ? currPerson.get("changci")
							+ "" : "";
					pagestatus = "1".equals(ifsucc) ? pagestatus : "failure";
					ifsuccess = 0;
					return "cdfinish";
				} else {
					if (currDayCount < 5) {
						pagestatus = pvMap.get("changci") + "";
						ifsuccess = 1;
					} else {
						pagestatus = "failure";
						ifsuccess = 0;
					}
				}
			} else {
				pagestatus = "failure";
				ifsuccess = 0;
			}

			pvMap.put("changci", currchangci);
			pvMap.put("ifsuccess", ifsuccess);
			pvMap.put("openid", openid);
			pvMap.put("createtime", new Date());
			batteryService.insertCD(pvMap);
			forward = "cdyes";
		} else {
			forward = "cdno";
		}
		return forward;
	}

	/**
	 * 1:831 2:dd 3:thank 5:have 6:jz 7:maik
	 * 
	 * @return
	 */

	public String cdinsert() {

		String forward = "cdsuccess";
		// 获得当前的抢票
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		String changci = "" + session.get("changci");
		String openid = "" + session.get("openid");

		// 首先判断此人 在是否抢过票
		pvMap.put("changci", changci);
		pvMap.put("openid", openid);
		Map<String, Object> currPerson = batteryService.getCD(pvMap);
		int currDayCount = batteryService.getCDNumByDay(pvMap);
		boolean ist = batteryService.getCDSwtich(pvMap);
		if (ist) {
			if (null != currPerson) {
				String ifsucc = "" + currPerson.get("ifsuccess");
				pagestatus = null != currPerson ? currPerson.get("changci")
						+ "" : "";
				pagestatus = "1".equals(ifsucc) ? pagestatus : "failure";
				ifsuccess = 0;
				return "cdfinish";
			} else {
				if (currDayCount < 5) {
					pagestatus = pvMap.get("changci") + "";
					ifsuccess = 1;
				} else {
					pagestatus = "failure";
					ifsuccess = 0;
				}
			}
		} else {
			pagestatus = "failure";
			ifsuccess = 0;
		}

		this.changci = changci;
		return forward;
	}

	private Map<String, Object> textKeyCondition;
	private List<Map<String, Object>> textKeyList;
	private List<Map<String, Object>> msgtypeList;
	private List<Map<String, Object>> respmsgtypeList;

	public List<Map<String, Object>> getRespmsgtypeList() {
		return respmsgtypeList;
	}

	public void setRespmsgtypeList(List<Map<String, Object>> respmsgtypeList) {
		this.respmsgtypeList = respmsgtypeList;
	}

	public List<Map<String, Object>> getMsgtypeList() {
		return msgtypeList;
	}

	public void setMsgtypeList(List<Map<String, Object>> msgtypeList) {
		this.msgtypeList = msgtypeList;
	}

	public Map<String, Object> getTextKeyCondition() {
		return textKeyCondition;
	}

	public void setTextKeyCondition(Map<String, Object> textKeyCondition) {
		this.textKeyCondition = textKeyCondition;
	}

	public String saveTextKey() {
		if (null != textKeyCondition && !textKeyCondition.isEmpty()) {
			FileDeal.parseSimpleForm(textKeyCondition);
			if (textKeyCondition.containsKey("id")
					&& null != textKeyCondition.get("id")
					&& !"".equals(textKeyCondition.get("id").toString())) {
				this.batteryService.updateTextKey(textKeyCondition);
			} else {
				this.batteryService.insertTextKey(textKeyCondition);
			}
		}
		return findTextKeyList();
	}

	public String deleteTextKeyList() {
		if (null != textKeyCondition && !textKeyCondition.isEmpty()) {
			FileDeal.parseSimpleForm(textKeyCondition);
			this.batteryService.deleteTextKey(textKeyCondition);
		}
		return findTextKeyList();
	}

	private File imgurl;
	private String imgurlFileName;

	public File getImgurl() {
		return imgurl;
	}

	public void setImgurl(File imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgurlFileName() {
		return imgurlFileName;
	}

	public void setImgurlFileName(String imgurlFileName) {
		this.imgurlFileName = imgurlFileName;
	}

	public String uploadImgurl() {
		// 上传图片
		if (null != imgurl) {
			String realPath = getRequest().getRealPath("/");
			String path = "/activity/upload/images/";
			String targetPath = UploadFileUtil.copyFile(imgurl, imgurlFileName,
					realPath, path);
			// 返回图片地址
			getRequest().setAttribute("imgPath", targetPath);
		}
		return "showImg";
	}

	public String changeNickName() {
		List<Map<String, Object>> openidList = this.batteryService
				.getMemberOpenList();
		if (null != openidList && !openidList.isEmpty()) {
			for (Map<String, Object> map : openidList) {
				if (null != map && map.containsKey("openid")) {
					String openid = MapUtils.getString(map, "openid");
					if (!StringUtils.isEmpty(openid)) {
						try {
							Map<String, Object> infoMap = WeixinUtil
									.getUserInfo(openid);
							if (null != infoMap && !infoMap.isEmpty()) {
								pvMap.put("nickname", infoMap.get("nickname"));
								pvMap.put("openid", openid);
								pvMap.put("gaintime", FileDeal
										.getSubscribeTime(infoMap));
								batteryService.updateNickName(pvMap);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return "nickNameSuccess";
	}

	private Map<String, Object> memberInfo;

	public Map<String, Object> getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(Map<String, Object> memberInfo) {
		this.memberInfo = memberInfo;
	}

	private List<Map<String, Object>> provinceList;

	public List<Map<String, Object>> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Map<String, Object>> provinceList) {
		this.provinceList = provinceList;
	}

	/**
	 * 初始化积分记录界面
	 * */
	public String initInteRecordIndex() {
		reurl = Constants.PROJECT_PATH + "/inte!initInteRecord.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	/**
	 * 初始化积分记录界面
	 * */
	public String initInteShopIndex() {
		reurl = Constants.PROJECT_PATH + "/inte!inteShop.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	/**
	 * 初始化乘车记录界面
	 * */
	public String initByCarIndex() {
		reurl = Constants.PROJECT_PATH + "/inte!initByCarRecord.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	/**
	 * 初始化互动记录界面
	 * */
	public String initInteractionIndex() {
		reurl = Constants.PROJECT_PATH
				+ "/battery!initInteractionRecord.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	/**
	 * 初始化兑换记录界面
	 * */
	public String initShowInteChangeIndex() {
		reurl = Constants.PROJECT_PATH + "/inte!showInteChange.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	/**
	 * 初始化调查问卷界面
	 * */
	public String initQuestionIndex() {
		reurl = Constants.PROJECT_PATH + "/backer/question!initQuestion.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	/**
	 * 初始化刮刮卡界面
	 * */
	public String initGuaGuaKaIndex() {
		reurl = Constants.PROJECT_PATH + "/guaguaka!initGuaGuaKa.action";
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private String backurl;

	public String getBackurl() {
		return backurl;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	/**
	 * 过度页面
	 * */
	public String initBackurlIndex() {
		System.out.println("backurl = " + backurl);
		reurl = Constants.PROJECT_PATH + "/battery!initBackurl.action?backurl="
				+ backurl;
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	public String initBackurl() {
		System.out.println("backurl = " + backurl);
		String openid = MapUtils.getString(pvMap, "openid");
		if (null == openid || "".equals(openid)) {
			// 当前是从微信端跳转过来的
			// 判断该用户是否关注过微信,获得cd
			HttpServletRequest request = ServletActionContext.getRequest();

			String scode = request.getParameter("code");
			String result = new WeixinUtil().getInfo(scode);
			JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
			openid = jsonobj.containsKey("openid") ? jsonobj
					.getString("openid") : "";// 获取openid
			if (null == openid || "".equals(openid)) {
				reurl = Constants.PROJECT_PATH + "/wechat!initBackurl.action";
				CORPID = Constants.CORPID;
				return "getopenid";
			}
			pvMap.put("openid", openid);
		}

		return "backurl";
	}

	/**
	 * 初始化会员管理界面
	 * 
	 * */
	public String initMemberIndex() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put("changci", changci);
		System.out.println("\n\ninitMemberIndex.source=" + source);
		session.put("source", source);
		reurl = Constants.PROJECT_PATH + "/battery!initMember.action?source="
				+ source;
		CORPID = Constants.CORPID;
		return "getopenid";
	}

	private Map<String, Object> inteTJ;

	public Map<String, Object> getInteTJ() {
		return inteTJ;
	}

	public void setInteTJ(Map<String, Object> inteTJ) {
		this.inteTJ = inteTJ;
	}

	private String backGroundImg;

	public String getBackGroundImg() {
		return backGroundImg;
	}

	public void setBackGroundImg(String backGroundImg) {
		this.backGroundImg = backGroundImg;
	}

	public String getCityList() {
		System.out.println("getCityList");
		List<Map<String, Object>> cityList = null;
		// 选择的省份
		String province = getRequestParameterValue("province");
		if (!StringUtil.isNullOrEmpty(province)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("parentid", province);
			cityList = this.batteryService.getCityListByProvince(param);
			System.out.println(cityList);
		}
		this.result = parseJsonData(cityList);
		return "cityListJson";
	}

	private List<Map<String, Object>> dataList;

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	// 互动记录（微信端管理中）
	public String initInteractionRecord() {
		if (null == pvMap) {
			pvMap = new HashMap<String, Object>();
		}
		FileDeal.parseSimpleForm(pvMap);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (!pvMap.containsKey("gainstarttime")
				|| null == pvMap.get("gainstarttime")) {
			pvMap.put("gainstarttime", format.format(new Date()));
		}
		if (!pvMap.containsKey("gainendtime")
				|| null == pvMap.get("gainendtime")) {
			pvMap.put("gainendtime", format.format(new Date()));
		}
		String openid = MapUtils.getString(pvMap, "openid");
		if (null == openid || "".equals(openid)) {
			// 当前是从微信端跳转过来的
			// 判断该用户是否关注过微信,获得cd

			HttpServletRequest request = ServletActionContext.getRequest();
			String scode = request.getParameter("code");
			String result = new WeixinUtil().getInfo(scode);
			JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
			openid = jsonobj.containsKey("openid") ? jsonobj
					.getString("openid") : "";// 获取openid
			if (null == openid || "".equals(openid)) {
				reurl = Constants.PROJECT_PATH + "/wechat!initInteractionRecord.action";
				CORPID = Constants.CORPID;
				return "getopenid";
			}
			pvMap.put("openid", openid);
		}

		// 根据传递到pvMap中的参数，查询对应的乘车记录
		PageHelper pageHelper = new PageHelper(this.getRequest());
		// 处理分页参数
		initPage(pvMap, pageHelper);
		checkStartEndTime(pvMap, "gainstarttime", "gainendtime");

		dataList = this.batteryService.getInteractionList(pvMap);
		int num = this.batteryService.getInteractionListNum(pvMap);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		formatStartEndTime(pvMap, "gainstarttime", "gainendtime");
		return "interactionRecord";
	}

	// 互动记录（微信端管理中）
	// 瀑布式加载数据
	public String initInteractionWater() {
		FileDeal.parseSimpleForm(pvMap);
		PageHelper pageHelper = new PageHelper(this.getRequest());
		// 处理分页参数
		initPage(pvMap, pageHelper);
		List<Map<String, Object>> tempList = this.batteryService
				.getInteractionList(pvMap);
		if (null != tempList) {
			for (Map<String, Object> map : tempList) {
				Date gaintime = null != map ? (Date) map.get("gaintime") : null;
				map.put("gaintime", null != gaintime ? gaintime.getTime()
						: null);
			}
		}
		this.result = parseJsonData(tempList);
		return "dataJson";
	}

	private String memberid;

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	// 添加标签
	public String authMarks() {
		if (null != paramCondition && !paramCondition.isEmpty()) {
			List<Map<String, Object>> markList = FileDeal
					.parseFormList(paramCondition);
			this.batteryService.updateMemberMark(memberid, markList);
			System.out.println();
		}
		return findMember();
	}

	private Map<String, Object> paramCondition;

	public Map<String, Object> getParamCondition() {
		return paramCondition;
	}

	public void setParamCondition(Map<String, Object> paramCondition) {
		this.paramCondition = paramCondition;
	}

	/**
	 * 后台业务逻辑开始
	 * 
	 * @return
	 */

	// 互动记录
	public String findInteractionList() {
		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(pvMap);
		// 处理分页参数
		initPage(pvMap, pageHelper);

		dataList = this.batteryService.getInteractionList(pvMap);
		int num = this.batteryService.getInteractionListNum(pvMap);
		// msgtypeList = this.batteryService.getMsgtypeList();
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "interactionList";
	}

	public String findMarkList() {
		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(pvMap);
		// 处理分页参数
		initPage(pvMap, pageHelper);
		dataList = this.batteryService.getMarkList(pvMap);
		int num = this.batteryService.getMarkListNum(pvMap);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "memberMarkList";
	}

	public String deleteMark() {
		if (null != paramCondition && !paramCondition.isEmpty()) {
			FileDeal.parseSimpleForm(paramCondition);
			this.batteryService.deleteMark(paramCondition);
		}
		return findMarkList();
	}

	public String saveMark() {
		if (null != paramCondition && !paramCondition.isEmpty()) {
			FileDeal.parseSimpleForm(paramCondition);
			if (paramCondition.containsKey("markid")
					&& null != paramCondition.get("markid")
					&& !"".equals(paramCondition.get("markid").toString())) {
				this.batteryService.updateMark(paramCondition);
			} else {
				this.batteryService.insertMark(paramCondition);
			}
		}
		return findMarkList();
	}

	public String findMessageList() {
		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(pvMap);
		// 处理分页参数
		initPage(pvMap, pageHelper);

		dataList = this.batteryService.getMessageList(pvMap);
		int num = this.batteryService.getMessageListNum(pvMap);
		// msgtypeList = this.batteryService.getMsgtypeList();
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "messageList";
	}

	public String saveMessage() {
		if (null != pvMap && !pvMap.isEmpty()) {
			FileDeal.parseSimpleForm(pvMap);
			if (pvMap.containsKey("id") && null != pvMap.get("id")
					&& !"".equals(pvMap.get("id").toString())) {
				this.batteryService.updateMessage(pvMap);
			} else {
				this.batteryService.insertMessage(pvMap);
			}
		}
		return findHelloMessage();
	}

	public String findHelloMessage() {
		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(pvMap);
		// 处理分页参数
		initPage(pvMap, pageHelper);

		pvMap = this.batteryService.getHelloMessage(pvMap);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "helloMessage";
	}

	public String saveHelloMessage() {
		if (null != pvMap && !pvMap.isEmpty()) {
			FileDeal.parseSimpleForm(pvMap);
			if (pvMap.containsKey("id") && null != pvMap.get("id")
					&& !"".equals(pvMap.get("id").toString())) {
				this.batteryService.updateHelloMessage(pvMap);
			} else {
				this.batteryService.insertHelloMessage(pvMap);
			}
		}
		return findHelloMessage();
	}

	public List<Map<String, Object>> getTextKeyList() {
		return textKeyList;
	}

	public void setTextKeyList(List<Map<String, Object>> textKeyList) {
		this.textKeyList = textKeyList;
	}

	public String findTextKeyList() {
		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(pvMap);
		// 处理分页参数
		initPage(pvMap, pageHelper);

		textKeyList = this.batteryService.getTextKeyList(pvMap);
		int num = this.batteryService.getTextKeyListNum(pvMap);
		msgtypeList = this.batteryService.getMsgtypeList(Constants.REQUEST_MSG);
		respmsgtypeList = this.batteryService
				.getMsgtypeList(Constants.RESPONSE_MSG);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "textKey";
	}
	public String findMember() {
		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(pvMap);
		// 处理分页参数
		initPage(pvMap, pageHelper);
		memberList = this.batteryService.findMemberList(pvMap);
		int num = this.batteryService.findMemberListNum(pvMap);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());

		// 1. 获取省市的下拉列表
		this.provinceList = batteryService.getProvinceList();
		// 2. 标签类型
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("ifactive", Constants.IFACTIVE_TRUE);
		this.markList = batteryService.getMarkList(temp);
		return "member";
	}

	/**
	 * 后台业务逻辑结束
	 * 
	 * @return
	 */

}
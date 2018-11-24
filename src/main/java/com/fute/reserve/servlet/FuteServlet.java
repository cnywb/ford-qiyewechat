package com.fute.reserve.servlet;

import com.fute.reserve.service.CoreService;
import com.fute.reserve.util.WXBizMsgCrypt;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class FuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FuteServlet.class);
	//正式
	private static String  encodingAESKey="3XzguuhS2kbV4uDYQHwDCwfWvXxD6RhimM9QkRQmIEg";
	private static String  corpId="wxd0677a981b2a0d3e";
	private static String  token="Z8OrUy0PyiQ4DN7fml4XhASQkJTtAY";
	//测试
//	private static String  encodingAESKey="qRNHU7bKo4coidpKws0DSBanuwAMVzk18Q7rlyN4HkY";
//	private static String  corpId="wx2346f6fa2833d502";
//	private static String  token="fute";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FuteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 灏嗚姹傘�鍝嶅簲鐨勭紪鐮佸潎璁剧疆涓篣TF-8锛堥槻姝腑鏂囦贡鐮侊級
		log
				.info("...............................doget...............................");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml; charset=UTF-8");
		String msg_signature ="";
		String timestamp="";
		String nonce="";
		String echostr ="";
		try {
		// 寰俊鍔犲瘑绛惧悕
			System.out.println("1111111111111");
		msg_signature=request.getParameter("msg_signature");
		// 鏃堕棿鎴�
		System.out.println("222222222222");
		timestamp = request.getParameter("timestamp");
		// 闅忔満鏁�
		System.out.println("333333333333");
		nonce = request.getParameter("nonce");
		// 闅忔満瀛楃涓�
		System.out.println("444444444444");
		echostr= request.getParameter("echostr");
		
		System.out.println("msg_signature="+msg_signature+"\ntimestamp="+timestamp+"\nnonce="+nonce+"\nechostr="+echostr);
        // 娴�
		
		} catch (Exception e) {
			e.printStackTrace();
		}
        PrintWriter out = response.getWriter();
        
		String result="";
		 try {
	        	WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token,encodingAESKey,corpId);
	        	System.out.println("21312123123213");
	        	// 楠岃瘉URL鍑芥暟
	        	result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
				System.out.println("verifyurl echostr: " + result);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        if (result == null) {
	        	// result涓虹┖锛岃祴浜坱oken
	        	result = token;
	        }
	        // 鎷兼帴璇锋眰鍙傛暟
	        String str = msg_signature+" "+timestamp+" "+nonce+" "+echostr;
	        // 鎵撳嵃鍙傛暟+鍦板潃+result
	        System.out.println("Exception:"+result+" "+ request.getRequestURL()+" "+"FourParames:"+str);
	        out.print(result);
	        out.close();  
	        out = null;  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log
				.info("...............................dopost...............................");
		System.out.println("+++++++++++杩涘叆futeServlet doPost鏂规硶..........");
		log.debug("杩涘叆futeServlet doPost鏂规硶..........");
		// 灏嗚姹傘�鍝嶅簲鐨勭紪鐮佸潎璁剧疆涓篣TF-8锛堥槻姝腑鏂囦贡鐮侊級
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml; charset=UTF-8");
		// 寰俊鍔犲瘑绛惧悕
		String msg_signature = request.getParameter("msg_signature");
		// 鏃堕棿鎴�
		String timestamp = request.getParameter("timestamp");
		// 闅忔満鏁�
		String nonce = request.getParameter("nonce");
		System.out.println("msg_signature="+msg_signature+"\ntimestamp="+timestamp+"\nnonce="+nonce);
		log.debug("澧炲姞璁剧疆绫诲瀷");
		
		InputStream inputStream = request.getInputStream();  
        String postData = IOUtils.toString(inputStream, "UTF-8");  
        System.out.println(postData);  
        String msg = "";  
        WXBizMsgCrypt wxcpt = null;  
        try {  
            wxcpt = new WXBizMsgCrypt(token, encodingAESKey, corpId);  
            //瑙ｅ瘑娑堟伅  
            msg = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        System.out.println("msg=" + msg);  
          
        // 璋冪敤鏍稿績涓氬姟绫绘帴鏀舵秷鎭�澶勭悊娑堟伅  
        String respMessage = CoreService.processRequest(msg); 
		
		
		
		// 璋冪敤鏍稿績涓氬姟绫绘帴鏀舵秷鎭�澶勭悊娑堟伅
		System.out.println("111111111111111111111");
//		log.debug("respMessage=" + respMessage);
		// 鍝嶅簲娑堟伅
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}

}

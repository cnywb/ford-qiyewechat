package com.ford.qiye.web.util;

import com.ford.qiye.common.FileUtil;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtQuestion;
import com.ford.qiye.model.OperationLog;
import com.ford.qiye.model.UserAuthList;
import com.fute.common.util.DateUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Created by wanglijun on 16/8/21.
 */
public class ExcelUtils {
    /**
     * 日志
     * */
    private static final Logger logger= LoggerFactory.getLogger (ExcelUtils.class);

    public static HSSFWorkbook question(Pagination<DtQuestion> page) {
        String[] header={"编号","经销商姓名","服务代码","所属大区","所属小区","发送部门",	"内容摘要",	"提问时间","解答时间","解答人","状态","回复内容"};
        HSSFWorkbook wb = new HSSFWorkbook(); // create new workbook object
        HSSFSheet sheet = wb.createSheet(); // create new sheet for workbook
        HSSFRow row;
        HSSFCell cell;
        row = sheet.createRow(0);
        for(int i=0;i<header.length;i++){
            cell=row.createCell((short)i);
            cell.setCellValue(header[i]);
        }
        for(int i=0;i<page.getData ().size ();i++){
            row = sheet.createRow((short) i + 1); // create new row for
            DtQuestion question =page.getData ().get (i);
            for(int j=0;j<header.length;j++){
                cell =row.createCell((short) (j));
                switch ( j ){
                    case 0:
                        cell.setCellValue(question.getId ()+"");
                        break;
                    case 1:
                        cell.setCellValue(setCellValue (question.getRealName ()));
                        break;
                    case 2:
                        cell.setCellValue(setCellValue (question.getServeCode ()));
                        break;
                    case 3:
                        cell.setCellValue(setCellValue (question.getAreaName ()));
                        break;
                    case 4:
                        cell.setCellValue(setCellValue (question.getSmallName ()));
                        break;
                    case 5:
                        cell.setCellValue(setCellValue (question.getDepartName ()));
                        break;
                    case 6:
                        cell.setCellValue(setCellValue (question.getContent ()));
                        break;
                    case 7:
                        cell.setCellValue(setCellValue (question.getQuestionTime ()));
                        break;
                    case 8:
                        cell.setCellValue("");
                        break;
                    case 9:
                        cell.setCellValue("");
                        break;
                    case 10:
                        int status=question.getStatus ();
                        if(status==0){
                            cell.setCellValue("未回复");
                        }else if(status==1){
                            cell.setCellValue("已回复");
                        }else if(status==2){
                            cell.setCellValue("已关闭");
                        }
                        break;
                    case 11:
                        cell.setCellValue(setCellValue (question.getAnswerContent ()));
                        break;

                }
            }
        }
        return wb;
    }

    /**
     * The content type for an Excel response Excel文件类型
     */
    private static final String CONTENT_TYPE = "application/vnd.ms-excel";
    /**
     * The  content disposition for an Excel response
     */
    private static final String CONTENT_DISPOSITION = "Content-disposition";
    /**
     * The extension to look for existing templates
     */
    private static final String EXTENSION = FileUtil.EXCEL_EXTENSION;
    /**
     * attachment
     */
    private static final String ATTACHMENT = "attachment;filename=";
    /**
     * 参数名称
     */
    private static final String FILENAME = "fileName";



    public  static String setCellValue(Object value){
        if(value==null){
            return StringUtils.EMPTY;
        }
        if(value instanceof  String){
             String tempStr=String.valueOf (value);
             if(StringUtils.isEmpty (tempStr)){
                return StringUtils.EMPTY;
             }
            return tempStr;
        }

        if(value instanceof  Date){
           return  DateUtils.formatDate ((Date)value,DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);
        }


        String val=String.valueOf (value);
        if(StringUtils.isEmpty (val)){
            return StringUtils.EMPTY;
        }
        return val;
    }

    /***
     * 格式为：UUID+时分少毫秒+后缀名
     * @param extension 后缀名
     * @return 文件名
     */
    protected static String createFileName(String extension){
        StringBuilder sb=new StringBuilder();
        sb.append(UUID.randomUUID().toString().replace("-","").toUpperCase());
        sb.append(DateUtils.formatDate(new Date (), DateUtils.FORMAT_TIME_HHMMSS_SSS));
        sb.append(extension);
        return sb.toString();
    }

    public static void writer(Pagination<DtQuestion> page, HttpServletResponse response) {

        String fullFileName=createFileName(FileUtil.EXCEL_EXTENSION);
        StringBuilder sb=new StringBuilder(ATTACHMENT);
        sb.append(fullFileName);
        response.setContentType (CONTENT_TYPE);
        response.setHeader (CONTENT_DISPOSITION,sb.toString ());
        OutputStream out=null;
        try {
            out = response.getOutputStream ();
            question (page).write (out);
        } catch (IOException e) {
            e.printStackTrace ();
        }finally{
            IOUtils.closeQuietly(out);
        }

    }

    public static void writerUserAuthList(Pagination<UserAuthList> page, HttpServletResponse response) {

        String fullFileName=createFileName(FileUtil.EXCEL_EXTENSION);
        StringBuilder sb=new StringBuilder(ATTACHMENT);
        sb.append(fullFileName);
        response.setContentType (CONTENT_TYPE);
        response.setHeader (CONTENT_DISPOSITION,sb.toString ());
        OutputStream out=null;
        try {
            out = response.getOutputStream ();
            buildUserAuthList (page).write (out);
        } catch (IOException e) {
            e.printStackTrace ();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }


    public static HSSFWorkbook buildUserAuthList(Pagination<UserAuthList> page) {
        String[] header={"用户名","用户姓名","角色名称","资源名称","路径","顺序","备注"};
        HSSFWorkbook wb = new HSSFWorkbook(); // create new workbook object
        HSSFSheet sheet = wb.createSheet(); // create new sheet for workbook
        HSSFRow row;
        HSSFCell cell;
        row = sheet.createRow(0);
        for(int i=0;i<header.length;i++){
            cell=row.createCell((short)i);
            cell.setCellValue(header[i]);
        }
        for(int i=0;i<page.getData ().size ();i++){
            row = sheet.createRow((short) i + 1); // create new row for
            UserAuthList vo =page.getData ().get (i);
            for(int j=0;j<header.length;j++){
                cell =row.createCell((short) (j));
                switch ( j ){
                    case 0:
                        cell.setCellValue(vo.getUserName ());
                        break;
                    case 1:
                        cell.setCellValue(setCellValue (vo.getRealName ()));
                        break;
                    case 2:
                        cell.setCellValue(setCellValue (vo.getRoleName ()));
                        break;
                    case 3:
                        cell.setCellValue(setCellValue (vo.getFunctionName ()));
                        break;
                    case 4:
                        cell.setCellValue(setCellValue (vo.getLinkUrl ()));
                        break;
                    case 5:
                        cell.setCellValue(setCellValue (vo.getPriority ()));
                        break;
                    case 6:
                        cell.setCellValue(setCellValue (vo.getRemark ()));
                        break;

                }
            }
        }
        return wb;
    }


    public static void writerLog(Pagination<OperationLog> page, HttpServletResponse response) {

        String fullFileName=createFileName(FileUtil.EXCEL_EXTENSION);
        StringBuilder sb=new StringBuilder(ATTACHMENT);
        sb.append(fullFileName);
        response.setContentType (CONTENT_TYPE);
        response.setHeader (CONTENT_DISPOSITION,sb.toString ());
        OutputStream out=null;
        try {
            out = response.getOutputStream ();
            buildOperationLog (page).write (out);
        } catch (IOException e) {
            e.printStackTrace ();
        }finally{
            IOUtils.closeQuietly(out);
        }
    }


    public static HSSFWorkbook buildOperationLog(Pagination<OperationLog> page) {
        String[] header={"用户名","操作类型","操作内容","操作日期"};
        HSSFWorkbook wb = new HSSFWorkbook(); // create new workbook object
        HSSFSheet sheet = wb.createSheet(); // create new sheet for workbook
        HSSFRow row;
        HSSFCell cell;
        row = sheet.createRow(0);
        for(int i=0;i<header.length;i++){
            cell=row.createCell((short)i);
            cell.setCellValue(header[i]);
        }
        for(int i=0;i<page.getData ().size ();i++){
            row = sheet.createRow((short) i + 1); // create new row for
            OperationLog vo =page.getData ().get (i);
            for(int j=0;j<header.length;j++){
                cell =row.createCell((short) (j));
                switch ( j ){
                    case 0:
                        cell.setCellValue(vo.getUserName ());
                        break;

                    case 1:
                        cell.setCellValue(setCellValue (vo.getOperationType ().getName ()));
                        break;
                    case 2:
                        cell.setCellValue(setCellValue (vo.getOperationContent ()));
                        break;
                    case 3:
                        cell.setCellValue(setCellValue (vo.getOperationDate ()));
                        break;


                }
            }
        }
        return wb;
    }
}

package com.fute.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.biff.FontRecord;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Font;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.fute.reserve.util.Constants;

public class ExcelUtil {
	// 导出excel
	public static void exportExcel(String title, String[] headers,
			Collection<Map<String, Object>> dataset, OutputStream out,
			String pattern) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth((short) 15);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
				0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("ape-tech");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<Map<String, Object>> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Map<String, Object> t = (Map<String, Object>) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				// fieldName 属性名 如果对属性需要处理的话.用这个区分***我用性别做处理吧
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,
							new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					if (fieldName.equals("age")) {
						int num = (Integer) value;
					}
					String textValue = null;
					// 有boolean类型时
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
								1023, 255, (short) 6, index, (short) 6, index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(
								bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串处理
						textValue = value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^\\d+(\\.\\d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}

				} catch (SecurityException e) {
					e.printStackTrace();
					e = null;
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					e = null;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					e = null;
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					e = null;
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					e = null;
				} finally {
				}
			}
		}

		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
			e = null;
		}
	}

	/**
	 * 
	 * @param map
	 * @param listMap
	 * @param titleMap
	 * @return
	 */
	public String downloadExcel(Map<String, String> map,
			List<Map<String, Object>> listMap, Map<String, Object> titleMap) {
		// TODO Auto-generated method stub
		try {

			String filePath = Constants.PATH_CLASS_ROOT.replace(
					"WEB-INF/classes/", "")
					+ "/upload/info.xls";
			String biaoti = map.get("yuefen").replace("-", "年") + "月";
			WritableWorkbook workbook = null;
			OutputStream os = null;
			os = new FileOutputStream(filePath);
			workbook = Workbook.createWorkbook(os);
			WritableSheet writsheet = workbook.createSheet("薪酬数据", 0);
			// 设置单元格的格式
			// 主标题设置
			WritableFont main_font = new WritableFont(WritableFont
					.createFont(" 黑体 "), 15, WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat main_title = new WritableCellFormat();
			main_title.setAlignment(Alignment.CENTRE);// 水平对齐
			main_title.setBackground(Colour.WHITE);
			main_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			main_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			main_title.setWrap(true);
			main_title.setFont(main_font);
			// 表头的设置
			WritableFont max_font = new WritableFont(WritableFont
					.createFont(" 黑体 "), 13, WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat max_title = new WritableCellFormat();
			max_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			max_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			max_title.setBackground(Colour.GRAY_25);
			max_title.setWrap(true);
			max_title.setAlignment(Alignment.CENTRE);
			max_title.setFont(max_font);
			// 内容的设置
			WritableFont count_font = new WritableFont(WritableFont
					.createFont(" 黑体 "), 12, WritableFont.NO_BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat count_title = new WritableCellFormat();
			count_title.setVerticalAlignment(VerticalAlignment.CENTRE);
			count_title.setBorder(Border.ALL, BorderLineStyle.THIN);
			count_title.setWrap(true);
			count_title.setAlignment(Alignment.LEFT);
			count_title.setFont(count_font);
			// 报表的名称
			// writsheet.mergeCells(0,1,title_info.length-1,0);// 合并标题行
			writsheet.addCell(new jxl.write.Label(0, 0, biaoti, main_title));// 标题行值

			int n = 0;
			for (String key : titleMap.keySet()) {
				writsheet.addCell(new jxl.write.Label(n, 2, String
						.valueOf(titleMap.get(key)), max_title));
				writsheet.setColumnView(n, titleMap.size());
			}

			int j = 1;
			for (Map<String, Object> inMap : listMap) {
				int z = 0;
				for (String key : inMap.keySet()) {
					writsheet.addCell(new jxl.write.Label(z, j + 2, String
							.valueOf(inMap.get(key)), count_title));
					z++;
				}
				j++;
			}
			// 写入数据并关闭文件
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

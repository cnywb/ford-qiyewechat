package com.fute.common.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class JFreeChartUtil {
	public static void main(String[] args) {

	}

	public static CategoryDataset getCategoryDataSet() {
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.setValue(30, "", "市场人员");
		categoryDataset.setValue(30, "", "开发管理人员");
		categoryDataset.setValue(30, "", "成员");
		categoryDataset.setValue(30, "", "人事");
		return categoryDataset;

	}

	public static PieDataset getPieDataset() {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("开发人员", 25);
		pieDataset.setValue("管理人员", 40);
		pieDataset.setValue("市场人员", 30);
		pieDataset.setValue("开发管理人员", 50);
		pieDataset.setValue("成员", 60);
		pieDataset.setValue("人事", 20);
		return pieDataset;
	}
}

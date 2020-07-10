package xyz.rexlin600.easy.excel.simple;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import xyz.rexlin600.easy.excel.util.ExcelFilePathUtil;

import java.io.File;
import java.util.List;

/**
 * Simple easy excel util
 *
 * @author hekunlin
 */
public class SimpleEasyExcelUtil {

	/**
	 * Read excel list
	 *
	 * @param filePath file path
	 * @return the list
	 */
	public static List<SimpleData> readExcel(String filePath) {
		SimpleExcelListener simpleExcelListener = new SimpleExcelListener();

		ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(filePath, SimpleData.class, simpleExcelListener);

		excelReaderBuilder.sheet().doRead();

		List<SimpleData> list = simpleExcelListener.getList();

		System.out.println(list.toString());
		return list;
	}

	/**
	 * Main
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		String filePath = ExcelFilePathUtil.getPath() + "demo" + File.separator + "demo.xlsx";
		readExcel(filePath);
	}

}
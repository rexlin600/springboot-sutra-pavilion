package xyz.rexlin600.easy.excel.restful.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.AbstractVerticalCellStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.easy.excel.biz.data.EmployeesData;
import xyz.rexlin600.easy.excel.restful.mapper.EmployeesMapper;
import xyz.rexlin600.easy.excel.restful.model.EmployeesDO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Simple Read Rest
 *
 * @author hekunlin
 * @since 2020 /8/6
 */
@SuppressWarnings("rawtypes")
@Slf4j
@RestController
@RequestMapping(value = "/simple/export")
public class SimpleExportRest {

	AbstractVerticalCellStyleStrategy verticalCellStyleStrategy = new AbstractVerticalCellStyleStrategy() {
		@Override
		protected WriteCellStyle headCellStyle(Head head) {
			WriteCellStyle writeCellStyle = new WriteCellStyle();
			writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
			writeCellStyle.setDataFormat((short) 0);
			writeCellStyle.setHidden(false);
			writeCellStyle.setLocked(true);
			writeCellStyle.setQuotePrefix(true);
			writeCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
			writeCellStyle.setWrapped(true);
			writeCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			writeCellStyle.setRotation((short) 0);
			writeCellStyle.setIndent((short) 10);
			writeCellStyle.setBorderLeft(BorderStyle.THIN);
			writeCellStyle.setBorderRight(BorderStyle.THIN);
			writeCellStyle.setBorderTop(BorderStyle.THIN);
			writeCellStyle.setBorderBottom(BorderStyle.THIN);
			writeCellStyle.setLeftBorderColor(IndexedColors.RED.getIndex());
			writeCellStyle.setRightBorderColor(IndexedColors.RED.getIndex());
			writeCellStyle.setTopBorderColor(IndexedColors.RED.getIndex());
			writeCellStyle.setBottomBorderColor(IndexedColors.RED.getIndex());
			writeCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
			writeCellStyle.setShrinkToFit(Boolean.TRUE);

			if (head.getColumnIndex() == 0) {
				writeCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
				WriteFont writeFont = new WriteFont();
				writeFont.setItalic(true);
				writeFont.setStrikeout(true);
				writeFont.setTypeOffset(Font.SS_NONE);
				writeFont.setUnderline(Font.U_DOUBLE);
				writeFont.setBold(true);
				writeFont.setCharset((int) Font.DEFAULT_CHARSET);
			} else {
				writeCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
			}
			return writeCellStyle;
		}

		@Override
		protected WriteCellStyle contentCellStyle(Head head) {
			WriteCellStyle writeCellStyle = new WriteCellStyle();
			writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
			if (head.getColumnIndex() == 0) {
				writeCellStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
			} else {
				writeCellStyle.setFillForegroundColor(IndexedColors.PINK.getIndex());
			}
			return writeCellStyle;
		}
	};
	@Resource
	private EmployeesMapper employeesMapper;

	/**
	 * 简单读
	 *
	 * @return the r
	 */
	@SneakyThrows
	@GetMapping("/export")
	public R simpleRead(HttpServletResponse response) {
		// 查询出 List
		LambdaQueryWrapper<EmployeesDO> queryWrapper = new LambdaQueryWrapper<>();
		List<EmployeesDO> list = employeesMapper.selectList(queryWrapper);

		// write
		OutputStream out = null;
		String filename = "雇员列表-" + System.currentTimeMillis() + ".xlsx";
		try {
			// 导出文件名称
			filename = URLEncoder.encode(filename, "UTF-8");
			response.setContentType("application/force-download;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			response.setHeader("filename", filename);
			response.setHeader("Access-Control-Expose-Headers", "filename");
			response.setCharacterEncoding("utf8");
			out = response.getOutputStream();
		} catch (IOException e) {
			log.error("==>  导出异常 {}", e.getMessage());
			return R.failed("导出异常");
		}

		if (CollectionUtil.isNotEmpty(list)) {
			ExcelWriter excelWriter = null;
			try {
				excelWriter = EasyExcelFactory.write(out, EmployeesData.class)
						.registerWriteHandler(verticalCellStyleStrategy).build();
				WriteSheet sheet = EasyExcelFactory.writerSheet().build();
				excelWriter.write(list, sheet);
			} finally {
				// 千万别忘记finish 会帮忙关闭流
				if (excelWriter != null) {
					excelWriter.finish();
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						log.error("==>  导出异常 {}", e.getMessage());
					}
				}
			}

		}

		return R.ok(null);
	}


}
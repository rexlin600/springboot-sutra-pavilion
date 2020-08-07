package xyz.rexlin600.easy.excel.restful.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.rexlin600.easy.excel.biz.data.EmployeesData;
import xyz.rexlin600.easy.excel.restful.mapper.EmployeesMapper;
import xyz.rexlin600.easy.excel.restful.model.EmployeesDO;
import xyz.rexlin600.easy.excel.util.TimerContext;

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
@Slf4j
@RestController
@RequestMapping(value = "/excel/export")
public class ExportExcelRest {

	/**
	 * Employees mapper
	 */
	@Resource
	private EmployeesMapper employeesMapper;

	/**
	 * 浏览器导出
	 *
	 * @param response response
	 */
	@SneakyThrows
	@GetMapping("/browserExport")
	public void browserExport(HttpServletResponse response) {
		TimerContext.start("EXCEL_30W数据导出");

		// 查询出 List
		LambdaQueryWrapper<EmployeesDO> queryWrapper = new LambdaQueryWrapper<>();
		List<EmployeesDO> list = employeesMapper.selectList(queryWrapper);

		// write
		String filename = "雇员列表-" + System.currentTimeMillis() + ".xlsx";
		OutputStream out = null;
		try {
			out = getOutputStream(filename, response);
		} catch (Exception ex) {
			log.error("==>  导出失败：{}", ex.getMessage());
			return;
		}

		if (CollectionUtil.isNotEmpty(list)) {
			ExcelWriter excelWriter = null;
			try {
				excelWriter = EasyExcelFactory.write(out, EmployeesData.class).build();
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
				TimerContext.end(log, "INFO");
			}
		}

	}


	/**
	 * Gets output stream
	 *
	 * @param filename filename
	 * @param response response
	 * @return the output stream
	 * @throws IOException io exception
	 */
	private OutputStream getOutputStream(String filename, HttpServletResponse response) throws IOException {
		OutputStream out = null;
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
			throw new IOException(e.getMessage());
		}

		return out;
	}


}
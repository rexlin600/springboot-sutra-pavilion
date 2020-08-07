package xyz.rexlin600.easy.excel.restful.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.api.R;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.rexlin600.easy.excel.biz.data.SimpleData;
import xyz.rexlin600.easy.excel.biz.listener.SimpleDataListener;
import xyz.rexlin600.easy.excel.util.ExcelFilePathUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
@RequestMapping(value = "/excel/read")
public class ExcelReadRest {

	/**
	 * 简单读
	 *
	 * @param multipartFile multipart file
	 * @return the r
	 */
	@GetMapping("/simpleRead")
	public R simpleRead(@RequestPart MultipartFile multipartFile) {
		// 读取 file
		File file = ExcelFilePathUtil.readFile("simple" + File.separator + "simple07.xlsx");

		SimpleDataListener listener = new SimpleDataListener();
		EasyExcel.read(file, SimpleData.class, listener)
				.sheet() // 指定要读取的 sheet
				.doRead(); // 简单read

		// 获取读取的数据
		List<SimpleData> list = listener.getList();

		return R.ok(list);
	}

	/**
	 * 同步简单读
	 *
	 * @return the r
	 */
	@GetMapping("/syncRead")
	public R syncReadSimple() {
		// 读取 file
		File file = ExcelFilePathUtil.readFile("simple" + File.separator + "simple07.xlsx");

		SimpleDataListener listener = new SimpleDataListener();
		EasyExcel.read(file, SimpleData.class, listener)
				.sheet() // 指定要读取的 sheet
				.doReadSync(); // 同步read

		// 获取读取的数据
		List<SimpleData> list = listener.getList();

		return R.ok(list);
	}

	/**
	 * 读取 Excel 文件
	 *
	 * <p>上传文件，从文件读取内容后进行相应的业务逻辑处理
	 *
	 * @param file file
	 * @return the r
	 */
	@SneakyThrows
	@PostMapping("/readFile")
	public R readFile(@RequestPart(value = "file") MultipartFile file) {
		// check file：size、type etc

		InputStream inputStream = file.getInputStream();

		SimpleDataListener listener = new SimpleDataListener();
		EasyExcel.read(inputStream, SimpleData.class, listener)
				.sheet() // 指定要读取的 sheet
				.doReadSync(); // 同步read

		// 获取读取的数据
		List<SimpleData> list = listener.getList();

		// 其他业务逻辑 ...

		return R.ok(list);
	}


	// -----------------------------------------------------------------------------------------------
	// OTHER READ METHOD
	//
	// EasyExcel 提供了相当丰富的 API，下面就平时常用的 API 做一些简单说明
	// -----------------------------------------------------------------------------------------------

	/**
	 * Other read.
	 */
	@SneakyThrows
	@Ignore
	public void otherRead() {
		//-------------------------------------------- 基本数据 ----------------------------------------------------
		File file = ExcelFilePathUtil.readFile("simple" + File.separator + "simple07.xlsx");
		String pathName = ExcelFilePathUtil.getPath() + "simple" + File.separator + "simple07.xlsx";
		InputStream inputStream = new FileInputStream(file);

		//---------------------------------------- 各种形式的 read -------------------------------------------------

		// 1. 直接读文件
		EasyExcel.read(file);
		// 2. 读文件并将 cell 转换为对应类属性，使用 listener 接收
		EasyExcel.read(file, SimpleData.class, new SimpleDataListener());
		// 3. 从指定文件路径读取文件
		EasyExcel.read(pathName);
		// 4. 从文件流读取
		EasyExcel.read(inputStream);
		// 5. 从文件读取并使用 listener 接收
		EasyExcel.read(file, new SimpleDataListener());

		// 其他 ...

		//---------------------------------------- read 指定 head、sheet -------------------------------------------------

		// 指定简单 head
		EasyExcel.read(file)
				.head(SimpleData.class)
				.sheet("sheet名称");

		List<List<SimpleData>> list = new ArrayList<>();
		// 指定复杂 head
		EasyExcel.read(file)
				.head(list.getClass())
				.sheet("sheet名称");

		// 1. 指定 sheet 名称读取
		EasyExcel.read(file)
				.sheet("sheet名称");
		// 2. 指定 sheet 编号读取
		EasyExcel.read(file)
				.sheet(0);
		// 3. 指定 sheet 编号 + 名称读取
		EasyExcel.read(file)
				.sheet(1, "sheet名称");

		//---------------------------------------- read 指定 sheet -------------------------------------------------

		// 读取带密码的 excel
		EasyExcel.read(file).password("123456");
		// 指定 Excel 格式
		EasyExcel.read(file).excelType(ExcelTypeEnum.XLSX);
		// 异步读取所有 sheet
		EasyExcel.read(file).doReadAll();
		// 同步读取所有 sheet
		EasyExcel.read(file).doReadAllSync();

	}


}
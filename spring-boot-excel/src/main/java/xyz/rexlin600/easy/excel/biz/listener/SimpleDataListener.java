package xyz.rexlin600.easy.excel.biz.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import xyz.rexlin600.easy.excel.biz.data.SimpleData;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听器
 *
 * @author hekunlin
 * @since 2020/8/6
 */
@Slf4j
public class SimpleDataListener extends AnalysisEventListener<SimpleData> {

	/**
	 * 读取数据的列表
	 */
	public List<SimpleData> list = new ArrayList<SimpleData>();

	@Override
	public void invoke(SimpleData simpleData, AnalysisContext analysisContext) {
		log.info("==>  read simple data begin at {}", System.currentTimeMillis());
		list.add(simpleData);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		log.info("==>  read simple data list size is {} at {}", list.size(), System.currentTimeMillis());
	}

}
package xyz.rexlin600.easy.excel.simple;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


/**
 * Simple excel listener
 *
 * @author hekunlin
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleExcelListener extends AnalysisEventListener<SimpleData> {

	/**
	 * BATCH_COUNT
	 */
	private static final int BATCH_COUNT = 3000;
	/**
	 * List
	 */
	private List<SimpleData> list = new ArrayList<>();

	/**
	 * Invoke *
	 *
	 * @param simpleData      simple data
	 * @param analysisContext analysis context
	 */
	@Override
	public void invoke(SimpleData simpleData, AnalysisContext analysisContext) {
		list.add(simpleData);

		if (list.size() >= BATCH_COUNT) {
			list.clear();
		}

	}

	/**
	 * Do after all analysed *
	 *
	 * @param analysisContext analysis context
	 */
	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		log.info("==>  do after all analysed ...");
	}

}
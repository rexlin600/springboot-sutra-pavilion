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
 * Excel 监听类
 *
 * @author hekunlin
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleExcelListener extends AnalysisEventListener<SimpleData> {

    private static final int BATCH_COUNT = 3000;
    private List<SimpleData> list = new ArrayList<>();

    /**
     * 调用方法
     *
     * @param simpleData      简单日期
     * @param analysisContext 分析上下文类
     */
    @Override
    public void invoke(SimpleData simpleData, AnalysisContext analysisContext) {
        list.add(simpleData);

        if (list.size() >= BATCH_COUNT) {
            list.clear();
        }

    }

    /**
     * if have something to do after all analysis
     *
     * @param analysisContext 分析上下文类
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("==>  do after all analysed ...");
    }

}
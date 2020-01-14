package xyz.rexlin600.mybatisplus.codegen.rest;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;
import xyz.rexlin600.mybatisplus.codegen.service.CodeService;

import java.util.Arrays;
import java.util.List;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-14 19:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeGenerateRestTest {

    @Autowired
    private CodeService codeService;

    @Test
    public void getPage() {
        R<Page<TableMetaData>> page = codeService.page(1, 10, null, 1L);
        List<TableMetaData> records = page.getData().getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            records.stream().forEach(m -> {
                log.info(m.toString());
            });
        }
    }

    @Test
    public void generate() {
        CodeGenReq build = CodeGenReq.builder()
                .id(1L)
                .packageName("xyz.rexlin600.test")
                .author("rexlin600")
                .tablePrefix("tb_")
                .list(Arrays.asList("tb_flyway"))
                .build();
        codeService.generate(build);
    }
}
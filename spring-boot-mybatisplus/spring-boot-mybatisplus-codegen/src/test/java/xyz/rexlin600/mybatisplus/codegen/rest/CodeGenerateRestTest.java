package xyz.rexlin600.mybatisplus.codegen.rest;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.SneakyThrows;
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

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeGenerateRestTest {

    @Autowired
    private CodeService codeService;

    @SneakyThrows
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
                .entityPath("xyz.rexlin600.test")
                .mapperPath("xyz.rexlin600.test")
                .svcPath("xyz.rexlin600.test")
                .restPath("xyz.rexlin600.test")
                .xmlPath("xyz.rexlin600.test")
                .author("rexlin600")
                .prefix("tb_")
                .list(Arrays.asList("tb_flyway"))
                .build();
        codeService.generate(build);
    }
}
package xyz.rexlin600.mybatisplus.codegen.service.impl;

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
public class CodeServiceImplTest {

    @Autowired
    private CodeService codeService;

    @SneakyThrows
    @Test
    public void page() {
        R<Page<TableMetaData>> page = codeService.page(1, 10, null, 2L);
        Page<TableMetaData> rs = page.getData();
        log.info("==>  search assign database's table page {}", rs.getCurrent());
        log.info("==>  search assign database's table size {}", rs.getSize());
        log.info("==>  search assign database's table total {}", rs.getTotal());

        List<TableMetaData> records = rs.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            log.info("==>  search assign database's table records: ");
            records.stream().forEach(m -> {
                log.info(m.toString());
            });
        }
    }

    @Test
    public void generate() {
        CodeGenReq codeGenReq = CodeGenReq.builder()
                .id(2L)
                .author("hekunlin")
                .entityPath("xyz.rexlin600.test")
                .mapperPath("xyz.rexlin600.test")
                .svcPath("xyz.rexlin600.test")
                .restPath("xyz.rexlin600.test")
                .xmlPath("xyz.rexlin600.test")
                .list(Arrays.asList("brand_account"))
                .build();

        R r = codeService.generate(codeGenReq);
        log.info("==>  生成代码结果 {}", r.getMsg());
    }
}
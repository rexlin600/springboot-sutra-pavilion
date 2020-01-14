package xyz.rexlin600.mybatisplus.codegen.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;
import xyz.rexlin600.mybatisplus.codegen.service.CodeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-01-14 15:04
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeServiceImplTest {

    @Autowired
    private CodeService codeService;

    @Test
    public void page() {
        R<Page<TableMetaData>> page = codeService.page(new Page(1, 10), null, 2L);
        Page<TableMetaData> rs = page.getData();
        log.info("==>  search assign database's table page {}", rs.getCurrent());
        log.info("==>  search assign database's table size {}", rs.getSize());
        log.info("==>  search assign database's table total {}", rs.getTotal());

        List<TableMetaData> records = rs.getRecords();
        log.info("==>  search assign database's table records: ");
        records.stream().forEach(m -> {
            log.info(m.toString());
        });

    }

    @Test
    public void generate() {
        CodeGenReq codeGenReq = CodeGenReq.builder()
                .id(2L)
                .author("rexlin600")
                .packageName("xyz.rexlin600.test")
                .list(Arrays.asList("test_user"))
                .build();

        codeService.generate(codeGenReq);
    }
}
package xyz.rexlin600.mybatisplus.codegen.rest;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;
import xyz.rexlin600.mybatisplus.codegen.service.CodeService;

import java.util.List;

/**
 * 代码生成 API接口
 *
 * @author rexlin600
 */
@RestController
@RequestMapping("/code")
@Validated
public class CodeRest {

    private final CodeService codeService;

    @Autowired
    public CodeRest(CodeService codeService) {
        this.codeService = codeService;
    }

    /**
     * 查询指定数据源的表 列表
     *
     * @param page
     * @param tableName
     * @param id
     * @return
     */
    @GetMapping("/page")
    public R<Page<TableMetaData>> getPage(Page page, String tableName, Long id) {
        return codeService.page(page, tableName, id);
    }


    /**
     * 生成代码
     *
     * @param codeGenReq
     * @return
     */
    @PostMapping("/generate")
    public R generate(@Validated @RequestBody CodeGenReq codeGenReq) {
        return codeService.generate(codeGenReq);
    }

}

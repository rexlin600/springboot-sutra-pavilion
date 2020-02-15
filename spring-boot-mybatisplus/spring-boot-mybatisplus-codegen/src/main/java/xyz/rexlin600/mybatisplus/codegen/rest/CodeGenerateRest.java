package xyz.rexlin600.mybatisplus.codegen.rest;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.rexlin600.mybatisplus.codegen.common.req.CodeGenReq;
import xyz.rexlin600.mybatisplus.codegen.entity.TableMetaData;
import xyz.rexlin600.mybatisplus.codegen.service.CodeService;

import java.sql.SQLException;

/**
 * @author rexlin600
 * @menu 代码生成-分页与生成
 */
@RestController
@RequestMapping("/code")
@Validated
public class CodeGenerateRest {

    private final CodeService codeService;

    @Autowired
    public CodeGenerateRest(CodeService codeService) {
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
    public R<Page<TableMetaData>> getPage(@RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "size") Integer size,
                                          @RequestParam(value = "tableName", required = false) String tableName,
                                          @RequestParam(value = "id") Long id) {
        try {
            return codeService.page(page, size, tableName, id);
        } catch (SQLException e) {
            return R.failed(e.getMessage());
        }
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

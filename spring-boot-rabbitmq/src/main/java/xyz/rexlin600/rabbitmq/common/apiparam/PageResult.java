package xyz.rexlin600.rabbitmq.common.apiparam;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author: hekunlin
 * @date: 2019/12/11
 */
@Data
@Accessors(chain = true)
public class PageResult<T> {

    private long totalCount;
    private long totalPage;
    private Integer page;
    private Integer size;
    private List<T> list;

    public PageResult() {
    }

    /**
     * @param totalCount 总条数
     * @param page       第几页
     * @param size       每页几条记录
     */
    public PageResult(@NonNull Integer totalCount, @NonNull Integer page, @NonNull Integer size, List<T> list) {
        if (size == 0) {
            throw new RuntimeException("size不能为0");
        }
        this.totalCount = totalCount;
        this.page = page;
        this.size = size;
        this.totalPage = totalCount / size;
        if (totalCount % size != 0) {
            this.totalPage += 1;
        }
        this.list = list;
    }

    public PageResult(PageInfo<T> pageInfo) {
        this.totalCount = pageInfo.getTotal();
        this.totalPage = pageInfo.getPages();
        this.page = pageInfo.getPageNum();
        this.size = pageInfo.getPageSize();
        this.list = pageInfo.getList();
    }

    public <R> PageResult<R> convert(List<R> list) {
        PageResult<R> result = new PageResult<>();

        // 属性拷贝
        result.totalCount = this.totalCount;
        result.totalPage = this.totalPage;
        result.page = this.page;
        result.size = this.size;
        // 列表赋值
        result.list = list;

        return result;
    }
}
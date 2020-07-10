package xyz.rexlin600.mybatisplus.crud.common.apiparam;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Page result
 *
 * @param <T> parameter
 * @author hekunlin
 */
@Data
@Accessors(chain = true)
public class PageResult<T> {

	/**
	 * Total count
	 */
	private long totalCount;
	/**
	 * Total page
	 */
	private long totalPage;
	/**
	 * Page
	 */
	private Integer page;
	/**
	 * Size
	 */
	private Integer size;
	/**
	 * List
	 */
	private List<T> list;

	/**
	 * Page result
	 */
	public PageResult() {
	}

	/**
	 * Page result
	 *
	 * @param totalCount total count
	 * @param page       page
	 * @param size       size
	 * @param list       list
	 * @throws RuntimeException runtime exception
	 */
	public PageResult(@NonNull Integer totalCount, @NonNull Integer page, @NonNull Integer size, List<T> list) throws RuntimeException {
		if (size == 0) {
			throw new InvalidParameterException("size不能为0");
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

	/**
	 * Page result
	 *
	 * @param pageInfo page info
	 */
	public PageResult(PageInfo<T> pageInfo) {
		this.totalCount = pageInfo.getTotal();
		this.totalPage = pageInfo.getPages();
		this.page = pageInfo.getPageNum();
		this.size = pageInfo.getPageSize();
		this.list = pageInfo.getList();
	}

	/**
	 * Convert page result
	 *
	 * @param <R>  parameter
	 * @param list list
	 * @return the page result
	 */
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
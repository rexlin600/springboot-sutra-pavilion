package xyz.rexlin600.mybatisplus.codegen.common.req;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Code gen req
 *
 * @author hekunlin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CodeGenReq implements Serializable {

	/**
	 * Id
	 */
	@NotNull
	private Long id;

	/**
	 * Author
	 */
	@Builder.Default
	private String author = "rexlin600";

	/**
	 * Open api doc
	 */
	@Builder.Default
	private Boolean openApiDoc = false;

	// -----------------------------------------------------------------------------------------------
	// 各层代码位置
	// -----------------------------------------------------------------------------------------------

	/**
	 * Entity path
	 */
	@NotBlank
	private String entityPath;

	/**
	 * Mapper path
	 */
	@NotBlank
	private String mapperPath;

	/**
	 * Svc path
	 */
	@NotBlank
	private String svcPath;

	/**
	 * Svc impl path
	 */
	@NotBlank
	private String svcImplPath;

	/**
	 * Rest path
	 */
	@NotBlank
	private String restPath;

	/**
	 * Xml path
	 */
	@NotBlank
	private String xmlPath;

	// -----------------------------------------------------------------------------------------------
	// 其余配置
	// -----------------------------------------------------------------------------------------------

	/**
	 * Prefix
	 */
	private String prefix;

	/**
	 * Version column
	 */
	private String versionColumn;

	/**
	 * Lombok
	 */
	@Builder.Default
	private boolean lombok = true;

	/**
	 * Logic column
	 */
	private String logicColumn;

	// -----------------------------------------------------------------------------------------------
	// 要生成的表 LIST
	// -----------------------------------------------------------------------------------------------

	/**
	 * List
	 */
	@NotEmpty
	private List<String> list;


}
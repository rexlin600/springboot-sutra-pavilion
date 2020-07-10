package xyz.rexlin600.github.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Starred resp
 *
 * @author hekunlin
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StarredResp implements Serializable {

	/**
	 * Full name
	 */
	private String fullName;

	/**
	 * Name
	 */
	private String name;

	/**
	 * Author
	 */
	private String author;

	/**
	 * Htmlurl
	 */
	private String htmlurl;

	/**
	 * Description
	 */
	private String description;

	/**
	 * Create at
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createAt;

	/**
	 * Update at
	 */
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateAt;

	/**
	 * Clone url
	 */
	private String cloneUrl;

	/**
	 * Home page
	 */
	private String homePage;

	/**
	 * Size
	 */
	private Long size;

	/**
	 * Stargazers count
	 */
	private Long stargazersCount;

	/**
	 * Watchers count
	 */
	private Long watchersCount;

	/**
	 * Forks count
	 */
	private Long forksCount;

	/**
	 * Language
	 */
	private String language;

	/**
	 * License
	 */
	private String license;

	/**
	 * Default branch
	 */
	private String defaultBranch;

}
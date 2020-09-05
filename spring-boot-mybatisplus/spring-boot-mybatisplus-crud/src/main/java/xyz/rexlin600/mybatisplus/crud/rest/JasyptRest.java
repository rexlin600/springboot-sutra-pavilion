package xyz.rexlin600.mybatisplus.crud.rest;

import com.baomidou.mybatisplus.extension.api.R;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test
 *
 * @author hekunlin
 * @since 2020 /8/12
 */
@RestController
@RequestMapping("/jasypt")
public class JasyptRest {

	/**
	 * Encryptor
	 */
	private final StringEncryptor encryptor;

	/**
	 * Jasypt rest
	 *
	 * @param encryptor encryptor
	 */
	@Autowired
	public JasyptRest(StringEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	/**
	 * Main 测试方法：上面的接口和下面的测试方法加密、解密生成的 str 是一样的
	 *
	 * @param args args
	 */
	public static void main(String[] args) {
		// 加密工具
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		// 加密配置
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setPassword("rexlin600");
		config.setKeyObtentionIterations(1000);
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setProviderName(null);
		config.setProviderClassName(null);
		config.setStringOutputType("base64");
		encryptor.setConfig(config);

		//加密 oqvgamdolawgdgdc
		String encrypt = encryptor.encrypt("oqvgamdolawgdgdc@");

		System.out.println(encrypt);

		String decrypt = encryptor.decrypt(encrypt);
		System.out.println(decrypt);
	}

	/**
	 * Encrypt r
	 *
	 * @param content content
	 * @return the r
	 */
	@GetMapping("/encrypt")
	public R<String> encrypt(@RequestParam(value = "content") String content) {
		String encrypt = encryptor.encrypt(content);
		return R.ok(encrypt);
	}

	// -----------------------------------------------------------------------------------------------
	// EXTRA MAIN METHOD
	// -----------------------------------------------------------------------------------------------

	/**
	 * Decrypt r
	 *
	 * @param content content
	 * @return the r
	 */
	@GetMapping("/decrypt")
	public R<String> decrypt(@RequestParam(value = "content") String content) {
		String decrypt = encryptor.decrypt(content);
		return R.ok(decrypt);
	}

}
package xyz.rexlin600.jaxb.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * Jaxb util
 *
 * @param <T> parameter
 * @author hekunlin
 */
@Slf4j
public class JaxbUtil<T> {

	/**
	 * DEFAULT_PREFIX
	 */
	public final static String DEFAULT_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

	/**
	 * Java 2 xml string
	 *
	 * @param <T>      parameter
	 * @param t        t
	 * @param encoding encoding
	 * @return the string
	 */
	@SneakyThrows(value = {JAXBException.class, IOException.class})
	public static <T> String java2Xml(T t, String encoding) {
		Marshaller marshaller = createMarshaller(t, encoding);

		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(t, stringWriter);
		String result = stringWriter.toString();
		stringWriter.close();

		return result;
	}

	/**
	 * Xml 2 java t
	 *
	 * @param <T> parameter
	 * @param t   t
	 * @param xml xml
	 * @return the t
	 */
	@SneakyThrows(value = {JAXBException.class})
	public static <T> T xml2Java(Class<T> t, String xml) {
		Unmarshaller unmarshaller = createUnmarshaller(t);

		StringReader stringReader = new StringReader(xml);
		T result = (T) unmarshaller.unmarshal(new StreamSource(stringReader));
		stringReader.close();

		return result;
	}

	// -----------------------------------------------------------------------------------------------
	// 构建 Marshaller、Unmarshaller
	// -----------------------------------------------------------------------------------------------

	/**
	 * Create marshaller marshaller
	 *
	 * @param <T>      parameter
	 * @param t        t
	 * @param encoding encoding
	 * @return the marshaller
	 */
	@SneakyThrows(value = {JAXBException.class})
	private static <T> Marshaller createMarshaller(T t, String encoding) {
		Marshaller marshaller = JAXBContext.newInstance(t.getClass()).createMarshaller();
		// 设置转换参数
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		if (!StringUtils.isEmpty(encoding)) {
			marshaller.setProperty(Marshaller.JAXB_ENCODING, Charset.forName("UTF-8"));
		}

		return marshaller;
	}

	/**
	 * Create unmarshaller unmarshaller
	 *
	 * @param <T> parameter
	 * @param t   t
	 * @return the unmarshaller
	 */
	@SneakyThrows(value = {JAXBException.class})
	private static <T> Unmarshaller createUnmarshaller(Class<T> t) {
		Unmarshaller unmarshaller = JAXBContext.newInstance(t).createUnmarshaller();
		return unmarshaller;
	}

	/**
	 * Handle remove xml prefix string
	 *
	 * @param xml    xml
	 * @param prefix prefix
	 * @return the string
	 */
	public static final String handleRemoveXmlPrefix(String xml, String prefix) {
		xml = StringEscapeUtils.unescapeXml(xml);
		prefix = StringEscapeUtils.unescapeXml(prefix);

		if (xml.contains(prefix)) {
			xml = xml.substring(prefix.length());
		}

		return xml;
	}


	/**
	 * Handle add xml prefix string
	 *
	 * @param xml    xml
	 * @param prefix prefix
	 * @return the string
	 */
	public static final String handleAddXmlPrefix(String xml, String prefix) {
		xml = StringEscapeUtils.unescapeXml(xml);
		prefix = StringEscapeUtils.unescapeXml(prefix);
		return prefix + "\n" + xml.trim();
	}

}
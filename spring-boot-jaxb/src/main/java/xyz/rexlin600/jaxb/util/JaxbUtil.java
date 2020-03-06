package xyz.rexlin600.jaxb.util;

import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Jaxb工具类
 *
 * @author: hekunlin
 * @date: 2020/3/6
 */
@Slf4j
public class JaxbUtil<T> {

    public final static String DEFAULT_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

    /**
     * Java对象转XML
     *
     * @param t        对象
     * @param encoding 编码
     * @return
     * @throws JAXBException
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
     * XML转Java对象
     *
     * @param t   对象
     * @param xml xml
     * @return
     * @throws JAXBException
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
     * 创建 Marshaller
     *
     * @param t        对象
     * @param encoding 编码
     * @return
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
     * 创建 Unmarshaller
     *
     * @param t 对象
     * @return
     */
    @SneakyThrows(value = {JAXBException.class})
    private static <T> Unmarshaller createUnmarshaller(Class<T> t) {
        Unmarshaller unmarshaller = JAXBContext.newInstance(t).createUnmarshaller();
        return unmarshaller;
    }

    /**
     * 消除XML前缀
     *
     * @param xml xml
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
     * 增加默认XML前缀
     *
     * @param xml xml
     */
    public static final String handleAddXmlPrefix(String xml, String prefix) {
        xml = StringEscapeUtils.unescapeXml(xml);
        prefix = StringEscapeUtils.unescapeXml(prefix);
        return prefix + "\n" + xml.trim();
    }

}
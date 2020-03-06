package xyz.rexlin600.jaxb.util;

import lombok.SneakyThrows;
import org.junit.Test;
import xyz.rexlin600.jaxb.entity.User;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-06 13:34
 */
public class JaxbUtilTest {

    private static final User user = new User(1L, 1234567890123456L, "james", "2020-03-05 12:00:00", true, 20, 13242141.2312D);
    private static final String simpleXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<USER>\n" +
            "    <ID>1</ID>\n" +
            "    <NUMBER>1234567890123456</NUMBER>\n" +
            "    <NAME>james</NAME>\n" +
            "    <BIRTHDAY>2020-03-05 12:00:00</BIRTHDAY>\n" +
            "    <FOLLOWER>true</FOLLOWER>\n" +
            "    <AGE>20</AGE>\n" +
            "    <DEPOSIT>1.32421412312E7</DEPOSIT>\n" +
            "</USER>";


    @SneakyThrows
    @Test
    public void java2Xml() {
        String xml = JaxbUtil.java2Xml(user, null);
        System.out.println(xml);
    }

    @SneakyThrows
    @Test
    public void simpleXml2Java() {
        User user = JaxbUtil.xml2Java(User.class, simpleXml);
        System.out.println(user.toString());
    }

}
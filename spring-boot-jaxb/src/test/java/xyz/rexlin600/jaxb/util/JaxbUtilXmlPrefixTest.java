package xyz.rexlin600.jaxb.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-06 14:07
 */
public class JaxbUtilXmlPrefixTest {

    private static final String allXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<USER>\n" +
            "    <ID>1</ID>\n" +
            "    <NUMBER>1234567890123456</NUMBER>\n" +
            "    <NAME>james</NAME>\n" +
            "    <BIRTHDAY>2020-03-05 12:00:00</BIRTHDAY>\n" +
            "    <FOLLOWER>true</FOLLOWER>\n" +
            "    <AGE>20</AGE>\n" +
            "    <DEPOSIT>1.32421412312E7</DEPOSIT>\n" +
            "</USER>";
    private static final String simpleXml = "<USER>\n" +
            "    <ID>1</ID>\n" +
            "    <NUMBER>1234567890123456</NUMBER>\n" +
            "    <NAME>james</NAME>\n" +
            "    <BIRTHDAY>2020-03-05 12:00:00</BIRTHDAY>\n" +
            "    <FOLLOWER>true</FOLLOWER>\n" +
            "    <AGE>20</AGE>\n" +
            "    <DEPOSIT>1.32421412312E7</DEPOSIT>\n" +
            "</USER>";

    @Test
    public void handleRemoveXmlPrefix() {
        String xml = JaxbUtil.handleRemoveXmlPrefix(allXml, JaxbUtil.DEFAULT_PREFIX);
        System.out.println(xml);
    }

    @Test
    public void handleAddXmlPrefix() {
        String xml = JaxbUtil.handleAddXmlPrefix(simpleXml, JaxbUtil.DEFAULT_PREFIX);
        System.out.println(xml);
    }

}

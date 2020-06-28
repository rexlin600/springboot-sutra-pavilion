package xyz.rexlin600.jaxb.util;

import org.junit.Test;
import xyz.rexlin600.jaxb.entity.list.Document;
import xyz.rexlin600.jaxb.entity.list.FileType;

import java.util.Arrays;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-06 16:09
 */
public class JaxbUtilListTest {

    private final static Document document = new Document(1L, "超级文件", Arrays.asList(
            new FileType(100L, "PNG"),
            new FileType(200L, "JPG"),
            new FileType(300L, "GIF")
    ));
    private final static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<DOCUMENT>\n" +
            "    <ID>1</ID>\n" +
            "    <NAME>超级文件</NAME>\n" +
            "    <FileType>\n" +
            "        <ID>100</ID>\n" +
            "        <TYPE>PNG</TYPE>\n" +
            "    </FileType>\n" +
            "    <FileType>\n" +
            "        <ID>200</ID>\n" +
            "        <TYPE>JPG</TYPE>\n" +
            "    </FileType>\n" +
            "    <FileType>\n" +
            "        <ID>300</ID>\n" +
            "        <TYPE>GIF</TYPE>\n" +
            "    </FileType>\n" +
            "</DOCUMENT>";

    @Test
    public void java2Xml() {
        String xml = JaxbUtil.java2Xml(document, null);
        System.out.println(xml);
    }

    @Test
    public void xml2Java() {
        Document document = JaxbUtil.xml2Java(Document.class, xml);
        System.out.println(document.toString());
    }

}
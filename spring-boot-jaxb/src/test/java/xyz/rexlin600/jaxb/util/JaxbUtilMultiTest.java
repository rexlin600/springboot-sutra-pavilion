package xyz.rexlin600.jaxb.util;

import org.junit.Test;
import xyz.rexlin600.jaxb.entity.nest.Company;
import xyz.rexlin600.jaxb.entity.nest.Computer;
import xyz.rexlin600.jaxb.entity.nest.Cpu;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-06 15:57
 */
public class JaxbUtilMultiTest {

    private final static Computer computer = new Computer(1L, "雷神", new Cpu(4, "x64", new Company("海尔", "北京")));
    private final static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<COMPUTER>\n" +
            "    <ID>1</ID>\n" +
            "    <NAME>雷神</NAME>\n" +
            "    <CPU>\n" +
            "        <CORESIZE>4</CORESIZE>\n" +
            "        <ARCH>x64</ARCH>\n" +
            "        <COMPANY>\n" +
            "            <NAME>海尔</NAME>\n" +
            "            <ADDRESS>北京</ADDRESS>\n" +
            "        </COMPANY>\n" +
            "    </CPU>\n" +
            "</COMPUTER>";

    @Test
    public void java2Xml() {
        String xml = JaxbUtil.java2Xml(computer, null);
        System.out.println(xml);
    }

    @Test
    public void xml2Java() {
        Computer computer = JaxbUtil.xml2Java(Computer.class, xml);
        System.out.println(computer.toString());
    }
}
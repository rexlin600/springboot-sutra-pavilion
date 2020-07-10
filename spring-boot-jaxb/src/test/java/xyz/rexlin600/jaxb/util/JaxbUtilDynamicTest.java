package xyz.rexlin600.jaxb.util;

import lombok.SneakyThrows;
import org.junit.Test;
import xyz.rexlin600.jaxb.entity.dynamic.simple.Biscuit;
import xyz.rexlin600.jaxb.entity.dynamic.simple.Cake;
import xyz.rexlin600.jaxb.entity.dynamic.simple.Order;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.Arrays;

public class JaxbUtilDynamicTest {

    private final static Order ORDER = new Order(1L, "产品1", Arrays.asList(
            new Cake("蛋糕"),
            new Biscuit("饼干")
    ));
    private final static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<PRODUCT>\n" +
            "    <ID>1</ID>\n" +
            "    <NAME>产品1</NAME>\n" +
			"    <PRODUCT>\n" +
			"        <CAKE>\n" +
			"            <NAME>蛋糕</NAME>\n" +
			"        </CAKE>\n" +
			"        <BISCUIT>\n" +
			"            <NAME>饼干</NAME>\n" +
			"        </BISCUIT>\n" +
			"    </PRODUCT>\n" +
			"</PRODUCT>";

	@SneakyThrows
	@Test
	public void java2Xml() {
		JAXBContext jaxbContext = JAXBContext.newInstance(Order.class, Cake.class, Biscuit.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(ORDER, System.out);
	}

	@SneakyThrows
	@Test
	public void xml2Java() {
		JAXBContext jaxbContext = JAXBContext.newInstance(Order.class, Cake.class, Biscuit.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader stringReader = new StringReader(xml);
		Order result = (Order) unmarshaller.unmarshal(new StreamSource(stringReader));
		stringReader.close();

		// 注意：这里的特定对象转 Object 为 null
        System.out.println(result.toString());
    }

}
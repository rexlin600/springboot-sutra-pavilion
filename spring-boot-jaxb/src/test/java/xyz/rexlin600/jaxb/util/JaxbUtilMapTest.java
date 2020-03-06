package xyz.rexlin600.jaxb.util;

import lombok.SneakyThrows;
import org.junit.Test;
import xyz.rexlin600.jaxb.entity.map.Order;
import xyz.rexlin600.jaxb.entity.map.Product;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @description
 * @auther hekunlin
 * @create 2020-03-06 16:49
 */
public class JaxbUtilMapTest {

    private final static String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<Order id=\"100\" classification=\"food\">\n" +
            "    <PRODUCT>\n" +
            "        <ID>1</ID>\n" +
            "        <NAME>product-1</NAME>\n" +
            "    </PRODUCT>\n" +
            "</Order>";

    @SneakyThrows
    @Test
    public void java2Xml() {
        Product product = new Product();
        product.setId(1L);
        product.setName("product-1");

        Map<QName, String> map = new HashMap<>();
        map.put(new QName("id"), "100");
        map.put(new QName("classification"), "food");

        Order order = new Order();
        order.setProduct(product);
        order.setProperties(map);

        JAXBContext context = JAXBContext.newInstance(Order.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(order, System.out);
    }

    @Test
    public void xml2Java() {
        Order order = JaxbUtil.xml2Java(Order.class, xml);
        System.out.println(order.toString());
    }

}
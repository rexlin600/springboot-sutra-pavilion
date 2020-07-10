package xyz.rexlin600.common.util.sensitive;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * Sensitive serialize
 *
 * @author hekunlin
 */
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveSerialize extends JsonSerializer<String> implements ContextualSerializer {

	/**
	 * Type
	 */
	private SensitiveTypeEnum type;
	/**
	 * Prefix no mask len
	 */
	private Integer prefixNoMaskLen;
	/**
	 * Suffix no mask len
	 */
	private Integer suffixNoMaskLen;
	/**
	 * Mask str
	 */
	private String maskStr;

	/**
	 * Serialize *
	 *
	 * @param origin             origin
	 * @param jsonGenerator      json generator
	 * @param serializerProvider serializer provider
	 * @throws IOException io exception
	 */
	@Override
	public void serialize(final String origin, final JsonGenerator jsonGenerator,
						  final SerializerProvider serializerProvider) throws IOException {
		switch (type) {
			case CHINESE_NAME:
				jsonGenerator.writeString(SensitiveUtils.chineseName(origin));
				break;
			case ID_CARD:
				jsonGenerator.writeString(SensitiveUtils.idCardNum(origin));
				break;
			case FIXED_PHONE:
				jsonGenerator.writeString(SensitiveUtils.fixedPhone(origin));
				break;
			case MOBILE_PHONE:
				jsonGenerator.writeString(SensitiveUtils.mobilePhone(origin));
				break;
			case ADDRESS:
				jsonGenerator.writeString(SensitiveUtils.address(origin));
				break;
			case EMAIL:
				jsonGenerator.writeString(SensitiveUtils.email(origin));
				break;
			case BANK_CARD:
				jsonGenerator.writeString(SensitiveUtils.bankCard(origin));
				break;
			case PASSWORD:
				jsonGenerator.writeString(SensitiveUtils.password(origin));
				break;
			case KEY:
				jsonGenerator.writeString(SensitiveUtils.key(origin));
				break;
			case CUSTOMER:
				jsonGenerator.writeString(SensitiveUtils.desValue(origin, prefixNoMaskLen, suffixNoMaskLen, maskStr));
				break;
			default:
				throw new IllegalArgumentException("Unknow sensitive type enum " + type);
		}
	}

	/**
	 * Create contextual json serializer
	 *
	 * @param serializerProvider serializer provider
	 * @param beanProperty       bean property
	 * @return the json serializer
	 * @throws JsonMappingException json mapping exception
	 */
	@Override
	public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
											  final BeanProperty beanProperty) throws JsonMappingException {
		if (beanProperty != null) {
			if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
				Sensitive sensitive = beanProperty.getAnnotation(Sensitive.class);
				if (sensitive == null) {
					sensitive = beanProperty.getContextAnnotation(Sensitive.class);
				}
				if (sensitive != null) {
					return new SensitiveSerialize(sensitive.type(), sensitive.prefixNoMaskLen(), sensitive.suffixNoMaskLen(), sensitive.maskStr());
				}
			}
			return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
		}
		return serializerProvider.findNullValueSerializer(null);
	}
}


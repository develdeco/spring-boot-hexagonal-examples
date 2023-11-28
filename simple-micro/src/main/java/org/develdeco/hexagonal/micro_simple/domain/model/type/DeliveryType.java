package org.develdeco.hexagonal.micro_simple.domain.model.type;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.develdeco.hexagonal.micro_simple.domain.model.exception.ApiException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public enum DeliveryType
{
    DELIVERY("Delivery"),
    LOCAL("Local"),
    PICKUP("Pickup"),
    SHIPMENT("Shipment"),
    SHIPPING("Shipping");

    private static final HashMap<String, DeliveryType> mapByValue = new HashMap<>();

    static {
        for (DeliveryType deliveryType : values()) {
            mapByValue.put(deliveryType.getValue().toUpperCase(), deliveryType);
        }
    }

    private final String value;

    DeliveryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DeliveryType ofValue(String value) {
        if (StringUtils.isNotBlank(value) && mapByValue.containsKey(value.toUpperCase())) {
            return mapByValue.get(value.toUpperCase());
        }
        return null;
    }

    @Slf4j
    public static class JsonDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<DeliveryType>
    {
        private static final String ERROR_MESSAGE_TEMPLATE = "%s for field '%s'. Allowed values: [%s]";

        @Override
        public DeliveryType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            log.info("Deserializing delivery type");

            String inputValue = deserializationContext.readValue(jsonParser, String.class);
            log.info("Value to deserialize: {}", inputValue);

            if (inputValue == null) {
                log.info("Deserialized value: null");
                return null;
            }

            DeliveryType deliveryType = DeliveryType.ofValue(inputValue);

            if (deliveryType == null) {
                String fieldName = jsonParser.getCurrentName();
                String errorMessage = String.format(
                        ERROR_MESSAGE_TEMPLATE,
                        "Invalid type value",
                        fieldName,
                        Arrays.stream(DeliveryType.values())
                                .map(DeliveryType::getValue)
                                .collect(Collectors.joining(", ")));
                log.error(errorMessage);
                throw new ApiException(errorMessage);
            }

            log.info("Deserialized value: {}", deliveryType.name());
            return deliveryType;
        }
    }

    public static class JsonSerializer extends com.fasterxml.jackson.databind.JsonSerializer<DeliveryType>
    {
        @Override
        public void serialize(DeliveryType deliveryType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (deliveryType == null) jsonGenerator.writeNull();
            else jsonGenerator.writeString(deliveryType.getValue());
        }
    }
}

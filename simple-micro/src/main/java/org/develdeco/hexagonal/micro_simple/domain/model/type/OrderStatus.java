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

public enum OrderStatus {

    NEW("New", "New"),

    OPEN("Open", "Processing"),

    IN_TRANSIT("InTransit", "In-Transit/Shipped"),

    CLOSED("Closed", "Fulfilled"),

    CANCELLED("Cancelled", "Cancelled");

    private static final HashMap<String, OrderStatus> mapByValue = new HashMap<>();
    private static final HashMap<String, OrderStatus> mapByInputValue = new HashMap<>();

    static {
        for (OrderStatus orderStatus : values()) {
            mapByValue.put(orderStatus.getValue().toUpperCase(), orderStatus);
            mapByInputValue.put(orderStatus.getInputValue().toUpperCase(), orderStatus);
        }
    }

    private final String value;

    private final String inputValue;

    OrderStatus(String value, String inputValue) {
        this.value = value;
        this.inputValue = inputValue;
    }

    public String getValue() {
        return value;
    }

    public String getInputValue() {
        return inputValue;
    }

    public static OrderStatus ofValue(String value) {
        if (StringUtils.isNotBlank(value) && mapByValue.containsKey(value.toUpperCase())) {
            return mapByValue.get(value.toUpperCase());
        }
        return null;
    }

    public static OrderStatus ofInputValue(String inputValue) {
        if (StringUtils.isNotBlank(inputValue) && mapByInputValue.containsKey(inputValue.toUpperCase())) {
            return mapByInputValue.get(inputValue.toUpperCase());
        }
        return null;
    }

    @Slf4j
    public static class JsonDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<OrderStatus>
    {
        private static final String ERROR_MESSAGE_TEMPLATE = "%s for field '%s'. Allowed values: [%s]";

        @Override
        public OrderStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            log.info("Deserializing order status");

            String inputValue = deserializationContext.readValue(jsonParser, String.class);
            log.info("Value to deserialize: {}", inputValue);

            if (inputValue == null) {
                log.info("Deserialized value: null");
                return null;
            }

            OrderStatus orderStatus = OrderStatus.ofInputValue(inputValue);

            if (orderStatus == null) {
                String fieldName = jsonParser.getCurrentName();
                String errorMessage = String.format(
                        ERROR_MESSAGE_TEMPLATE,
                        "Invalid type value",
                        fieldName,
                        Arrays.stream(OrderStatus.values())
                                .map(OrderStatus::getInputValue)
                                .collect(Collectors.joining(", ")));
                log.error(errorMessage);
                throw new ApiException(errorMessage);
            }

            log.info("Deserialized value: {}", orderStatus.name());
            return orderStatus;
        }
    }

    public static class JsonSerializer extends com.fasterxml.jackson.databind.JsonSerializer<OrderStatus>
    {
        @Override
        public void serialize(OrderStatus orderStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (orderStatus == null) jsonGenerator.writeNull();
            else jsonGenerator.writeString(orderStatus.getInputValue());
        }
    }
}

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

public enum PaymentStatus {

    NONE("None"),

    OVERDUE("Overdue"),

    PAID_FULL("PaidFull"),

    PAID_PARTIAL("PaidPartial");

    private static final HashMap<String, PaymentStatus> mapByValue = new HashMap<>();

    static {
        for (PaymentStatus paymentStatus : values()) {
            mapByValue.put(paymentStatus.getValue().toUpperCase(), paymentStatus);
        }
    }

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PaymentStatus ofValue(String value) {
        if (StringUtils.isNotBlank(value) && mapByValue.containsKey(value.toUpperCase())) {
            return mapByValue.get(value.toUpperCase());
        }
        return null;
    }

    @Slf4j
    public static class JsonDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<PaymentStatus>
    {
        private static final String ERROR_MESSAGE_TEMPLATE = "%s for field '%s'. Allowed values: [%s]";

        @Override
        public PaymentStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            log.info("Deserializing order payment status");

            String inputValue = deserializationContext.readValue(jsonParser, String.class);
            log.info("Value to deserialize: {}", inputValue);

            if (inputValue == null) {
                log.info("Deserialized value: null");
                return null;
            }

            PaymentStatus orderPaymentStatus = PaymentStatus.ofValue(inputValue);

            if (orderPaymentStatus == null) {
                String fieldName = jsonParser.getCurrentName();
                String errorMessage = String.format(
                        ERROR_MESSAGE_TEMPLATE,
                        "Invalid type value",
                        fieldName,
                        Arrays.stream(PaymentStatus.values())
                                .map(PaymentStatus::getValue)
                                .collect(Collectors.joining(", ")));
                log.error(errorMessage);
                throw new ApiException(errorMessage);
            }

            log.info("Deserialized value: {}", orderPaymentStatus.name());
            return orderPaymentStatus;
        }
    }

    public static class JsonSerializer extends com.fasterxml.jackson.databind.JsonSerializer<PaymentStatus>
    {
        @Override
        public void serialize(PaymentStatus orderPaymentStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (orderPaymentStatus == null) jsonGenerator.writeNull();
            else jsonGenerator.writeString(orderPaymentStatus.getValue());
        }
    }
}

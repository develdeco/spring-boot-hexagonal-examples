package org.develdeco.hexagonal.micro_simple.domain.model.converter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.develdeco.hexagonal.micro_simple.domain.model.exception.ApiException;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.time.LocalDate;

import static org.develdeco.hexagonal.micro_simple.domain.model.util.DateUtil.parseToDate;
import static org.develdeco.hexagonal.micro_simple.domain.model.util.DateUtil.formatDate;

public class LocalDateConverter {

    @Slf4j
    public static class JsonDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<LocalDate>
    {
        private static final String ERROR_MESSAGE_TEMPLATE = "%s. For field '%s'. Required format '%s'";

        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            log.info("Deserializing local date");

            String inputValue = deserializationContext.readValue(jsonParser, String.class);
            log.info("Value to deserialize: {}", inputValue);

            if (inputValue == null) {
                log.info("Deserialized value: null");
                return null;
            }

            LocalDate convertedValue = parseToDate(inputValue);

            if (convertedValue == null) {
                String fieldName = jsonParser.getCurrentName();
                String errorMessage = String.format(
                        ERROR_MESSAGE_TEMPLATE,
                        "Invalid date format",
                        fieldName,
                        "yyyy-MM-dd");
                log.error(errorMessage);
                throw new ApiException(errorMessage, HttpStatus.BAD_REQUEST);
            }

            log.info("Deserialized value: {}", convertedValue);
            return convertedValue;
        }
    }

    public static class JsonSerializer extends com.fasterxml.jackson.databind.JsonSerializer<LocalDate>
    {
        @Override
        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(formatDate(localDate));
        }
    }
}

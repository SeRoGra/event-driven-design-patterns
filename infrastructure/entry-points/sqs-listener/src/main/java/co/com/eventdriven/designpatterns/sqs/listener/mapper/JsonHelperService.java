package co.com.eventdriven.designpatterns.sqs.listener.mapper;

import co.com.eventdriven.designpatterns.enums.InfrastructureType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JsonHelperService {

    private final ObjectMapper objectMapper;

    public <T> T jsonStringToObject(String json, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw InfrastructureType.JSON_HELPER_ERROR.build(e);
        }
    }

    public JsonNode jsonStringToJsonNode(String json) {
        try {
            return this.objectMapper.readTree(json);
        } catch (Exception e) {
            throw InfrastructureType.JSON_HELPER_ERROR.build(e);
        }
    }

    public <T> T jsonNodeToObject(JsonNode node, Class<T> clazz) {
        try {
            return this.objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw InfrastructureType.JSON_HELPER_ERROR.build(e);
        }
    }

    public static Boolean isEqual(JsonNode node, String key, String value) {
        return Optional.ofNullable(node.get(key))
                .map(jsonNode -> value.equals(jsonNode.asText()))
                .orElse(Boolean.FALSE);
    }

}

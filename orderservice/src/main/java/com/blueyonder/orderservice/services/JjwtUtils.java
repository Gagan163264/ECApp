package com.blueyonder.orderservice.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;

public class JjwtUtils {
    public static String getClaims(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

//    	String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        return payload;

    }
    public static String extractUsername(String token) {
        String payload = getClaims(token);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode payloadJson = objectMapper.readTree(payload);
            String username = payloadJson.get("sub").asText();
            return username;
        } catch (Exception e) {
            // Handle parsing exception
            e.printStackTrace();
            return null;
        }
    }
}

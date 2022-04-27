package com.hahaup.api.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class PhotoResponse {

    private String photoUrl;

    @JsonProperty("data")
    private void extractPhoto(Map<String, String> data) {
        this.photoUrl = data.get("photoUrl");
    }
}

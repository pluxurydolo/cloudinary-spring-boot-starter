package com.pluxurydolo.cloudinary.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pluxurydolo.cloudinary.dto.ResourceType;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResourceResponse(

    @JsonProperty("asset_id")
    String assetId,

    @JsonProperty("public_id")
    String publicId,

    @JsonProperty("version")
    Long version,

    @JsonProperty("version_id")
    String versionId,

    @JsonProperty("signature")
    String signature,

    @JsonProperty("width")
    Integer width,

    @JsonProperty("height")
    Integer height,

    @JsonProperty("format")
    String format,

    @JsonProperty("resource_type")
    ResourceType resourceType,

    @JsonProperty("created_at")
    String createdAt,

    @JsonProperty("tags")
    List<String> tags,

    @JsonProperty("bytes")
    Long bytes,

    @JsonProperty("type")
    String type,

    @JsonProperty("etag")
    String etag,

    @JsonProperty("url")
    String url,

    @JsonProperty("secure_url")
    String secureUrl,

    @JsonProperty("backup_url")
    String backupUrl,

    @JsonProperty("context")
    Map<String, Object> context,

    @JsonProperty("metadata")
    Map<String, Object> metadata,

    @JsonProperty("colors")
    Map<String, String> colors,

    @JsonProperty("faces")
    List<List<Integer>> faces,

    @JsonProperty("derived")
    List<DerivedResource> derived,

    @JsonProperty("access_mode")
    String accessMode,

    @JsonProperty("access_control")
    Object accessControl,

    @JsonProperty("existing")
    Boolean existing,

    @JsonProperty("info")
    Map<String, Object> info,

    @JsonProperty("original_filename")
    String originalFilename,

    @JsonProperty("folder")
    String folder,

    @JsonProperty("api_metadata")
    Map<String, Object> apiMetadata
) {
    public boolean isSuccessful() {
        boolean publicIdValid = publicId != null && !publicId.isEmpty();
        boolean secureUrlValid = secureUrl != null && !secureUrl.isEmpty();
        return publicIdValid && secureUrlValid;
    }
}

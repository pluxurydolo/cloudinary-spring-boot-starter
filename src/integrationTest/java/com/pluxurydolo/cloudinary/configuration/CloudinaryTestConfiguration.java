package com.pluxurydolo.cloudinary.configuration;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import com.cloudinary.http5.api.Response;
import org.apache.hc.core5.http.message.BasicClassicHttpResponse;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@TestConfiguration
public class CloudinaryTestConfiguration {

    @Bean
    public Cloudinary cloudinary() throws Exception {
        Cloudinary cloudinary = mock(Cloudinary.class);
        Uploader uploader = mock(Uploader.class);
        Api api = mock(Api.class);

        when(cloudinary.uploader())
            .thenReturn(uploader);
        when(uploader.upload(any(), anyMap()))
            .thenReturn(resourceResponse());
        when(uploader.destroy(anyString(), anyMap()))
            .thenReturn(deleteResponse());

        when(cloudinary.api())
            .thenReturn(api);
        when(api.resource(anyString(), anyMap()))
            .thenReturn(response());

        return cloudinary;
    }

    private static Response response() {
        BasicClassicHttpResponse response = new BasicClassicHttpResponse(OK.value());
        return new Response(response, resourceResponse());
    }

    private static Map<String, Object> resourceResponse() {
        return Map.of(
            "public_id", "publicId",
            "secure_url", "secureUrl"
        );
    }

    private static Map<String, Object> deleteResponse() {
        return Map.of("result", "ok");
    }
}

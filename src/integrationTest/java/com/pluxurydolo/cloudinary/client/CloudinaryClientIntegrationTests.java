package com.pluxurydolo.cloudinary.client;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import ch.qos.logback.core.spi.AppenderAttachable;
import com.pluxurydolo.cloudinary.base.AbstractIntegrationTests;
import com.pluxurydolo.cloudinary.dto.request.DeleteRequest;
import com.pluxurydolo.cloudinary.dto.request.ResourceRequest;
import com.pluxurydolo.cloudinary.dto.request.UploadRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.slf4j.LoggerFactory.getLogger;

class CloudinaryClientIntegrationTests extends AbstractIntegrationTests {
    private static final AppenderAttachable<ILoggingEvent> LOGGER =
        (Logger) getLogger(CloudinaryClient.class);

    @Autowired
    private CloudinaryClient cloudinaryClient;

    @Test
    void testUploadImage() {
        List<ILoggingEvent> logs = listAppender().list;

        UploadRequest request = new UploadRequest(bytes(), "folder", "name");
        cloudinaryClient.uploadImage(request)
            .subscribe();

        await().atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
                assertThat(logs)
                    .hasSize(1);

                assertThat(logs.getFirst().getFormattedMessage())
                    .isEqualTo("apjz [cloudinary-starter] Изображение успешно загружено");
            });
    }

    @Test
    void testUploadVideo() {
        List<ILoggingEvent> logs = listAppender().list;

        UploadRequest request = new UploadRequest(bytes(), "folder", "name");
        cloudinaryClient.uploadVideo(request)
            .subscribe();

        await().atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
                assertThat(logs)
                    .hasSize(1);

                assertThat(logs.getFirst().getFormattedMessage())
                    .isEqualTo("ehrv [cloudinary-starter] Видео успешно загружено");
            });
    }

    @Test
    void testGetImageSecureUrl() {
        List<ILoggingEvent> logs = listAppender().list;

        ResourceRequest request = new ResourceRequest("publicId");
        cloudinaryClient.getImageSecureUrl(request)
            .subscribe();

        await().atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
                assertThat(logs)
                    .hasSize(1);

                assertThat(logs.getFirst().getFormattedMessage())
                    .isEqualTo("ohlb [cloudinary-starter] secure_url для изображения успешно получен");
            });
    }

    @Test
    void testGetVideoSecureUrl() {
        List<ILoggingEvent> logs = listAppender().list;

        ResourceRequest request = new ResourceRequest("publicId");
        cloudinaryClient.getVideoSecureUrl(request)
            .subscribe();

        await().atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
                assertThat(logs)
                    .hasSize(1);

                assertThat(logs.getFirst().getFormattedMessage())
                    .isEqualTo("bnvs [cloudinary-starter] secure_url для видео успешно получен");
            });
    }

    @Test
    void testDeleteImage() {
        List<ILoggingEvent> logs = listAppender().list;

        DeleteRequest request = new DeleteRequest("publicId");
        cloudinaryClient.deleteImage(request)
            .subscribe();

        await().atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
                assertThat(logs)
                    .hasSize(1);

                assertThat(logs.getFirst().getFormattedMessage())
                    .isEqualTo("zgts [cloudinary-starter] Изображение успешно удалено");
            });
    }

    @Test
    void testDeleteVideo() {
        List<ILoggingEvent> logs = listAppender().list;

        DeleteRequest request = new DeleteRequest("publicId");
        cloudinaryClient.deleteVideo(request)
            .subscribe();

        await().atMost(Duration.ofSeconds(5))
            .untilAsserted(() -> {
                assertThat(logs)
                    .hasSize(1);

                assertThat(logs.getFirst().getFormattedMessage())
                    .isEqualTo("ztrf [cloudinary-starter] Видео успешно удалено");
            });
    }

    private static ListAppender<ILoggingEvent> listAppender() {
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        LOGGER.addAppender(listAppender);
        return listAppender;
    }

    private static byte[] bytes() {
        return new byte[]{1, 2, 3};
    }
}

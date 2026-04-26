package com.pluxurydolo.cloudinary.base;

import com.pluxurydolo.cloudinary.TestApplication;
import com.pluxurydolo.cloudinary.configuration.CloudinaryTestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest(classes = {
    TestApplication.class,
    CloudinaryTestConfiguration.class
})
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public abstract class AbstractIntegrationTests {
}

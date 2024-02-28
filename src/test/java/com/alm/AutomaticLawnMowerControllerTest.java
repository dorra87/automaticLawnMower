package com.alm;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

class AutomaticLawnMowerControllerTest {
    @Test
    void TestMain() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("test.txt");
        File file = Paths.get(resource.toURI()).toFile();
        String[] args = {file.getAbsolutePath()};
        AutomaticLawnMowerController.main(args);
    }
}
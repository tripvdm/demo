package com.test_project.demo.generator;

import java.io.File;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public interface GeneratorFile {

    static File generateCorrectFile() {
        return new File("src/main/resources/test.txt");
    }

    static File generateUnCorrectFile() {
        return new File("src/main/resources/tedsfsst.txt");
    }

    static File generateRequestFile() {
        return new File("src/main/resources/request_test.txt");
    }

    static MultipartFile generateCorrectMultiPartFile() {
        return new MockMultipartFile("src/main/resources/test.txt", "1\n2\n3\n4\n5\n".getBytes());
    }

    static MultipartFile generateUnCorrectMultiPartFile() {
        return new MockMultipartFile("src/main/resources/tes32423t.txt", "1\n2\n3\n4\n5\n".getBytes());
    }

    static MultipartFile generateCorrectMultiPartFileButNotCorrectContent() {
        return new MockMultipartFile("src/main/resources/tes32423t.txt", "1\n2\n3\n4\newrewgreg5\n".getBytes());
    }
}

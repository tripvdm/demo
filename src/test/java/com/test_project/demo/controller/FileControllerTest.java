package com.test_project.demo.controller;

import com.test_project.demo.generator.GeneratorFile;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.test_project.demo.utils.ConstantUtils.NOT_EXISTS_NUMBER_OF_ARRAY;
import static com.test_project.demo.utils.ConstantUtils.NUMBER_OF_ARRAY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {
    private static final String RESULT = "8";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uploadFileWhenOk() throws Exception {
        final byte[] bytes = Files.readAllBytes(GeneratorFile.generateRequestFile().toPath());
        this.mockMvc.perform(multipart("/upload")
                .file("file", bytes)
                .param("number", String.valueOf(NUMBER_OF_ARRAY)))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().string(RESULT));
    }

    @Test
    void uploadFileWhenElementIsNotExists() throws Exception {
        final byte[] bytes = Files.readAllBytes(GeneratorFile.generateRequestFile().toPath());
        this.mockMvc.perform(multipart("/upload")
                .file("file", bytes)
                .param("number", String.valueOf(NOT_EXISTS_NUMBER_OF_ARRAY)))
            .andExpect(status().is4xxClientError());
    }

    @Test
    void uploadFileWhenElementIsNotCorrect() throws Exception {
        final byte[] bytes = Files.readAllBytes(GeneratorFile.generateRequestFile().toPath());
        final String numberParam = "sf23sdf";
        this.mockMvc.perform(multipart("/upload")
                .file("file", bytes)
                .param("number", numberParam))
            .andExpect(status().is4xxClientError());
    }
}
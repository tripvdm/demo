package com.test_project.demo.controller;

import com.test_project.demo.service.FileService;
import java.io.File;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.test_project.demo.utils.ConstantUtils.CORRECT_FILE_NAME;
import static com.test_project.demo.utils.ConstantUtils.NUMBER_OF_ARRAY;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    private static final Integer RESULT = 2;

    @Test
    void uploadFileWhenOk() throws Exception {
        when(fileService.getMaxNumberForPosition(new File(CORRECT_FILE_NAME),
            NUMBER_OF_ARRAY)).thenReturn(RESULT);

        final byte[] bytes = Files.readAllBytes(new File("src/main/resources/test.txt").toPath());
        this.mockMvc.perform(multipart("/upload")
                .file("file", bytes)
                .param("number", String.valueOf(NUMBER_OF_ARRAY)))
            .andExpect(status().isOk());
    }
}
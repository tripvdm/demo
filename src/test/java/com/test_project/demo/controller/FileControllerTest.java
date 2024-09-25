package com.test_project.demo.controller;

import com.test_project.demo.service.FileService;
import com.test_project.demo.utils.ConstantUtils;
import java.io.File;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static com.test_project.demo.utils.ConstantUtils.NUMBER_OF_ARRAY;
import static org.apache.logging.log4j.util.LoaderUtil.getClassLoader;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    @Test
    void uploadFileWhenOk() throws Exception {
        when(fileService.getMaxNumberForPosition(new File(ConstantUtils.CORRECT_FILE_NAME),
            ConstantUtils.NUMBER_OF_ARRAY)).thenReturn(NUMBER_OF_ARRAY);

        final byte[] bytes = getClassLoader().getResource(ConstantUtils.CORRECT_FILE_NAME).getFile().getBytes();
        this.mockMvc.perform(multipart("/upload")
                .file("file", bytes)
                .param("number", String.valueOf(ConstantUtils.NUMBER_OF_ARRAY)))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().string(String.valueOf(NUMBER_OF_ARRAY)));
    }

    @Test
    void uploadFileWhenNumberNotFoundException() throws Exception {
        when(fileService.getMaxNumberForPosition(new File(ConstantUtils.CORRECT_FILE_NAME),
            ConstantUtils.NOT_EXISTS_NUMBER_OF_ARRAY)).thenReturn(NUMBER_OF_ARRAY);

        final byte[] bytes = Objects.requireNonNull(getClassLoader().getResource(ConstantUtils.CORRECT_FILE_NAME)).getFile().getBytes();
        this.mockMvc.perform(multipart("/upload")
                .file("file", bytes)
                .param("number", String.valueOf(ConstantUtils.NOT_EXISTS_NUMBER_OF_ARRAY)))
            .andExpect(status().isOk());
    }

}
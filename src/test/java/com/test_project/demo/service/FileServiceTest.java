package com.test_project.demo.service;

import com.test_project.demo.generator.GeneratorFile;
import com.test_project.demo.service.util.FileUtil;
import com.test_project.demo.utils.ConstantUtils;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import static com.test_project.demo.utils.ConstantUtils.EXPECTED_RESULT_TEXT;
import static com.test_project.demo.utils.ConstantUtils.INT_ARRAY;
import static com.test_project.demo.utils.ConstantUtils.NOT_EXISTS_NUMBER_OF_ARRAY;
import static com.test_project.demo.utils.ConstantUtils.NUMBER_OF_ARRAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FileServiceTest {

    @Mock
    private FileUtil fileUtil;

    @InjectMocks
    private FileService fileService;

    @Test
    public void getMaxNumberForPositionWhen() {
        MultipartFile multipart = GeneratorFile.generateUnCorrectMultiPartFile();
        when(fileUtil.createTemporalyFile(any())).thenReturn(GeneratorFile.generateUnCorrectFile());

        assertThrows(RuntimeException.class, () -> fileService.getMaxNumberForPosition(multipart, NUMBER_OF_ARRAY));
        verify(fileUtil).createTemporalyFile(multipart);
    }

    @Test
    public void getMaxNumberForPositionWhenFileIsNotFound() {
        MultipartFile multipart = GeneratorFile.generateCorrectMultiPartFile();
        when(fileUtil.createTemporalyFile(any())).thenReturn(GeneratorFile.generateCorrectFile());
        when(fileUtil.readFile(any())).thenReturn("");

        assertThrows(RuntimeException.class, () -> fileService.getMaxNumberForPosition(multipart, NUMBER_OF_ARRAY));
        verify(fileUtil).createTemporalyFile(multipart);
        verify(fileUtil).readFile(GeneratorFile.generateCorrectFile());
    }

    @Test
    public void getMaxNumberForPositionWhenParsingIsNotCorrect() {
        when(fileUtil.createTemporalyFile(any())).thenReturn(GeneratorFile.generateCorrectFile());
        when(fileUtil.readFile(any())).thenReturn(ConstantUtils.NOT_CORRECTED_RESULT_TEXT);
        when(fileUtil.parseToIntArrayFromString(any())).thenReturn(new int[] {});

        assertThrows(RuntimeException.class, () ->
            fileService.getMaxNumberForPosition(GeneratorFile.generateCorrectMultiPartFileButNotCorrectContent(), NUMBER_OF_ARRAY));
        verify(fileUtil).createTemporalyFile(any());
        verify(fileUtil).readFile(any());
        verify(fileUtil).parseToIntArrayFromString(any());
    }

    @Test
    public void getMaxNumberForPositionWhenNumberIsNotFound() throws IOException {
        when(fileUtil.createTemporalyFile(any())).thenReturn(GeneratorFile.generateCorrectFile());
        when(fileUtil.readFile(any())).thenReturn(EXPECTED_RESULT_TEXT);
        when(fileUtil.parseToIntArrayFromString(any())).thenReturn(INT_ARRAY);

        assertThrows(RuntimeException.class, () ->
            fileService.getMaxNumberForPosition(GeneratorFile.generateCorrectMultiPartFile(), NOT_EXISTS_NUMBER_OF_ARRAY));
        verify(fileUtil).createTemporalyFile(any());
        verify(fileUtil).readFile(any());
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);
    }

    @Test
    public void getMaxNumberForPositionWhenWorkingIsOk() {
        when(fileUtil.createTemporalyFile(any())).thenReturn(GeneratorFile.generateCorrectFile());
        when(fileUtil.readFile(any())).thenReturn(EXPECTED_RESULT_TEXT);
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);

        int result = fileService.getMaxNumberForPosition(GeneratorFile.generateCorrectMultiPartFile(), NUMBER_OF_ARRAY);

        assertEquals(result, 2);
        verify(fileUtil).createTemporalyFile(any());
        verify(fileUtil).readFile(any());
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);
    }
}
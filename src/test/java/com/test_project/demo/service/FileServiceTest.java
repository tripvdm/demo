package com.test_project.demo.service;

import com.test_project.demo.exception.NumberNotFoundException;
import com.test_project.demo.service.util.FileUtil;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FileServiceTest {

    @Mock
    private FileUtil fileUtil;

    @InjectMocks
    private FileService fileService;

    private static final Integer NUMBER_OF_ARRAY = 4;
    private static final Integer NOT_EXISTS_NUMBER_OF_ARRAY = 77;

    private static final int[] INT_ARRAY = new int[] {1, 2, 3, 4, 5};

    private final String CORRECT_FILE_NAME = "test.txt";
    private final String NOT_CORRECT_FILE_NAME = "test12.txt";
    private final String EXPECTED_RESULT_TEXT = "1/n2/n3/n4/n5/n";

    @Test
    public void getMaxNumberForPositionWhenFileIsNotFound() throws IOException {
        File file = new File(NOT_CORRECT_FILE_NAME);
        when(fileUtil.readFile(file)).thenReturn(EXPECTED_RESULT_TEXT);

        assertThrows(NumberNotFoundException.class, () -> fileService.getMaxNumberForPosition(file, NUMBER_OF_ARRAY));
        verify(fileUtil).readFile(file);
    }

    @Test
    public void getMaxNumberForPositionWhenParsingIsNotCorrect() throws IOException {
        File file = new File(CORRECT_FILE_NAME);
        when(fileUtil.readFile(file)).thenReturn(EXPECTED_RESULT_TEXT);
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);

        assertThrows(NumberNotFoundException.class, () -> fileService.getMaxNumberForPosition(file, NUMBER_OF_ARRAY));
        verify(fileUtil).readFile(file);
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);
    }

    @Test
    public void getMaxNumberForPositionWhenNumberIsNotFound() throws IOException {
        File file = new File(CORRECT_FILE_NAME);
        when(fileUtil.readFile(file)).thenReturn(EXPECTED_RESULT_TEXT);
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);

        assertThrows(NumberNotFoundException.class, () -> fileService.getMaxNumberForPosition(file, NOT_EXISTS_NUMBER_OF_ARRAY));
        verify(fileUtil).readFile(file);
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);
    }

    @Test
    public void getMaxNumberForPositionWhenWorkingIsOk() throws IOException {
        File file = new File(CORRECT_FILE_NAME);
        when(fileUtil.readFile(file)).thenReturn(EXPECTED_RESULT_TEXT);
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);

        int result = fileService.getMaxNumberForPosition(file, NUMBER_OF_ARRAY);

        assertEquals(result, NUMBER_OF_ARRAY);
        verify(fileUtil).readFile(file);
        when(fileUtil.parseToIntArrayFromString(EXPECTED_RESULT_TEXT)).thenReturn(INT_ARRAY);
    }
}
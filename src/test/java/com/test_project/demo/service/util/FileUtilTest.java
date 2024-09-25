package com.test_project.demo.service.util;

import com.test_project.demo.utils.ConstantUtils;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.logging.log4j.util.LoaderUtil.getClassLoader;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class FileUtilTest {

    @InjectMocks
    private FileUtil fileUtil;

    @Test
    public void readFileWhenFileIsCorrect() throws IOException {
        String result = fileUtil.readFile(new File(Objects.requireNonNull(
            getClassLoader().getResource(ConstantUtils.CORRECT_FILE_NAME)).getFile()));

        assertNotNull(result);
    }

    @Test
    public void readFileWhenFileNotFound() throws IOException {
        assertThrows(NullPointerException.class, () -> fileUtil.readFile(new File(Objects.requireNonNull(
            getClassLoader().getResource(ConstantUtils.NOT_CORRECT_FILE_NAME)).getFile())));
    }

    @Test
    public void parseToIntArrayFromStringWhenParsingIsCorrect() {
        String text = ConstantUtils.EXPECTED_RESULT_TEXT;

        int[] array = fileUtil.parseToIntArrayFromString(text);

        assertTrue(Arrays.isArray(array));
    }

    @Test
    public void parseToIntArrayFromStringWhenParsingIsNotCorrect() {
        String text = ConstantUtils.NOT_CORRECTED_RESULT_TEXT;

        assertThrows(NumberFormatException.class, () -> fileUtil.parseToIntArrayFromString(text));
    }

}
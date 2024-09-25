package com.test_project.demo.service.util;

import com.test_project.demo.generator.GeneratorFile;
import com.test_project.demo.utils.ConstantUtils;
import java.io.File;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class FileUtilTest {

    @InjectMocks
    private FileUtil fileUtil;

    @Test
    public void createTemporalyFileIsOk() {
        File file = fileUtil.createTemporalyFile(GeneratorFile.generateCorrectMultiPartFile());

        assertNotNull(file);
    }

    @Test
    public void createTemporalyFileIsNotFound() {
        File file = fileUtil.createTemporalyFile(null);

        assertNull(file);
    }

    @Test
    public void readFileWhenFileIsCorrect() {
        File file = fileUtil.createTemporalyFile(GeneratorFile.generateCorrectMultiPartFile());
        String result = fileUtil.readFile(file);

        assertNotNull(result);
    }

    @Test
    public void readFileWhenFileNotFound() {
        String result = fileUtil.readFile(GeneratorFile.generateUnCorrectFile());

        assertNull(result);
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
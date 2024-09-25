package com.test_project.demo.service;

import com.test_project.demo.exception.ResourceNotFoundException;
import com.test_project.demo.service.util.FileUtil;
import java.io.File;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Autowired
    private FileUtil fileUtil;

    public int getMaxNumberForPosition(MultipartFile multipart, Optional<Integer> optNumber) {
        File file = null;
        try {
            file = fileUtil.createTemporalyFile(multipart);
            String text = fileUtil.readFile(file);
            int[] arr = fileUtil.parseToIntArrayFromString(text);
            quickSort(arr, 0, arr.length - 1);
            if (optNumber.isPresent()) {
                return arr[arr.length - optNumber.get()];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ResourceNotFoundException(e.getMessage());
        } finally {
            Objects.requireNonNull(file).deleteOnExit();
        }

        throw new ResourceNotFoundException("Элемент не число");
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

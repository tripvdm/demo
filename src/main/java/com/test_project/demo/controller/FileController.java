package com.test_project.demo.controller;

import com.test_project.demo.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Tag(name="Контроллер файла", description="Контроллер для чтение файла и получение числа")
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Operation(
            summary = "Получение N-го числа из массива",
            description = "Позволяет прочитать файл и получить N-ое число из сортированного массива"
    )
    @RequestMapping(
        path = "/upload",
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public int uploadFile(@RequestPart("file") MultipartFile file,
                           @RequestParam("number") Integer number) throws IOException {
        return fileService.getMaxNumberForPosition(file.getResource().getFile(), number);
    }

}

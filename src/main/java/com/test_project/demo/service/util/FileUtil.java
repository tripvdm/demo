package com.test_project.demo.service.util;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

    public File createTemporalyFile(MultipartFile multipart) {
        try {
            File file = File.createTempFile("file", ".txt");
            multipart.transferTo(file);

            return file;
        } catch (Exception e) {
            return null;
        }
    }

    public String readFile(File file) {
        try {
            RandomAccessFile reader = new RandomAccessFile(file, "r");
            FileChannel channel = reader.getChannel();

            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            channel.read(buff);
            buff.flip();

            return new String(buff.array());
        } catch (Exception e) {
            return null;
        }
    }

    public int[] parseToIntArrayFromString(String text) {
        String[] strings = text.split("\n");

        int[] intArray = new int[strings.length];
        int counter = 0;
        for (String s : strings) {
            intArray[counter] = Integer.parseInt(s);
            counter++;
        }

        return intArray;
    }

}

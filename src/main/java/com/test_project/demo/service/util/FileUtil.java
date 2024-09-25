package com.test_project.demo.service.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

    public String readFile(File file) throws IOException {
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
    }

    public int[] parseToIntArrayFromString(String text) {
        String[] strings = text.split("/n");

        int[] intArray = new int[strings.length];
        int counter = 0;
        for (String s : strings) {
            intArray[counter] = Integer.parseInt(s);
            counter++;
        }

        return intArray;
    }

}

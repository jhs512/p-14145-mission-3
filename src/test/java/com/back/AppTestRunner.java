package com.back;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AppTestRunner {

    public static String run(String input) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        System.setOut(new PrintStream(byteArrayOutputStream));

        try {
            App app = new App();
            app.run();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        return byteArrayOutputStream.toString();
    }
}

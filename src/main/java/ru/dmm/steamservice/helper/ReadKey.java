package ru.dmm.steamservice.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Dmitry
 */
public class ReadKey {
    public static String getConsoleOutput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = null;
        try {
            result = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

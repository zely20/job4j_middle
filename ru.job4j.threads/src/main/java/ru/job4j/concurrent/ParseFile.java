package ru.job4j.concurrent;

import java.io.*;

public class ParseFile {

    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        String output = "";
        int data;
        try (InputStream i = new FileInputStream(file)) {
            while ((data = i.read()) > 0) {
                output += (char) data;
            }
        }
        return output;
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        String output = "";
        int data;
        try (InputStream i = new FileInputStream(file)) {
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        }
        return output;
    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}

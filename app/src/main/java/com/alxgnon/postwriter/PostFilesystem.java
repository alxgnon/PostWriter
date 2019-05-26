package com.alxgnon.postwriter;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class PostFilesystem {

    private static final String WORKING_DIRECTORY = "Posts";
    private static final String FILENAME_FORMAT = "yyyy.MM.dd.HH.mm.ss";
    private static final String FILE_EXTENSION = ".txt";

    static String saveFile(String text) {
        File file = namedFileWithTimestamp();

        if (saveFile(file, text)) {
            return file.getName();
        }
        return null;
    }

    private static File namedFileWithTimestamp() {
        Locale l = Locale.getDefault();
        String timestamp = new SimpleDateFormat(FILENAME_FORMAT, l).format(new Date());

        return new File(getWorkingDirectory(), timestamp + FILE_EXTENSION);
    }

    private static boolean saveFile(File file, String text) {
        BufferedWriter buffer = null;

        try {
            buffer = saveFileThrows(file, text);
        } catch (IOException e) {
            return false;
        } finally {
            tryClose(buffer);
        }
        return true;
    }

    private static BufferedWriter saveFileThrows(File file, String text) throws IOException {
        BufferedWriter buffer = new BufferedWriter(new FileWriter(file));

        buffer.write(text);
        return buffer;
    }

    @SuppressWarnings("EmptyCatchBlock")
    private static void tryClose(Closeable buffer) {
        try {
            if (buffer != null) {
                buffer.close();
            }
        } catch (IOException e) {
        }
    }

    private static File getWorkingDirectory() {
        return new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS),
                WORKING_DIRECTORY);
    }
}

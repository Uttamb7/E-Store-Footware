package com.estore.api.estoreapi.utils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class FlatFileOps {

    public static void ensureDataFileExists(String fullPath, String starterJson) throws IOException {
        String[] pathSplit = fullPath.split("/");
        if (pathSplit.length > 1) {
            String directories = String.join("/", Arrays.copyOf(pathSplit, pathSplit.length - 1));
            Files.createDirectories(Paths.get(directories));
        }
        try {
            Path filePath = Paths.get(fullPath);
            Files.createFile(filePath);
            Files.writeString(filePath, starterJson);
        } catch (FileAlreadyExistsException ignored) {
        }
    }

    public static void ensureDataFileExists(String fullPath) throws IOException {
        ensureDataFileExists(fullPath, "[]");
    }

}

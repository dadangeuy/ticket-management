package id.ac.its.ticketmanagement.model;

import java.io.File;
import java.nio.file.Path;

public class FileMeta {
    private String name;

    private FileMeta(String name) {
        this.name = name;
    }

    public static FileMeta fromPath(Path path) {
        File file = path.toFile();
        return new FileMeta(file.getName());
    }

    public String getName() {
        return name;
    }
}

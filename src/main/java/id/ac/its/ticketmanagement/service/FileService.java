package id.ac.its.ticketmanagement.service;

import id.ac.its.ticketmanagement.model.FileMeta;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FileService {
    private static final String directory = "./.file/";
    private static final Path directoryPath = Paths.get(directory);

    public FileService() throws IOException {
        Path path = Paths.get(directory);
        Files.createDirectories(path);
    }

    public Collection<FileMeta> list() throws IOException {
        return Files.list(directoryPath)
                .map(FileMeta::fromPath)
                .collect(Collectors.toList());
    }

    public FileMeta write(String name, InputStream stream) throws IOException {
        Path path = Paths.get(directory + name);
        Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
        return FileMeta.fromPath(path);
    }


    public InputStream read(String name) throws FileNotFoundException {
        return new FileInputStream(directory + name);
    }
}

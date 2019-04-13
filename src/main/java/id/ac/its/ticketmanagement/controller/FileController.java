package id.ac.its.ticketmanagement.controller;

import id.ac.its.ticketmanagement.model.FileMeta;
import id.ac.its.ticketmanagement.service.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<FileMeta> list() throws IOException {
        return service.list();
    }

    @PostMapping("/upload")
    public FileMeta upload(@RequestParam MultipartFile file) throws IOException {
        return service.write(file.getOriginalFilename(), file.getInputStream());
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String name) throws FileNotFoundException {
        InputStream stream = service.read(name);
        InputStreamResource resource = new InputStreamResource(stream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=" + name)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}

package com.ex.FitApp.file.service;

import com.ex.FitApp.file.exception.FileStorageException;
import com.ex.FitApp.file.model.DBFile;
import com.ex.FitApp.file.repository.DBFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class DBFileStorageService {

    private final DBFileRepository fileRepo;

    public DBFileStorageService(DBFileRepository fileRepo) {
        this.fileRepo = fileRepo;
    }

    public DBFile storeFile(MultipartFile file) throws FileStorageException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (fileName.contains("..")) {

                throw new FileStorageException("Filename contains invalid characters!");
            }


            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            String fileType = file.getContentType();
            if (file.getContentType().equals("image/x-png") ||
                file.getContentType().equals("image/jpeg") ||
                file.getContentType().equals("image/jpg")) {

                System.out.println();

            }

            return this.fileRepo.save(dbFile);
        } catch (IOException | FileStorageException e) {

            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public DBFile getFile(String fileId) throws FileNotFoundException {

        return this.fileRepo.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File with id " + fileId + " not found!"));
    }

    public void deleteFile(String fileId) {

        this.fileRepo.deleteById(fileId);

    }

    public List<DBFile> getAllFiles() {

        return this.fileRepo.findAll();
    }
}

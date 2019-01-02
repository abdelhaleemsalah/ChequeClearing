package com.egabi.blockchain.chequeClearing.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file, String merchant);

    Stream<Path> loadAll(String tenant);

    Path load(String filename , String merchant);

    Resource loadAsResource(String filename, String merchant);

    void deleteAll();
}

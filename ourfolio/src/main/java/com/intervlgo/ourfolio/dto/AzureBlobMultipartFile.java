package com.intervlgo.ourfolio.dto;

import com.azure.storage.blob.models.BlobProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RequiredArgsConstructor
public class AzureBlobMultipartFile implements MultipartFile {

    private final InputStream inputStream;
    private final String originalFilename;
    private final long size;

    @Override
    public String getName() {
        return originalFilename;
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public byte[] getBytes() throws IOException {
        throw new UnsupportedOperationException("AzureBlobMultipartFile does not support getBytes()");
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return inputStream;
    }

    @Override
    public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
        throw new UnsupportedOperationException("AzureBlobMultipartFile does not support transferTo()");
    }
}


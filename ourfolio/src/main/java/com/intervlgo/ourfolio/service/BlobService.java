package com.intervlgo.ourfolio.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.intervlgo.ourfolio.dto.AzureBlobMultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class BlobService {

    @Value("${spring.cloud.azure.storage.blob.account-name}")
    private String accountName;

    @Value("${spring.cloud.azure.storage.blob.account-key}")
    private String accountKey;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String blobServiceEndpoint;

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = generateUniqueFileName(file.getOriginalFilename());
        BlobContainerClient containerClient = getBlobContainerClient();
        containerClient.getBlobClient(fileName).upload(file.getInputStream(), file.getSize());
        return fileName;
    }

    public MultipartFile downloadFile(String fileName) throws IOException {
        BlobContainerClient containerClient = getBlobContainerClient();
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        InputStream inputStream = blobClient.openInputStream();
        return new AzureBlobMultipartFile(inputStream, fileName, blobClient.getProperties().getBlobSize());
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = StringUtils.getFilenameExtension(originalFilename);
        return UUID.randomUUID().toString() + "." + extension;
    }

    private BlobContainerClient getBlobContainerClient() {
        String connectionString = String.format("DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;EndpointSuffix=core.windows.net", accountName, accountKey);
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .endpoint(blobServiceEndpoint)
                .buildClient()
                .getBlobContainerClient(containerName);
    }
}
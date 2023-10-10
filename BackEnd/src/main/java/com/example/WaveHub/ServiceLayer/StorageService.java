package com.example.WaveHub.ServiceLayer;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    private final AmazonS3 space;

    public StorageService() {

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("DO00JGRDKCFTEPCEH66J","Bc6JRflEP5iQMaKGKy3pO3BfCuRNyoNFU7LeszQqrqc")
        );

        space = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("fra1.cdn.digitaloceanspaces.com", "fra1")
                )
                .build();

    }

    public List<String> getSongFileNames() {

        ListObjectsV2Result result = space.listObjectsV2("wavehub");
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        return objects.stream()
                .map(S3ObjectSummary::getKey).collect(Collectors.toList());

    }

    public void uploadSong(MultipartFile file) throws IOException {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        space.putObject(new PutObjectRequest("wavehub", file.getOriginalFilename(), file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));

    }
}

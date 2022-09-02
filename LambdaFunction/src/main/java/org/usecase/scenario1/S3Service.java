package org.usecase.scenario1;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Service {
    private S3Client getClient() {
        Region region = Region.US_EAST_1;
        return S3Client.builder()
                .region(region)
                .build();
    }

    public ResponseInputStream<GetObjectResponse> getCSVFileByteArray(String bucketName, String keyName) {
        S3Client s3 = getClient();
        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();
            // Return the byte[] from
            // this object.
            return s3.getObject(objectRequest);
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }
}

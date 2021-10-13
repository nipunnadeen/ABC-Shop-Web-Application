package com.abc.shop.bucket;

public enum BucketName {

    PROFILE_IMAGE("give the bucket name");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}

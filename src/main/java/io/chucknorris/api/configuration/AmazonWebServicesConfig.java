package io.chucknorris.api.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonWebServicesConfig {

  @Value("${aws.access_key_id}")
  private String accessKeyId;

  @Value("${aws.access_key_secret}")
  private String accessKeySecret;

  @Value("${aws.region}")
  private String region;

  /**
   * Returns a new {@link AmazonS3} instance.
   */
  public @Bean AmazonS3 amazonS3() {
    AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);

    return AmazonS3ClientBuilder
        .standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(region)
        .build();
  }

  /**
   * Returns a new {@link AmazonSNSClient} instance.
   */
  public @Bean AmazonSNSClient snsClient() {
    AWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);

    return new AmazonSNSClient(credentials);
  }
}

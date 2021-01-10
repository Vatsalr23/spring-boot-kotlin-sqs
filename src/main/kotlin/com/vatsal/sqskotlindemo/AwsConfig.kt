package com.vatsal.sqskotlindemo

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.regions.Regions.DEFAULT_REGION
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

data class Keys(
	var access: String = "Access Key",
	var secret: String = "Secret Key"
)

data class AwsProperties(
	var keys: Keys = Keys(),
	var region: Regions = DEFAULT_REGION
)

@Configuration
class AwsConfig {

	@Bean
	@ConfigurationProperties(prefix = "aws")
	fun fillCreds(): AwsProperties {
		return AwsProperties()
	}

	@Bean
	fun createAmazonSQS(awsProperties: AwsProperties): AmazonSQS {
		val awsCredentials = BasicAWSCredentials(
			awsProperties.keys.access,
			awsProperties.keys.secret
		)
		return AmazonSQSClientBuilder.standard()
			.withCredentials(AWSStaticCredentialsProvider(awsCredentials))
			.withRegion(awsProperties.region)
			.build()
	}
}

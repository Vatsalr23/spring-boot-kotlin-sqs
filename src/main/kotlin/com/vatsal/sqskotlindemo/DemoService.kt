package com.vatsal.sqskotlindemo

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.DeleteMessageRequest
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class DemoService(
	val sqs: AmazonSQS,
	@Value("\${aws.queue.name}") val queueName: String
) : CommandLineRunner {

	companion object : KLogging()

	override fun run(vararg args: String?) {
		postMessage("Test Message")
		consumeMessage()
	}

	fun getQueueUrl(queueName: String): String {
		return sqs.createQueue(queueName).queueUrl
	}

	fun postMessage(
		message: String = "Hello From Spring Boot!"
	) {
		sqs.sendMessage(getQueueUrl(queueName), message)
		logger.info { "Message sent: $message" }
	}

	fun consumeMessage() {
		val message = sqs.receiveMessage(getQueueUrl(queueName))
		message.messages.forEach {
			logger.info { "Message sent: ${it.body}" }
			sqs.deleteMessage(DeleteMessageRequest(getQueueUrl(queueName), it.receiptHandle))
		}
	}
}

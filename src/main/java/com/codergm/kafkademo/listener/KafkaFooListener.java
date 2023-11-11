package com.codergm.kafkademo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaFooListener {
    @KafkaListener(topics = "codergm", groupId = "foo",
    topicPartitions = @TopicPartition(
            topic = "codergm",
            partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")),
    containerFactory = "kafkaListenerContainerFactory")
    public void listenerGroupFooPartition0(
                                 @Payload String msg,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                 @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                 @Header(KafkaHeaders.GROUP_ID) String groupId) {
        System.out.println("Listener1: Received Message from group foo: " + msg + " from partition " + partition +
                ", topic " + topic + ", groupId " + groupId);
    }

    @KafkaListener(topics = "codergm", groupId = "foo",
            topicPartitions = @TopicPartition(
                    topic = "codergm",
                    partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "0")),
            containerFactory = "kafkaListenerContainerFactory")
    public void listenerGroupFooPartition1(
            @Payload String msg,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.GROUP_ID) String groupId) {
        System.out.println("Listener1: Received Message from group foo: " + msg + " from partition " + partition +
                ", topic " + topic + ", groupId " + groupId);
    }
    @KafkaListener(topics = "codergm", groupId = "foo",
            topicPartitions = @TopicPartition(
                    topic = "codergm",
                    partitionOffsets = @PartitionOffset(partition = "2", initialOffset = "0")),
            containerFactory = "kafkaListenerContainerFactory")
    public void listenerGroupFooPartition2(
            @Payload String msg,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.GROUP_ID) String groupId) {
        System.out.println("Listener1: Received Message from group foo: " + msg + " from partition " + partition +
                ", topic " + topic + ", groupId " + groupId);
    }

    @KafkaListener(topics = "codercm", groupId = "bar", containerFactory = "kafkaListenerContainerFactory")
    public void listenerGroupFoo2(@Payload String msg,
                                  @Header(KafkaHeaders.GROUP_ID) String groupId,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        System.out.println("Listener2: Received Message from group bar: " + msg + " from group " + groupId +
                ", topic " + topic);
    }
}

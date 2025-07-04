package com.kafka.demo.consumer;
import org.apache.commons.lang3.SerializationUtils;

import com.kafka.demo.model.Student;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class StudentConsumer {

    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";
        String topic = "student-topic";
        String groupId = "student-group";

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put("auto.offset.reset", "earliest");

        try (KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(topic));

            System.out.println("Waiting for messages...");
            while (true) {
                ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, byte[]> record : records) {
                    Student student = (Student) SerializationUtils.deserialize(record.value());
                    System.out.println("Received student: " + student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

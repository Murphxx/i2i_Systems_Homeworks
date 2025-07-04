package com.kafka.demo.producer;
import org.apache.commons.lang3.SerializationUtils;

import com.kafka.demo.model.Student;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class StudentProducer {

    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";

        String topic = "student-topic";

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        try (KafkaProducer<String, byte[]> producer = new KafkaProducer<>(props)) {

            Student student = new Student(1, "Eray");

            byte[] serializedStudent = SerializationUtils.serialize(student);

            ProducerRecord<String, byte[]> record = new ProducerRecord<>(topic, serializedStudent);

            Future<RecordMetadata> future = producer.send(record);

            RecordMetadata metadata = future.get();
            System.out.println("Message sent to topic " + metadata.topic() + " partition " + metadata.partition() + " offset " + metadata.offset());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

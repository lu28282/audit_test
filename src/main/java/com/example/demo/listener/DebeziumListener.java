package com.example.demo.listener;

import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.debezium.data.Envelope.FieldName.*;
import static io.debezium.data.Envelope.Operation;

// import com.example.demo.service.CustomerService;

@Slf4j
@Component
public class DebeziumListener {

    private final Executor executor = Executors.newSingleThreadExecutor();
    // private final CustomerService customerService;
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

    // public DebeziumListener(Configuration customerConnectorConfiguration, CustomerService customerService) throws IOException {
    public DebeziumListener() throws IOException {

        // ============================
        // Configuration for mysql connector
        File offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        File dbHistoryTempFile = File.createTempFile("dbhistory_", ".dat");
        final Properties props = new Properties();
        props.setProperty("name", "engine");
        props.setProperty("connector.class", "io.debezium.connector.mysql.MySqlConnector");
        props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
        props.setProperty("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath());
        props.setProperty("offset.flush.interval.ms", "60000");
        /* begin connector properties */
        props.setProperty("database.hostname", "localhost");
        props.setProperty("database.hostname", "localhost");
        props.setProperty("database.port", "3306");
        props.setProperty("database.user", "user");
        props.setProperty("database.password", "password");
        props.setProperty("include.schema.changes", "false");
        props.setProperty("database.server.id", "1");
        props.setProperty("topic.prefix", "my-app-connector");
        props.setProperty("database.allowPublicKeyRetrieval", "true");
        props.setProperty("schema.history.internal",
                "io.debezium.storage.file.history.FileSchemaHistory");
        props.setProperty("schema.history.internal.file.filename", dbHistoryTempFile.getAbsolutePath());
        // ============================

        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(props)
                .notifying(this::handleChangeEvent)
                .build();


        // this.customerService = customerService;
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();

        log.info("Key = '" + sourceRecord.key() + "' value = '" + sourceRecord.value() + "'");
        System.out.println("Key = '" + sourceRecord.key() + "' value = '" + sourceRecord.value() + "'");

        Struct sourceRecordChangeValue = (Struct) sourceRecord.value();

        if (sourceRecordChangeValue != null) {
            Operation operation = Operation.forCode((String) sourceRecordChangeValue.get(OPERATION));

            // if(operation != Operation.READ) {
            // String record = operation == Operation.DELETE ? BEFORE : AFTER; // Handling
            // Update & Insert operations.

            // Struct struct = (Struct) sourceRecordChangeValue.get(record);
            // Map<String, Object> payload = struct.schema().fields().stream()
            // .map(Field::name)
            // .filter(fieldName -> struct.get(fieldName) != null)
            // .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
            // .collect(toMap(Pair::getKey, Pair::getValue));

            // this.customerService.replicateData(payload, operation);
            // log.info("Updated Data: {} with Operation: {}", payload, operation.name());
            // }
        }
    }

    @PostConstruct
    private void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (this.debeziumEngine != null) {
            this.debeziumEngine.close();
        }
    }

}

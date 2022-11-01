package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumConnectorConfig {

    /**
     * Database details.
     */
    @Value("${customer.datasource.host}")
    private String customerDbHost;

    @Value("${customer.datasource.database}")
    private String customerDbName;

    @Value("${customer.datasource.port}")
    private String customerDbPort;

    @Value("${customer.datasource.username}")
    private String customerDbUsername;

    @Value("${customer.datasource.password}")
    private String customerDbPassword;

    // /**
    //  * Customer Database Connector Configuration
    //  * Adapt connector class to fit source database.
    //  * Adapt offsetStorage to change data processing
    //  */
    // @Bean
    // public io.debezium.config.Configuration customerConnector() throws IOException {
    //     File offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
    //     File dbHistoryTempFile = File.createTempFile("dbhistory_", ".dat");
    //     System.out.println("==========================================================================================");
    //     System.out.println(customerDbHost);
    //     System.out.println(customerDbPort);
    //     System.out.println(customerDbUsername);
    //     System.out.println(customerDbPassword);
    //     System.out.println(customerDbName);
    //     System.out.println(offsetStorageTempFile.getAbsolutePath());
    //     System.out.println(dbHistoryTempFile.getAbsolutePath());
    //     System.out.println("==========================================================================================");
        
    //     return io.debezium.config.Configuration.create()
    //             .with("name", "engine")
    //             .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
    //             .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
    //             .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
    //             .with("database.hostname", customerDbHost)
    //             .with("database.port", customerDbPort)
    //             .with("database.user", customerDbUsername)
    //             .with("database.password", customerDbPassword)
    //             .with("database.server.id", "1")
    //             .with("schema.history.internal",
    //             "io.debezium.storage.file.history.FileSchemaHistory")
    //             .with("database.history.internal.file.filename", dbHistoryTempFile.getAbsolutePath())
    //             .build();
    //     // return io.debezium.config.Configuration.create()
    //     //         .with("name", "customer-mysql-connector")
    //     //         .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
    //     //         .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
    //     //         .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
    //     //         .with("offset.flush.interval.ms", "60000")
    //     //         .with("database.hostname", customerDbHost)
    //     //         .with("database.port", customerDbPort)
    //     //         .with("database.user", customerDbUsername)
    //     //         .with("database.password", customerDbPassword)
    //     //         .with("database.dbname", customerDbName)
    //     //         .with("database.include.list", customerDbName)
    //     //         .with("include.schema.changes", "false")
    //     //         .with("database.allowPublicKeyRetrieval", "true")
    //     //         .with("database.server.id", "1")
    //     //         .with("database.server.name", "customerdb")
    //     //         .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
    //     //         .with("database.history.file.filename", dbHistoryTempFile.getAbsolutePath())
    //     //         .build();
    // }
}
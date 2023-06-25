package com.example.springmongodb;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
public record NetworkDeviceRecord(
        @BsonId()
        @BsonRepresentation(BsonType.OBJECT_ID)
        String deviceId,
        String name,
        @BsonProperty("type")
        String deviceType
)
{}
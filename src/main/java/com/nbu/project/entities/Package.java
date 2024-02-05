package com.nbu.project.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

public record Package(
        int id,
        String status,
        double weight,
        double price,
        Date dateOfPayment,
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
        Timestamp timeOfPayment,
        String senderEmail,
        String receiverEmail,
        int deliveryAddressId,
        String registerEmail,
        String courierEmail
) { }

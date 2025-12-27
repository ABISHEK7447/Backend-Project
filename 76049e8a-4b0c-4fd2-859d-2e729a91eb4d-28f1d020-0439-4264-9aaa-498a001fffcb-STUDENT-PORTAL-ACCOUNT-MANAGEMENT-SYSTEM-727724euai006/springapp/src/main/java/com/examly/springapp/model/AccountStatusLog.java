package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class AccountStatusLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusLogId;
}

package com.ra.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Timing {
    private String countdownServerAdjustment;
    private LocalDateTime source;
    private LocalDateTime insert;
    private LocalDateTime read;
    private LocalDateTime sent;
    private LocalDateTime received;
}

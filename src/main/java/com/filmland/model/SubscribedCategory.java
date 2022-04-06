package com.filmland.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SubscribedCategory {
    private String name;
    private Double price;
    private LocalDate startDate;
    private LocalDate paymentDate;
    private Integer remainingContent;
}

package com.phimmoi.techwizapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_name", length = 50)
    private String packageName;

    @Column(name = "price_per_month")
    private Float pricePerMonth;

    @Column(name = "duration_months")
    private Integer durationMonths;

    @Column(name = "ultra_hd")
    private Boolean ultraHd;

    @Column(name = "sale")
    private Integer sale;

    @Column(name = "limited_availability")
    private Boolean limitedAvailability;

    @Column(name = "any_device")
    private Boolean anyDevice;

    @Column(name = "support_24_7")
    private Boolean support24_7;
}

package com.example.ltw.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "service_bill_row")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrderBillRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "service_order_id")
    private long serviceOrderId;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "start_date")
    private long startDate;
    @Column(name = "end_date")
    private long endDate;
    @Column(name = "number_day")
    private long numberDay;
    @Column(name = "ti_suat")
    private float tiSuat;
    @Column(name = "number_user")
    private long soNguoiSuDung;
    @Column(name = "square")
    private long dienTichSan;
    @Column(name = "total")
    private double total;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "service_order_bill_id")
    private ServiceOrderBill serviceOrderBill;
}

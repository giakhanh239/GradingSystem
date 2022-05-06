package com.example.ltw.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "service_order_bill")
@Data
@Getter
@Setter
@NoArgsConstructor
public class ServiceOrderBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "total")
    private double total;
    @Column(name = "date_create")
    private long dateCreate;
    @Column(name = "companyId")
    private long companyId;
    @OneToMany(mappedBy = "serviceOrderBill", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ServiceOrderBillRow> serviceOrderList;
    @Column(name = "status")
    private boolean status;
}

package com.hahaup.api.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "partner")
public class PartnerEntity {

    @Id
    @GeneratedValue(generator = "partner_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "partner_seq", sequenceName = "partner_seq", allocationSize = 100)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "fio")
    private String fio;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "external_user_id")
    private String externalUserId;

    @Column(name = "external_layout_id")
    private String externalLayoutId;

    @Column(name = "photo_url")
    private String photoUrl;
}

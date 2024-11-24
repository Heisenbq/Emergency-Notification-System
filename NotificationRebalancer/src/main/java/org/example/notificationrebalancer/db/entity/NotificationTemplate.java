package org.example.notificationrebalancer.db.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notificationtemplates")
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long id;

    private String name;

    @Column(name = "body")
    private String text;
    // куда отправлять mail or sms
    private String type;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }
}

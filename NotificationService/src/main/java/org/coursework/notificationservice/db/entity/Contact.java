package org.coursework.notificationservice.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")

    private Long id;
    private String contactName;
    private String email;
    private String phone;

    //  можно выставить активный контакт или нет
    private String status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }


    public Timestamp getUpdatedAt() {
        return updatedAt;
    }


    @Override
    public String toString() {
        return "ContactEntity{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(contactName, contact.contactName) && Objects.equals(email, contact.email) && Objects.equals(phone, contact.phone) && Objects.equals(status, contact.status) && Objects.equals(createdAt, contact.createdAt) && Objects.equals(updatedAt, contact.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactName, email, phone, status, createdAt, updatedAt);
    }
}

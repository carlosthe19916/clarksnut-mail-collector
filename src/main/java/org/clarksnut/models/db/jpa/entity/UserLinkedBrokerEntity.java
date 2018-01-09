package org.clarksnut.models.db.jpa.entity;

import org.clarksnut.models.BrokerType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "cl_user_linked_broker", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"user_id", "type"})
})
@NamedQueries({
        @NamedQuery(name = "getAllLinkedBrokers", query = "select b from UserLinkedBrokerEntity b join fetch b.user u order by b.email")
})
public class UserLinkedBrokerEntity {

    @Id
    @Access(AccessType.PROPERTY)
    @Column(name = "id", length = 36)
    private String id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BrokerType type;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey)
    private UserEntity user;

    @Type(type = "org.hibernate.type.LocalDateTimeType")
    @Column(name = "last_time_synchronized")
    private LocalDateTime lastTimeSynchronized;

    @Version
    @Column(name = "version")
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BrokerType getType() {
        return type;
    }

    public void setType(BrokerType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getLastTimeSynchronized() {
        return lastTimeSynchronized;
    }

    public void setLastTimeSynchronized(LocalDateTime lastTimeSynchronized) {
        this.lastTimeSynchronized = lastTimeSynchronized;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

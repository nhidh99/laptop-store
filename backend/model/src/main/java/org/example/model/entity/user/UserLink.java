package org.example.model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_link")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLink implements Serializable {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "facebook_id", length = 30)
    private String facebookId;

    @Column(name = "google_id", length = 30)
    private String googleId;
}

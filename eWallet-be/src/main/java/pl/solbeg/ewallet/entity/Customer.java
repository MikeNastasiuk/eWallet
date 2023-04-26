package pl.solbeg.ewallet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private byte[] password;

    @Column(name = "salt")
    private byte[] salt;
}

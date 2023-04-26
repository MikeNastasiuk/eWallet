package pl.solbeg.ewallet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_history")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountHistory {

    @Id
    @SequenceGenerator(name = "acc_history_seq", sequenceName = "account_history_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_history_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "operation_date")
    private LocalDateTime operationDate;

    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    private OperationEnum operation;

    @Column(name = "quantity")
    private Double quantity;
}

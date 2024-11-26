package src.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import src.model.enums.TransactionType;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
    private int id;
    private TransactionType type;
    private int amount;
    private LocalDateTime time;
    private Wallet wallet;

}

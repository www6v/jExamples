package ddd.refactor.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Date;

@Value
@AllArgsConstructor
public class AuditMessage {

    private UserId userId = null;
    private AccountNumber source= null;
    private AccountNumber target= null;
    private Money money= null;
    private Date date= null;

    public AuditMessage(Account s, Account t, Money m) {

    }

    public String serialize() {
        return userId + "," + source + "," + target + "," + money + "," + date;
    }

    public static AuditMessage deserialize(String value) {
        // todo
        return null;
    }
}

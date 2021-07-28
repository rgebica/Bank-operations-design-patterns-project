package pl.bank.demo.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.bank.demo.account.domain.AccountTypeSingleton;
import pl.bank.demo.account.visitor.Visitor;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndividualAccountCreateDto {
    String firstName;
    String lastName;
    Long userId;
    String taxNumber;
    String phoneNumber;
    String email;
    String login;
    String password;
    AccountTypeSingleton accountTypeSingleton;
    String code;

    public String accept(Visitor visitor) {
        return visitor.visitIndividualAccount(this);
    }
}

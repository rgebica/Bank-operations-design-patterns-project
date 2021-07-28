package pl.bank.demo.account.domain;

import lombok.Builder;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;

@Entity(name = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;
    String firstName;
    String lastName;
    Long userId;
    String taxNumber;
    String phoneNumber;
    String email;
    String login;
    String password;
    String accountType;
    Long balance;
    String accountNumber;
    String code;
    Boolean loginStatus;
    static Account instance;

    public Account() {
    }

    private Account(Builder builder) {
    accountId = builder.accountId;
    firstName = builder.firstName;
    lastName = builder.lastName;
    userId = builder.userId;
    taxNumber = builder.taxNumber;
    phoneNumber = builder.phoneNumber;
    email = builder.email;
    login = builder.login;
    password = builder.password;
    accountType = builder.accountType;
    balance = builder.balance;
    accountNumber = builder.accountNumber;
    code = builder.code;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public Long getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCode() {
        return code;
    }

    public Boolean getLoginStatus() {
        return loginStatus;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public static final class Builder {
        Long accountId;
        String firstName;
        String lastName;
        Long userId;
        String taxNumber;
        String phoneNumber;
        String email;
        String login;
        String password;
        String accountType;
        Long balance;
        String accountNumber;
        String code;

        public Builder() {

        }

        public Builder buildAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder buildFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder buildLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder buildUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder buildTaxNumber(String taxNumber) {
            this.taxNumber = taxNumber;
            return this;
        }

        public Builder buildPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder buildEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder buildLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder buildAccountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder buildBalance(Long balance) {
            this.balance = balance;
            return this;
        }

        public Builder buildAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder buildCode(String code) {
            this.code = code;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }
}

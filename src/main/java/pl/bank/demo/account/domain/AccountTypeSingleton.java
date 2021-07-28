package pl.bank.demo.account.domain;

public class AccountTypeSingleton {

    private static AccountTypeSingleton account_type_single_instance = null;

    String accountTypeIndividual;
    String accountTypeCompany;

    public AccountTypeSingleton() {
        accountTypeIndividual = "INDIVIDUAL";
        accountTypeCompany = "COMPANY";
    }

    public static AccountTypeSingleton getInstance()
    {
        if (account_type_single_instance == null)
            account_type_single_instance = new AccountTypeSingleton();

        return account_type_single_instance;
    }
}

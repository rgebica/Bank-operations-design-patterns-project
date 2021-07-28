package pl.bank.demo.account.visitor;

import pl.bank.demo.account.dto.CompanyAccountCreateDto;
import pl.bank.demo.account.dto.IndividualAccountCreateDto;

public interface Visitor {
    String visitCompanyAccount(CompanyAccountCreateDto companyAccountCreate);
    String visitIndividualAccount(IndividualAccountCreateDto individualAccountCreateDto);
}

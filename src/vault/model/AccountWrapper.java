package vault.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Accounts")
public class AccountWrapper {

    private List<Account> accounts;

    @XmlElement(name = "account")
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> account) {
        this.accounts = account;
    }
}
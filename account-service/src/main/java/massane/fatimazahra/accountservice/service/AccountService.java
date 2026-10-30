package fatimazahra.accountservice.service;

import fatimazahra.accountservice.domain.Account;
import fatimazahra.accountservice.domain.AccountStatus;
import fatimazahra.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        if (account.getStatus() == null) {
            account.setStatus(AccountStatus.ACTIVE);
        }
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable avec l'ID : " + id));
    }

    // Méthode pour vérifier si le compte est actif (utile pour les transactions)
    public Account getActiveAccount(UUID id) {
        Account account = getAccountById(id);
        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new RuntimeException("Le compte n'est pas actif");
        }
        return account;
    }

    // Méthode pour mettre à jour le solde
    public Account updateBalance(UUID id, double amount) {
        Account account = getAccountById(id);
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }
}
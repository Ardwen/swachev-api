package com.evliion.ev.repository;

import com.evliion.ev.model.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository  extends JpaRepository<UserWallet, Long> {
}

package com.evliion.ev.model;

import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity(name = "user_wallets")
public class UserWallet {

  @Id
  private Long userId;

  @OneToOne
  @MapsId
  private User user;

  @NotBlank
  private String userWalletToken;

  @ElementCollection
  @CollectionTable(name="user_wallet_cards", joinColumns=@JoinColumn(name="user_id"))
  private Set<String> cardTokens;

  public UserWallet() {

  }

  public UserWallet(User user, String userWalletToken, Set<String> cardTokens) {
    this.user = user;
    this.userId = user.getId();
    this.userWalletToken = userWalletToken;
    this.cardTokens = cardTokens;
  }

  public String getUserWalletToken() {
    return userWalletToken;
  }

  public Long getUserId() {
    return userId;
  }

  public User getUser() {
    return user;
  }

  public Set<String> getCardTokens() {
    return cardTokens;
  }
}

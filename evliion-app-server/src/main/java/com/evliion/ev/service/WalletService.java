package com.evliion.ev.service;

import com.evliion.ev.exception.BadRequestException;
import com.evliion.ev.model.User;
import com.evliion.ev.model.UserWallet;
import com.evliion.ev.payload.AddAchRequest;
import com.evliion.ev.payload.AddCardRequest;
import com.evliion.ev.payload.BillingAddressRequest;
import com.evliion.ev.payload.BillingAddressResponse;
import com.evliion.ev.payload.FundSourceResponse;
import com.evliion.ev.payload.RechargeRequest;
import com.evliion.ev.payload.TransactRequest;
import com.evliion.ev.payload.marqeta.AddFundingSourceAddressRequest;
import com.evliion.ev.payload.marqeta.LoadFundsRequest;
import com.evliion.ev.payload.marqeta.PeerTransferRequest;
import com.evliion.ev.payload.marqeta.UserBalanceResponse;
import com.evliion.ev.payload.marqeta.UserFundSourceResponse;
import com.evliion.ev.payload.marqeta.UserWalletRequest;
import com.evliion.ev.repository.UserRepository;
import com.evliion.ev.repository.WalletRepository;
import com.evliion.ev.security.UserPrincipal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
  private UserRepository userRepository;
  private MarqetaService marqetaService;
  private WalletRepository walletRepository;
  private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

  public WalletService(UserRepository userRepository, MarqetaService marqetaService,
                       WalletRepository walletRepository) {
    this.userRepository = userRepository;
    this.marqetaService = marqetaService;
    this.walletRepository = walletRepository;
  }

  public void activateWallet() {
    User user = getCurrentUser();

    Optional<UserWallet> walletOptional = getWalletById(user.getId());

    //User wallet activation already happened
    if (walletOptional.isPresent()) {
      throw new BadRequestException("User userWallet activated already");
    } else {
      UserWalletRequest userRequest = new UserWalletRequest(user.getEmail(), user.getName());
      String userToken = marqetaService.createUser(userRequest);
      UserWallet userWallet;
      try {
        userWallet = new UserWallet(user, userToken, new HashSet<>());
        walletRepository.save(userWallet);
      } catch (RuntimeException e) {
        throw e;
      }
    }
  }

  public void activateWalletCard() {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet before adding card");
    } else {
      String cardToken = marqetaService.createCard(wallet.get().getUserWalletToken());
      wallet.get().getCardTokens().add(cardToken);
      walletRepository.save(wallet.get());
    }
  }

  private Optional<UserWallet> getWallet() {
    User user = getCurrentUser();
    return getWalletById(user.getId());
  }

  private Optional<UserWallet> getWalletById(Long id) {
    return walletRepository.findById(id);
  }

  public void addBankAccount(AddAchRequest request) {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet before adding bank");
    } else {
      marqetaService.addAchFundSource(request, wallet.get().getUserWalletToken());
    }
  }

  public void addPaymentCard(AddCardRequest request) {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet before adding card as"
          + " funding source");
    } else {
      marqetaService.addCardFundSource(request, wallet.get().getUserWalletToken());
    }
  }

  public void addBillingAddress(BillingAddressRequest request) {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet before adding billing address");
    } else {
      marqetaService.addFundSourceAddress(request, wallet.get().getUserWalletToken());
    }
  }

  public void rechargeWallet(RechargeRequest request) {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet before recharging");
    } else {
      UserWallet userWallet = wallet.get();
      LoadFundsRequest loadFundsRequest = new LoadFundsRequest(userWallet.getUserWalletToken(),
          request.getFundSourceId(), request.getBillingAddressId(), request.getCurrency(), request.getAmount());
      marqetaService.loadFunds(loadFundsRequest);
    }
  }

  public void transferMoney(TransactRequest request) {
    Optional<UserWallet> senderWallet = getWallet();
    if (!senderWallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet before transferring money");
    } else {
      Optional<UserWallet> merchantOptional = getWalletById(request.getMerchantId());
      if (!merchantOptional.isPresent()) {
        throw new BadRequestException("Invalid merchant id '" + request.getMerchantId() + "'");
      } else {
        marqetaService.transferFunds(new PeerTransferRequest(senderWallet.get().getUserWalletToken(),
            merchantOptional.get().getUserWalletToken(), request.getCurrency(), request.getAmount()));
      }
    }
  }

  public UserBalanceResponse getUserAccountBalance() {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet to get wallet balance");
    } else {
      UserBalanceResponse response = marqetaService.getBalance(wallet.get().getUserWalletToken());
      return response;
    }
  }

  public FundSourceResponse[] getUserWalletFundSources() {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet to get the fund sources");
    } else {
      UserFundSourceResponse.UserFundSource[] response = marqetaService.getFundingSources(wallet.get()
          .getUserWalletToken());
      return Arrays.stream(response).map(fundSource -> new FundSourceResponse()
          .setAccountSuffix(fundSource.getAccountSuffix())
          .setCreatedTime(fundSource.getCreatedTime())
          .setExpDate(fundSource.getExpDate())
          .setId(fundSource.getToken())
          .setLastModifiedTime(fundSource.getLastModifiedTime())
          .setNameOnAccount(fundSource.getNameOnAccount())
          .setType(fundSource.getType())).toArray(FundSourceResponse[]::new);
    }
  }

  public BillingAddressResponse[] getUserBillingAddresses() {
    Optional<UserWallet> wallet = getWallet();
    if (!wallet.isPresent()) {
      throw new BadRequestException("User wallet is not activated yet. Activate wallet to get the fund sources");
    } else {
      AddFundingSourceAddressRequest[] response = marqetaService.getFundingSourceAddresses(wallet.get()
          .getUserWalletToken());
      return Arrays.stream(response).map(billingAddress -> new BillingAddressResponse()
              .setAddress1(billingAddress.getAddress1())
              .setAddress2(billingAddress.getAddress2())
              .setCity(billingAddress.getCity())
              .setCountry(billingAddress.getCountry())
              .setId(billingAddress.getToken())
              .setFirstName(billingAddress.getFirstName())
              .setLastName(billingAddress.getLastName())
              .setPostalCode(billingAddress.getPostalCode())
              .setState(billingAddress.getState())
              .setZip(billingAddress.getZip())
      ).toArray(BillingAddressResponse[]::new);
    }
  }

  private User getCurrentUser() {
    UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Optional<User> user = userRepository.findById(userPrincipal.getId());
    if (!user.isPresent()) {
      logger.error("Not able to find user id '" + userPrincipal.getId() + "' set in authentication");
      throw new RuntimeException("Authenticated user not found");
    }
    return user.get();
  }
}

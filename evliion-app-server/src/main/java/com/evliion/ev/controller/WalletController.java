package com.evliion.ev.controller;

import com.evliion.ev.payload.AddAchRequest;
import com.evliion.ev.payload.AddCardRequest;
import com.evliion.ev.payload.ApiResponse;
import com.evliion.ev.payload.BillingAddressRequest;
import com.evliion.ev.payload.GetWalletResponse;
import com.evliion.ev.payload.RechargeRequest;
import com.evliion.ev.payload.TransactRequest;
import com.evliion.ev.payload.marqeta.UserBalanceResponse;
import com.evliion.ev.service.WalletService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

  private WalletService walletService;

  public WalletController(WalletService walletService) {
    this.walletService = walletService;
  }

  @PostMapping("/activate")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> activateWallet() {
    walletService.activateWallet();
    return new ResponseEntity<>(new ApiResponse(true, "User wallet activated successfully"), HttpStatus.OK);
  }

  @PostMapping("/card")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> activateCard() {
    walletService.activateWalletCard();
    return new ResponseEntity<>(new ApiResponse(true, "Card added to user wallet successfully"), HttpStatus.OK);
  }

  @PostMapping("/billing_address")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> addBillingAddress(
      @RequestBody @Valid BillingAddressRequest billingAddressRequest) {
    walletService.addBillingAddress(billingAddressRequest);
    return new ResponseEntity<>(new ApiResponse(true, "Billing address added successfully"), HttpStatus.OK);
  }

  @PostMapping("/fund_source/ach")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> addWalletFundsSource(@RequestBody @Valid AddAchRequest addAchRequest) {
    walletService.addBankAccount(addAchRequest);
    return new ResponseEntity<>(new ApiResponse(true, "Added bank to user wallet successfully"), HttpStatus.OK);
  }

  @PostMapping("/fund_source/card")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> addWalletFundsSource(@RequestBody @Valid AddCardRequest addCardRequest) {
    walletService.addPaymentCard(addCardRequest);
    return new ResponseEntity<>(new ApiResponse(true, "Added card to user wallet successfully"), HttpStatus.OK);
  }

  @PostMapping("/recharge")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> rechargeWallet(@RequestBody @Valid RechargeRequest rechargeRequest) {
    walletService.rechargeWallet(rechargeRequest);
    return new ResponseEntity<>(new ApiResponse(true, "User wallet recharged successfully"), HttpStatus.OK);
  }

  @PostMapping("/transact")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<ApiResponse> transactWallet(@RequestBody @Valid TransactRequest transactRequest) {
    walletService.transferMoney(transactRequest);
    return new ResponseEntity<>(new ApiResponse(true, "UserWallet transacted successfully"), HttpStatus.OK);
  }

  @GetMapping("/balance")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<UserBalanceResponse> walletBalance() {
    UserBalanceResponse response = walletService.getUserAccountBalance();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<GetWalletResponse> wallet() {
    GetWalletResponse response = new GetWalletResponse()
        .setFundSources(walletService.getUserWalletFundSources())
        .setBillingAddresses(walletService.getUserBillingAddresses());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}

package com.evliion.ev.service;

import com.evliion.ev.payload.AddAchRequest;
import com.evliion.ev.payload.AddCardRequest;
import com.evliion.ev.payload.BillingAddressRequest;
import com.evliion.ev.payload.marqeta.AchRequest;
import com.evliion.ev.payload.marqeta.AddFundingSourceAddressRequest;
import com.evliion.ev.payload.marqeta.CreateCardRequest;
import com.evliion.ev.payload.marqeta.GetUserFundSourceAddresses;
import com.evliion.ev.payload.marqeta.LoadFundsRequest;
import com.evliion.ev.payload.marqeta.PaymentCardRequest;
import com.evliion.ev.payload.marqeta.PeerTransferRequest;
import com.evliion.ev.payload.marqeta.UserBalanceResponse;
import com.evliion.ev.payload.marqeta.UserFundSourceResponse;
import com.evliion.ev.payload.marqeta.UserWalletRequest;
import com.evliion.ev.payload.marqeta.MarqetaResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Objects;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarqetaService {
  private String serverUrl;

  private String appToken;

  private String accessToken;

  private RestTemplate restTemplate;

  private HttpHeaders headers;

  private String cardProductToken;

  private static final Logger logger = LoggerFactory.getLogger(MarqetaService.class);

  public MarqetaService(@Value("${marqeta.url}") String serverUrl,
                        @Value("${marqeta.application.token}") String appToken,
                        @Value("${marqeta.master.access.token}") String accessToken,
                        @Value("${marqeta.card.product.token}") String cardProductToken,
                        RestTemplate restTemplate, ObjectMapper objectMapper) {
    this.serverUrl = serverUrl;
    this.appToken = appToken;
    this.accessToken = accessToken;
    this.restTemplate = restTemplate;
    this.cardProductToken = cardProductToken;
    headers = getHeaders(this.appToken, this.accessToken);
    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
  }

  public String createUser(UserWalletRequest userRequest) {
    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl + "/users", HttpMethod.POST,
        new HttpEntity<>(userRequest, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in creating user. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in creating user. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Created user successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public String createCard(String userToken) {
    CreateCardRequest createCardRequest = new CreateCardRequest(userToken, cardProductToken);
    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl +"/cards", HttpMethod.POST,
        new HttpEntity<>(createCardRequest, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in creating card. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in creating card. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Created card successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public String loadFunds(LoadFundsRequest loadFundsRequest) {
    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl +"/gpaorders", HttpMethod.POST,
        new HttpEntity<>(loadFundsRequest, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in loading funds. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in loading funds. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Loaded funds successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public String transferFunds(PeerTransferRequest peerTransferRequest) {
    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl +"/peertransfers", HttpMethod.POST,
        new HttpEntity<>(peerTransferRequest, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in peer funds transfer. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in peer funds transfer. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Peer funds transfer completed successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public String addAchFundSource(AddAchRequest addAchRequest, String userToken) {
    AchRequest request = new AchRequest()
        .setAccountNumber(addAchRequest.getAccountNumber())
        .setAccountType(addAchRequest.getAccountType().getValue())
        .setBankName(addAchRequest.getBankName())
        .setNameOnAccount(addAchRequest.getNameOnAccount())
        .setRoutingNumber(addAchRequest.getRoutingNumber())
        .setUserToken(userToken)
        .setVerificationOverride(true)
        .setDefaultAccount(addAchRequest.isDefaultAccount());

    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl +"/fundingsources/ach", HttpMethod.POST,
        new HttpEntity<>(request, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in adding ACH fund source. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in adding ACH fund source. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Added ACH fund source successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public String addCardFundSource(AddCardRequest addCardRequest, String userToken) {

    PaymentCardRequest request = new PaymentCardRequest()
        .setAccountNumber(addCardRequest.getAccountNumber())
        .setCvvNumber(addCardRequest.getCvvNumber())
        .setExpiryDate(addCardRequest.getExpMonth() + addCardRequest.getExpYear())
        .setUserToken(userToken);

    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl +"/fundingsources/paymentcard",
        HttpMethod.POST, new HttpEntity<>(request, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in adding payment card fund source. Error message : "
            + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in adding payment card fund source. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Added payment card fund source successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public String addFundSourceAddress(BillingAddressRequest billingAddressRequest, String userToken) {

    AddFundingSourceAddressRequest request = new AddFundingSourceAddressRequest()
        .setAddress1(billingAddressRequest.getAddress1())
        .setAddress2(billingAddressRequest.getAddress2())
        .setCity(billingAddressRequest.getCity())
        .setCountry(billingAddressRequest.getCountry())
        .setFirstName(billingAddressRequest.getFirstName())
        .setLastName(billingAddressRequest.getLastName())
        .setPostalCode(billingAddressRequest.getPostalCode())
        .setZip(billingAddressRequest.getZip())
        .setState(billingAddressRequest.getState())
        .setUserToken(userToken);

    ResponseEntity<MarqetaResponse> response = restTemplate.exchange(serverUrl +"/fundingsources/addresses",
        HttpMethod.POST, new HttpEntity<>(request, headers), MarqetaResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in adding fund source address. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in adding fund source address. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Added fund source address successfully");
      return Objects.requireNonNull(response.getBody()).getToken();
    }
  }

  public UserBalanceResponse getBalance(String userToken) {
    ResponseEntity<UserBalanceResponse> response = restTemplate.exchange(serverUrl +"/balances/" + userToken,
        HttpMethod.GET, new HttpEntity<>(headers), UserBalanceResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in getting user balance. Error message : " + response.getBody().getErrorMessage();
      } else {
        errorMessage = "Error in getting user balance. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("Account balance fetched successfully");
      return Objects.requireNonNull(response.getBody());
    }
  }

  public UserFundSourceResponse.UserFundSource[] getFundingSources(String userToken) {
    ResponseEntity<UserFundSourceResponse> response = restTemplate.exchange(serverUrl +"/fundingsources/user/"
        + userToken, HttpMethod.GET, new HttpEntity<>(headers), UserFundSourceResponse.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in getting user fund source. Error message : " + response.getBody();
      } else {
        errorMessage = "Error in getting user fund source. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("User fund sources fetched successfully");
      return Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getData());
    }
  }

  public AddFundingSourceAddressRequest[] getFundingSourceAddresses(String userToken) {
    ResponseEntity<GetUserFundSourceAddresses> response = restTemplate.exchange(serverUrl
        + "/fundingsources/addresses/user/" + userToken, HttpMethod.GET, new HttpEntity<>(headers),
        GetUserFundSourceAddresses.class);
    if (response.getStatusCode().isError()) {
      String errorMessage;
      if (Objects.nonNull(response.getBody())) {
        errorMessage = "Error in getting user fund source addresses. Error message : " + response.getBody();
      } else {
        errorMessage = "Error in getting user fund source addresses. Status code : " + response.getStatusCode();
      }
      logger.error(errorMessage);
      throw new RuntimeException(errorMessage);
    } else {
      logger.debug("User fund sources fetched successfully");
      return Objects.requireNonNull(Objects.requireNonNull(response.getBody()).getData());
    }
  }

  private HttpHeaders getHeaders(String username, String password) {
    String credentials = username + ":" + password;
    byte[] credentialsBytes = credentials.getBytes();
    byte[] base64CredsBytes = Base64.encodeBase64(credentialsBytes);
    String base64Creds = new String(base64CredsBytes);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Basic " + base64Creds);
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return headers;
  }
}

CREATE TABLE `user_wallets` (
  `user_token` varchar(64) NOT NULL,
  `user_id` bigint(20) NOT NULL UNIQUE,
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`.`id`
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_wallet_fund_sources` (
  `card_token` varchar(64) NOT NULL,
  `user_id` bigint(20) NOT NULL UNIQUE,
  `id` bigint(20) AUTO_INCREMENT,
  `is_default` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`.`id`
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_wallet_cards` (
  `card_token` varchar(64) NOT NULL,
  `user_id` bigint(20) NOT NULL UNIQUE,
  PRIMARY KEY (`user_id`, `card_token`),
  FOREIGN KEY (`user_id`) REFERENCES `users`.`id`
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
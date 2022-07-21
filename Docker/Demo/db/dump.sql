CREATE DATABASE IF NOT EXISTS `zinkworks` DEFAULT CHARACTER SET utf8;

USE `zinkworks`;

DROP TABLE IF EXISTS `account_details`;

CREATE TABLE `account_details` (
  `id` bigint NOT NULL,
  `actual_balance` bigint NOT NULL,
  `overdraft` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


insert  into `account_details`(`id`,`actual_balance`,`overdraft`) values
(1, 800, 200),
(2, 1230, 150);

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` bigint NOT NULL,
  `account_details` bigint NOT NULL,
  `account_number` varchar(50) NOT NULL,
  `pin` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `account_details` (`account_details`),
  CONSTRAINT `fk_account_details_account` FOREIGN KEY (`account_details`) REFERENCES
  `account_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


insert  into `account`(`id`,`account_details`,`account_number`,`pin`) values
(1,1,'123456789','1234'),
(2,2,'987654321','4321');

DROP TABLE IF EXISTS `atm`;

CREATE TABLE `atm` (
  `id` bigint NOT NULL,
  `euro_fifty_count` bigint NOT NULL,
  `euro_twenty_count` bigint NOT NULL,
  `euro_ten_count` bigint NOT NULL,
  `euro_five_count` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


insert  into `atm`(`id`,`euro_fifty_count`,`euro_twenty_count`,`euro_ten_count`,`euro_five_count`)
values(1, 10, 30, 30, 20);
-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  lun. 01 juin 2020 à 21:08
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gecompt`
--
CREATE DATABASE IF NOT EXISTS `gecompt` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gecompt`;

-- --------------------------------------------------------

--
-- Structure de la table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) UNSIGNED DEFAULT NULL,
  `street` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10120 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `referenced_web` tinyint(1) NOT NULL DEFAULT '0',
  `referenced_sellers` tinyint(1) NOT NULL DEFAULT '0',
  `id_super_category` int(11) DEFAULT NULL,
  `categorieses_ORDER` int(11) DEFAULT NULL,
  `id_super_categoy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `super_category` (`id_super_category`) USING BTREE,
  KEY `FKc0xulpg2sqdgtpf1etxnah46o` (`id_super_categoy`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `current_seller`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `current_seller`;
CREATE TABLE IF NOT EXISTS `current_seller` (
`id` int(11)
,`surname` varchar(64)
,`first_name` varchar(64)
,`login` varchar(32)
,`id_store` int(11)
,`phone` varchar(32)
);

-- --------------------------------------------------------

--
-- Structure de la table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(64) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `society_name` varchar(64) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `id_address` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `liveAt` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` text NOT NULL,
  `price_df` decimal(10,3) UNSIGNED NOT NULL DEFAULT '0.000',
  `referenced_web` tinyint(1) NOT NULL DEFAULT '0',
  `referenced_sellers` tinyint(1) NOT NULL DEFAULT '1',
  `id_category` int(11) DEFAULT NULL,
  `id_vat` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `belongCategory` (`id_category`) USING BTREE,
  KEY `tax` (`id_vat`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `products_transactions_histories`
--

DROP TABLE IF EXISTS `products_transactions_histories`;
CREATE TABLE IF NOT EXISTS `products_transactions_histories` (
  `id_product` int(11) NOT NULL,
  `id_transaction` int(11) NOT NULL,
  `amount` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_product`,`id_transaction`),
  KEY `transaction_id` (`id_transaction`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `sellers`
--

DROP TABLE IF EXISTS `sellers`;
CREATE TABLE IF NOT EXISTS `sellers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(64) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `login` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_store` int(11) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `workAt` (`id_store`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sellers`
--

INSERT INTO `sellers` (`id`, `surname`, `first_name`, `login`, `password`, `id_store`, `phone`) VALUES
(1, 'root', 'Enes', NULL, NULL, NULL, ''),
(2, 'Vendeur', 'Hey', NULL, NULL, NULL, '8888'),
(3, 'Nouveau vendeur', 'vendeur', NULL, NULL, NULL, ''),
(4, 'Vendeur', 'test', NULL, NULL, NULL, '777');

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `id_store` int(11) NOT NULL DEFAULT '0',
  `id_product` int(11) NOT NULL DEFAULT '0',
  `amount` int(11) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_store`,`id_product`),
  KEY `productId` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `stores`
--

DROP TABLE IF EXISTS `stores`;
CREATE TABLE IF NOT EXISTS `stores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id_address` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `locatedAt` (`id_address`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payed_amount` decimal(10,3) UNSIGNED NOT NULL DEFAULT '0.000',
  `id_customer` int(11) DEFAULT NULL,
  `id_seller` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sold_by` (`id_seller`),
  KEY `bought_by` (`id_customer`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `vat`
--

DROP TABLE IF EXISTS `vat`;
CREATE TABLE IF NOT EXISTS `vat` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `pourcentage` decimal(10,5) UNSIGNED NOT NULL DEFAULT '0.00000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vat`
--

INSERT INTO `vat` (`id`, `pourcentage`) VALUES
(1, '20.00000');

-- --------------------------------------------------------

--
-- Structure de la vue `current_seller`
--
DROP TABLE IF EXISTS `current_seller`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `current_seller`  AS  select `sellers`.`id` AS `id`,`sellers`.`surname` AS `surname`,`sellers`.`first_name` AS `first_name`,`sellers`.`login` AS `login`,`sellers`.`id_store` AS `id_store`,`sellers`.`phone` AS `phone` from `sellers` where (substring_index(current_user(),'@',1) = `sellers`.`login`) ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `categories`
--
ALTER TABLE `categories`
  ADD CONSTRAINT `FKc0xulpg2sqdgtpf1etxnah46o` FOREIGN KEY (`id_super_categoy`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `supercat` FOREIGN KEY (`id_super_category`) REFERENCES `categories` (`id`) ON DELETE SET NULL;

--
-- Contraintes pour la table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `liveAt` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id`) ON DELETE SET NULL;

--
-- Contraintes pour la table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `belongCategory` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `tax` FOREIGN KEY (`id_vat`) REFERENCES `vat` (`id`) ON DELETE SET NULL;

--
-- Contraintes pour la table `products_transactions_histories`
--
ALTER TABLE `products_transactions_histories`
  ADD CONSTRAINT `product_id` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaction_id` FOREIGN KEY (`id_transaction`) REFERENCES `transactions` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT;

--
-- Contraintes pour la table `sellers`
--
ALTER TABLE `sellers`
  ADD CONSTRAINT `workAt` FOREIGN KEY (`id_store`) REFERENCES `stores` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `productId` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `store_id` FOREIGN KEY (`id_store`) REFERENCES `stores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `stores`
--
ALTER TABLE `stores`
  ADD CONSTRAINT `locatedAt` FOREIGN KEY (`id_address`) REFERENCES `addresses` (`id`);

--
-- Contraintes pour la table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `bought_by` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `sold_by` FOREIGN KEY (`id_seller`) REFERENCES `sellers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

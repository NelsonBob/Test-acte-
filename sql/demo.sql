-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 16 juin 2020 à 14:58
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `demo`
--

-- --------------------------------------------------------

--
-- Structure de la table `acte`
--

CREATE TABLE `acte` (
  `id` bigint(20) NOT NULL,
  `arr_origin` varchar(255) DEFAULT NULL,
  `adress_domicil_mer` varchar(255) DEFAULT NULL,
  `adress_domicil_per` varchar(255) DEFAULT NULL,
  `date_creat` datetime DEFAULT NULL,
  `date_etabliss_act_naiss` datetime DEFAULT NULL,
  `date_naiss_enf` date DEFAULT NULL,
  `date_naiss_mere` date DEFAULT NULL,
  `date_naiss_pere` date DEFAULT NULL,
  `date_upd` datetime DEFAULT NULL,
  `depart_origin` varchar(255) DEFAULT NULL,
  `lieu_naiss` varchar(255) DEFAULT NULL,
  `nom_ass_off_et_cv` varchar(255) DEFAULT NULL,
  `nom_cent_et_cv` varchar(255) DEFAULT NULL,
  `nom_enf` varchar(255) DEFAULT NULL,
  `nom_mere_enf` varchar(255) DEFAULT NULL,
  `nom_offi_et_cv` varchar(255) DEFAULT NULL,
  `nom_per` varchar(255) DEFAULT NULL,
  `num_act_naiss` int(11) NOT NULL,
  `num_transac` double NOT NULL,
  `pren_mer_enf` varchar(255) DEFAULT NULL,
  `prenom_enf` varchar(255) DEFAULT NULL,
  `prenom_per` varchar(255) DEFAULT NULL,
  `profess_mer` varchar(255) DEFAULT NULL,
  `profess_per` varchar(255) DEFAULT NULL,
  `reg_origin` varchar(255) DEFAULT NULL,
  `sexe_enf` varchar(255) DEFAULT NULL,
  `solde` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `telephone` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `acte`
--

INSERT INTO `acte` (`id`, `arr_origin`, `adress_domicil_mer`, `adress_domicil_per`, `date_creat`, `date_etabliss_act_naiss`, `date_naiss_enf`, `date_naiss_mere`, `date_naiss_pere`, `date_upd`, `depart_origin`, `lieu_naiss`, `nom_ass_off_et_cv`, `nom_cent_et_cv`, `nom_enf`, `nom_mere_enf`, `nom_offi_et_cv`, `nom_per`, `num_act_naiss`, `num_transac`, `pren_mer_enf`, `prenom_enf`, `prenom_per`, `profess_mer`, `profess_per`, `reg_origin`, `sexe_enf`, `solde`, `status`, `telephone`, `user_id`) VALUES
(1, 'wwww', '237', '237', NULL, '2020-06-16 13:01:57', '2020-06-04', '2019-07-11', '2019-12-05', '2020-06-16 12:35:48', 'wwww', 'yaounde', 'Koum Wo', 'nelson', 'bob', 'nelson', 'KOUMWO', 'nelson', 25897, 0, 'bob', 'SAMUEL', 'bob', 'Menagere', 'qqs', 'yaounde', 'Masculin', 0, 'traite', 0, 1),
(2, 'wwww', 'NKOABANG', '237', '2020-06-16 12:35:19', NULL, '2019-07-05', '2019-11-08', '2019-08-08', NULL, 'wwww', 'yaounde', NULL, 'bowell', 'mike', 'nelson', NULL, 'bowell', 0, 0, 'bob', 'winnie', 'mike', 'qwerr', 'qqs', 'eeeeee', 'Feminin', 0, 'valider', 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `password_reset_token`
--

CREATE TABLE `password_reset_token` (
  `id` bigint(20) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `date_enreg` datetime DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `date_updat` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nationalite` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `telephone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `date_enreg`, `date_naissance`, `date_updat`, `email`, `enabled`, `first_name`, `last_name`, `nationalite`, `password`, `profession`, `sexe`, `telephone`) VALUES
(1, '2020-06-16 12:32:44', '2018-08-02', '2020-06-16 13:06:14', 'koumwinnie@gmail.com', b'1', 'Winnie', 'nelson', 'Cameroun', '$2a$10$W2PrEdNVqgLlVMYqV3S4GeX1lsI1LYMetS3MwD9hDh.aMvuLIW3pm', 'Developpeur', 'Masculin', 656111580);

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `acte`
--
ALTER TABLE `acte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4f50idlvm041rauh96x7st2dh` (`user_id`);

--
-- Index pour la table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_g0guo4k8krgpwuagos61oc06j` (`token`),
  ADD KEY `FK5lwtbncug84d4ero33v3cfxvl` (`user_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Index pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `acte`
--
ALTER TABLE `acte`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `acte`
--
ALTER TABLE `acte`
  ADD CONSTRAINT `FK4f50idlvm041rauh96x7st2dh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `password_reset_token`
--
ALTER TABLE `password_reset_token`
  ADD CONSTRAINT `FK5lwtbncug84d4ero33v3cfxvl` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2023 at 06:16 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agl`
--

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE `compte` (
  `id` int(11) NOT NULL,
  `RoleID` int(11) DEFAULT NULL,
  `UtilisateurID` int(11) DEFAULT NULL,
  `login` varchar(80) DEFAULT NULL,
  `pass` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`id`, `RoleID`, `UtilisateurID`, `login`, `pass`) VALUES
(3, 1, 4, 'khalid@gmail.com', 'khalid'),
(5, 2, 5, 'hamid@gmail.com', 'hamid'),
(6, 1, 6, 'oussama@gmail.com', 'oussama');

-- --------------------------------------------------------

--
-- Table structure for table `empruntbibliotheque`
--

CREATE TABLE `empruntbibliotheque` (
  `id` int(11) NOT NULL,
  `emprunteur` int(11) DEFAULT NULL,
  `dateEmprunt` date DEFAULT current_timestamp(),
  `dateRetour` date DEFAULT NULL,
  `livre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `isbn` varchar(40) DEFAULT NULL,
  `titre` varchar(40) DEFAULT NULL,
  `auteur` varchar(40) DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livre`
--

INSERT INTO `livre` (`id`, `isbn`, `titre`, `auteur`, `description`) VALUES
(25, '67890', ' Titre', 'New ', 'New Description'),
(26, '97820', 'Antoine de Saint-Exupéry', 'lala fatima', 'C\'est un livre poétique et philosophique'),
(27, '978207', 'La Peste', 'Albert Camus', ' publié en 1947'),
(28, '12345', 'Test Livre', 'Test Auteur', 'Test Description');

-- --------------------------------------------------------

--
-- Table structure for table `reservationbibliotheque`
--

CREATE TABLE `reservationbibliotheque` (
  `id` int(11) NOT NULL,
  `dateR` date DEFAULT current_timestamp(),
  `etat` varchar(80) DEFAULT 'suspendu',
  `reservataire` int(11) DEFAULT NULL,
  `livre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `code` varchar(60) DEFAULT NULL,
  `nom` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `code`, `nom`) VALUES
(1, 'ETUDIANT', 'etudiant'),
(2, 'Bibliotheque', 'Agent de biblio');

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(80) DEFAULT NULL,
  `prenom` varchar(80) DEFAULT NULL,
  `sex` varchar(40) DEFAULT NULL,
  `cin` varchar(40) DEFAULT NULL,
  `dateNais` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `sex`, `cin`, `dateNais`) VALUES
(4, 'Elbouari', 'khalid', 'homme', 'G741888', '2001-02-04'),
(5, 'Alhayan', 'Hamid', 'homme', 'G61823', '1996-02-04'),
(6, 'elbakri', 'oussama', 'homme', 'G518783', '2002-04-11'),
(7, 'drwich', 'hassan', 'homme', 'G61353', '2005-04-11'),
(8, 'bohado', 'mohammed', 'homme', 'G87383', '2002-04-11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkr` (`RoleID`),
  ADD KEY `fku` (`UtilisateurID`);

--
-- Indexes for table `empruntbibliotheque`
--
ALTER TABLE `empruntbibliotheque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fke` (`emprunteur`),
  ADD KEY `fkl` (`livre`);

--
-- Indexes for table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservationbibliotheque`
--
ALTER TABLE `reservationbibliotheque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkuser` (`reservataire`),
  ADD KEY `fklivre` (`livre`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `empruntbibliotheque`
--
ALTER TABLE `empruntbibliotheque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `reservationbibliotheque`
--
ALTER TABLE `reservationbibliotheque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `fkr` FOREIGN KEY (`RoleID`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `fku` FOREIGN KEY (`UtilisateurID`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `empruntbibliotheque`
--
ALTER TABLE `empruntbibliotheque`
  ADD CONSTRAINT `fke` FOREIGN KEY (`emprunteur`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkl` FOREIGN KEY (`livre`) REFERENCES `livre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reservationbibliotheque`
--
ALTER TABLE `reservationbibliotheque`
  ADD CONSTRAINT `fklivre` FOREIGN KEY (`livre`) REFERENCES `livre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkuser` FOREIGN KEY (`reservataire`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 17, 2023 at 09:38 PM
-- Server version: 8.0.33-0ubuntu0.20.04.2
-- PHP Version: 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `415Db`
--

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `name`, `size`, `status`, `company_id`) VALUES
(1, 'Smart Chatbot', 'MEDIUM', 'ACTIVE', NULL),
(2, 'Weather Forecasting System', 'SMALL', 'PLANNED', NULL),
(3, 'Ecommerce Fake Product Reviews Detection System', 'BIG', 'ACTIVE', NULL),
(4, 'Credit Card Fraud Detection', 'BIG', 'ACTIVE', NULL),
(5, 'Signature Verification System', 'BIG', 'PLANNED', NULL),
(6, 'Face Detector', 'BIG', 'PLANNED', NULL),
(7, 'Fingerprint-Based ATM System', 'MEDIUM', 'SUSPENDED', NULL),
(8, 'Employees DB', 'BIG', 'PLANNED', NULL),
(9, 'AI Shopping System', 'BIG', 'FINISHED', NULL),
(10, 'Legacy Software Maintenance', 'MEDIUM', 'ACTIVE', NULL),
(11, 'Android Task Monitoring', 'SMALL', 'PLANNED', NULL),
(12, 'Financial Banking System', 'BIG', 'ACTIVE', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKo0aubdbyrpgoyr068q9tqhsa4` (`company_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `FKo0aubdbyrpgoyr068q9tqhsa4` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

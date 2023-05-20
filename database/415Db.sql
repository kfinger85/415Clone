-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 16, 2023 at 03:30 PM
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
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `name`) VALUES
(1, 'CS415Startup');

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `name`, `size`, `company_id`, `status`) VALUES
(1, 'Weather Forecasting System', 'SMALL', 1, NULL),
(2, 'Credit Card Fraud Detection', 'BIG', 1, NULL),
(3, 'Signature Verification System', 'BIG', 1, NULL),
(4, 'Fingerprint-Based ATM System', 'MEDIUM', 1, NULL),
(5, 'Employees DB', 'BIG', 1, NULL),
(6, 'Android Task Monitoring', 'SMALL', 1, NULL),
(7, 'Financial Banking System', 'BIG', 1, NULL),
(8, 'Smart Chatbot', 'MEDIUM', 1, NULL),
(9, 'Ecommerce Fake Product Reviews Detection System', 'BIG', 1, NULL),
(10, 'Face Detector', 'BIG', 1, NULL),
(11, 'AI Shopping System', 'BIG', 1, NULL),
(12, 'Legacy Software Maintanance', 'MEDIUM', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `projects_company`
--

CREATE TABLE `projects_company` (
  `project_id` int NOT NULL,
  `company_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `projects_company`
--

INSERT INTO `projects_company` (`project_id`, `company_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `project_qualification`
--

CREATE TABLE `project_qualification` (
  `project_id` int DEFAULT NULL,
  `qualification_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `project_qualification`
--

INSERT INTO `project_qualification` (`project_id`, `qualification_id`) VALUES
(1, 8),
(1, 11),
(2, 1),
(2, 3),
(2, 5),
(2, 12),
(2, 7),
(3, 1),
(3, 11),
(3, 12),
(3, 7),
(4, 2),
(4, 9),
(4, 4),
(4, 6),
(5, 8),
(5, 2),
(5, 9),
(5, 10),
(5, 12),
(5, 7),
(6, 2),
(6, 10),
(6, 4),
(6, 7),
(7, 8),
(7, 2),
(7, 3),
(7, 9),
(7, 4),
(7, 6),
(8, 1),
(8, 12),
(9, 1),
(9, 2),
(9, 3),
(9, 9),
(9, 5),
(9, 12),
(10, 1),
(10, 8),
(10, 6),
(10, 12),
(11, 1),
(11, 12),
(12, 8),
(12, 2),
(12, 9),
(12, 12),
(12, 7);

-- --------------------------------------------------------

--
-- Table structure for table `qualifications`
--

CREATE TABLE `qualifications` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `qualifications`
--

INSERT INTO `qualifications` (`id`, `name`) VALUES
(1, 'Tensorflow'),
(2, 'Java'),
(3, 'Cyber Security'),
(4, 'Spring'),
(5, 'React'),
(6, 'Angular'),
(7, 'Sql'),
(8, 'TypeScript'),
(9, 'JavaScript'),
(10, 'MongoDB'),
(11, 'Spark'),
(12, 'Python');

-- --------------------------------------------------------

--
-- Table structure for table `workers`
--

CREATE TABLE `workers` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `workload` double DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `company_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `workers`
--

INSERT INTO `workers` (`id`, `name`, `workload`, `salary`, `company_id`) VALUES
(1, 'Jamie Burgess', 3, 1000, 1),
(2, 'Erika Johnston', 6, 1000, 1),
(3, 'Terry Hampton', 6, 1000, 1),
(4, 'Nick Hubbard', 11, 1000, 1),
(5, 'Marcus Schneider', 5, 1000, 1),
(6, 'Tim Conner', 11, 1000, 1),
(7, 'Benjamin Guzman', 5, 1000, 1),
(8, 'Omar Williamson', 9, 1000, 1),
(9, 'Ron Logan', 7, 1000, 1),
(10, 'Gene Robertson', 6, 1000, 1),
(11, 'Nina Banks', 12, 1000, 1),
(12, 'Robert Lambert', 1, 1000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `worker_project`
--

CREATE TABLE `worker_project` (
  `worker_id` int DEFAULT NULL,
  `project_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `worker_project`
--

INSERT INTO `worker_project` (`worker_id`, `project_id`) VALUES
(12, 1),
(11, 1),
(1, 2),
(4, 2),
(6, 2),
(8, 2),
(2, 3),
(10, 3),
(7, 4),
(11, 4),
(2, 5),
(4, 5),
(6, 5),
(11, 5),
(3, 6),
(7, 6),
(5, 7),
(3, 7),
(8, 7),
(11, 7),
(3, 8),
(9, 8),
(4, 9),
(6, 9),
(8, 9),
(9, 9),
(10, 10),
(11, 10),
(5, 12),
(4, 12),
(6, 12),
(7, 12),
(9, 12);

-- --------------------------------------------------------

--
-- Table structure for table `worker_qualification`
--

CREATE TABLE `worker_qualification` (
  `qualification_id` int DEFAULT NULL,
  `worker_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `worker_qualification`
--

INSERT INTO `worker_qualification` (`qualification_id`, `worker_id`) VALUES
(12, 10),
(2, 3),
(4, 3),
(6, 11),
(8, 11),
(9, 8),
(3, 7),
(2, 7),
(4, 7),
(9, 5),
(8, 5),
(7, 6),
(11, 6),
(7, 6),
(9, 1),
(12, 1),
(1, 1),
(9, 4),
(5, 4),
(2, 12),
(11, 12),
(2, 9),
(4, 9),
(1, 9),
(2, 2),
(3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_company_id` (`company_id`);

--
-- Indexes for table `projects_company`
--
ALTER TABLE `projects_company`
  ADD KEY `project_id` (`project_id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `project_qualification`
--
ALTER TABLE `project_qualification`
  ADD KEY `project_id` (`project_id`),
  ADD KEY `qualification_id` (`qualification_id`);

--
-- Indexes for table `qualifications`
--
ALTER TABLE `qualifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `workers`
--
ALTER TABLE `workers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsrcsmffpn73vmyet8hxuo9hxf` (`company_id`);

--
-- Indexes for table `worker_project`
--
ALTER TABLE `worker_project`
  ADD KEY `worker_id` (`worker_id`),
  ADD KEY `project_id` (`project_id`);

--
-- Indexes for table `worker_qualification`
--
ALTER TABLE `worker_qualification`
  ADD KEY `qualification_id` (`qualification_id`),
  ADD KEY `worker_id` (`worker_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `qualifications`
--
ALTER TABLE `qualifications`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `workers`
--
ALTER TABLE `workers`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `fk_company_id` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `projects_company`
--
ALTER TABLE `projects_company`
  ADD CONSTRAINT `projects_company_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `projects_company_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `project_qualification`
--
ALTER TABLE `project_qualification`
  ADD CONSTRAINT `project_qualification_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  ADD CONSTRAINT `project_qualification_ibfk_2` FOREIGN KEY (`qualification_id`) REFERENCES `qualifications` (`id`);

--
-- Constraints for table `workers`
--
ALTER TABLE `workers`
  ADD CONSTRAINT `FKsrcsmffpn73vmyet8hxuo9hxf` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `worker_project`
--
ALTER TABLE `worker_project`
  ADD CONSTRAINT `worker_project_ibfk_1` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`),
  ADD CONSTRAINT `worker_project_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`);

--
-- Constraints for table `worker_qualification`
--
ALTER TABLE `worker_qualification`
  ADD CONSTRAINT `worker_qualification_ibfk_1` FOREIGN KEY (`qualification_id`) REFERENCES `qualifications` (`id`),
  ADD CONSTRAINT `worker_qualification_ibfk_2` FOREIGN KEY (`worker_id`) REFERENCES `workers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2020 at 03:44 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `helthcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `cardpayID` int(10) NOT NULL,
  `holderName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ctype` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `cardNo` varchar(17) COLLATE utf8_unicode_ci NOT NULL,
  `cvv` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `expMonth` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `expYear` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `total` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `payDate` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`cardpayID`, `holderName`, `ctype`, `cardNo`, `cvv`, `expMonth`, `expYear`, `total`, `payDate`) VALUES
(4, 'vethusshan', 'VISA', '4567654321212123', '900', '10', '2023', '3000', '2020-05-03'),
(5, 'thusshan', 'MasterCard', '4565434565432123', '543', '03', '2022', '5000', '2020-05-04'),
(6, 'rrrr', 'VISA', '5654321234565432', '234', '09', '2023', '4000', '2020-05-04');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`cardpayID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `cardpayID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
<<<<<<< HEAD
-- Generation Time: May 02, 2018 at 10:18 AM
=======
-- Generation Time: May 02, 2018 at 01:53 PM
>>>>>>> 3bd0d851d351052d08afcf1361e31be2eacf3c53
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbggates`
--

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

CREATE TABLE `participant` (
  `tid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `role` enum('Trainee','Facilitator','Supervisor','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `participant`
--

INSERT INTO `participant` (`tid`, `uid`, `role`) VALUES
(1, 1, 'Trainee'),
(1, 2, 'Facilitator');

-- --------------------------------------------------------

--
-- Table structure for table `trainingplan`
--

CREATE TABLE `trainingplan` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `sid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `trainingplan`
--

INSERT INTO `trainingplan` (`id`, `title`, `description`, `sid`) VALUES
(1, 'Agile ', 'blablba', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
<<<<<<< HEAD
  `age` int(3) NOT NULL,
  `position` varchar(50) NOT NULL,
=======
  `position` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
>>>>>>> 3bd0d851d351052d08afcf1361e31be2eacf3c53
  `status` enum('approved','pending','','') NOT NULL DEFAULT 'pending',
  `is_admin` int(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

<<<<<<< HEAD
INSERT INTO `user` (`id`, `name`, `age`, `position`, `status`, `is_admin`) VALUES
(1, 'andrew', 19, 'beggar', 'approved', 1),
(2, 'giselle', 50, 'HR', 'approved', 0);
=======
INSERT INTO `user` (`id`, `name`, `position`, `email`, `password`, `status`, `is_admin`) VALUES
(1, 'andrew', 'beggar', '', '', 'approved', 1),
(2, 'giselle', 'HR', '', '', 'approved', 0);
>>>>>>> 3bd0d851d351052d08afcf1361e31be2eacf3c53

--
-- Indexes for dumped tables
--

--
-- Indexes for table `participant`
--
ALTER TABLE `participant`
  ADD UNIQUE KEY `unique_fk` (`tid`,`uid`),
  ADD KEY `uid_fk` (`uid`);

--
-- Indexes for table `trainingplan`
--
ALTER TABLE `trainingplan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `trainingplan`
--
ALTER TABLE `trainingplan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `tid_fk` FOREIGN KEY (`tid`) REFERENCES `trainingplan` (`id`),
  ADD CONSTRAINT `uid_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

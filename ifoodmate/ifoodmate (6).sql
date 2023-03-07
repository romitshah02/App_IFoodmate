-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 07, 2023 at 06:45 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ifoodmate`
--

-- --------------------------------------------------------

--
-- Table structure for table `cat_sp`
--

CREATE TABLE `cat_sp` (
  `SP_ID` int(11) NOT NULL,
  `CAT_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cat_sp`
--

INSERT INTO `cat_sp` (`SP_ID`, `CAT_ID`) VALUES
(4, 7),
(7, 7),
(9, 7);

-- --------------------------------------------------------

--
-- Table structure for table `menu_table`
--

CREATE TABLE `menu_table` (
  `ITEM_ID` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  `ITEM_NAME` varchar(30) NOT NULL,
  `ITEM_PRICE` int(11) NOT NULL,
  `ITEM_STATUS` tinyint(1) NOT NULL,
  `SERVICE_PROVIDER_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu_table`
--

INSERT INTO `menu_table` (`ITEM_ID`, `CATEGORY_ID`, `ITEM_NAME`, `ITEM_PRICE`, `ITEM_STATUS`, `SERVICE_PROVIDER_ID`) VALUES
(201, 11, 'Lababdar Thali', 170, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mst_admin`
--

CREATE TABLE `mst_admin` (
  `ADMIN_ID` int(11) NOT NULL,
  `ADMIN_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `ADMIN_PASSWORD` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mst_admin`
--

INSERT INTO `mst_admin` (`ADMIN_ID`, `ADMIN_NAME`, `EMAIL`, `ADMIN_PASSWORD`) VALUES
(1, 'admin', 'admin@gmail.com', '123');

-- --------------------------------------------------------

--
-- Table structure for table `mst_category`
--

CREATE TABLE `mst_category` (
  `CATEGORY_ID` int(11) NOT NULL,
  `CATEGORY_NAME` varchar(30) NOT NULL,
  `CATEGORY_STATUS` tinyint(1) NOT NULL,
  `CAT_IMG` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mst_category`
--

INSERT INTO `mst_category` (`CATEGORY_ID`, `CATEGORY_NAME`, `CATEGORY_STATUS`, `CAT_IMG`) VALUES
(6, 'Italian', 0, '06-03-2023-167812527581026804.jpg'),
(7, 'Punjabi', 0, '06-03-2023-167812529380285186.jpg'),
(8, 'South Indian', 0, '06-03-2023-167812530528422406.jpg'),
(9, 'Chinese', 0, '06-03-2023-167812531722982930.jpg'),
(10, 'Gujarati', 0, '06-03-2023-167812533522670513.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `mst_feedback`
--

CREATE TABLE `mst_feedback` (
  `FEEDBACK_ID` int(11) NOT NULL,
  `ORDER_ID` varchar(10) NOT NULL,
  `FEEDBACK_DATE` date NOT NULL,
  `FEEDBACK_DETAILS` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mst_offer`
--

CREATE TABLE `mst_offer` (
  `OFFER_ID` int(11) NOT NULL,
  `SERVICE_PROVIDER_ID` int(11) NOT NULL,
  `OFFER_NAME` varchar(30) NOT NULL,
  `OFFER_START_DATE` date NOT NULL,
  `OFFER_END_DATE` date NOT NULL,
  `OFFER_STATUS` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mst_order`
--

CREATE TABLE `mst_order` (
  `ORDER_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `SERVICE_PROVIDER_ID` int(11) NOT NULL,
  `OFFER_ID` int(11) NOT NULL,
  `ORDER_DATE` date NOT NULL,
  `ORDER_PRICE` int(11) NOT NULL,
  `ORDER_QUANTITY` int(11) NOT NULL,
  `ORDER_DETAILS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mst_payment`
--

CREATE TABLE `mst_payment` (
  `PAYMENT_ID` int(11) NOT NULL,
  `ORDER_ID` int(11) NOT NULL,
  `PYAMENT_DATE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `mst_provider`
--

CREATE TABLE `mst_provider` (
  `SERVICE_PROVIDER_ID` int(10) NOT NULL,
  `SERVICE_PROVIDER_NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PHONE_NO` varchar(10) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `REGISTERATION_DATE` date DEFAULT NULL,
  `GST_NO` varchar(15) NOT NULL,
  `SERVICE_PROVIDER_PASSWORD` varchar(30) NOT NULL,
  `SP_IMG` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mst_provider`
--

INSERT INTO `mst_provider` (`SERVICE_PROVIDER_ID`, `SERVICE_PROVIDER_NAME`, `EMAIL`, `PHONE_NO`, `ADDRESS`, `REGISTERATION_DATE`, `GST_NO`, `SERVICE_PROVIDER_PASSWORD`, `SP_IMG`) VALUES
(4, 'AshokCaterers', 'ashokctrs@gmail.com', '4321876509', 'Sindhu Bhavan Road, Ahmedabad Gujarat', '2023-02-15', '24BAAPP7324R1Z9', 'ashok1234', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(5, 'Alokh Food Service', 'alokh@gmail,com', '8989767654', 'Mumbai, Maharashtra', '2023-02-21', 'Ae345Fnsu9h78', 'provider5', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(6, 'SagarCateringService', 'sagarcaterers@gmail.com', '9090878765', 'Agra, UP', '2023-02-16', '24ACNFS2374jt6R', 'provider6', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(7, 'HotelRoyale', 'hotelRoyale@gmail.com', '4567890321', 'Surat, Gujarat', '2023-02-16', '08CUWP2476N1ZP', 'provider8', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(9, 'ShivSagarCaterers', 'shivsagarcaterers@gmail.com', '5678905432', 'Vadodra, Gujarat', '2023-02-23', 'Ae345Fnsu9g78', 'provider9', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(10, 'RawMango', 'rawmango@gmail.com', '9809876532', 'Nashik, Maharashtra', '2023-02-18', 'h7g8j8f656ghjk0', 'provider10', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(11, 'Mafatlal Caterer', 'mafatlalcaterer@gmail.com', '1234567890', 'Ahmedabad,gujarat', '2023-02-27', '24ACAFS1574jt6R', 'provider11', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(12, 'Sukhdev Vaishno Dhaba', 'sukhdev@gmail.com', '4987654321', 'surat,gujarat', '2023-02-27', '08CUAF3571N1ZP', 'provider12', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(13, 'Silver Apple', 'silverapple@gmail.com', '1234567890', 'surat,gujarat', '2023-02-22', '14ACNFS2374jt6A', 'provider13', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(14, 'Manorama Caterers', 'manoramacaterers@gmail.com', '1234561245', 'surat,gujarat', '2023-02-20', '19CUWPK3571N1ZP', 'provider14', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg'),
(15, 'alok caterers', 'alokcaterers@gmail.com', '8005773145', 'surat , gujarat', '2023-02-22', '12abcdklqw12asl', 'alok15', '06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg,06-03-2023-167812527581026804.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `mst_user`
--

CREATE TABLE `mst_user` (
  `USER_ID` int(11) NOT NULL,
  `USERNAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PHONE_NO` varchar(11) NOT NULL,
  `ADDRESS` varchar(100) NOT NULL,
  `REGISTRATION_DATE` date NOT NULL,
  `GENDER` varchar(10) NOT NULL,
  `USER_PASSWORD` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mst_user`
--

INSERT INTO `mst_user` (`USER_ID`, `USERNAME`, `EMAIL`, `PHONE_NO`, `ADDRESS`, `REGISTRATION_DATE`, `GENDER`, `USER_PASSWORD`) VALUES
(2, 'KushalGohel', 'kushalgohel@gmail.com', '765432890', 'Ahmedabad, Gujarat', '2023-02-27', 'Male', 'xyz678'),
(3, 'PrathanSharma', 'patthu@gmail.com', '2345678901', 'Agra, UP', '2023-02-21', 'Female', 'ghj567'),
(4, 'SanaSharma', 'sanasharma@gmail.com', '8005773144', 'Bhilwara, Rajhasthan', '2020-02-08', 'Female', 'sana234'),
(6, 'romit', 'rswtfcr7@gmail.com', '8356889604', 'B/1304,gokul gagan ', '2023-03-01', 'male', 'abcd'),
(8, 'RomitShah', 'romitshah@yahoo.com', '9898234567', 'Mumbai, Maharashtra', '2023-02-15', 'Male', 'romrom'),
(9, 'henisdcosta', 'henis@dcosta.com', '67675432109', 'Bhuj, Gujarat ', '2023-02-27', 'Male', 'xyz678'),
(10, 'Ashmita Bose', 'ashmitabose@gmail.com', '8976345201', 'Baroda, Gujarat', '2023-02-21', 'Female', 'abc789'),
(11, 'Hanish Narwani', 'Narru@gmail.com', '56949054321', 'Naroda, Ahmedabad, Gujarat', '2023-02-27', 'Male', 'hani890'),
(12, 'rajivlalwani', 'rajjo@gmail.com', '9725001131', 'Naroda, Ahmedabad, Gujarat', '2023-02-15', 'Male', 'abc123'),
(13, 'YusufKhan', 'yusuf34@gmail.com', '73567213304', 'Mumbai, Maharashtra', '2023-02-27', 'Male', 'yusuf123'),
(14, 'Manali Rao', 'manalirao@gmail.com', '9825029490', 'Ranthambore, Rajasthan', '2023-02-06', 'Female', 'rao8910'),
(15, 'Diwali Sharma', 'chhotidiwali@gmail.com', '8008578620', 'Baroda, GUjarat', '2023-02-28', 'Female', 'ddd234'),
(16, 'Ram Nath', 'ramnath@gmail.com', '1234567891', 'Manali', '2023-02-06', 'Male', 'hum677'),
(17, 'Sita Ram', 'sitaram@gmail.com', '234567890', 'Chhattisgarh, Bihar', '2023-02-14', 'Female', 'namo234'),
(18, 'KripalSharma', 'kripal@gmail.com', '7878654390', 'Bhilwara, Rajashtan', '2023-02-22', 'Male', 'abc234'),
(19, 'Shivansh Patel', 'shivanshpatel@gmail.com', '7359212203', 'Raipur, Chhattisgarh', '2023-02-22', 'Male', 'rai987'),
(20, 'DhruviPatel', 'dhruvipatel@gmail.com', '9865327845', 'Baroda, Gujarat', '2023-03-01', 'Female', 'dhurvi20'),
(23, 'mshah', 'manishshah02@gmail.com', '9223295258', 'gokul gagan', '2023-03-05', 'male', 'manish1998');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cat_sp`
--
ALTER TABLE `cat_sp`
  ADD UNIQUE KEY `CAT_ID` (`CAT_ID`,`SP_ID`),
  ADD KEY `sp` (`SP_ID`);

--
-- Indexes for table `menu_table`
--
ALTER TABLE `menu_table`
  ADD PRIMARY KEY (`ITEM_ID`);

--
-- Indexes for table `mst_admin`
--
ALTER TABLE `mst_admin`
  ADD PRIMARY KEY (`ADMIN_ID`);

--
-- Indexes for table `mst_category`
--
ALTER TABLE `mst_category`
  ADD PRIMARY KEY (`CATEGORY_ID`);

--
-- Indexes for table `mst_feedback`
--
ALTER TABLE `mst_feedback`
  ADD PRIMARY KEY (`FEEDBACK_ID`);

--
-- Indexes for table `mst_offer`
--
ALTER TABLE `mst_offer`
  ADD PRIMARY KEY (`OFFER_ID`);

--
-- Indexes for table `mst_order`
--
ALTER TABLE `mst_order`
  ADD PRIMARY KEY (`ORDER_ID`);

--
-- Indexes for table `mst_payment`
--
ALTER TABLE `mst_payment`
  ADD PRIMARY KEY (`PAYMENT_ID`);

--
-- Indexes for table `mst_provider`
--
ALTER TABLE `mst_provider`
  ADD PRIMARY KEY (`SERVICE_PROVIDER_ID`),
  ADD UNIQUE KEY `GST_NO` (`GST_NO`);

--
-- Indexes for table `mst_user`
--
ALTER TABLE `mst_user`
  ADD PRIMARY KEY (`USER_ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mst_category`
--
ALTER TABLE `mst_category`
  MODIFY `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `mst_provider`
--
ALTER TABLE `mst_provider`
  MODIFY `SERVICE_PROVIDER_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1007;

--
-- AUTO_INCREMENT for table `mst_user`
--
ALTER TABLE `mst_user`
  MODIFY `USER_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

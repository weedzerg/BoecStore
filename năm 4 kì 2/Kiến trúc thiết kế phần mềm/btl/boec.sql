-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 21, 2018 lúc 10:14 AM
-- Phiên bản máy phục vụ: 10.1.31-MariaDB
-- Phiên bản PHP: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `boec`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `Id` int(10) NOT NULL,
  `Username` varchar(100) DEFAULT NULL,
  `Password` int(10) DEFAULT NULL,
  `PersonId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`Id`, `Username`, `Password`, `PersonId`) VALUES
(1, 'anhtuan', 123, 1),
(2, 'anhquan', 123, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `id` int(10) NOT NULL,
  `year` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `ItemId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`id`, `year`, `author`, `ItemId`) VALUES
(1, '2014', 'Nguyễn ', 2),
(2, '2011', 'Nguyễn Nhật Ánh', 3),
(3, '2016', 'Richelle Mead', 4),
(4, '2017', 'P. C. Cast', 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `Id` int(10) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `TypeCategoryId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`Id`, `Name`, `TypeCategoryId`) VALUES
(1, 'Sách tiếng việt', 1),
(2, 'Sách tiếng anh', 1),
(3, 'Ebook', 1),
(4, 'Tivi - Thiết bị IT', 2),
(5, 'Điện thoại', 2),
(6, 'Laptop - Máy tính', 2),
(7, 'Thời trang nam', 3),
(8, 'Thời trang nữ', 3),
(9, 'Thời trang trẻ em', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `clothes`
--

CREATE TABLE `clothes` (
  `id` int(10) NOT NULL,
  `color` varchar(100) DEFAULT NULL,
  `size` varchar(100) DEFAULT NULL,
  `ItemId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `clothes`
--

INSERT INTO `clothes` (`id`, `color`, `size`, `ItemId`) VALUES
(1, 'Trắng, hồng, vàng', 'S,M,L', 16),
(2, 'Cam, trắng', 'M', 17),
(3, 'Trắng, đen', 'L,XL,XXL', 15),
(4, 'Đen, xanh', 'L', 14),
(5, 'Trắng, hồng, vàng', 'XS,S', 18);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `Id` int(11) NOT NULL,
  `Phone` varchar(100) DEFAULT NULL,
  `PersonId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`Id`, `Phone`, `PersonId`) VALUES
(1, '01889097654', 1),
(2, '01669090777', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `electronics`
--

CREATE TABLE `electronics` (
  `id` int(10) NOT NULL,
  `size` varchar(100) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `ItemId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `electronics`
--

INSERT INTO `electronics` (`id`, `size`, `weight`, `ItemId`) VALUES
(1, '15.6 inch HD (1366 x 768) LED-Backlit Display', '2.4kg', 13),
(2, '14 inch FHD (1920 x 1080) IPS, wide 100% sRGB color gamut', '1.25kg', 12),
(3, ' IPS LCD, 2K (1440 x 2560 pixels)', 'RAM: 4GB', 11),
(4, '4.7 inch, HD (1334 x 750 Pixels)', 'RAM: 1GB', 10),
(5, '43 inch, Ultra HD 4K (3840 x 2160)', '2.5 kg', 8),
(6, '43 inch, Ultra HD 4K (3840 x 2160)', '2,2 kg', 7),
(7, '43 inch, 1920 x 1080 pixels', '1.9 kg', 6),
(8, 'IPS LCD, 5 inch HD, 720 x 1280 pixels', 'RAM: 2GB', 9);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `Id` int(10) NOT NULL,
  `Salary` int(10) DEFAULT NULL,
  `Position` varchar(100) DEFAULT NULL,
  `PersonId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`Id`, `Salary`, `Position`, `PersonId`) VALUES
(1, 15000000, 'Nhân viên bán hàng quầy', 1),
(2, 5000000, 'Nhân viên nhập kho', 2),
(3, 7000000, 'Nhân viên bán hàng quầy', 3),
(4, 5000000, 'Nhân viên bán hàng qua mạng', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `item`
--

CREATE TABLE `item` (
  `Id` int(10) NOT NULL,
  `CategoryId` int(10) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `image` varchar(100) NOT NULL,
  `Price` int(10) DEFAULT NULL,
  `Amount` int(10) DEFAULT NULL,
  `PublisherId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `item`
--

INSERT INTO `item` (`Id`, `CategoryId`, `Name`, `image`, `Price`, `Amount`, `PublisherId`) VALUES
(2, 1, 'Đừng coi ai đó cả thế giới', 'images/t1.jpg', 57000, 10, 1),
(3, 1, 'Cho tôi xin một vé đi tuổi thơ', 'images/t2.jpg', 78000, 11, 2),
(4, 2, 'The Glittering Court', 'images/b1.jpg', 250000, 5, 2),
(5, 3, 'Moon Chosen: Tales Of A New World', 'images/t1.jpg', 15000, 5, 2),
(6, 4, 'Smart Tivi LG 43 inch 4K UHD 43UJ632T', 'images/tv2.jpg', 12900000, 5, 5),
(7, 4, 'Android Tivi 4K Sony 43 inch KD-43X8000E - Đen', 'images/tv3.jpg', 17000000, 11, 4),
(8, 4, 'Smart Tivi Samsung 43 inch 4K UHD UA43MU6400KXXV', 'images/tv2.jpg', 15900000, 5, 6),
(9, 5, 'Điện Thoại Sony Xperia XA F3116 - Hàng Chính Hãng', 'images/dt1.jpg', 6900000, 7, 4),
(10, 5, 'Điện Thoại iPhone 6 32GB (Vàng Đồng) - Hàng Chính Hãng VN/A', 'images/dt2.jpg', 8990000, 9, 10),
(11, 5, 'Điện Thoại Nokia 8 - Hàng Chính Hãng', 'images/dt3.jpg', 13000000, 3, 9),
(12, 6, 'Laptop Asus Zenbook UX430UN-GV069T Core i5-8250U/Win 10 (14 inch) - Xanh Dương - Hàng Chính Hãng', 'images/lt1.jpg', 24100000, 6, 7),
(13, 6, 'Laptop Dell Inspiron N3567 70119158 Core i5-7200U/Win 10 (15.6 inch) - Black', 'images/lt2.jpg', 15600000, 8, 8),
(14, 7, 'Áo nam cổ tròn in họa tiết', 'images/cl3.jpg', 549000, 15, 11),
(15, 7, 'Áo len nam kẻ', 'images/cl4.jpg', 399000, 10, 11),
(16, 8, 'Áo sơ mi nữ', 'images/cl1.jpg', 250000, 5, 11),
(17, 8, 'Áo ngắn tay nữ', 'images/cl2.jpg', 150000, 1, 12),
(18, 9, 'Váy bé gái in họa tiết', 'images/cl5.jpg', 229000, 6, 12);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order`
--

CREATE TABLE `order` (
  `Id` int(10) NOT NULL,
  `Payment` int(10) DEFAULT NULL,
  `TimeOrder` varchar(100) DEFAULT NULL,
  `CustomerId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderdetail`
--

CREATE TABLE `orderdetail` (
  `Id` int(10) NOT NULL,
  `Amount` int(10) DEFAULT NULL,
  `ItemId` int(10) NOT NULL,
  `OrderId` int(10) NOT NULL,
  `EmployeeId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `person`
--

CREATE TABLE `person` (
  `Id` int(10) NOT NULL,
  `Dateofbirth` varchar(100) DEFAULT NULL,
  `Fullname` varchar(100) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `person`
--

INSERT INTO `person` (`Id`, `Dateofbirth`, `Fullname`, `Address`) VALUES
(1, '09/04/1996', 'Hoàng Anh Tuấn', 'Hà Đông, Hà Nội'),
(2, '04/07/1991', 'Phan Anh Quân', 'Đống Đa, Hà Nội'),
(3, '22/2/2000', 'Đỗ Dương', 'Thái Bình'),
(4, '11/12/1995', 'Đỗ Gia Khánh', 'Thái Bình');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `publisher`
--

CREATE TABLE `publisher` (
  `Id` int(10) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `publisher`
--

INSERT INTO `publisher` (`Id`, `Name`, `Address`) VALUES
(1, 'NXB Trẻ', 'Hà Nội'),
(2, 'NXB Lao động', 'Hà Nội'),
(3, 'NXB Văn Học', 'TP.Hồ Chí Minh'),
(4, 'SONY', 'Việt Nam'),
(5, 'LG', 'Trung Quốc'),
(6, 'SAMSUNG', 'Hàn Quốc'),
(7, 'ASUS', 'Việt Nam'),
(8, 'DELL', 'Việt Nam'),
(9, 'NOKIA', 'Thái Lan'),
(10, 'APPLE', 'Mỹ'),
(11, 'CANIFA', 'Hà Nội'),
(12, 'BLUE EXCHANGE', 'TP.Hồ Chí Minh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `typecategory`
--

CREATE TABLE `typecategory` (
  `Id` int(10) NOT NULL,
  `Type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `typecategory`
--

INSERT INTO `typecategory` (`Id`, `Type`) VALUES
(1, 'Sách'),
(2, 'Đồ điện tử'),
(3, 'Quần áo');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKAccount705891` (`PersonId`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKBook428677` (`ItemId`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKCategory247565` (`TypeCategoryId`);

--
-- Chỉ mục cho bảng `clothes`
--
ALTER TABLE `clothes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKClothes719994` (`ItemId`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKCustomer220795` (`PersonId`);

--
-- Chỉ mục cho bảng `electronics`
--
ALTER TABLE `electronics`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKElectronic367310` (`ItemId`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKEmployee925791` (`PersonId`);

--
-- Chỉ mục cho bảng `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKItem525138` (`CategoryId`),
  ADD KEY `FKItem434741` (`PublisherId`);

--
-- Chỉ mục cho bảng `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKOrder556775` (`CustomerId`);

--
-- Chỉ mục cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `FKOrderDetai704949` (`ItemId`),
  ADD KEY `FKOrderDetai762072` (`OrderId`),
  ADD KEY `FKOrderDetai856236` (`EmployeeId`);

--
-- Chỉ mục cho bảng `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`Id`);

--
-- Chỉ mục cho bảng `typecategory`
--
ALTER TABLE `typecategory`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `clothes`
--
ALTER TABLE `clothes`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `electronics`
--
ALTER TABLE `electronics`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `item`
--
ALTER TABLE `item`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT cho bảng `order`
--
ALTER TABLE `order`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `person`
--
ALTER TABLE `person`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `publisher`
--
ALTER TABLE `publisher`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `typecategory`
--
ALTER TABLE `typecategory`
  MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FKAccount705891` FOREIGN KEY (`PersonId`) REFERENCES `person` (`Id`);

--
-- Các ràng buộc cho bảng `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FKBook428677` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`);

--
-- Các ràng buộc cho bảng `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FKCategory247565` FOREIGN KEY (`TypeCategoryId`) REFERENCES `typecategory` (`Id`);

--
-- Các ràng buộc cho bảng `clothes`
--
ALTER TABLE `clothes`
  ADD CONSTRAINT `FKClothes719994` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`);

--
-- Các ràng buộc cho bảng `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FKCustomer220795` FOREIGN KEY (`PersonId`) REFERENCES `person` (`Id`);

--
-- Các ràng buộc cho bảng `electronics`
--
ALTER TABLE `electronics`
  ADD CONSTRAINT `FKElectronic367310` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`);

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKEmployee925791` FOREIGN KEY (`PersonId`) REFERENCES `person` (`Id`);

--
-- Các ràng buộc cho bảng `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FKItem434741` FOREIGN KEY (`PublisherId`) REFERENCES `publisher` (`Id`),
  ADD CONSTRAINT `FKItem525138` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`Id`);

--
-- Các ràng buộc cho bảng `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `FKOrder556775` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`Id`);

--
-- Các ràng buộc cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD CONSTRAINT `FKOrderDetai704949` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`),
  ADD CONSTRAINT `FKOrderDetai762072` FOREIGN KEY (`OrderId`) REFERENCES `order` (`Id`),
  ADD CONSTRAINT `FKOrderDetai856236` FOREIGN KEY (`EmployeeId`) REFERENCES `employee` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

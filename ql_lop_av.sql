-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 08, 2023 lúc 05:46 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ql_lop_av`
--

DELIMITER $$
--
-- Thủ tục
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `HienThiLopHocTheoKhoaHoc` (IN `p_id_khoa_hoc` VARCHAR(11))   BEGIN
    SELECT lh.* 
    FROM lop_hoc lh
    INNER JOIN khoahoc kh ON lh.id_lop = kh.id_lop
    WHERE kh.id_khoa_hoc = p_id_khoa_hoc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SapXepKhoaHocTheoId` ()   BEGIN
    SELECT * FROM khoahoc
    ORDER BY id_khoa_hoc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SapXepKhoaHocTheoTen` ()   BEGIN
    SELECT * FROM khoahoc
    ORDER BY ten_khoa_hoc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SapXepVaHienThiGiangVienTheoID` ()   BEGIN
    SELECT * FROM giangvien
    ORDER BY id_gv ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SapXepVaHienThiGiangVienTheoTen` ()   BEGIN
    SELECT * FROM giangvien
    ORDER BY ten_gv ASC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SapXepVaHienThiHocVienTheoID` ()   BEGIN
    SELECT * FROM hocvien
    ORDER BY id_hocvien DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SapXepVaHienThiHocVienTheoTen` ()   BEGIN
    SELECT * FROM hocvien
    ORDER BY ten_hocvien DESC	;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SuaKhoaHoc` (IN `p_id_khoa_hoc` VARCHAR(11), IN `p_ten_khoa_hoc` VARCHAR(45), IN `p_ngay_bd` DATE, IN `p_ngay_kt` DATE, IN `p_tai_lieu` VARCHAR(45), IN `p_mota` VARCHAR(45), IN `p_id_lop` VARCHAR(45))   BEGIN
    UPDATE `khoahoc`
    SET `ten_khoa_hoc` = p_ten_khoa_hoc,
        `ngay_bd` = p_ngay_bd,
        `ngay_kt` = p_ngay_kt,
        `tai_lieu` = p_tai_lieu,
        `mota` = p_mota,
        `id_lop` = p_id_lop
    WHERE `id_khoa_hoc` = p_id_khoa_hoc;
END$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

$$

--
-- Các hàm
--
$$

$$

$$

DELIMITER ;
<div class="alert alert-danger" role="alert"><h1>Lỗi</h1><p><strong>Truy vấn SQL:</strong>  <a href="#" class="copyQueryBtn" data-text="SET SQL_QUOTE_SHOW_CREATE = 1">Chép</a>
<a href="index.php?route=/database/sql&sql_query=SET+SQL_QUOTE_SHOW_CREATE+%3D+1&show_query=1&db=ql_lop_av"><span class="text-nowrap"><img src="themes/dot.gif" title="Sửa" alt="Sửa" class="icon ic_b_edit">&nbsp;Sửa</span></a>    </p>
<p>
<code class="sql"><pre>
SET SQL_QUOTE_SHOW_CREATE = 1
</pre></code>
</p>
<p>
    <strong>MySQL đã nói: </strong><a href="./url.php?url=https%3A%2F%2Fdev.mysql.com%2Fdoc%2Frefman%2F8.0%2Fen%2Fserver-error-reference.html" target="mysql_doc"><img src="themes/dot.gif" title="Tài liệu" alt="Tài liệu" class="icon ic_b_help"></a>
</p>
<code>#2006 - MySQL server has gone away</code><br></div>
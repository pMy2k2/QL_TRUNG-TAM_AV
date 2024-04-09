-- Tạo cơ sở dữ liệu: `QL_LOP_AV`
CREATE DATABASE QL_LOP_AV CHARACTER SET utf8;

-- Sử dụng cơ sở dữ liệu: `QL_LOP_AV`
USE QL_LOP_AV;


-- Tạo bảng `lop_hoc` cho thông tin lớp học
CREATE TABLE `lop_hoc` (
  `id_lop` VARCHAR(45) NOT NULL,
  `ten_lop` VARCHAR(45) NOT NULL,
  `buoi_hoc` VARCHAR(45) DEFAULT NULL,
  `tg_bd` TIME DEFAULT NULL,
  `tg_kt` TIME DEFAULT NULL,
  `phong` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`id_lop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Đổ dữ liệu vào bảng `lop_hoc`
INSERT INTO `lop_hoc` (`id_lop`, `ten_lop`, `buoi_hoc`, `tg_bd`, `tg_kt`, `phong`) VALUES
('TE01', 'TOIEC_01', '2-4-6', '15:00:00', '17:00:00', 'P01'),
('TE02', 'TOIEC_450', '3-5-7', '15:00:00', '17:00:00', 'P02'),
('TE03', 'IELTS', '2-4-6', '08:00:00', '10:00:00', 'P03'),
('TE04', 'TOIEC_03', '3-5-7', '08:00:00', '10:00:00', 'P04'),
('TE05', 'TOIEC_ADV', '2-4-6', '10:30:00', '12:30:00', 'P05'),
('TE06', 'IELTS', '3-5-7', '10:30:00', '12:30:00', 'P06'),
('TE07', 'TOIEC_04', '2-4-6', '13:00:00', '15:00:00', 'P07');

-- Tạo bảng `user` cho thông tin người dùng
CREATE TABLE `user` (
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Đổ dữ liệu vào bảng `user`
INSERT INTO `user` (`Username`, `Password`, `Email`) 
VALUES ('admin', '123456', 'admin@gmail.com'),
('phumy', '123456', 'my@gmail.com'),
('tiendat', '123456', 'dat@gmail.com'),
('phuonghuy', '123456', 'huy@gmail.com'),
('phuchau', '123456', 'hau@gmail.com');

-- Tạo bảng `hocvien` cho thông tin học viên
CREATE TABLE `hocvien` (
  `id_hocvien` VARCHAR(45) NOT NULL,
  `ten_hocvien` VARCHAR(45) DEFAULT NULL,
  `namsinh` VARCHAR(45) DEFAULT NULL,
  `diachi` VARCHAR(100) DEFAULT NULL,
  `email` VARCHAR(45) DEFAULT NULL,
  `phone` VARCHAR(45) DEFAULT NULL,
  `id_khoa_hoc` VARCHAR(45) NOT NULL,
  `id_lop` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_hocvien`),
  FOREIGN KEY (`id_lop`) REFERENCES `lop_hoc`(`id_lop`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Đổ dữ liệu vào bảng `hocvien`
INSERT INTO `hocvien` (`id_hocvien`, `ten_hocvien`, `namsinh`, `diachi`, `email`, `phone`, `id_khoa_hoc`, `id_lop`) VALUES
('B2014585', 'Le Phu My', '2002', 'An Giang', 'myb2014585@gmail.com', '0879654564', 'K01', 'TE01'),
('B20000', 'Tien Dat', '2002', 'Kien Giang', 'Dat@gmail.com', '0879654564', 'K01', 'TE01'),
('B20001', 'Nguyen Mai Phuc Hau', '2002', 'Vinh Long', 'hau@gmail.com', '0879654565', 'K01', 'TE01'),
('B20002', 'Tran Tien Dat', '2002', 'Kien Giang', 'dat@gmail.com', '0879654566', 'K01', 'TE01'),
('B20003', 'Nguyen Dung Phuong Huy', '2002', 'Vinh Long', 'huy@gmail.com', '0879654567', 'K01', 'TE02'),
('B20004', 'Nguyen Van An', '2004', 'Can Tho', 'an@gmail.com', '0879654568', 'K01', 'TE02'),
('B20005', 'Tran Thi Binh', '2004', 'Hau Giang', 'binh@gmail.com', '0879654569', 'K02', 'TE03'),
('B20006', 'Le Van Cuong', '2004', 'Soc Trang', 'cuong@gmail.com', '0879654570', 'K02', 'TE04'),
('B20007', 'Hoang Thi Lan', '2005', 'Bac Lieu', 'lan@gmail.com', '0879654571', 'K02', 'TE05'),
('B20008', 'Nguyen Van Phuc', '2005', 'Can Tho', 'phuc@gmail.com', '0879654572', 'K02', 'TE06'),
('B20009', 'Tran Van Hieu', '2005', 'Hau Giang', 'hieu@gmail.com', '0879654573', 'K02', 'TE07'),
('B20010', 'Le Thi Thu', '2006', 'Soc Trang', 'thu@gmail.com', '0879654574', 'K03', 'TE03'),
('B20011', 'Nguyen Van Quan', '2006', 'Bac Lieu', 'quan@gmail.com', '0879654575', 'K03', 'TE33'),
('B20012', 'Tran Van Tinh', '2006', 'Can Tho', 'tinh@gmail.com', '0879654576', 'K03', 'TE04'),
('B20013', 'Hoang Van Tho', '2007', 'Hau Giang', 'tho@gmail.com', '0879654577', 'K03', 'TE05'),
('B20014', 'Le Van Thang', '2007', 'Soc Trang', 'thang@gmail.com', '0879654578', 'K03', 'TE06');


-- Tạo bảng `giangvien` cho thông tin giảng viên
CREATE TABLE `giangvien` (
  `id_gv` VARCHAR(45) NOT NULL,
  `ten_gv` VARCHAR(45) DEFAULT NULL,
  `chuyen_nganh` VARCHAR(45) DEFAULT NULL,
  `kinh_nghiem` VARCHAR(100) DEFAULT NULL,
  `lich_day` VARCHAR(45) DEFAULT NULL,
  `id_khoa_hoc` VARCHAR(45) NOT NULL,
  `id_lop` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_gv`),
  FOREIGN KEY (`id_lop`) REFERENCES `lop_hoc`(`id_lop`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Đổ dữ liệu vào bảng `giangvien`
INSERT INTO `giangvien` (`id_gv`, `ten_gv`, `chuyen_nganh`, `kinh_nghiem`, `lich_day`, `id_khoa_hoc`, `id_lop`) VALUES
('gv01', 'Nguyen Thi Kim Yen', 'Su pham Ngoai Ngu', 'Giang vien DH', 'Thu 2-4-6', 'K01', 'TE01'),
('gv02', 'Phan Tan Tai', 'Anh van Giao tiep', 'Giang vien Trung tam', 'Thu 3-5-7', 'K01', 'TE02'),
('gv03', 'Tran Van Hung', 'Quan tri Nhan su', 'Giang vien Kinh doanh', 'Thu 2-4-6', 'K02', 'TE03'),
('gv04', 'Le Thi Huong', 'Ke toan Tai chinh', 'Giang vien Kinh doanh', 'Thu 3-5-7', 'K02', 'TE04'),
('gv05', 'Nguyen Van Hieu', 'Kinh te Phat trien', 'Giang vien Kinh te', 'Thu 2-4-6', 'K03', 'TE05'),
('gv06', 'Pham Van Cuong', 'Ky thuat Lap trinh', 'Giang vien CNTT', 'Thu 3-5-7', 'K03', 'TE06'),
('gv07', 'Hoang Thi Lan', 'Quan ly Du an', 'Giang vien Quan tri', 'Thu 2-4-6', 'K03', 'TE07');


	-- Tạo bảng `khoahoc` cho thông tin khóa học
	CREATE TABLE `khoahoc` (
	  `id_khoa_hoc` VARCHAR(11) NOT NULL,
	  `ten_khoa_hoc` VARCHAR(45) DEFAULT NULL,
	  `ngay_bd` DATE DEFAULT NULL,
	  `ngay_kt` DATE DEFAULT NULL,
	  `tai_lieu` VARCHAR(45) DEFAULT NULL,
	  `mota` VARCHAR(45) DEFAULT NULL,
	  `id_lop` VARCHAR(45) NOT NULL,
	  PRIMARY KEY (`ten_khoa_hoc`),
	  FOREIGN KEY (`id_lop`) REFERENCES `lop_hoc`(`id_lop`) ON DELETE CASCADE
	) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

	-- Đổ dữ liệu vào bảng `khoahoc`
INSERT INTO `khoahoc` (`id_khoa_hoc`, `ten_khoa_hoc`, `ngay_bd`, `ngay_kt`, `tai_lieu`, `mota`, `id_lop`) VALUES
('K01', 'TOEFL 100+', '2023-01-01', '2023-05-01', 'Tài liệu TOEFL', 'Chinh phục 100+ TOEFL', 'TE01'),
('K01', 'IELTS 8.0+', '2023-07-01', '2023-12-01', 'Tài liệu IELTS', 'Đạt điểm 8.0+ IELTS', 'TE02'),
('K04', 'Tiếng Anh Giao tiếp', '2023-02-01', '2023-06-01', 'Tài liệu Giao tiếp', 'Học giao tiếp tiếng Anh', 'TE03'),
('K02', 'TOEIC 750+', '2023-08-01', '2023-12-01', 'Tài liệu TOEIC', 'Chinh phục 750+ TOEIC', 'TE04'),
('K02', 'Tiếng Anh Phản xạ', '2023-03-01', '2023-07-01', 'Tài liệu Phản xạ', 'Học tiếng Anh phản xạ', 'TE05'),
('K03', 'TOEFL Speaking', '2023-09-01', '2023-12-01', 'Tài liệu TOEFL Speaking', 'Luyện TOEFL Speaking', 'TE06'),
('K03', 'Tiếng Anh Kinh tế', '2023-04-01', '2023-08-01', 'Tài liệu Kinh tế', 'Học tiếng Anh kinh tế', 'TE07'),
('K02', 'TOEIC Listening', '2023-10-01', '2023-12-01', 'Tài liệu TOEIC Listening', 'Luyện TOEIC Listening', 'TE08'),
('K01', 'Tiếng Anh Du lịch', '2023-05-01', '2023-09-01', 'Tài liệu Du lịch', 'Học tiếng Anh du lịch', 'TE09'),
('K04', 'TOEFL Writing', '2023-11-01', '2023-12-01', 'Tài liệu TOEFL Writing', 'Luyện TOEFL Writing', 'TE10');


-- Thêm User mới
DELIMITER //
CREATE PROCEDURE ThemNguoiDung(
    IN p_Username VARCHAR(45),
    IN p_Password VARCHAR(45),
    IN p_Email VARCHAR(45)
)
BEGIN
    INSERT INTO `user` (`Username`, `Password`, `Email`) VALUES (p_Username, p_Password, p_Email);
END //
DELIMITER ;

-- Hiển thị thông tin lớp học dựa trên id_khoa_hoc
DELIMITER //
CREATE PROCEDURE HienThiLopHocTheoKhoaHoc(IN p_id_khoa_hoc VARCHAR(11))
BEGIN
    SELECT lh.* 
    FROM lop_hoc lh
    INNER JOIN khoahoc kh ON lh.id_lop = kh.id_lop
    WHERE kh.id_khoa_hoc = p_id_khoa_hoc;
END //
DELIMITER ;
-- Đếm số lượng học viên:
DELIMITER //
CREATE FUNCTION DemSoLuongHocVien() RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM hocvien;
    RETURN total;
END //
DELIMITER ;
giangviengiangvien
-- Đếm số lượng giảng viên:
DELIMITER //
CREATE FUNCTION DemSoLuongGiangVien() RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM giangvien;
    RETURN total;
END //
DELIMITER ;

-- Đếm số lượng khóa học:
DELIMITER //
CREATE FUNCTION DemSoLuongKhoaHoc() RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM khoahoc;
    RETURN total;
END //
DELIMITER ;
-- thêm học viên
DELIMITER //
CREATE PROCEDURE ThemHocVien(
    IN p_id_hocvien VARCHAR(45),
    IN p_ten_hocvien VARCHAR(45),
    IN p_namsinh VARCHAR(45),
    IN p_diachi VARCHAR(100),
    IN p_email VARCHAR(45),
    IN p_phone VARCHAR(45),
    IN p_id_khoa_hoc VARCHAR(45),
    IN p_id_lop VARCHAR(45)
)
BEGIN
    INSERT INTO `hocvien` (`id_hocvien`, `ten_hocvien`, `namsinh`, `diachi`, `email`, `phone`, `id_khoa_hoc`, `id_lop`)
    VALUES (p_id_hocvien, p_ten_hocvien, p_namsinh, p_diachi, p_email, p_phone, p_id_khoa_hoc, p_id_lop);
END //
DELIMITER ;

-- sửa thông tin học viên
DELIMITER //
CREATE PROCEDURE SuaThongTinHocVien(
    IN p_id_hocvien VARCHAR(45),
    IN p_ten_hocvien VARCHAR(45),
    IN p_namsinh VARCHAR(45),
    IN p_diachi VARCHAR(100),
    IN p_email VARCHAR(45),
    IN p_phone VARCHAR(45),
    IN p_id_khoa_hoc VARCHAR(45),
    IN p_id_lop VARCHAR(45)
)
BEGIN
    UPDATE `hocvien`
    SET `ten_hocvien` = p_ten_hocvien,
        `namsinh` = p_namsinh,
        `diachi` = p_diachi,
        `email` = p_email,
        `phone` = p_phone,
        `id_khoa_hoc` = p_id_khoa_hoc,
        `id_lop` = p_id_lop
    WHERE `id_hocvien` = p_id_hocvien;
END //
DELIMITER ;
--  xóa học viên
DELIMITER //
CREATE PROCEDURE XoaHocVien(IN p_id_hocvien VARCHAR(45))
BEGIN
    DELETE FROM `hocvien`
    WHERE `id_hocvien` = p_id_hocvien;
END //
DELIMITER ;

-- Thêm giảng viên
DELIMITER //
CREATE PROCEDURE ThemGiangVien(
    IN p_id_gv VARCHAR(45),
    IN p_ten_gv VARCHAR(45),
    IN p_chuyen_nganh VARCHAR(45),
    IN p_kinh_nghiem VARCHAR(100),
    IN p_lich_day VARCHAR(45),
    IN p_id_khoa_hoc VARCHAR(45),
    IN p_id_lop VARCHAR(45)
)
BEGIN
    INSERT INTO `giangvien` (`id_gv`, `ten_gv`, `chuyen_nganh`, `kinh_nghiem`, `lich_day`, `id_khoa_hoc`, `id_lop`)
    VALUES (p_id_gv, p_ten_gv, p_chuyen_nganh, p_kinh_nghiem, p_lich_day, p_id_khoa_hoc, p_id_lop);
END //
DELIMITER ;

-- Sửa thông tin giảng viên
DELIMITER //
CREATE PROCEDURE SuaThongTinGiangVien(
    IN p_id_gv VARCHAR(45),
    IN p_ten_gv VARCHAR(45),
    IN p_chuyen_nganh VARCHAR(45),
    IN p_kinh_nghiem VARCHAR(100),
    IN p_lich_day VARCHAR(45),
    IN p_id_khoa_hoc VARCHAR(45),
    IN p_id_lop VARCHAR(45)
)
BEGIN
    UPDATE `giangvien`
    SET `ten_gv` = p_ten_gv,
        `chuyen_nganh` = p_chuyen_nganh,
        `kinh_nghiem` = p_kinh_nghiem,
        `lich_day` = p_lich_day,
        `id_khoa_hoc` = p_id_khoa_hoc,
        `id_lop` = p_id_lop
    WHERE `id_gv` = p_id_gv;
END //
DELIMITER ;

-- Xóa giảng viên
DELIMITER //
CREATE PROCEDURE XoaGiangVien(IN p_id_gv VARCHAR(45))
BEGIN
    DELETE FROM `giangvien`
    WHERE `id_gv` = p_id_gv;
END //
DELIMITER ;

-- Hàm sắp xếp học viên theo ID
DELIMITER //
CREATE FUNCTION SapXepHocVienTheoID()
RETURNS VARCHAR(100)
BEGIN
    DECLARE sorted_list VARCHAR(100);
    SET sorted_list = (
        SELECT GROUP_CONCAT(id_hocvien ORDER BY id_hocvien ASC SEPARATOR ', ') FROM hocvien
    );
    RETURN sorted_list;
END //
DELIMITER ;


-- Hàm sắp xếp học viên theo tên
DELIMITER //
CREATE FUNCTION SapXepHocVienTheoTen()
RETURNS VARCHAR(1000)
BEGIN
    DECLARE sorted_list VARCHAR(1000);
    SET sorted_list = (
        SELECT GROUP_CONCAT(id_hocvien ORDER BY RIGHT(ten_hocvien, 1) ASC SEPARATOR ', ') FROM hocvien
    );
    RETURN sorted_list;
END //
DELIMITER ;

-- Tạo stored procedure sắp xếp học viên theo ID
DELIMITER //
CREATE PROCEDURE SapXepVaHienThiHocVienTheoID()
BEGIN
    SELECT * FROM hocvien
    ORDER BY id_hocvien DESC;
END //

-- Tạo stored procedure sắp xếp học viên theo tên
CREATE PROCEDURE SapXepVaHienThiHocVienTheoTen()
BEGIN
    SELECT * FROM hocvien
    ORDER BY ten_hocvien DESC	;
END //

DELIMITER ;
-- Tạo stored procedure sắp xếp giảng viên theo ID
DELIMITER //
CREATE PROCEDURE SapXepVaHienThiGiangVienTheoID()
BEGIN
    SELECT * FROM giangvien
    ORDER BY id_gv ASC;
END //
DELIMITER ;
-- Tạo stored procedure sắp xếp giảng viên theo tên:
DELIMITER //
CREATE PROCEDURE SapXepVaHienThiGiangVienTheoTen()
BEGIN
    SELECT * FROM giangvien
    ORDER BY ten_gv ASC;
END //
DELIMITER ;

--  Thêm Khóa Học
DELIMITER //
CREATE PROCEDURE ThemKhoaHoc(
    IN p_id_khoa_hoc VARCHAR(11),
    IN p_ten_khoa_hoc VARCHAR(45),
    IN p_ngay_bd DATE,
    IN p_ngay_kt DATE,
    IN p_tai_lieu VARCHAR(45),
    IN p_mota VARCHAR(45),
    IN p_id_lop VARCHAR(45)
)
BEGIN
    INSERT INTO `khoahoc` (`id_khoa_hoc`, `ten_khoa_hoc`, `ngay_bd`, `ngay_kt`, `tai_lieu`, `mota`, `id_lop`)
    VALUES (p_id_khoa_hoc, p_ten_khoa_hoc, p_ngay_bd, p_ngay_kt, p_tai_lieu, p_mota, p_id_lop);
END //
DELIMITER ;
 --  Sửa Thông Tin Khóa Học
DELIMITER //
CREATE PROCEDURE `SuaKhoaHoc` (
    IN p_id_khoa_hoc VARCHAR(11),
    IN p_ten_khoa_hoc VARCHAR(45),
    IN p_ngay_bd DATE,
    IN p_ngay_kt DATE,
    IN p_tai_lieu VARCHAR(45),
    IN p_mota VARCHAR(45),
    IN p_id_lop VARCHAR(45)
)
BEGIN
    UPDATE `khoahoc`
    SET `id_khoa_hoc` = p_id_khoa_hoc,
        `ngay_bd` = p_ngay_bd,
        `ngay_kt` = p_ngay_kt,
        `tai_lieu` = p_tai_lieu,
        `mota` = p_mota,
        `id_lop` = p_id_lop
    WHERE `ten_khoa_hoc` = p_ten_khoa_hoc;
END;

//
DELIMITER ;


-- Xoá khoá học
DELIMITER //

CREATE PROCEDURE XoaKhoaHocTheoLop(IN p_id_lop VARCHAR(11))
BEGIN
    -- Kiểm tra xem lớp có tồn tại hay không
    DECLARE lop_count INT;
    SELECT COUNT(*) INTO lop_count FROM lop_hoc WHERE id_lop = p_id_lop;
    
    IF lop_count = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Lớp không tồn tại.';
    ELSE
        -- Xóa khóa học dựa trên ID lớp học
        DELETE FROM khoahoc WHERE id_lop = p_id_lop;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE XoaKhoaHocTheoTen(IN p_ten_khoa_hoc VARCHAR(45))
BEGIN
    -- Xóa khóa học dựa trên tên
    DELETE FROM khoahoc WHERE ten_khoa_hoc = p_ten_khoa_hoc;
END //

DELIMITER ;

-- Sắp xếp khoá học theo tên
DELIMITER //
CREATE PROCEDURE SapXepKhoaHocTheoTen()
BEGIN
    SELECT * FROM khoahoc
    ORDER BY ten_khoa_hoc;
END //
DELIMITER ;

-- Sắp xếp khoá học theo ID
DELIMITER //
CREATE PROCEDURE SapXepKhoaHocTheoId()
BEGIN
    SELECT * FROM khoahoc
    ORDER BY id_khoa_hoc;	
END //
DELIMITER ;



--  Thêm Lớp
DELIMITER //

CREATE PROCEDURE ThemLop (
    IN p_id_lop VARCHAR(45),
    IN p_ten_lop VARCHAR(45),
    IN p_buoi_hoc VARCHAR(45),
    IN p_tg_bd TIME,
    IN p_tg_kt TIME,
    IN p_phong VARCHAR(45)
)
BEGIN
    INSERT INTO lop_hoc (id_lop, ten_lop, buoi_hoc, tg_bd, tg_kt, phong)
    VALUES (p_id_lop, p_ten_lop, p_buoi_hoc, p_tg_bd, p_tg_kt, p_phong);
END;

//

DELIMITER ;

-- Sửa lớp
DELIMITER //

CREATE PROCEDURE SuaLop (
    IN p_id_lop VARCHAR(45),
    IN p_ten_lop VARCHAR(45),
    IN p_buoi_hoc VARCHAR(45),
    IN p_tg_bd TIME,
    IN p_tg_kt TIME,
    IN p_phong VARCHAR(45)
)
BEGIN
    UPDATE lop_hoc
    SET ten_lop = p_ten_lop,
        buoi_hoc = p_buoi_hoc,
        tg_bd = p_tg_bd,
        tg_kt = p_tg_kt,
        phong = p_phong
    WHERE id_lop = p_id_lop;
END;

//

DELIMITER ;



-- Xoá lớp
DELIMITER //

CREATE PROCEDURE XoaLop (
    IN p_id_lop VARCHAR(45)
)
BEGIN
    DELETE FROM lop_hoc
    WHERE id_lop = p_id_lop;
END;

//

DELIMITER ;
-- Đổi mật khẩu
DELIMITER //
CREATE PROCEDURE ChangePassword(IN p_Username VARCHAR(45), IN p_OldPassword VARCHAR(45), IN p_NewPassword VARCHAR(45))
BEGIN
  DECLARE v_CurrentPassword VARCHAR(45);
  SELECT `Password` INTO v_CurrentPassword FROM `user` WHERE `Username` = p_Username;
  
  IF v_CurrentPassword = p_OldPassword THEN
    UPDATE `user` SET `Password` = p_NewPassword WHERE `Username` = p_Username;
  ELSE
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Mat khau cu khong chinh xac';
  END IF;
END //
DELIMITER ;

-- kiểm tra mật khẩu cũ
DELIMITER //
CREATE PROCEDURE CheckPassword(IN p_username VARCHAR(255), IN p_oldPassword VARCHAR(255), OUT p_passwordCheck INT)
BEGIN
    SELECT COUNT(*) INTO p_passwordCheck
    FROM your_user_table
    WHERE username = p_username AND password = p_oldPassword;
END;
//
DELIMITER ;

-- Sắp xếp lớp học theo tên
DELIMITER //

CREATE PROCEDURE `SapXepLopTheoTen`()
BEGIN
    SELECT * FROM lop_hoc ORDER BY ten_lop DESC;
END//

DELIMITER ;

-- Sắp xếp lớp học theo ID
DELIMITER //

CREATE PROCEDURE `SapXepLopTheoID`()
BEGIN
    SELECT * FROM lop_hoc ORDER BY id_lop ASC;
END//

DELIMITER ;

CREATE TABLE log_hocvien (
  id_log INT AUTO_INCREMENT PRIMARY KEY,
  action VARCHAR(10) NOT NULL,
  id_hocvien VARCHAR(45) NOT NULL,
  ten_hocvien VARCHAR(45),
  thoi_gian TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DELIMITER //
CREATE TRIGGER tr_insert_hocvien
AFTER INSERT ON hocvien
FOR EACH ROW
BEGIN
  -- Ghi log khi có dữ liệu mới được chèn vào bảng `hocvien`
  INSERT INTO log_hocvien (action, id_hocvien, ten_hocvien)
  VALUES ('INSERT', NEW.id_hocvien, NEW.ten_hocvien);
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER tr_update_hocvien
AFTER UPDATE ON hocvien
FOR EACH ROW
BEGIN
  IF OLD.ten_hocvien != NEW.ten_hocvien THEN
    INSERT INTO log_hocvien (action, id_hocvien, ten_hocvien)
    VALUES ('UPDATE', NEW.id_hocvien, NEW.ten_hocvien);
  END IF;
END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER tr_delete_hocvien
AFTER DELETE ON hocvien
FOR EACH ROW
BEGIN
  INSERT INTO log_hocvien (action, id_hocvien, ten_hocvien)
  VALUES ('DELETE', OLD.id_hocvien, OLD.ten_hocvien);
END;
//
DELIMITER ;

CREATE TABLE log_giangvien (
  id_log INT AUTO_INCREMENT PRIMARY KEY,   
  action VARCHAR(10) NOT NULL,            
  id_gv VARCHAR(45) NOT NULL,            
  ten_gv VARCHAR(45),                    
  thoi_gian TIMESTAMP DEFAULT CURRENT_TIMESTAMP  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Trigger sau khi chèn dữ liệu mới vào bảng `giangvien`
DELIMITER //
CREATE TRIGGER tr_insert_giangvien
AFTER INSERT ON giangvien
FOR EACH ROW
BEGIN
  -- Ghi log khi có dữ liệu mới được chèn vào bảng `giangvien`
  INSERT INTO log_giangvien (action, id_gv, ten_gv)
  VALUES ('INSERT', NEW.id_gv, NEW.ten_gv);
END;
//
DELIMITER ;

-- Trigger sau khi cập nhật thông tin trong bảng `giangvien`
DELIMITER //
CREATE TRIGGER tr_update_giangvien
AFTER UPDATE ON giangvien
FOR EACH ROW
BEGIN
  -- Kiểm tra nếu tên giảng viên bị thay đổi sau khi cập nhật
  IF OLD.ten_gv != NEW.ten_gv THEN
    -- Ghi log sự kiện cập nhật vào bảng `log_giangvien`
    INSERT INTO log_giangvien (action, id_gv, ten_gv)
    VALUES ('UPDATE', NEW.id_gv, NEW.ten_gv);
  END IF;
END;
//
DELIMITER ;

-- Trigger sau khi xoá dữ liệu từ bảng `giangvien`
DELIMITER //
CREATE TRIGGER tr_delete_giangvien
AFTER DELETE ON giangvien
FOR EACH ROW
BEGIN
  -- Ghi log khi có dữ liệu bị xoá khỏi bảng `giangvien`
  INSERT INTO log_giangvien (action, id_gv, ten_gv)
  VALUES ('DELETE', OLD.id_gv, OLD.ten_gv);
END;
//
DELIMITER ;


CREATE TABLE log_khoahoc (
    id_log INT AUTO_INCREMENT PRIMARY KEY,
    action VARCHAR(45) NOT NULL,
    id_khoa_hoc VARCHAR(11),
    ten_khoa_hoc VARCHAR(45),
    log_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
use ql_lop_av;
SET GLOBAL event_scheduler = ON;

DELIMITER //
DROP EVENT IF EXISTS XoaKhoaHocQuaHan;

CREATE EVENT XoaKhoaHocQuaHan
ON SCHEDULE EVERY 1 MINUTE
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    -- Ghi log trước khi xóa
    INSERT INTO log_khoahoc (action, id_khoa_hoc, ten_khoa_hoc)
    SELECT 'Qua han', id_khoa_hoc, ten_khoa_hoc
    FROM khoahoc
    WHERE ngay_kt < CURDATE();

    -- Xóa khóa học
    DELETE FROM khoahoc WHERE ngay_kt < CURDATE();
END;
//
DELIMITER ;



DELIMITER //
CREATE TRIGGER after_khoahoc_insert
AFTER INSERT ON khoahoc
FOR EACH ROW
BEGIN
    INSERT INTO log_khoahoc (action, id_khoa_hoc, ten_khoa_hoc) 
    VALUES ('INSERT', NEW.id_khoa_hoc, NEW.ten_khoa_hoc);
END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER after_khoahoc_update
AFTER UPDATE ON khoahoc
FOR EACH ROW
BEGIN
    INSERT INTO log_khoahoc (action, id_khoa_hoc, ten_khoa_hoc) 
    VALUES ('UPDATE', NEW.id_khoa_hoc, NEW.ten_khoa_hoc);
END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER after_khoahoc_delete
AFTER DELETE ON khoahoc
FOR EACH ROW
BEGIN
    INSERT INTO log_khoahoc (action, id_khoa_hoc, ten_khoa_hoc) 
    VALUES ('DELETE', OLD.id_khoa_hoc, OLD.ten_khoa_hoc);
END;
//
DELIMITER ;


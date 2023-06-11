USE [BookWorld]
GO
/****** Object:  Table [dbo].[Authors]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authors](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[author_name] [nvarchar](255) NULL,
	[date_of_birth] [date] NOT NULL,
	[nationality] [nvarchar](50) NOT NULL,
	[description] [nvarchar](max) NULL,
 CONSTRAINT [PK_Authors] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Authors_Books]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authors_Books](
	[author_id] [bigint] NOT NULL,
	[book_id] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Authors_Books] PRIMARY KEY CLUSTERED 
(
	[author_id] ASC,
	[book_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books](
	[id] [nvarchar](50) NOT NULL,
	[title] [nvarchar](max) NOT NULL,
	[category_id] [bigint] NOT NULL,
	[publisher_id] [bigint] NOT NULL,
	[created_date] [datetime] NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[image] [nvarchar](max) NULL,
	[available] [bit] NULL,
	[description] [nvarchar](max) NULL,
 CONSTRAINT [PK_Books] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Carts]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carts](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[book_id] [nvarchar](50) NOT NULL,
	[total_price] [float] NOT NULL,
	[quantity] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[category_name] [nvarchar](max) NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Favorites]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Favorites](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[book_id] [nvarchar](50) NOT NULL,
	[liked_date] [datetime] NOT NULL,
 CONSTRAINT [PK_Favorites] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_Details]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_Details](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[orders_id] [varchar](30) NOT NULL,
	[book_id] [nvarchar](50) NOT NULL,
	[total_price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_Order_Details] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [varchar](30) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[order_date] [datetime] NOT NULL,
	[status] [int] NOT NULL,
	[payment_method] [bit] NULL,
	[cancellation_date] [datetime] NULL,
	[cancellation_reason] [nvarchar](max) NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Publishers]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Publishers](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[publisher_name] [nvarchar](255) NOT NULL,
	[address] [nvarchar](max) NOT NULL,
	[city] [nvarchar](max) NOT NULL,
	[country] [nvarchar](max) NOT NULL,
	[email] [nvarchar](255) NOT NULL,
	[phone] [varchar](12) NOT NULL,
 CONSTRAINT [PK_Publishers] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shares]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shares](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[book_id] [nvarchar](50) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[shared_date] [datetime] NOT NULL,
 CONSTRAINT [PK_Shares] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[full_name] [nvarchar](100) NOT NULL,
	[phone] [varchar](12) NULL,
	[email] [varchar](255) NOT NULL,
	[address] [nvarchar](max) NULL,
	[image] [nvarchar](100) NULL,
	[admin] [bit] NULL,
	[activated] [bit] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[Authors_Books]  WITH CHECK ADD  CONSTRAINT [FK_Authors_Authors_Books] FOREIGN KEY([author_id])
REFERENCES [dbo].[Authors] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Authors_Books] CHECK CONSTRAINT [FK_Authors_Authors_Books]
GO
ALTER TABLE [dbo].[Authors_Books]  WITH CHECK ADD  CONSTRAINT [FK_Books_Authors_Books] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Authors_Books] CHECK CONSTRAINT [FK_Books_Authors_Books]
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD  CONSTRAINT [FK_Categories_Books] FOREIGN KEY([category_id])
REFERENCES [dbo].[Categories] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Books] CHECK CONSTRAINT [FK_Categories_Books]
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD  CONSTRAINT [FK_Publishers_Books] FOREIGN KEY([publisher_id])
REFERENCES [dbo].[Publishers] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Books] CHECK CONSTRAINT [FK_Publishers_Books]
GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Books] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([id])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK_Cart_Books]
GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK_Cart_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK_Cart_Users]
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Books_Favorites] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Books_Favorites]
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Users_Favorites] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([username])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Users_Favorites]
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD  CONSTRAINT [FK_Books_Order_Details] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Order_Details] CHECK CONSTRAINT [FK_Books_Order_Details]
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD  CONSTRAINT [FK_Order_Order_Details] FOREIGN KEY([orders_id])
REFERENCES [dbo].[Orders] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Order_Details] CHECK CONSTRAINT [FK_Order_Order_Details]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([username])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Users]
GO
ALTER TABLE [dbo].[Shares]  WITH CHECK ADD  CONSTRAINT [FK_Books_Shares] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([id])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Shares] CHECK CONSTRAINT [FK_Books_Shares]
GO
ALTER TABLE [dbo].[Shares]  WITH CHECK ADD  CONSTRAINT [FK_Users_Shares] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([username])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Shares] CHECK CONSTRAINT [FK_Users_Shares]
GO
/****** Object:  StoredProcedure [dbo].[sp_dsPhieuNhapKho]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*
SELECT * FROM dbo.TaiKhoan
SELECT * FROM dbo.NhanVien
SELECT * FROM dbo.KhachHang
SELECT * FROM dbo.DanhMuc
SELECT * FROM dbo.SanPhamBan
SELECT * FROM dbo.QuaTang
SELECT * FROM dbo.HoaDon 
SELECT * FROM dbo.HoaDonChiTiet
SELECT * FROM dbo.PhieuNhapKho
SELECT * FROM dbo.PhieuNhapChiTiet
--DROP DATABASE QL_PHU_KIEN_QUA_TANG
*/

/*
SELECT COUNT(SoLuong), SUM(thanhTien) FROM dbo.PhieuNhapChiTiet
WHERE MaPNK = 'MP00001'
GROUP BY MaSP*/

CREATE PROC [dbo].[sp_dsPhieuNhapKho] AS 
SELECT pnct.MaPNK,pnk.NgayNhap, SUM (SoLuong) SoLuong, SUM(thanhTien) ThanhTien FROM dbo.PhieuNhapChiTiet pnct
INNER JOIN dbo.PhieuNhapKho pnk ON pnk.MaPNK = pnct.MaPNK
GROUP BY pnct.MaPNK,pnk.NgayNhap
GO
/****** Object:  StoredProcedure [dbo].[sp_TimHD_TheoNgay]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[sp_TimHD_TheoNgay] @TuNgay DATE, @DenNgay DATE AS 
SELECT hd.MaHD, hd.MaNV,hd.MaKH,kh.TenKH,hd.NgayTao,
(SELECT SUM(ThanhTien) FROM dbo.HoaDonChiTiet WHERE MaHD = hd.MaHD) TongTien
FROM dbo.HoaDon hd INNER JOIN dbo.NhanVien nv ON nv.MaNV = hd.MaNV
INNER JOIN dbo.KhachHang kh ON kh.MaKH = hd.MaKH 
WHERE hd.NgayTao BETWEEN @TuNgay AND @DenNgay
GO
/****** Object:  StoredProcedure [dbo].[sp_TimHD_TheoNgayTke]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[sp_TimHD_TheoNgayTke] @TuNgay DATE, @DenNgay DATE AS 
SELECT hdct.MaHD,hd.NgayTao, SUM(hdct.ThanhTien) TongTien
FROM dbo.HoaDon hd INNER JOIN dbo.HoaDonChiTiet hdct
ON hdct.MaHD = hd.MaHD
WHERE hd.NgayTao BETWEEN @TuNgay AND @DenNgay
GROUP BY hdct.MaHD ,hd.NgayTao
--SELECT * FROM dbo.HoaDonChiTiet --WHERE MaSP = ''
/*EXEC dbo.sp_ThongKeDoanhThu @TuNgay = '2022-12-15', -- date
                            @DenNgay = '2022-12-15' -- date
							*/
/*
SELECT MaSP,SoLuong,SUM(ThanhTien) FROM dbo.HoaDonChiTiet GROUP BY MaSP,SoLuong
SELECT MAX(MaPNK) FROM dbo.PhieuNhapKho
	
SELECT MAX(SUBSTRING(MaHD,LEN(MaHD) - 3,LEN(MaHD)))FROM HOADON
SELECT * FROM dbo.PhieuNhapChiTiet WHERE MaPNK LIKE '%MP00002%'
SELECT * FROM dbo.PhieuNhapChiTiet WHERE MaPNK LIKE '%MP00002%'
*/

GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeDoanhThu]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE PROC [dbo].[sp_ThongKeDoanhThu] @TuNgay DATE, @DenNgay DATE AS
SELECT hdct.MaHD, hd.NgayTao, SUM(hdct.ThanhTien) TongTien
FROM dbo.HoaDonChiTiet hdct INNER JOIN dbo.HoaDon hd ON hd.MaHD = hdct.MaHD
WHERE hd.NgayTao BETWEEN @TuNgay AND @DenNgay
GROUP BY hdct.MaHD, hd.NgayTao
GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeSanPham]    Script Date: 11/06/2023 10:57:15 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[sp_ThongKeSanPham] AS 
SELECT hdct.MaSP,(SELECT TenSP FROM dbo.SanPhamBan WHERE MaSP LIKE hdct.MaSP) TenSP,
SUM(SoLuong) SoLuong,SUM(hdct.ThanhTien) TongTien
FROM dbo.HoaDonChiTiet hdct GROUP BY MaSP ORDER BY SUM(SoLuong) DESC
GO

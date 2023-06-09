USE [BookWorld]
GO
/****** Object:  Table [dbo].[Authors]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  Table [dbo].[Authors_Books]    Script Date: 15/06/2023 10:07:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authors_Books](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[author_id] [bigint] NOT NULL,
	[book_id] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Authors_Books_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 15/06/2023 10:07:52 PM ******/
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
	[status] [int] NULL,
	[description] [nvarchar](max) NULL,
 CONSTRAINT [PK_Books] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Carts]    Script Date: 15/06/2023 10:07:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carts](
	[id] [nvarchar](50) NOT NULL,
	[user_id] [varchar](50) NOT NULL,
	[book_id] [nvarchar](50) NOT NULL,
	[total_price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[image] [nvarchar](max) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  Table [dbo].[Favorites]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  Table [dbo].[Order_Details]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  Table [dbo].[Orders]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  Table [dbo].[Publishers]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  Table [dbo].[Shares]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 15/06/2023 10:07:52 PM ******/
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
SET IDENTITY_INSERT [dbo].[Authors] ON 

INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (1, N'Hoàng dinh', CAST(N'2003-08-02' AS Date), N'Việt nam', N'ông bụt')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (5, N'Minh Khôi', CAST(N'2003-12-30' AS Date), N'Việt nam', N'ông già')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (6, N'Dale Carnegie', CAST(N'1888-11-24' AS Date), N'Việt nam', N'Dale Carnegie là một nhà văn và diễn giả nổi ')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (8, N'Vũ Trọng Phụng', CAST(N'1912-10-21' AS Date), N'Việt nam', N'Vũ Trọng Phụng là một nhà văn xuất sắc của Việt Nam, tác phẩm của ông thường phản ánh thực tế xã hội và nhân văn.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (9, N'Nguyễn Nhật Ánh', CAST(N'1955-05-07' AS Date), N'Việt nam', N'Nguyễn Nhật Ánh là một nhà văn nổi tiếng của Việt Nam, tác phẩm của ông thường đậm chất hài hước và chân thực.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (10, N'Nguyễn Huy Thiệp', CAST(N'1950-10-10' AS Date), N'Việt nam', N'Nguyễn Huy Thiệp là một nhà văn nổi tiếng của Việt Nam, tác phẩm của ông thường đặt câu hỏi về cuộc sống và tình yêu.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (12, N'Phạm Nhật Ánh', CAST(N'1966-05-20' AS Date), N'Việt nam', N'Phạm Nhật Ánh là một nhà văn nổi tiếng của Việt Nam, tác phẩm của ông thường mang tính nhân văn và cảm động.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (13, N'Nguyễn Du', CAST(N'1765-01-03' AS Date), N'Việt nam', N'Nguyễn Du là một nhà văn và nhà thơ nổi tiếng của Việt Nam, tác phẩm của ông có ảnh hưởng sâu sắc đến văn hóa dân tộc.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (14, N'Tô Hoài', CAST(N'1920-11-24' AS Date), N'Việt nam', N'Tô Hoài là một nhà văn nổi tiếng của Việt Nam, tác phẩm của ông thường mang tính giáo dục và truyền cảm hứng.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (15, N'Nam Cao', CAST(N'1915-08-21' AS Date), N'Việt nam', N'Nam Cao là một nhà văn xuất sắc của Việt Nam, tác phẩm của ông thường khắc họa cuộc sống đầy thăng trầm của con người.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (16, N'Nguyễn Thế Đức', CAST(N'1970-03-12' AS Date), N'Việt nam', N'Nguyễn Thế Đức là một nhà văn và nhà báo nổi tiếng của Việt Nam, tác phẩm của ông thường tập trung vào giáo dục trẻ em.')
INSERT [dbo].[Authors] ([id], [author_name], [date_of_birth], [nationality], [description]) VALUES (19, N'Ngô Tất Tố', CAST(N'1897-11-14' AS Date), N'Việt nam', N'Ngô Tất Tố là một nhà văn và nhà nghiên cứu văn học nổi tiếng của Việt Nam, tác phẩm của ông thường sắc bén')
SET IDENTITY_INSERT [dbo].[Authors] OFF
GO
SET IDENTITY_INSERT [dbo].[Authors_Books] ON 

INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (1, 1, N'B001')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (2, 5, N'B002')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (3, 6, N'B003')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (4, 8, N'B004')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (5, 9, N'B005')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (6, 10, N'B006')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (7, 12, N'B007')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (8, 13, N'B008')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (9, 14, N'B009')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (10, 15, N'B0010')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (11, 16, N'B001')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (12, 19, N'B002')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (31, 1, N'Aaaa1')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (32, 5, N'Aaaa1')
INSERT [dbo].[Authors_Books] ([id], [author_id], [book_id]) VALUES (36, 9, N'Aaaa1')
SET IDENTITY_INSERT [dbo].[Authors_Books] OFF
GO
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'Aaaa1', N'sss', 1, 1, CAST(N'2023-06-14T00:00:00.000' AS DateTime), 1, 0, N'Sach47.jpg', 2, N'111')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B001', N'Dế Mèn Phiêu Lưu Ký', 1, 1, CAST(N'2023-06-12T00:00:00.000' AS DateTime), 100000, 6, N'Sach11.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B0010', N'Dế Mèn Phiêu Lưu Ký', 10, 4, CAST(N'2023-06-12T00:00:00.000' AS DateTime), 70000, 0, N'Sach17.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B0012', N'123', 1, 1, CAST(N'2023-06-12T00:00:00.000' AS DateTime), 11, 0, N'Sach15.jpg', 2, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B0013', N'1111111', 2, 1, CAST(N'2023-06-12T17:28:24.090' AS DateTime), 1, 1, N'Sach22.jpg', 1, N'11')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B002', N'Số Đỏ', 2, 3, CAST(N'2023-06-12T17:29:41.697' AS DateTime), 120000, 1, N'Sach19.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B003', N'Tôi Thấy Hoa Vàng Trên Cỏ Xanh', 3, 4, CAST(N'2023-06-12T17:30:14.367' AS DateTime), 90000, 1, N'Sach27.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B004', N'Chiếc Lược Ngà', 4, 3, CAST(N'2023-06-12T17:30:31.627' AS DateTime), 150000, 1, N'Sach32.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B005', N'Tôi Là Bêtô', 5, 1, CAST(N'2023-06-12T17:30:44.913' AS DateTime), 85000, 1, N'Sach42.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B006', N'Truyện Kiều', 6, 4, CAST(N'2023-06-12T17:31:05.470' AS DateTime), 110000, 1, N'Sach50.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B007', N'Hai số phận', 7, 3, CAST(N'2023-06-12T17:31:22.447' AS DateTime), 110000, 1, N'Sach46.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B008', N'Những Cô Gái Rừng Xanh', 8, 3, CAST(N'2023-06-12T17:31:48.117' AS DateTime), 95000, 1, N'Sach45.jpg', 1, N'1')
INSERT [dbo].[Books] ([id], [title], [category_id], [publisher_id], [created_date], [price], [quantity], [image], [status], [description]) VALUES (N'B009', N'Nỗi Buồn Chiến Tranh', 9, 4, CAST(N'2023-06-12T17:32:10.010' AS DateTime), 85000, 1, N'Sach49.jpg', 1, N'1')
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([id], [category_name]) VALUES (1, N'Tiểu thuyết2221111')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (2, N'Khoa học viễn tưởng')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (3, N'Tâm lý học')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (4, N'Kinh doanh và quản lý')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (5, N'Tự truyện và hồi ký')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (6, N'Lịch sử')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (7, N'Văn học Việt Nam')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (8, N'Văn học nước ngoài')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (9, N'Huyền bí và ma quái')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (10, N'Kỹ năng sống')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (11, N'Y học và sức khỏe')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (12, N'Thiếu nhi')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (13, N'Tôn giáo và tâm linh')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (14, N'Nghệ thuật và thiết kế')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (15, N'Triết học và tư duy')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (16, N'Khoa học và công nghệ')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (17, N'Du lịch và khám phá')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (18, N'Đời sống gia đình')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (19, N'Âm nhạc và nghệ thuật biểu diễn')
INSERT [dbo].[Categories] ([id], [category_name]) VALUES (20, N'Thể thao và thể dục')
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Favorites] ON 

INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (1, N'user1', N'B001', CAST(N'2012-12-12T00:00:00.000' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (3, N'user1', N'B002', CAST(N'2012-12-12T00:00:00.000' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (4, N'user1', N'B003', CAST(N'2012-12-12T00:00:00.000' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (5, N'user1', N'B001', CAST(N'2012-12-12T00:00:00.000' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (6, N'user1', N'B0010', CAST(N'2023-06-11T23:11:41.480' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (7, N'user1', N'B0012', CAST(N'2023-06-11T23:11:50.570' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (8, N'user1', N'B005', CAST(N'2023-06-11T23:12:05.607' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (9, N'user1', N'B006', CAST(N'2023-06-11T23:12:26.390' AS DateTime))
INSERT [dbo].[Favorites] ([id], [user_id], [book_id], [liked_date]) VALUES (10, N'user1', N'B008', CAST(N'2023-06-11T23:18:59.447' AS DateTime))
SET IDENTITY_INSERT [dbo].[Favorites] OFF
GO
SET IDENTITY_INSERT [dbo].[Order_Details] ON 

INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (1, N'D001121223', N'B003', 90000, 3)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (2, N'D002121223', N'B005', 100000, 5)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (3, N'D002121223', N'B002', 100000, 5)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (4, N'D002121223', N'B004', 100000, 5)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (5, N'D002121223', N'B0010', 100000, 5)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (7, N'D005121223', N'B003', 1000, 3)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (8, N'D005121223', N'B004', 1200, 3)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (9, N'D005121223', N'B005', 3452, 3)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (10, N'D0013120623', N'B0010', 280000, 4)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (11, N'D0016120623', N'B0010', 70000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (12, N'D0023120623', N'B0012', 11, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (13, N'D0024120623', N'B0012', 33, 3)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (14, N'D0025120623', N'Aaaa1', 1, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (15, N'D0026150623', N'Aaaa1', 1, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (16, N'D0027150623', N'Aaaa1', 1, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (17, N'D0028150623', N'Aaaa1', 1, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (18, N'D0029150623', N'Aaaa1', 1, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (19, N'D0030150623', N'Aaaa1', 1, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (20, N'D0031150623', N'B001', 100000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (21, N'D0032150623', N'B001', 100000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (22, N'D0033150623', N'B0012', 11, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (23, N'D0034150623', N'B001', 100000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (24, N'D0035150623', N'B001', 100000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (25, N'D0036150623', N'B001', 200000, 2)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (26, N'D0037150623', N'B001', 100000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (27, N'D0038150623', N'B001', 100000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (28, N'D0039150623', N'B001', 100000, 1)
INSERT [dbo].[Order_Details] ([id], [orders_id], [book_id], [total_price], [quantity]) VALUES (29, N'D0039150623', N'B0010', 70000, 1)
SET IDENTITY_INSERT [dbo].[Order_Details] OFF
GO
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D001121223', N'user1', CAST(N'2022-12-12T00:00:00.000' AS DateTime), 3, 1, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'Hủy')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0013120623', N'user1', CAST(N'2023-06-12T10:52:21.430' AS DateTime), 2, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0013121223', N'user2', CAST(N'2022-12-12T00:00:00.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0015120623', N'user1', CAST(N'2023-06-12T11:04:48.390' AS DateTime), 3, 1, CAST(N'2023-06-12T14:32:53.353' AS DateTime), NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0016120623', N'user1', CAST(N'2023-06-12T11:06:24.577' AS DateTime), 3, 1, CAST(N'2023-06-12T14:44:29.807' AS DateTime), N'ssss')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0017120623', N'user1', CAST(N'2023-06-12T11:38:43.537' AS DateTime), 3, 1, CAST(N'2023-06-15T21:17:28.427' AS DateTime), N'')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0018120623', N'user1', CAST(N'2023-06-12T11:45:21.020' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0019120623', N'user1', CAST(N'2023-06-12T11:45:24.393' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0020120623', N'user1', CAST(N'2023-06-12T11:46:14.490' AS DateTime), 3, 1, CAST(N'2023-06-15T21:38:46.833' AS DateTime), N'')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0021120623', N'user1', CAST(N'2023-06-12T11:46:23.647' AS DateTime), 3, 1, CAST(N'2023-06-15T21:36:05.830' AS DateTime), N'')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D002121223', N'user1', CAST(N'2022-12-13T00:00:00.000' AS DateTime), 3, 1, CAST(N'2023-06-15T21:18:42.967' AS DateTime), N'')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0022120623', N'user1', CAST(N'2023-06-12T12:12:20.817' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0023120623', N'user1', CAST(N'2023-06-12T12:14:12.553' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0024120623', N'user1', CAST(N'2023-06-12T12:25:33.717' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0025120623', N'admin', CAST(N'2023-06-12T17:02:57.253' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0026150623', N'user1', CAST(N'2023-06-15T13:00:40.907' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0027150623', N'user1', CAST(N'2023-06-15T13:02:02.147' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0028150623', N'user1', CAST(N'2023-06-15T13:02:54.447' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0029150623', N'user1', CAST(N'2023-06-15T13:03:29.547' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0030150623', N'user1', CAST(N'2023-06-15T13:07:36.313' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0031150623', N'user1', CAST(N'2023-06-15T13:18:05.530' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D003121223', N'user1', CAST(N'2022-12-13T00:00:00.000' AS DateTime), 3, 1, CAST(N'2023-06-15T21:45:26.263' AS DateTime), N'')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0032150623', N'user1', CAST(N'2023-06-15T17:29:58.870' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0033150623', N'user1', CAST(N'2023-06-15T17:31:24.243' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0034150623', N'user1', CAST(N'2023-06-15T17:44:02.667' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0035150623', N'user1', CAST(N'2023-06-15T17:44:51.110' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0036150623', N'user1', CAST(N'2023-06-15T19:44:05.920' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0037150623', N'user1', CAST(N'2023-06-15T21:16:19.223' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0038150623', N'user1', CAST(N'2023-06-15T21:35:54.030' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D0039150623', N'user1', CAST(N'2023-06-15T21:44:46.147' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D004121223', N'user2', CAST(N'2022-12-13T00:00:00.000' AS DateTime), 3, 1, CAST(N'2022-12-12T00:00:00.000' AS DateTime), N'Hủy')
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D005121223', N'user2', CAST(N'2022-12-12T00:00:00.000' AS DateTime), 2, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D007121223', N'user2', CAST(N'2022-12-12T00:00:00.000' AS DateTime), 2, 0, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D008121223', N'user2', CAST(N'2022-12-12T00:00:00.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D009121223', N'user1', CAST(N'2023-12-23T00:00:00.000' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D010121223', N'user1', CAST(N'2023-12-23T00:00:00.000' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D011121223', N'user1', CAST(N'2023-12-23T00:00:00.000' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D011221223', N'user1', CAST(N'2023-12-23T00:00:00.000' AS DateTime), 0, NULL, NULL, NULL)
INSERT [dbo].[Orders] ([id], [user_id], [order_date], [status], [payment_method], [cancellation_date], [cancellation_reason]) VALUES (N'D012221223', N'user1', CAST(N'2023-12-23T00:00:00.000' AS DateTime), 0, NULL, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[Publishers] ON 

INSERT [dbo].[Publishers] ([id], [publisher_name], [address], [city], [country], [email], [phone]) VALUES (1, N'NXB Trẻ', N'Sóc trăng', N'Cần thơ', N'Việt nam', N'nxb@gmail.com', N'012345678')
INSERT [dbo].[Publishers] ([id], [publisher_name], [address], [city], [country], [email], [phone]) VALUES (3, N'NXB Văn học', N'Đồng tháp', N'Cần thơ', N'Việt nam', N'nxb@gmail.com', N'012345678')
INSERT [dbo].[Publishers] ([id], [publisher_name], [address], [city], [country], [email], [phone]) VALUES (4, N'NXB Kim Đồng', N'An Giang', N'Cần thơ', N'Việt', N'nxb@gmail.com', N'012345678')
SET IDENTITY_INSERT [dbo].[Publishers] OFF
GO
SET IDENTITY_INSERT [dbo].[Shares] ON 

INSERT [dbo].[Shares] ([id], [user_id], [book_id], [email], [shared_date]) VALUES (1, N'user1', N'B001', N'hoangdinh@gmail.com', CAST(N'2022-12-12T00:00:00.000' AS DateTime))
INSERT [dbo].[Shares] ([id], [user_id], [book_id], [email], [shared_date]) VALUES (3, N'user1', N'B002', N'tuhieu@gmail.com', CAST(N'2022-12-12T00:00:00.000' AS DateTime))
INSERT [dbo].[Shares] ([id], [user_id], [book_id], [email], [shared_date]) VALUES (4, N'user1', N'B0010', N'huutai@gmail.com', CAST(N'2022-12-12T00:00:00.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Shares] OFF
GO
INSERT [dbo].[Users] ([username], [password], [full_name], [phone], [email], [address], [image], [admin], [activated]) VALUES (N'admin', N'00000000', N'Super Admin', N'0123456778', N'admin@gmail.com', N'admin_CT', N'TamLyKyNang_50.png', 1, 1)
INSERT [dbo].[Users] ([username], [password], [full_name], [phone], [email], [address], [image], [admin], [activated]) VALUES (N'user1', N'00000000', N'Super User', N'0123456779', N'user1@gmail.com', N'user1_CT', NULL, 0, 1)
INSERT [dbo].[Users] ([username], [password], [full_name], [phone], [email], [address], [image], [admin], [activated]) VALUES (N'user2', N'PsQgpyqYW7qiZh0nxbvU', N'Super User', N'0123456779', N'nguyentaint979@gmail.com', N'user1_CT', N'img', 0, 1)
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
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK_Carts_Books] FOREIGN KEY([book_id])
REFERENCES [dbo].[Books] ([id])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK_Carts_Books]
GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK_Carts_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([username])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK_Carts_Users]
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
/****** Object:  StoredProcedure [dbo].[sp_dsPhieuNhapKho]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  StoredProcedure [dbo].[sp_TimHD_TheoNgay]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  StoredProcedure [dbo].[sp_TimHD_TheoNgayTke]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  StoredProcedure [dbo].[sp_ThongKeDoanhThu]    Script Date: 15/06/2023 10:07:52 PM ******/
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
/****** Object:  StoredProcedure [dbo].[sp_ThongKeSanPham]    Script Date: 15/06/2023 10:07:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[sp_ThongKeSanPham] AS 
SELECT hdct.MaSP,(SELECT TenSP FROM dbo.SanPhamBan WHERE MaSP LIKE hdct.MaSP) TenSP,
SUM(SoLuong) SoLuong,SUM(hdct.ThanhTien) TongTien
FROM dbo.HoaDonChiTiet hdct GROUP BY MaSP ORDER BY SUM(SoLuong) DESC
GO

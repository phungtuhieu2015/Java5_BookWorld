USE [master]
GO

CREATE DATABASE BookWorld
GO 

USE BookWorld
GO

-- TẠO BẢNG
CREATE TABLE [Users] (
	[username] VARCHAR(50) NOT NULL,
	[password] VARCHAR(50) NOT NULL,
	[full_name] NVARCHAR(100) NOT NULL,
	[phone] VARCHAR(12) ,
	[email] VARCHAR(255) NOT NULL,
	[address] NVARCHAR(MAX) ,
	[image] NVARCHAR(100),
	[admin] BIT ,
	[activated] BIT 
);
GO

CREATE TABLE [Authors] (
	[id] BIGINT IDENTITY(1,1) NOT NULL,
	[author_name] NVARCHAR(255) ,
	[date_of_birth] DATE NOT NULL,
	[nationality] NVARCHAR(50) NOT NULL,
	[description] NVARCHAR(MAX)
);
GO

CREATE TABLE [Publishers] (
	[id] BIGINT IDENTITY(1,1) NOT NULL,
	[publisher_name] NVARCHAR(255) NOT NULL,
	[address] NVARCHAR(MAX) NOT NULL,
	[city] NVARCHAR(MAX ) NOT NULL,
	[country] NVARCHAR(MAX) NOT NULL,
	[email] NVARCHAR(255) NOT NULL,
	[phone] VARCHAR(12) NOT NULL
);
GO
CREATE TABLE [Categories] (
	[id] BIGINT IDENTITY(1,1) NOT NULL,
	[category_name] NVARCHAR(MAX)
);
GO
CREATE TABLE [Books] (
	[id] VARCHAR(30) NOT NULL,
	title NVARCHAR(MAX) NOT NULL,
	[author_id] BIGINT NOT NULL,
	[category_id] BIGINT NOT NULL,
	[publisher_id] BIGINT NOT NULL,
	[created_date] DATETIME NOT NULL,
	[price] FLOAT NOT NULL,
	[quantity] INT NOT NULL,
	[image] NVARCHAR(MAX) NOT NULL,
	[available] BIT ,
	[description] NVARCHAR(MAX) ,
);
GO

CREATE TABLE [Authors_Books] (
	[author_id] BIGINT NOT NULL,
	[book_id] VARCHAR(30) NOT NULL,
);
GO
CREATE TABLE [Favorites] (
	[id] BIGINT IDENTITY(1,1) NOT NULL,
	[user_id] VARCHAR(50) NOT NULL,
	[book_id] VARCHAR(30) NOT NULL,
	[liked_date] DATETIME NOT NULL
);
GO 

CREATE TABLE [Shares] (
	[id] BIGINT IDENTITY(1,1) NOT NULL,
	[user_id] VARCHAR(50) NOT NULL,
	[book_id] VARCHAR(30) NOT NULL,
	[email] VARCHAR(255) NOT NULL,
	[shared_date] DATETIME NOT NULL
);
GO
CREATE TABLE [Orders] (
	[id] VARCHAR(30) NOT NULL,
	[user_id] VARCHAR(50) NOT NULL,
	[order_date] DATETIME NOT NULL,
	[status] INT NOT NULL,
	[payment_method] INT NOT NULL,
	[cancellation_date] DATETIME ,
	[cancellation_reason] NVARCHAR(MAX) ,
);
GO 

CREATE TABLE [Order_Details] (
	[id] BIGINT IDENTITY(1,1) NOT NULL,
	[orders_id] VARCHAR(30) NOT NULL,
	[book_id] VARCHAR(30) NOT NULL,
	[total_price] FLOAT NOT NULL,
	[quantity] INT NOT NULL
); 
GO
-- KHÓA CHÍNH

ALTER TABLE dbo.Users 
ADD CONSTRAINT PK_Users PRIMARY KEY(username)
GO 

ALTER TABLE dbo.Authors
ADD CONSTRAINT PK_Authors PRIMARY KEY(id)
GO

ALTER TABLE dbo.Authors_Books
ADD CONSTRAINT PK_Authors_Books PRIMARY KEY(author_id,book_id)
GO 

ALTER TABLE dbo.Publishers 
ADD CONSTRAINT PK_Publishers PRIMARY KEY (id)
GO
ALTER TABLE dbo.Categories
ADD CONSTRAINT PK_Categories PRIMARY KEY(id)
GO

ALTER TABLE dbo.Books
ADD CONSTRAINT PK_Books PRIMARY KEY (id)
GO

ALTER TABLE dbo.Favorites 
ADD CONSTRAINT PK_Favorites PRIMARY KEY(id)
GO

ALTER TABLE dbo.Shares 
ADD CONSTRAINT PK_Shares PRIMARY KEY (id)
GO

ALTER TABLE dbo.Orders 
ADD CONSTRAINT PK_Orders PRIMARY KEY(id)
GO

ALTER TABLE dbo.Order_Details
ADD CONSTRAINT PK_Order_Details PRIMARY KEY (id)
GO 

-- KHÓA NGOẠI

ALTER TABLE dbo.Authors_Books
ADD CONSTRAINT FK_Authors_Authors_Books
FOREIGN KEY(author_id) REFERENCES dbo.Authors(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Authors_Books
ADD CONSTRAINT FK_Books_Authors_Books
FOREIGN KEY(book_id) REFERENCES dbo.Books(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Books
ADD CONSTRAINT FK_Publishers_Books
FOREIGN KEY(publisher_id) REFERENCES dbo.Publishers(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO 

ALTER TABLE dbo.Books
ADD CONSTRAINT FK_Categories_Books 
FOREIGN KEY (category_id) REFERENCES dbo.Categories(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Favorites
ADD CONSTRAINT FK_Books_Favorites 
FOREIGN KEY(book_id) REFERENCES dbo.Books(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Favorites
ADD CONSTRAINT FK_Users_Favorites
FOREIGN KEY ([user_id]) REFERENCES dbo.Users(username) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Shares
ADD CONSTRAINT FK_Books_Shares 
FOREIGN KEY (book_id) REFERENCES dbo.Books(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Shares
ADD CONSTRAINT FK_Users_Shares
FOREIGN KEY ([user_id])REFERENCES dbo.Users(username) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Orders
ADD CONSTRAINT FK_Orders_Users
FOREIGN KEY ([user_id]) REFERENCES dbo.Users(username) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Order_Details
ADD CONSTRAINT FK_Order_Order_Details
FOREIGN KEY (orders_id) REFERENCES dbo.Orders(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO

ALTER TABLE dbo.Order_Details 
ADD CONSTRAINT FK_Books_Order_Details
FOREIGN KEY (book_id) REFERENCES dbo.Books(id) ON UPDATE CASCADE ON DELETE NO ACTION
GO


INSERT INTO dbo.Users
VALUES( 'admin',  'admin',   N'Super Admin',  '0123456778',   'admin@gmail.com',    N'admin_CT',  N'img',  1, 1  ),
		( 'user1',  'user1',   N'Super User',  '0123456779',   'user1@gmail.com',    N'user1_CT',  N'img',  0, 1  )
GO

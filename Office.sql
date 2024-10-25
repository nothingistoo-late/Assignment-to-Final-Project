USE [master]
GO

CREATE DATABASE [Office]
GO
USE [Office]
GO

CREATE TABLE [dbo].[tblUsers](
[userId] [nvarchar](50) NOT NULL,
[fullName] [nvarchar](50) NULL,
[password] [nvarchar](50) NULL,
[roleID] [nvarchar](50) NULL,
[gmail] [nvarchar](50) NULL,
[address] [nvarchar](50) NULL,
[status] [bit] NULL,
CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED
(
[userID] ASC
) WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON
    ) ON [PRIMARY]
) ON [PRIMARY];

DROP TABLE [dbo].[tblUsers];

INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],[gmail],[address], [status]) VALUES (N'user', N'Hoang', N'1', N'US',N'user1@gmail.com',N'ho chi minh',1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],[gmail],[address], [status]) VALUES (N'cs112', N'Le Thi B', N'xyz456', N'US',N'lethib@gmail.com',N'ho chi minh',1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],[gmail],[address], [status]) VALUES (N'cs113', N'Tran Van C', N'pass789', N'US', N'tranvanc@gmail.com', N'hai phong', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],[gmail],[address], [status]) VALUES (N'cs114', N'Pham Thi D', N'password123', N'US', N'phamthid@gmail.com', N'can tho', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],[gmail],[address], [status]) VALUES (N'cs115', N'Hoang Van E', N'admin123', N'US', N'hoangvane@gmail.com', N'vinh', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [roleID],[gmail],[address], [status]) VALUES (N'cs116', N'Dang Thi F', N'user123', N'US', N'dangthif@gmail.com', N'nha trang', 1)
go

CREATE TABLE [dbo].[tblProducts](
[idProducts] [nvarchar](50)NOT NULL,
[name] [nvarchar](50) NOT NULL,
[description] [nvarchar](50) NULL,
[type] [nvarchar](50) NULL,
[image] [nvarchar](max) NULL,
[price] [float]NOT NULL,
[status] [bit] NULL,
CONSTRAINT [PK_tblGlasses] PRIMARY KEY CLUSTERED
(
[idProducts] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
)ON [PRIMARY]
GO
SET ANSI_PADDING on
GO



DROP TABLE [dbo].[tblProducts];


CREATE TABLE [dbo].[tblInvoice](
[invId] [nvarchar](50) NOT NULL,
[userID] [nvarchar](50)not null,
[total] [float]NULL,
[dateBuy] [datetime] NULL,
[gmail] [nvarchar](50) NULL,
[address] [nvarchar](50) NULL,
CONSTRAINT [PK_tblInvoice] PRIMARY KEY CLUSTERED
(
[invId] ASC
) WITH (
        PAD_INDEX = OFF, 
        STATISTICS_NORECOMPUTE = OFF, 
        IGNORE_DUP_KEY = OFF, 
        ALLOW_ROW_LOCKS = ON, 
        ALLOW_PAGE_LOCKS = ON
    ) ON [PRIMARY]
) ON [PRIMARY];
GO

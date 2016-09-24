USE [master]
GO
/****** Object:  Database [workshapedb]    Script Date: 09/16/2016 14:13:56 ******/
CREATE DATABASE [workshapedb] ON  PRIMARY 
( NAME = N'workshapedb', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\workshapedb.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'workshapedb_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\workshapedb_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [workshapedb] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [workshapedb].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [workshapedb] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [workshapedb] SET ANSI_NULLS OFF
GO
ALTER DATABASE [workshapedb] SET ANSI_PADDING OFF
GO
ALTER DATABASE [workshapedb] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [workshapedb] SET ARITHABORT OFF
GO
ALTER DATABASE [workshapedb] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [workshapedb] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [workshapedb] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [workshapedb] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [workshapedb] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [workshapedb] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [workshapedb] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [workshapedb] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [workshapedb] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [workshapedb] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [workshapedb] SET  DISABLE_BROKER
GO
ALTER DATABASE [workshapedb] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [workshapedb] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [workshapedb] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [workshapedb] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [workshapedb] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [workshapedb] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [workshapedb] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [workshapedb] SET  READ_WRITE
GO
ALTER DATABASE [workshapedb] SET RECOVERY SIMPLE
GO
ALTER DATABASE [workshapedb] SET  MULTI_USER
GO
ALTER DATABASE [workshapedb] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [workshapedb] SET DB_CHAINING OFF
GO
USE [workshapedb]
GO
/****** Object:  Table [dbo].[product]    Script Date: 09/16/2016 14:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[idproduct] [int] NOT NULL,
	[reference] [nvarchar](50) NULL,
	[product_qr] [nvarchar](200) NULL,
	[fournisseur] [nvarchar](50) NULL,
	[ref_fournisseur] [nvarchar](50) NULL,
	[longueur_initiale] [numeric](12, 2) NULL,
	[longueur_actuelle] [numeric](12, 2) NULL,
	[largeur] [numeric](12, 2) NULL,
	[grammage] [nvarchar](50) NULL,
	[type_de_tissus] [nvarchar](50) NULL,
	[date_arrivee] [datetime] NULL,
	[transport_frigo] [nvarchar](50) NULL,
	[note] [text] NULL,
 CONSTRAINT [PK_product] PRIMARY KEY CLUSTERED 
(
	[idproduct] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[event]    Script Date: 09/16/2016 14:13:57 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[event](
	[idevent] [int] NOT NULL,
	[idproduct] [int] NULL,
	[name] [nvarchar](50) NULL,
	[date_in] [datetime] NULL,
	[date_out] [datetime] NULL,
 CONSTRAINT [PK_event] PRIMARY KEY CLUSTERED 
(
	[idevent] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_event_product]    Script Date: 09/16/2016 14:13:57 ******/
ALTER TABLE [dbo].[event]  WITH CHECK ADD  CONSTRAINT [FK_event_product] FOREIGN KEY([idproduct])
REFERENCES [dbo].[product] ([idproduct])
GO
ALTER TABLE [dbo].[event] CHECK CONSTRAINT [FK_event_product]
GO

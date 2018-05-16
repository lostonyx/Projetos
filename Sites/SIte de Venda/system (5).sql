-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 30-Mar-2018 às 06:47
-- Versão do servidor: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `system`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `admin`
--

INSERT INTO `admin` (`id`, `name`, `password`, `username`) VALUES
(1, 'wiljam admin', '21232f297a57a5a743894a0e4a801fc3', 'admin');

-- --------------------------------------------------------

--
-- Estrutura da tabela `changelog`
--

CREATE TABLE `changelog` (
  `id` int(11) NOT NULL,
  `texto` varchar(255) NOT NULL,
  `data` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `changelog`
--

INSERT INTO `changelog` (`id`, `texto`, `data`) VALUES
(5, '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>sdsd</p>\r\n</body>\r\n</html>\r\n', '2018-03-29 22:45:06'),
(6, '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>ssssssssssssss</p>\r\n</body>\r\n</html>\r\n', '2018-03-29 22:51:05'),
(7, '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>ACABOU COM TUDO</p>\r\n</body>\r\n</html>\r\n', '2018-03-29 23:05:21');

-- --------------------------------------------------------

--
-- Estrutura da tabela `criador`
--

CREATE TABLE `criador` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `mercadopagoID` varchar(255) NOT NULL,
  `mercadopagoSECRET` varchar(255) NOT NULL,
  `pagseguroEmail` varchar(255) NOT NULL,
  `pagseguroToken` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `criador`
--

INSERT INTO `criador` (`id`, `nome`, `mercadopagoID`, `mercadopagoSECRET`, `pagseguroEmail`, `pagseguroToken`) VALUES
(1, 'wiljam', '1751654759286751', 'foS7O2QBAycyCfWrHpQqJtp6jY1jjAzT', 'willian.programadorce@gmail.com', '46FA46AA1C9F4471AAF2FD0DDB682DB4'),
(2, 'eduard', 'lul', 'lul', 'lul', 'lul');

-- --------------------------------------------------------

--
-- Estrutura da tabela `plugins`
--

CREATE TABLE `plugins` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `preco` int(255) NOT NULL,
  `tipo` tinyint(1) NOT NULL,
  `base64` varchar(255) NOT NULL,
  `criador_id` int(11) NOT NULL,
  `pago` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `plugins`
--

INSERT INTO `plugins` (`id`, `nome`, `descricao`, `preco`, `tipo`, `base64`, `criador_id`, `pago`) VALUES
(18, 'Teste', '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>sdsdsd</p>\r\n</body>\r\n</html>\r\n', 1, 0, '', 1, 0),
(19, 'Testsdsdsd', '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>ssssssssss</p>\r\n</body>\r\n</html>\r\n', 80, 0, '', 1, 0),
(20, 'edu', '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>sadadadsada</p>\r\n</body>\r\n</html>\r\n', 90, 0, '', 1, 0),
(21, 'TintaLouko', '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>bvb</p>\r\n</body>\r\n</html>\r\n', 60, 0, '', 1, 0),
(22, 'Gra', '<html>\r\n<head>\r\n	<title></title>\r\n</head>\r\n<body>\r\n<p>um plugin free</p>\r\n</body>\r\n</html>\r\n', 0, 0, '', 1, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `plugins_comprado`
--

CREATE TABLE `plugins_comprado` (
  `id` int(11) NOT NULL,
  `id_plugin` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '0',
  `ip` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `plugins_comprado`
--

INSERT INTO `plugins_comprado` (`id`, `id_plugin`, `id_user`, `ativo`, `ip`) VALUES
(3, 18, 1, 1, '127.0.0.1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `session`
--

CREATE TABLE `session` (
  `id` int(11) NOT NULL,
  `ip` varchar(255) NOT NULL,
  `infopc` varchar(255) NOT NULL,
  `createdat` varchar(255) NOT NULL,
  `userid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `session`
--

INSERT INTO `session` (`id`, `ip`, `infopc`, `createdat`, `userid`) VALUES
(1, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 05:03:33', 1),
(2, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 05:05:08', 1),
(3, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 05:46:24', 1),
(4, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299', '2018-03-28 06:09:58', 1),
(5, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 06:12:34', 1),
(6, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 06:33:06', 9),
(7, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 22:29:50', 1),
(8, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 22:38:53', 1),
(9, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299', '2018-03-28 22:40:44', 1),
(10, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 23:05:30', 1),
(11, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-28 23:09:06', 1),
(12, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 01:34:53', 1),
(13, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 01:58:25', 1),
(14, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 02:36:27', 1),
(15, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 02:45:54', 1),
(16, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 02:46:17', 1),
(17, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 03:31:03', 1),
(18, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 05:15:28', 1),
(19, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 05:54:07', 1),
(20, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:16:02', 1),
(21, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:16:10', 1),
(22, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:17:07', 1),
(23, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:18:37', 1),
(24, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:19:30', 1),
(25, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:20:55', 1),
(26, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:21:02', 1),
(27, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 06:44:37', 1),
(28, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 17:52:47', 1),
(29, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 18:01:16', 1),
(30, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 18:01:48', 1),
(31, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 18:08:59', 1),
(32, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 18:16:05', 1),
(33, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 19:45:59', 1),
(34, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 19:46:03', 1),
(35, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 19:46:08', 1),
(36, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 19:46:19', 1),
(37, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 19:54:52', 1),
(38, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 20:07:22', 1),
(39, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 20:08:56', 1),
(40, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 20:24:32', 1),
(41, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 22:19:54', 1),
(42, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 22:32:22', 1),
(43, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 22:33:36', 1),
(44, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 22:36:33', 1),
(45, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 22:37:39', 1),
(46, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 22:40:23', 1),
(47, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-29 23:15:58', 1),
(48, '186.221.189.122', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 01:28:26', 2),
(49, '138.97.35.31', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0', '2018-03-30 01:28:26', 3),
(50, '177.198.115.225', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 01:30:36', 4),
(51, '177.198.115.225', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 01:32:02', 4),
(52, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 02:02:08', 1),
(53, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 02:19:55', 1),
(54, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 02:21:10', 1),
(55, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 02:29:21', 1),
(56, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 02:48:06', 1),
(57, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 03:03:58', 1),
(58, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 03:10:48', 1),
(59, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 03:11:27', 1),
(60, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 03:32:47', 1),
(61, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 03:41:06', 1),
(62, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 03:55:39', 1),
(63, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 05:49:42', 1),
(64, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 05:53:47', 1),
(65, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 05:59:39', 1),
(66, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 06:00:05', 1),
(67, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 06:08:07', 1),
(68, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 06:09:56', 1),
(69, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 06:11:33', 1),
(70, '::1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36', '2018-03-30 06:20:35', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `name`) VALUES
(1, 'teste', 'e10adc3949ba59abbe56e057f20f883e', 'Wiljam'),
(2, 'yAllanDev', 'e2f7416df3a35bdd9086a6e97ea40626', 'yAllanDev_'),
(3, 'tapassandofome', 'aa1bf4646de67fd9086cf6c79007026c', 'aprendercontassinaispocotom'),
(4, 'ExterSexotype', 'c9062611600f528e61107ed3719ca2ec', 'ExterSexotype');

-- --------------------------------------------------------

--
-- Estrutura da tabela `user_log`
--

CREATE TABLE `user_log` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `texto` varchar(255) NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `data` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user_log`
--

INSERT INTO `user_log` (`id`, `id_user`, `texto`, `tipo`, `data`) VALUES
(5, 1, 'Mudou ip do plugin.', 'Plugin', '2018-03-29'),
(6, 1, 'Mudou a senha.', 'Conta', '2018-03-29'),
(7, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(8, 1, 'Gerou a key.', 'Plugin', '2018-03-29'),
(9, 1, 'Mudou ip do plugin.', 'Plugin', '2018-03-29'),
(10, 1, 'Gerou a key.', 'Plugin', '2018-03-29'),
(11, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(12, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(13, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(14, 1, 'Gerou a key.', 'Plugin', '2018-03-29'),
(15, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(16, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(17, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(18, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(19, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(20, 1, 'Gerou a key.', 'Plugin', '2018-03-29'),
(21, 1, ' Logou na conta.', 'Conta', '2018-03-29'),
(22, 4, ' Logou na conta.', 'Conta', '2018-03-30'),
(23, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(24, 1, ' Logou na conta.', 'Conta', '2018-03-30'),
(25, 1, ' Logou na conta.', 'Conta', '2018-03-30'),
(26, 1, ' Logou na conta.', 'Conta', '2018-03-30'),
(27, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(28, 1, ' Logou na conta.', 'Conta', '2018-03-30'),
(29, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(30, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(31, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(32, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(33, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(34, 1, 'Mudou ip do plugin.', 'Plugin', '2018-03-30'),
(35, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(36, 1, 'Mudou ip do plugin.', 'Plugin', '2018-03-30'),
(37, 1, 'Gerou a key.', 'Plugin', '2018-03-30'),
(38, 1, 'Ligou um Plugin.', 'licen?a', '2018-03-30'),
(39, 1, 'Ligou um Plugin.', 'licen?a', '2018-03-30'),
(40, 1, 'Ligou um Plugin.', 'licenca', '2018-03-30'),
(41, 1, 'Ligou um Plugin.', 'licenca', '2018-03-30'),
(42, 1, ' Logou na conta.', 'Conta', '2018-03-30'),
(43, 1, ' Logou na conta.', 'Conta', '2018-03-30'),
(44, 1, ' Logou na conta.', 'Conta', '2018-03-30'),
(45, 1, ' Logou na conta.', 'Conta', '2018-03-30');

-- --------------------------------------------------------

--
-- Estrutura da tabela `user_transacoes`
--

CREATE TABLE `user_transacoes` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `plugin_id` int(11) NOT NULL,
  `data` varchar(255) NOT NULL,
  `gateway` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `preco` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user_transacoes`
--

INSERT INTO `user_transacoes` (`id`, `user_id`, `plugin_id`, `data`, `gateway`, `status`, `codigo`, `preco`) VALUES
(2, 1, 21, '2018-03-30', 'MercadoPago', 'Reprovado', '1', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `changelog`
--
ALTER TABLE `changelog`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `criador`
--
ALTER TABLE `criador`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `plugins`
--
ALTER TABLE `plugins`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_plugins_criadorid` (`criador_id`);

--
-- Indexes for table `plugins_comprado`
--
ALTER TABLE `plugins_comprado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_comprado_id_user` (`id_user`),
  ADD KEY `fk_comprado_id_plugin` (`id_plugin`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_session_user_id` (`userid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_log`
--
ALTER TABLE `user_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_log` (`id_user`);

--
-- Indexes for table `user_transacoes`
--
ALTER TABLE `user_transacoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_id` (`user_id`),
  ADD KEY `fk_plugin_id` (`plugin_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `changelog`
--
ALTER TABLE `changelog`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `criador`
--
ALTER TABLE `criador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `plugins`
--
ALTER TABLE `plugins`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `plugins_comprado`
--
ALTER TABLE `plugins_comprado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_log`
--
ALTER TABLE `user_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `user_transacoes`
--
ALTER TABLE `user_transacoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `plugins`
--
ALTER TABLE `plugins`
  ADD CONSTRAINT `fk_plugins_criadorid` FOREIGN KEY (`criador_id`) REFERENCES `criador` (`id`);

--
-- Limitadores para a tabela `plugins_comprado`
--
ALTER TABLE `plugins_comprado`
  ADD CONSTRAINT `fk_comprado_id_plugin` FOREIGN KEY (`id_plugin`) REFERENCES `plugins` (`id`),
  ADD CONSTRAINT `fk_comprado_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Limitadores para a tabela `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `fk_session_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`);

--
-- Limitadores para a tabela `user_log`
--
ALTER TABLE `user_log`
  ADD CONSTRAINT `fk_user_log` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Limitadores para a tabela `user_transacoes`
--
ALTER TABLE `user_transacoes`
  ADD CONSTRAINT `fk_plugin_id` FOREIGN KEY (`plugin_id`) REFERENCES `plugins` (`id`),
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

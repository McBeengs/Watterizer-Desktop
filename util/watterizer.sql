-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 27-Jul-2016 às 13:00
-- Versão do servidor: 10.1.13-MariaDB
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `watterizer`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `contribuicaometa`
--

CREATE TABLE `contribuicaometa` (
  `id_meta` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `contribuicao` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `gasto`
--

CREATE TABLE `gasto` (
  `id_usuario` int(11) NOT NULL,
  `data` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `consumo_array` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `gasto`
--

INSERT INTO `gasto` (`id_usuario`, `data`, `hora_inicio`, `consumo_array`) VALUES
(6, '2016-06-08', '12:00:00', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000007f77040000007f737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fe02e6bdc8057627371007e00023fde098ead65b7a37371007e00023fdefc962fc962fc7371007e00023fdc57619f0fb3897371007e00023fdf1a9fbe76c8b47371007e00023fdfb900aec33e1c7371007e00023fdf3b645a1cac067371007e00023fdf8a94d242e6c07371007e00023fe0ac083126e97a7371007e00023fdfc6a7ef9db22c7371007e00023fe04c756b2dbd1c7371007e00023fe104c756b2dbd37371007e00023fe00f04c756b2dc7371007e00023fddb7a32846ff547371007e00023fe011bfd44f30797371007e00023fdfbbbbbbbbbbbb7371007e00023fe0e978d4fdf3b67371007e00023fdf8a94d242e6c17371007e00023fdd810624dd2f1a7371007e00023fe153f7ced916867371007e00023fdf7777777777777371007e00023fdf7a32846ff5167371007e00023fdf1d5acb6f46537371007e00023fe03ab596de8ca07371007e00023fdf9db22d0e56047371007e00023fe194237fa89e627371007e00023fdf04c756b2dbd17371007e00023fe11a9fbe76c8b57371007e00023fdfd44f3078263a7371007e00023fdac8b4395810637371007e00023fde53490b9af7217371007e00023fde63ab596de8cb7371007e00023fda978d4fdf3b667371007e00023fe0ed916872b0217371007e00023fdfe76c8b43957f7371007e00023fdee402bb0cf8807371007e00023fe08057619f0fb37371007e00023fdf645a1cac08337371007e00023fdd9c54a69217347371007e00023fe049ba5e353f7e7371007e00023fdb2b020c49ba5e7371007e00023fe05604189374be7371007e00023fdcb17e4b17e4b37371007e00023fdd867c3ece2a527371007e00023fdf53f7ced916847371007e00023fe06666666666667371007e00023fe0131d5acb6f477371007e00023fe13e1f671529a57371007e00023fe09374bc6a7efa7371007e00023fe0b4395810624d7371007e00023fddbfd44f3078277371007e00023fe0978d4fdf3b647371007e00023fe11a9fbe76c8b27371007e00023fe08f5c28f5c28e7371007e00023fe0872b020c49bb7371007e00023fe0d4fdf3b645a27371007e00023fe1d867c3ece2a47371007e00023fe14fdf3b645a1d7371007e00023fe0f5c28f5c28f67371007e00023fdd70a3d70a3d6f7371007e00023fe076c8b43958117371007e00023fdea53490b9af757371007e00023fe0c8b4395810627371007e00023fe1596de8ca11c07371007e00023fde1f671529a4847371007e00023fe05b7a32846ff57371007e00023fdd5d867c3ece2d7371007e00023fde5e353f7ced917371007e00023fdf1529a485cd7a7371007e00023fdeaaaaaaaaaaab7371007e00023fdd604189374bc77371007e00023fdc2bb0cf87d9c67371007e00023fdc20c49ba5e3537371007e00023fdfe1f671529a497371007e00023fdf9af72015d8647371007e00023fdfc3ece2a5348f7371007e00023fdd99999999999b7371007e00023fdfdc8057619f0f7371007e00023fe1b38a94d242e67371007e00023fe15acb6f46508d7371007e00023fe2147ae147ae137371007e00023fe0263ab596de8e7371007e00023fe083126e978d507371007e00023fe1020c49ba5e367371007e00023fe0d3a06d3a06d27371007e00023fe0b9af72015d897371007e00023fdd604189374bc97371007e00023fdce5604189374b7371007e00023fde872b020c49b97371007e00023fdec083126e978d7371007e00023fdf7777777777777371007e00023fe195810624dd2f7371007e00023fe0b2dbd194237f7371007e00023fdc0aec33e1f6727371007e00023fdc369d0369d0367371007e00023fe060f04c756b2e7371007e00023fe1645a1cac08327371007e00023fdeb851eb851ebc7371007e00023fe11eb851eb851e7371007e00023fe121735ee402bb7371007e00023fdee6bdc805761a7371007e00023fe1e60f04c756b37371007e00023fe1ee402bb0cf8a7371007e00023fdecb6f46508dfc7371007e00023fe0f46508dfea267371007e00023fe178d4fdf3b6467371007e00023fe294d242e6bdca7371007e00023fdf5c28f5c28f5e7371007e00023fdd70a3d70a3d727371007e00023fde508dfea279847371007e00023fde1f671529a4857371007e00023fe0ff513cc1e0977371007e00023fdf3333333333347371007e00023fdbe4b17e4b17e47371007e00023fdd62fc962fc9627371007e00023fe16de8ca11bfd57371007e00023fe12dbd194237fb7371007e00023fe0d3a06d3a06d37371007e00023fdddb22d0e560437371007e00023fdf69d0369d036b7371007e00023fe0dfea27983c147371007e00023fe0c083126e978d7371007e00023fe032846ff513cc7371007e00023fe0d0e5604189377371007e00023fe067c3ece2a5347371007e00023fdd0369d0369d057371007e00023fe0c33e1f67152a78),
(6, '2016-06-18', '20:22:43', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000006770400000006737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fdead65b7a328477371007e00023fe0dbd194237fa97371007e00023fe03ab596de8ca17371007e00023fe15c28f5c28f5d7371007e00023fdcf04c756b2dbe7371007e00023fdddb22d0e5603e78),
(6, '2016-06-11', '20:00:00', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000007f77040000007f737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fe02e6bdc8057627371007e00023fde098ead65b7a37371007e00023fdefc962fc962fc7371007e00023fdc57619f0fb3897371007e00023fdf1a9fbe76c8b47371007e00023fdfb900aec33e1c7371007e00023fdf3b645a1cac067371007e00023fdf8a94d242e6c07371007e00023fe0ac083126e97a7371007e00023fdfc6a7ef9db22c7371007e00023fe04c756b2dbd1c7371007e00023fe104c756b2dbd37371007e00023fe00f04c756b2dc7371007e00023fddb7a32846ff547371007e00023fe011bfd44f30797371007e00023fdfbbbbbbbbbbbb7371007e00023fe0e978d4fdf3b67371007e00023fdf8a94d242e6c17371007e00023fdd810624dd2f1a7371007e00023fe153f7ced916867371007e00023fdf7777777777777371007e00023fdf7a32846ff5167371007e00023fdf1d5acb6f46537371007e00023fe03ab596de8ca07371007e00023fdf9db22d0e56047371007e00023fe194237fa89e627371007e00023fdf04c756b2dbd17371007e00023fe11a9fbe76c8b57371007e00023fdfd44f3078263a7371007e00023fdac8b4395810637371007e00023fde53490b9af7217371007e00023fde63ab596de8cb7371007e00023fda978d4fdf3b667371007e00023fe0ed916872b0217371007e00023fdfe76c8b43957f7371007e00023fdee402bb0cf8807371007e00023fe08057619f0fb37371007e00023fdf645a1cac08337371007e00023fdd9c54a69217347371007e00023fe049ba5e353f7e7371007e00023fdb2b020c49ba5e7371007e00023fe05604189374be7371007e00023fdcb17e4b17e4b37371007e00023fdd867c3ece2a527371007e00023fdf53f7ced916847371007e00023fe06666666666667371007e00023fe0131d5acb6f477371007e00023fe13e1f671529a57371007e00023fe09374bc6a7efa7371007e00023fe0b4395810624d7371007e00023fddbfd44f3078277371007e00023fe0978d4fdf3b647371007e00023fe11a9fbe76c8b27371007e00023fe08f5c28f5c28e7371007e00023fe0872b020c49bb7371007e00023fe0d4fdf3b645a27371007e00023fe1d867c3ece2a47371007e00023fe14fdf3b645a1d7371007e00023fe0f5c28f5c28f67371007e00023fdd70a3d70a3d6f7371007e00023fe076c8b43958117371007e00023fdea53490b9af757371007e00023fe0c8b4395810627371007e00023fe1596de8ca11c07371007e00023fde1f671529a4847371007e00023fe05b7a32846ff57371007e00023fdd5d867c3ece2d7371007e00023fde5e353f7ced917371007e00023fdf1529a485cd7a7371007e00023fdeaaaaaaaaaaab7371007e00023fdd604189374bc77371007e00023fdc2bb0cf87d9c67371007e00023fdc20c49ba5e3537371007e00023fdfe1f671529a497371007e00023fdf9af72015d8647371007e00023fdfc3ece2a5348f7371007e00023fdd99999999999b7371007e00023fdfdc8057619f0f7371007e00023fe1b38a94d242e67371007e00023fe15acb6f46508d7371007e00023fe2147ae147ae137371007e00023fe0263ab596de8e7371007e00023fe083126e978d507371007e00023fe1020c49ba5e367371007e00023fe0d3a06d3a06d27371007e00023fe0b9af72015d897371007e00023fdd604189374bc97371007e00023fdce5604189374b7371007e00023fde872b020c49b97371007e00023fdec083126e978d7371007e00023fdf7777777777777371007e00023fe195810624dd2f7371007e00023fe0b2dbd194237f7371007e00023fdc0aec33e1f6727371007e00023fdc369d0369d0367371007e00023fe060f04c756b2e7371007e00023fe1645a1cac08327371007e00023fdeb851eb851ebc7371007e00023fe11eb851eb851e7371007e00023fe121735ee402bb7371007e00023fdee6bdc805761a7371007e00023fe1e60f04c756b37371007e00023fe1ee402bb0cf8a7371007e00023fdecb6f46508dfc7371007e00023fe0f46508dfea267371007e00023fe178d4fdf3b6467371007e00023fe294d242e6bdca7371007e00023fdf5c28f5c28f5e7371007e00023fdd70a3d70a3d727371007e00023fde508dfea279847371007e00023fde1f671529a4857371007e00023fe0ff513cc1e0977371007e00023fdf3333333333347371007e00023fdbe4b17e4b17e47371007e00023fdd62fc962fc9627371007e00023fe16de8ca11bfd57371007e00023fe12dbd194237fb7371007e00023fe0d3a06d3a06d37371007e00023fdddb22d0e560437371007e00023fdf69d0369d036b7371007e00023fe0dfea27983c147371007e00023fe0c083126e978d7371007e00023fe032846ff513cc7371007e00023fe0d0e5604189377371007e00023fe067c3ece2a5347371007e00023fdd0369d0369d057371007e00023fe0c33e1f67152a78),
(6, '2016-07-11', '16:29:54', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000001770400000001737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fe2015d867c3ece78),
(6, '2016-07-12', '18:18:45', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000f77040000000f737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fde58bf258bf2567371007e00023fe28ca11bfd44f27371007e00023fdbc131d5acb6f27371007e00023fe00c49ba5e353e7371007e00023fdd4fdf3b645a1d7371007e00023fe0402bb0cf87db7371007e00023fdf53f7ced9168a7371007e00023fde76c8b439580e7371007e00023fe1a1cac083126f7371007e00023fdef46508dfea267371007e00023fdffd44f30782657371007e00023fd7ab596de8ca107371007e00023fe0aaaaaaaaaaac7371007e00023fdf46508dfea27b7371007e00023fe0d0e56041893778),
(7, '2016-07-13', '21:37:14', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000003770400000003737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fe15acb6f46508f7371007e00023fdf8a94d242e6c17371007e00023fdf3b645a1cac0978),
(7, '2016-07-15', '18:26:44', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a65787000000016770400000016737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fdf8a94d242e6be7371007e00023fde81b4e81b4e847371007e00023fdcf04c756b2dbf7371007e00023fdfe76c8b4395827371007e00023fde66666666666a7371007e00023fe2ead65b7a32837371007e00023fdd9f0fb38a94ce7371007e00023fdd4d242e6bdc837371007e00023fde3d70a3d70a3d7371007e00023fe015d867c3ece37371007e00023fe0b596de8ca11c7371007e00023fe189374bc6a7f07371007e00023fdf04c756b2dbd17371007e00023fdfbbbbbbbbbbbd7371007e00023fe02fc962fc96317371007e00023fe05b7a32846ff57371007e00023fe1513cc1e098ea7371007e00023fdf9af72015d8677371007e00023fe1b4e81b4e81b57371007e00023fdd7619f0fb38ab7371007e00023fdf7a32846ff5137371007e00023fe13490b9af720178),
(6, '2016-07-19', '11:00:11', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000f77040000000f737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fe1b0cf87d9c54a7371007e00023fdede8ca11bfd467371007e00023fdc28f5c28f5c277371007e00023fdf53f7ced9168d7371007e00023fdd867c3ece2a537371007e00023fdec8b4395810657371007e00023fddbfd44f3078237371007e00023fe04dd2f1a9fbe67371007e00023fde740da740da727371007e00023fdc1e098ead65b97371007e00023fdcb17e4b17e4b37371007e00023fdfb900aec33e217371007e00023fe0c8b4395810627371007e00023fe1a485cd7b900a7371007e00023fdeeeeeeeeeeeed78),
(6, '2016-07-19', '11:00:11', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000f77040000000f737200106a6176612e6c616e672e446f75626c6580b3c24a296bfb0402000144000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078703fe1b0cf87d9c54a7371007e00023fdede8ca11bfd467371007e00023fdc28f5c28f5c277371007e00023fdf53f7ced9168d7371007e00023fdd867c3ece2a537371007e00023fdec8b4395810657371007e00023fddbfd44f3078237371007e00023fe04dd2f1a9fbe67371007e00023fde740da740da727371007e00023fdc1e098ead65b97371007e00023fdcb17e4b17e4b37371007e00023fdfb900aec33e217371007e00023fe0c8b4395810627371007e00023fe1a485cd7b900a7371007e00023fdeeeeeeeeeeeed78);

-- --------------------------------------------------------

--
-- Estrutura da tabela `meta`
--

CREATE TABLE `meta` (
  `id` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `detalhes` text,
  `data_inicio` date NOT NULL,
  `data_prazo` date NOT NULL,
  `valor_inicial` double NOT NULL,
  `valor_final` double NOT NULL,
  `valor_atual` double NOT NULL,
  `terminado` tinyint(1) NOT NULL,
  `atingido` tinyint(1) NOT NULL,
  `exp_recompensa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `meta`
--

INSERT INTO `meta` (`id`, `titulo`, `detalhes`, `data_inicio`, `data_prazo`, `valor_inicial`, `valor_final`, `valor_atual`, `terminado`, `atingido`, `exp_recompensa`) VALUES
(1, 'xcv', 'xcvxcv', '2016-06-08', '2016-06-15', 123, 123, 123, 1, 1, 123),
(2, 'asd', 'wer', '2016-06-14', '2016-06-22', 127, 127, 127, 1, 1, 123);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ocorrencia`
--

CREATE TABLE `ocorrencia` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `id_setor` int(11) NOT NULL,
  `mensagem` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `ocorrencia`
--

INSERT INTO `ocorrencia` (`id`, `id_usuario`, `titulo`, `id_setor`, `mensagem`) VALUES
(1, 6, 'Mensagem do sistema - ', 1, 'Esta é uma mensagem automática do sistema, sendo altamente recomendável que a situação relatada aqui seja conferida para que os fatos relatados aqui tenham total veemência. \r\n\r\nHoje (dia &date), as &date, houve um incidente com a unidade Arduino relacionada ao usuário &string. Essa mensagem diz respeito a um aviso enviado a tal usuário, e que o mesmo o ignorou, não relatou a nenhum administrador do sistema a já mencionada falha de hardware.\r\n\r\nÉ necessário que a unidade seja reparada o mais rápido possível, e que as medidas necessárias sejam tomadas para que a omissão não venha a acontecer de novo.'),
(2, 6, 'Mensagem do sistema - Falha com um dos Arduinos', 1, 'Esta é uma mensagem automática do sistema, sendo altamente recomendável que a situação relatada aqui seja conferida para que os fatos relatados aqui tenham total veemência. \n\nHoje (dia &date), as &date, houve um incidente com a unidade Arduino relacionada ao usuário &string. Essa mensagem diz respeito a um aviso enviado a tal usuário, e que o mesmo o ignorou, não relatou a nenhum administrador do sistema a já mencionada falha de hardware.\n\nÉ necessário que a unidade seja reparada o mais rápido possível, e que as medidas necessárias sejam tomadas para que a omissão não venha a acontecer de novo.'),
(3, 6, 'Mensagem do sistema - Falha com um dos Arduinos', 1, 'Esta é uma mensagem automática do sistema, sendo altamente recomendável que a situação relatada aqui seja conferida para que os fatos relatados aqui tenham total veemência. \n\nHoje (dia &date), as &date, houve um incidente com a unidade Arduino relacionada ao usuário &string. Essa mensagem diz respeito a um aviso enviado a tal usuário, e que o mesmo o ignorou, não relatou a nenhum administrador do sistema a já mencionada falha de hardware.\n\nÉ necessário que a unidade seja reparada o mais rápido possível, e que as medidas necessárias sejam tomadas para que a omissão não venha a acontecer de novo.'),
(4, 6, 'Mensagem do sistema - Falha com um dos Arduinos', 1, 'Esta é uma mensagem automática do sistema, sendo altamente recomendável que a situação relatada aqui seja conferida para que os fatos relatados aqui tenham total veemência. \n\nHoje (dia &date), as &date, houve um incidente com a unidade Arduino relacionada ao usuário &string. Essa mensagem diz respeito a um aviso enviado a tal usuário, e que o mesmo o ignorou, não relatou a nenhum administrador do sistema a já mencionada falha de hardware.\n\nÉ necessário que a unidade seja reparada o mais rápido possível, e que as medidas necessárias sejam tomadas para que a omissão não venha a acontecer de novo.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil`
--

CREATE TABLE `perfil` (
  `id` int(11) NOT NULL,
  `perfil` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `perfil`
--

INSERT INTO `perfil` (`id`, `perfil`) VALUES
(1, 'Administrador'),
(2, 'Funcionario');

-- --------------------------------------------------------

--
-- Estrutura da tabela `perguntasecreta`
--

CREATE TABLE `perguntasecreta` (
  `id` int(11) NOT NULL,
  `pergunta` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `perguntasecreta`
--

INSERT INTO `perguntasecreta` (`id`, `pergunta`) VALUES
(1, 'asdasd'),
(2, 'Qual o nome do seu pai?');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ranking`
--

CREATE TABLE `ranking` (
  `id_usuario` int(11) NOT NULL,
  `posicao` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `metas_cumpridas` int(11) NOT NULL,
  `contribuicao_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `setor`
--

CREATE TABLE `setor` (
  `id` int(11) NOT NULL,
  `setor` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `setor`
--

INSERT INTO `setor` (`id`, `setor`) VALUES
(1, 'sdf');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `telefone` varchar(14) NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `hora_inicio_expediente` time NOT NULL,
  `hora_fim_expediente` time NOT NULL,
  `hora_almoco` time NOT NULL,
  `id_pergunta` int(11) NOT NULL,
  `resposta_pergunta` varchar(100) NOT NULL,
  `id_setor` int(11) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `telefone`, `data_cadastro`, `hora_inicio_expediente`, `hora_fim_expediente`, `hora_almoco`, `id_pergunta`, `resposta_pergunta`, `id_setor`, `id_perfil`, `username`) VALUES
(6, 'Administrador', 'governo@lheira.com', 'WCwLwn04k8xqKrTWuBCx8Q==', '(11) 1234-5678', '2016-06-10', '07:30:00', '18:00:00', '12:00:00', 2, 'fábio', 1, 1, 'admin'),
(7, '123', 'governo@lheira.com', 'HrqORkwYOHJKpet4MT5lPQ==', '1111', '2016-07-07', '04:00:00', '13:00:00', '12:00:00', 2, 'seila', 1, 2, '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contribuicaometa`
--
ALTER TABLE `contribuicaometa`
  ADD PRIMARY KEY (`id_meta`,`id_usuario`),
  ADD KEY `fk_meta_has_usuario_usuario1_idx` (`id_usuario`),
  ADD KEY `fk_meta_has_usuario_meta1_idx` (`id_meta`);

--
-- Indexes for table `gasto`
--
ALTER TABLE `gasto`
  ADD KEY `fk_gastos_usuario1_idx` (`id_usuario`);

--
-- Indexes for table `meta`
--
ALTER TABLE `meta`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ocorrencia`
--
ALTER TABLE `ocorrencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ocorrencia_usuario1_idx` (`id_usuario`);

--
-- Indexes for table `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perguntasecreta`
--
ALTER TABLE `perguntasecreta`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ranking`
--
ALTER TABLE `ranking`
  ADD KEY `fk_ranking_usuario1_idx` (`id_usuario`);

--
-- Indexes for table `setor`
--
ALTER TABLE `setor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_usuario_perguntaSecreta_idx` (`id_pergunta`),
  ADD KEY `fk_usuario_setor1_idx` (`id_setor`),
  ADD KEY `fk_usuario_perfil1_idx` (`id_perfil`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `meta`
--
ALTER TABLE `meta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `ocorrencia`
--
ALTER TABLE `ocorrencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `perfil`
--
ALTER TABLE `perfil`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `perguntasecreta`
--
ALTER TABLE `perguntasecreta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `setor`
--
ALTER TABLE `setor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `contribuicaometa`
--
ALTER TABLE `contribuicaometa`
  ADD CONSTRAINT `fk_meta_has_usuario_meta1` FOREIGN KEY (`id_meta`) REFERENCES `meta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_meta_has_usuario_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `gasto`
--
ALTER TABLE `gasto`
  ADD CONSTRAINT `fk_gastos_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `ocorrencia`
--
ALTER TABLE `ocorrencia`
  ADD CONSTRAINT `fk_ocorrencia_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `ranking`
--
ALTER TABLE `ranking`
  ADD CONSTRAINT `fk_ranking_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_perfil1` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_perguntaSecreta` FOREIGN KEY (`id_pergunta`) REFERENCES `perguntasecreta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_setor1` FOREIGN KEY (`id_setor`) REFERENCES `setor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

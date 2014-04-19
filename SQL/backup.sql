-- phpMyAdmin SQL Dump
-- version 4.1.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 19, 2014 at 03:55 PM
-- Server version: 5.5.36-cll
-- PHP Version: 5.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `adicon_siscob`
--

-- --------------------------------------------------------

--
-- Table structure for table `boleto`
--

CREATE TABLE IF NOT EXISTS `boleto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero_documento` varchar(200) NOT NULL,
  `nosso_numero` varchar(100) NOT NULL,
  `digito_nosso_numero` varchar(10) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `data_documento` datetime NOT NULL,
  `data_vencimento` datetime NOT NULL,
  `tipo_titulo` int(11) NOT NULL,
  `aceite` int(11) NOT NULL,
  `desconto` decimal(19,2) NOT NULL DEFAULT '0.00',
  `deducao` decimal(19,2) NOT NULL DEFAULT '0.00',
  `mora` decimal(19,2) NOT NULL DEFAULT '0.00',
  `acrescimo` decimal(19,2) NOT NULL DEFAULT '0.00',
  `valor_cobrado` decimal(19,2) NOT NULL DEFAULT '0.00',
  `local_pagamento` varchar(250) DEFAULT NULL,
  `instrucao_pagamento` varchar(250) DEFAULT NULL,
  `conta_id` int(11) NOT NULL,
  `condominio_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_titulo_conta1` (`conta_id`),
  KEY `fk_boleto_condominio1` (`condominio_id`),
  KEY `fk_boleto_usuario1` (`usuario_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `boleto`
--

INSERT INTO `boleto` (`id`, `numero_documento`, `nosso_numero`, `digito_nosso_numero`, `valor`, `data_documento`, `data_vencimento`, `tipo_titulo`, `aceite`, `desconto`, `deducao`, `mora`, `acrescimo`, `valor_cobrado`, `local_pagamento`, `instrucao_pagamento`, `conta_id`, `condominio_id`, `usuario_id`, `status`) VALUES
(1, '', '01041409040000003', '', '550.00', '2014-04-01 00:00:00', '2014-04-10 00:00:00', 0, 1, '0.00', '0.00', '0.00', '0.00', '550.00', 'RUA DA CONCÃ“RDIA, 153 - SALA 504', 'NÃ£o receber apos 30 dias de atraso', 1, 6, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `condominio`
--

CREATE TABLE IF NOT EXISTS `condominio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(250) DEFAULT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  `endereco_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_condominio_endereco1` (`endereco_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `condominio`
--

INSERT INTO `condominio` (`id`, `nome`, `cnpj`, `endereco_id`) VALUES
(6, 'COSTA DOURADA', '05.792.151/0001-43', 7);

-- --------------------------------------------------------

--
-- Table structure for table `conta`
--

CREATE TABLE IF NOT EXISTS `conta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banco` int(11) NOT NULL,
  `conta` int(11) NOT NULL,
  `digito_cc` varchar(2) NOT NULL,
  `agencia` int(11) NOT NULL,
  `digito_ag` varchar(2) NOT NULL,
  `carteira` int(11) NOT NULL,
  `codigo_operacao` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `conta`
--

INSERT INTO `conta` (`id`, `banco`, `conta`, `digito_cc`, `agencia`, `digito_ag`, `carteira`, `codigo_operacao`) VALUES
(1, 6, 84, '6', 1294, '0', 0, '870');

-- --------------------------------------------------------

--
-- Table structure for table `endereco`
--

CREATE TABLE IF NOT EXISTS `endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cidade` varchar(250) NOT NULL,
  `uf` int(11) NOT NULL,
  `cep` varchar(10) NOT NULL,
  `bairro` varchar(250) NOT NULL,
  `logradouro` varchar(250) NOT NULL,
  `numero` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `endereco`
--

INSERT INTO `endereco` (`id`, `cidade`, `uf`, `cep`, `bairro`, `logradouro`, `numero`) VALUES
(7, 'RECIFE', 18, '50.000-000', 'TEJIPIÃ“', 'RUA S/N', '23');

-- --------------------------------------------------------

--
-- Table structure for table `grupo`
--

CREATE TABLE IF NOT EXISTS `grupo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `situacao` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) DEFAULT NULL,
  `cpf` varchar(50) DEFAULT NULL,
  `senha` varchar(40) DEFAULT NULL,
  `permissao` varchar(45) NOT NULL DEFAULT 'ROLE_USER',
  `condominio_id` int(11) DEFAULT NULL,
  `ultimo_acesso` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_condominio1` (`condominio_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `cpf`, `senha`, `permissao`, `condominio_id`, `ultimo_acesso`) VALUES
(1, 'ADICON', '250.203.784-02', 'e2c92a2335e4708b7392c253c19e446e', 'ROLE_ADM', NULL, NULL),
(2, 'DOUGLAS QUEIROZ', '084.244.024-05', 'f74109c1ff4c38017be984a479db9a12', 'ROLE_ADM', NULL, NULL),
(3, 'ALDO HENRIQUE CARVALHO', '293.554.354-87', '81dc9bdb52d04dc20036dbd8313ed055', 'ROLE_USER', 6, '2014-04-01 00:00:00');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `boleto`
--
ALTER TABLE `boleto`
  ADD CONSTRAINT `fk_boleto_condominio1` FOREIGN KEY (`condominio_id`) REFERENCES `condominio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_boleto_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_titulo_conta1` FOREIGN KEY (`conta_id`) REFERENCES `conta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `condominio`
--
ALTER TABLE `condominio`
  ADD CONSTRAINT `fk_condominio_endereco1` FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_condominio1` FOREIGN KEY (`condominio_id`) REFERENCES `condominio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

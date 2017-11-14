CREATE TABLE `conta` (
  `idConta` int(11) NOT NULL,
  `Nome` varchar(30) DEFAULT NULL,
  `DataCriacao` date DEFAULT NULL,
  `CNPJ` varchar(14) DEFAULT NULL,
  `CPF` varchar(11) DEFAULT NULL,
  `tipoConta` varchar(1) DEFAULT NULL,
  `atividade` tinyint(4) NOT NULL,
  PRIMARY KEY (`idConta`),
  KEY `CPF_idx` (`CNPJ`),
  CONSTRAINT `CNPJ` FOREIGN KEY (`CNPJ`) REFERENCES `pessoa_juridica` (`CNPJ`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CPF` FOREIGN KEY (`CNPJ`) REFERENCES `pessoa_fisica` (`CPF`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8
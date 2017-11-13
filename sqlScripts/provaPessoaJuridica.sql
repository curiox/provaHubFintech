CREATE TABLE `pessoa_juridica` (
  `CNPJ` varchar(14) NOT NULL,
  `RazaoSocial` varchar(45) DEFAULT NULL,
  `NomeFantasia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CNPJ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
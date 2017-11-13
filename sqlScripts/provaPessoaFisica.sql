CREATE TABLE `pessoa_fisica` (
  `CPF` varchar(11) NOT NULL,
  `nomeCompleto` varchar(45) DEFAULT NULL,
  `dataNasc` date DEFAULT NULL,
  PRIMARY KEY (`CPF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
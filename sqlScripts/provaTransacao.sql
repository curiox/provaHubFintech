CREATE TABLE `transferencia` (
  `idContaOrigem` int(11) NOT NULL,
  `idContaDestino` int(11) NOT NULL,
  `quantia` float NOT NULL,
  PRIMARY KEY (`idContaOrigem`,`idContaDestino`),
  KEY `contaDestino_idx` (`idContaDestino`),
  CONSTRAINT `contaDestino` FOREIGN KEY (`idContaDestino`) REFERENCES `conta` (`idConta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `contaOrigem` FOREIGN KEY (`idContaOrigem`) REFERENCES `conta` (`idConta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8
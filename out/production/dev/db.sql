SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Esquema EstacionamentoPrivado
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EstacionamentoPrivado` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `EstacionamentoPrivado` ;

-- -----------------------------------------------------
-- Tabela `EstacionamentoPrivado`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EstacionamentoPrivado`.`Usuario` (
  `idUser` INT NOT NULL,
  `userLevel` INT NULL DEFAULT NULL,
  `cpf` VARCHAR(20) NULL DEFAULT NULL,
  `nome` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `senha` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabela `EstacionamentoPrivado`.`Modelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EstacionamentoPrivado`.`Modelo` (
  `idMod` INT NOT NULL,
  `marca` VARCHAR(50) NULL DEFAULT NULL,
  `ano` INT NULL DEFAULT NULL,
  `modelo` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`idMod`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabela `EstacionamentoPrivado`.`Carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EstacionamentoPrivado`.`Carro` (
  `idCar` INT NOT NULL,
  `idUser` INT NOT NULL,
  `idMod` INT NOT NULL,
  `placa` VARCHAR(20) NULL DEFAULT NULL,
  `cor` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`idCar`, `idUser`, `idMod`),
  INDEX `fk_Carro_Usuario_idx` (`idUser` ASC) VISIBLE,
  INDEX `fk_Carro_Modelo_idx` (`idMod` ASC) VISIBLE,
  CONSTRAINT `fk_Carro_Usuario`
    FOREIGN KEY (`idUser`)
    REFERENCES `EstacionamentoPrivado`.`Usuario` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Carro_Modelo`
    FOREIGN KEY (`idMod`)
    REFERENCES `EstacionamentoPrivado`.`Modelo` (`idMod`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabela `EstacionamentoPrivado`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EstacionamentoPrivado`.`Admin` (
  `idAdm` INT NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idAdm`, `idUser`),
  INDEX `fk_Admin_Usuario_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Admin_Usuario`
    FOREIGN KEY (`idUser`)
    REFERENCES `EstacionamentoPrivado`.`Usuario` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabela `EstacionamentoPrivado`.`UsuarioPremium`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EstacionamentoPrivado`.`UsuarioPremium` (
  `idPre` INT NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idPre`, `idUser`),
  INDEX `fk_UsuarioPremium_Usuario_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_UsuarioPremium_Usuario`
    FOREIGN KEY (`idUser`)
    REFERENCES `EstacionamentoPrivado`.`Usuario` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabela `EstacionamentoPrivado`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EstacionamentoPrivado`.`Funcionario` (
  `idFunc` INT NOT NULL,
  `idUser` INT NOT NULL,
  PRIMARY KEY (`idFunc`, `idUser`),
  INDEX `fk_Funcionario_Usuario_idx` (`idUser` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Usuario`
    FOREIGN KEY (`idUser`)
    REFERENCES `EstacionamentoPrivado`.`Usuario` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Tabela `EstacionamentoPrivado`.`parkLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EstacionamentoPrivado`.`parkLog` (
  `idParkLog` INT NOT NULL,
  `idUser` INT NOT NULL,
  `idCar` INT NOT NULL,
  `data_emitida` DATE NULL,
  `data_entrada` DATE NULL,
  `data_saida` DATE NULL,
  `valor` DECIMAL(10, 2) NULL,
  PRIMARY KEY (`idParkLog`, `idUser`, `idCar`),
  INDEX `fk_parkLog_Usuario_idx` (`idUser` ASC) VISIBLE,
  INDEX `fk_parkLog_Carro_idx` (`idCar` ASC) VISIBLE,
  CONSTRAINT `fk_parkLog_Usuario`
    FOREIGN KEY (`idUser`)
    REFERENCES `EstacionamentoPrivado`.`Usuario` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkLog_Carro`
    FOREIGN KEY (`idCar`)
    REFERENCES `EstacionamentoPrivado`.`Carro` (`idCar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `gateway` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `gateway` ;

-- -----------------------------------------------------
-- Table `gateway`.`operators`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`operators` ;

CREATE TABLE IF NOT EXISTS `gateway`.`operators` (
  `operator_id` INT NOT NULL AUTO_INCREMENT,
  `operator_name` VARCHAR(128) NULL,
  `api_uri` VARCHAR(128) NULL,
  `connector_uri` VARCHAR(128) NULL,
  `is_enabled` TINYINT(1) NULL,
  `primary_contact_name` VARCHAR(128) NULL,
  `primary_contact_email` VARCHAR(128) NULL,
  `primary_contact_phone` VARCHAR(10) NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`operator_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`application_providers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`application_providers` ;

CREATE TABLE IF NOT EXISTS `gateway`.`application_providers` (
  `application_provider_id` INT NOT NULL AUTO_INCREMENT,
  `application_provider_name` VARCHAR(64) NULL,
  `organization_name` VARCHAR(64) NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`application_provider_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`applications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`applications` ;

CREATE TABLE IF NOT EXISTS `gateway`.`applications` (
  `application_id` INT NOT NULL AUTO_INCREMENT,
  `application_provider_id` INT NULL,
  `client_id` VARCHAR(64) NULL,
  `shared_secret` VARCHAR(64) NULL,
  `application_name` VARCHAR(128) NULL,
  `description` MEDIUMTEXT NULL,
  `app_uri` VARCHAR(128) NULL,
  `redirect_uri` VARCHAR(128) NULL,
  `admin_uri` VARCHAR(128) NULL,
  `image_uri` VARCHAR(128) NULL,
  `is_approved` TINYINT(1) NULL,
  `is_admin` TINYINT(1) NULL,
  `is_bulk_extract` TINYINT(1) NULL,
  `registered_on` DATE NULL,
  `approved_on` DATE NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`application_id`),
  INDEX `fk_application_application_provider1_idx` (`application_provider_id` ASC),
  CONSTRAINT `fk_application_application_provider1`
    FOREIGN KEY (`application_provider_id`)
    REFERENCES `gateway`.`application_providers` (`application_provider_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`application_enablements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`application_enablements` ;

CREATE TABLE IF NOT EXISTS `gateway`.`application_enablements` (
  `application_enablement_id` INT NOT NULL AUTO_INCREMENT,
  `application_id` INT NULL,
  `ed_org_id` VARCHAR(64) NULL,
  `is_enabled` TINYINT(1) NULL,
  `effective_on` DATE NULL,
  `expires_on` DATE NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`application_enablement_id`),
  INDEX `fk_application_enablement_application1_idx` (`application_id` ASC),
  CONSTRAINT `fk_application_enablement_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `gateway`.`applications` (`application_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`users` ;

CREATE TABLE IF NOT EXISTS `gateway`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `application_provider_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_users_application_providers1_idx` (`application_provider_id` ASC),
  CONSTRAINT `fk_users_application_providers1`
    FOREIGN KEY (`application_provider_id`)
    REFERENCES `gateway`.`application_providers` (`application_provider_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `gateway`.`tokens`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`tokens` ;

CREATE TABLE IF NOT EXISTS `gateway`.`tokens` (
  `token_id` INT NOT NULL,
  `token` VARCHAR(22) NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`token_id`));


-- -----------------------------------------------------
-- Table `gateway`.`verifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`verifications` ;

CREATE TABLE IF NOT EXISTS `gateway`.`verifications` (
  `verification_id` INT NOT NULL,
  `token_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `is_verified` TINYINT(1) NULL,
  `valid_from` DATETIME NULL,
  `valid_until` DATETIME NULL,
  `client_ip_address` VARCHAR(45) NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`verification_id`),
  INDEX `fk_verifications_tokens1_idx` (`token_id` ASC),
  INDEX `fk_verifications_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_verifications_tokens1`
    FOREIGN KEY (`token_id`)
    REFERENCES `gateway`.`tokens` (`token_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_verifications_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `gateway`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

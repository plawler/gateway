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
-- Table `gateway`.`contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`contacts` ;

CREATE TABLE IF NOT EXISTS `gateway`.`contacts` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `contact_name` VARCHAR(128) NULL,
  `contact_email` VARCHAR(128) NULL,
  `is_primary` TINYINT(1) NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`contact_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`addresses` ;

CREATE TABLE IF NOT EXISTS `gateway`.`addresses` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `contact_id` INT NULL,
  `county` VARCHAR(64) NULL,
  `state_abbreviation` CHAR(2) NULL,
  `street1` VARCHAR(64) NULL,
  `street2` VARCHAR(64) NULL,
  `city` VARCHAR(64) NULL,
  `zip` VARCHAR(10) NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`address_id`),
  INDEX `fk_address_contact1_idx` (`contact_id` ASC),
  CONSTRAINT `fk_address_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `gateway`.`contacts` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`phone_number_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`phone_number_types` ;

CREATE TABLE IF NOT EXISTS `gateway`.`phone_number_types` (
  `phone_number_type_id` INT NOT NULL AUTO_INCREMENT,
  `phone_number_type` VARCHAR(10) NULL,
  `create_dt` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `update_dt` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`phone_number_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`phone_numbers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`phone_numbers` ;

CREATE TABLE IF NOT EXISTS `gateway`.`phone_numbers` (
  `phone_number_id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(10) NULL,
  `phone_number_type_id` INT NULL,
  `contact_id` INT NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`phone_number_id`),
  INDEX `fk_telephone_contact1_idx` (`contact_id` ASC),
  INDEX `fk_phone_numbers_phone_number_types1_idx` (`phone_number_type_id` ASC),
  CONSTRAINT `fk_telephone_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `gateway`.`contacts` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_phone_numbers_phone_number_types1`
    FOREIGN KEY (`phone_number_type_id`)
    REFERENCES `gateway`.`phone_number_types` (`phone_number_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`application_providers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`application_providers` ;

CREATE TABLE IF NOT EXISTS `gateway`.`application_providers` (
  `application_provider_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `application_provider_name` VARCHAR(64) NULL,
  `organization_name` VARCHAR(64) NULL,
  `is_terms_accepted` TINYINT(1) NULL,
  `is_account_confirmed` TINYINT(1) NULL,
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
-- Table `gateway`.`operators_contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`operators_contacts` ;

CREATE TABLE IF NOT EXISTS `gateway`.`operators_contacts` (
  `operator_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`operator_id`, `contact_id`),
  INDEX `fk_operator_has_contact_contact1_idx` (`contact_id` ASC),
  INDEX `fk_operator_has_contact_operator1_idx` (`operator_id` ASC),
  CONSTRAINT `fk_operator_has_contact_operator1`
    FOREIGN KEY (`operator_id`)
    REFERENCES `gateway`.`operators` (`operator_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_operator_has_contact_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `gateway`.`contacts` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gateway`.`application_providers_contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gateway`.`application_providers_contacts` ;

CREATE TABLE IF NOT EXISTS `gateway`.`application_providers_contacts` (
  `application_provider_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  `created_by` VARCHAR(45) NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(45) NULL,
  PRIMARY KEY (`application_provider_id`, `contact_id`),
  INDEX `fk_contact_has_application_provider_application_provider1_idx` (`application_provider_id` ASC),
  INDEX `fk_contact_has_application_provider_contact1_idx` (`contact_id` ASC),
  CONSTRAINT `fk_contact_has_application_provider_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `gateway`.`contacts` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contact_has_application_provider_application_provider1`
    FOREIGN KEY (`application_provider_id`)
    REFERENCES `gateway`.`application_providers` (`application_provider_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

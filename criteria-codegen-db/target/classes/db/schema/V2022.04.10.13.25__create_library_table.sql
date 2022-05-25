DELIMITER $$

DROP PROCEDURE IF EXISTS CREATE_LIBRARY_TABLE $$

CREATE PROCEDURE CREATE_LIBRARY_TABLE()
BEGIN
    IF NOT EXISTS(
            SELECT *
            FROM INFORMATION_SCHEMA.TABLES
            WHERE TABLE_SCHEMA = 'criteria_codegen'
              AND TABLE_NAME = 'library'
        )
    THEN
        CREATE TABLE library
        (
            id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            name        VARCHAR(500),
            city        VARCHAR(100),
            year_founded DATETIME
        );
    END IF;
END $$

DELIMITER ;

CALL CREATE_LIBRARY_TABLE();
DROP PROCEDURE CREATE_LIBRARY_TABLE;

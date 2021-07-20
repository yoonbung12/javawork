INSERT INTO `project`.`test`
(`memo`)
VALUES
('test memo');
UPDATE `project`.`test`
SET
`memo` = "edit memo"
WHERE `idx` = 3;


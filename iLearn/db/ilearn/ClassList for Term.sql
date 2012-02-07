SELECT DISTINCT `stuID` as 'id', CONCAT_WS(', ',`stuLastName`,`stuFirstName`) as 'name'
FROM `Grade`
INNER JOIN `Student` ON `Student`.`stuID` = `Grade`.`graStuID`
WHERE `graTrmCode` = 1 AND `graClsCode` = '1A'
ORDER BY CONCAT_WS(', ',`stuLastName`,`stuFirstName`)
SELECT `graStuID` AS 'Student ID',`graClsCode` AS 'Class', AVG(`graFinal`) AS 'Average', CONCAT_WS(' ',`stuFirstName`, `stuLastName`) AS 'Name', `trmShortName` AS 'Term'
FROM `Grade`
INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.`stuID`
INNER JOIN `Term` ON `Grade`.`graTrmCode` = `Term`.`trmID`
GROUP BY `graClsCode`,`graStuID`
ORDER BY `graClsCode` DESC
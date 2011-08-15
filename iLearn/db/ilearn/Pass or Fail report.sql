SELECT `graClsCode`,`graStuID`, CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name', AVG(`graMid`), AVG(`graFinal`), AVG(`graGPA`)
FROM `Grade`
INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.`stuID`
WHERE `graStatus` = 'Active'
GROUP BY `graClsCode`,`graStuID`
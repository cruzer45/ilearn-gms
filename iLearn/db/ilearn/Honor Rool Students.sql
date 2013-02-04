SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name',`graAvgClsCode` AS 'Class', `Grade_Average`.`graAvgFinal` AS 'Grade'
FROM `Grade_Average`
INNER JOIN `Student` ON `Student`.`stuID` = `Grade_Average`.`graAvgStuID`
WHERE `graAvgFinal` >= 80 AND graAvgTerm = 3
ORDER BY `graAvgFinal` DESC
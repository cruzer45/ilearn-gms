SELECT CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name',`graAvgClsCode`, `Grade_Average`.`graAvgFinal`
FROM `Grade_Average`
INNER JOIN `Student` ON `Student`.`stuID` = `Grade_Average`.`graAvgStuID`
WHERE `graAvgFinal` > 80
order by `graAvgFinal` desc
SELECT `Student`.`stuID`, CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS 'Student', `Student`.`stuClsCode`, COUNT(`Detention`.`detStuID`) AS 'Detentions', SUM(`Detention`.`detServed`) as 'Served'
FROM `Detention`
INNER JOIN `Student` ON `Student`.`stuID` = `Detention`.`detStuID`
WHERE `Detention`.`detStatus` = 'Active'
GROUP BY `Detention`.`detStuID`
ORDER BY 'Detentions'
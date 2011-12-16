SELECT CONCAT_WS(', ',`Student`.`stuLastName`,`Student`.`stuFirstName`) as 'Name', `Subject`.`subCode` as 'Subject'
FROM `Student`
INNER JOIN `Class` ON `Student`.`stuClsCode` = `Class`.`clsCode`
inner join `ClassSubjects` on `Class`.`clsID` = `ClassSubjects`.`clsCode`
inner join  `Subject` on `Subject`.`subID` = `ClassSubjects`.`subCode`
order by CONCAT_WS(', ',`Student`.`stuLastName`,`Student`.`stuFirstName`), `Subject`.`subCode`
SELECT `stuClsCode`,CONCAT_WS(', ',`stuLastName`,`stuFirstName`) as 'Student',`subName`, ((SUM(`grdPointsEarned`)/ SUM(`Assments`.assmtTotalPoints)) * 100) AS 'Grade'
FROM `TermGrade`
INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID`
INNER JOIN `Student` ON `Student`.`stuID` = `TermGrade`.`grdStuID`
inner join `Subject` on `Subject`.`subCode` = `Assments`.`assmtSubject`
WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused' 
group by `grdStuID`, `subCode`
order by `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`), `subName`

SELECT `stuClsCode`,CONCAT_WS(', ',`stuLastName`,`stuFirstName`) as 'Student',`subName`, avg(`grdPointsEarned`) AS "Average"
FROM `TermGrade`
INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID`
INNER JOIN `Student` ON `Student`.`stuID` = `TermGrade`.`grdStuID`
inner join `Subject` on `Subject`.`subCode` = `Assments`.`assmtSubject`
WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused' and `grdStuID` = '791' 
group by `grdStuID`, `subCode`
order by `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`), `subName`

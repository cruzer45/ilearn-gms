SELECT `student`.`stuClsCode` AS 'Class',`student`.`stuID` AS 'ID', CONCAT_WS(' ',`stuFirstName`,`stuLastName`) AS 'Name',  `termgrade`.`grdPointsEarned` AS 'Grade' , `assments`.`assmtTotalPoints` AS 'Max Points', `subject`.`subCode` AS 'Subject' ,`assments`.`assmtID` AS 'Assessment ID', `assments`.`assmtTitle` AS 'Assignment'
FROM `termgrade`, `subject`,`assments`,`student`
WHERE `termgrade`.`grdStuID` = `student`.`stuID` 
		AND `termgrade`.`grdAssmtID` = `assments`.`assmtID`
		AND `assments`.`assmtSubject` = `subject`.`subCode`
		
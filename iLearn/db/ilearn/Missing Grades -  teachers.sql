SELECT DISTINCT `subCode` as 'Subject Code', `subName` as 'Subject', CONCAT_WS(' ',`staFirstName`,`staLastName`) as 'Teacher'
FROM `Subject`
INNER JOIN `Staff` ON `Staff`.`staCode` = `Subject`.`subStaffCode`
LEFT JOIN `Assments` ON `Assments`.`assmtSubject` = `Subject`.`subCode`
LEFT JOIN `TermGrade` ON `TermGrade`.`grdAssmtID` = `Assments`.`assmtID`
WHERE (`grdPointsEarned` IS NULL OR `grdPointsEarned` = '' OR `grdPointsEarned` = ' ' OR `grdPointsEarned` = 'Absent' OR `grdPointsEarned` = 'Incomplete') AND `grdStatus` = 'Active'
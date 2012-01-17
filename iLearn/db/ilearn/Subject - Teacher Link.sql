SELECT `subCode` AS 'Subject Code', `subName` AS 'Subject', `subCredits` AS 'Credits', CONCAT_WS(' ',`staFirstName`,`staLastName`) AS 'Teacher'
FROM `Subject`
LEFT JOIN `Staff` ON `Staff`.`staCode` = `Subject`.`subStaffCode`
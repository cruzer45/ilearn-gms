SELECT `subject`.`subCode` as 'Subject', COUNT(`subject_weightings`.`id`) AS 'Grades', CONCAT_WS(' ',`staFirstName`,`staLastName`) AS 'Teacher'
FROM `subject`
LEFT JOIN `subject_weightings` ON `subject`.`subID` = `subject_weightings`.`subID`
LEFT JOIN `staff` ON `staff`.`staCode` = `subject`.`subStaffCode`
GROUP BY `subject`.`subCode`
ORDER BY COUNT(`subject_weightings`.`id`), `subject`.`subCode`
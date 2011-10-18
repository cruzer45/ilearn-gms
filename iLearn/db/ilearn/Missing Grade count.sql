SELECT count(`grdID`)
FROM `TermGrade`
WHERE (`grdPointsEarned` IS NULL OR `grdPointsEarned` = '' OR `grdPointsEarned` = ' ' OR `grdPointsEarned` = 'Absent' OR `grdPointsEarned` = 'Incomplete') AND `grdStatus` = 'Active'

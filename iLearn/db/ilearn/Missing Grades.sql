SELECT `grdStuID`, `grdAssmtID` from `TermGrade`
where `grdPointsEarned` is null or `grdPointsEarned` = '' or `grdPointsEarned` = ' ' or `grdPointsEarned` = 'Absent' or `grdPointsEarned` = 'Incomplete'
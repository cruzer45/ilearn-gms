SELECT `logID`, `logTimeStamp`,`logAction`, `logTrmCode`
FROM `log_actions`
WHERE `logAction` = '%' AND `logTrmCode` = ''
UPDATE Subject SET subStatus = 'Inactive'
WHERE subCode LIKE '%REM. MATH';

DELETE ClassSubjects
FROM ClassSubjects
INNER JOIN Subject ON Subject.subID = ClassSubjects.subCode
WHERE Subject.subStatus = 'Inactive';
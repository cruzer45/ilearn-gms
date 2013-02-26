SELECT CONCAT_WS(' ',staFirstName,staLastName) AS 'Teacher',assmtSubject, COUNT(assmtID) AS 'No of Assessments'
FROM Assments
INNER JOIN Staff ON Staff.staCode = Assments.assmtTeacher
WHERE assmtTerm = 3 AND assmtStatus = 'Active'
GROUP BY assmtTeacher, assmtSubject;
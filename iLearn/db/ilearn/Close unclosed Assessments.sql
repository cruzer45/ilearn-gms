UPDATE Assments SET assmtStatus = 'Closed'
WHERE assmtStatus = 'Active' AND assmtTerm != 3;

UPDATE TermGrade
INNER JOIN Assments ON Assments.assmtID = TermGrade.grdAssmtID 
SET TermGrade.grdStatus = 'Closed'
WHERE TermGrade.grdStatus = 'Active' AND Assments.assmtTerm != 3;


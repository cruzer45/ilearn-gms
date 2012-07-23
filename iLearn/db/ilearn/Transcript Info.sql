SELECT DISTINCT CONCAT(YEAR(syStartDate), '-', YEAR(syEndDate)) AS 'Year'
FROM SchoolYear
INNER JOIN Grade_Year_Average ON yrgraSchYear = SchoolYear.syID
WHERE yrgraStuID = 1
SELECT DISTINCT subName
FROM Subject
INNER JOIN Grade_Year_Average ON yrgraSubCode = subCode
WHERE yrgraStuID = 1
ORDER BY yrgraSchYear, yrgraSubCode
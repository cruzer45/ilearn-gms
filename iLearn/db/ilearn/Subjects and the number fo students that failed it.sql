SELECT `graSubCode`,count(`graID`)
FROM `Grade`
WHERE `graFinal` < 60 
group by `graSubCode` 
order by count(`graID`) desc,`graSubCode`
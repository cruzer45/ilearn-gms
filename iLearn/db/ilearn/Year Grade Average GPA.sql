
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 4, `yrgraRemark` = 'Excellence', `yrgraLetterGrade` = 'A' WHERE ROUND(`yrgraYearAverage`) >= 95;
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 3.75, `yrgraRemark` = 'Excellence', `yrgraLetterGrade` = 'A-' WHERE ROUND(`yrgraYearAverage`) >= 90 AND ROUND(`yrgraYearAverage`) < 95;
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 3, `yrgraRemark` = 'Exceeds Standards' , `yrgraLetterGrade` = 'B' WHERE ROUND(`yrgraYearAverage`) >= 80 AND ROUND(`yrgraYearAverage`) < 85;
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 2.5, `yrgraRemark` = 'Meeting Standards', `yrgraLetterGrade` = 'C+'  WHERE ROUND(`yrgraYearAverage`) >= 75 AND ROUND(`yrgraYearAverage`) < 80;
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 2, `yrgraRemark` = 'Meeting Standards' , `yrgraLetterGrade` = 'C' WHERE ROUND(`yrgraYearAverage`) >= 70 AND ROUND(`yrgraYearAverage`) < 75;
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 1.5, `yrgraRemark` = 'Passing' , `yrgraLetterGrade` = 'D+' WHERE ROUND(`yrgraYearAverage`) >= 60 AND ROUND(`yrgraYearAverage`) < 70;
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 1, `yrgraRemark` = 'Passing' , `yrgraLetterGrade` = 'D' WHERE ROUND(`yrgraYearAverage`) >= 60 AND ROUND(`yrgraYearAverage`) < 65;
UPDATE `Grade_Year_Average` SET `yrgraGPA` = 0, `yrgraRemark` = 'No Credit', `yrgraLetterGrade` = 'F'  WHERE ROUND(`yrgraYearAverage`) >= 0 AND ROUND(`yrgraYearAverage`) < 60;

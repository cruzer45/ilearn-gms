SELECT `stuGender`, COUNT(`stuGender`)
FROM `Student`
WHERE `stuStatus` = 'Active'
GROUP BY  `stuGender`
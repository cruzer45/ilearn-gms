select CONCAT(' ',stuFirstName, stuLastName) AS 'Student' , graAvgClsCode, sum(rolAbsent) as 'Absent', sum(rolTardy) as 'Late'
from Grade_Average
inner join Student on Student.stuID = Grade_Average.graAvgStuID
left outer join RollCall on RollCall.rolStuID = Grade_Average.graAvgStuID and rolTrmCode = 3
where (graAvgTerm = 3 )
group by Grade_Average.graAvgStuID
order by graAvgClsCode, CONCAT(' ',stuFirstName, stuLastName) 

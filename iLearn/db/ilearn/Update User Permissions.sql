UPDATE `User` SET `usrPermissions` = ' '
WHERE `usrPermissions` IS NULL;
UPDATE `User` SET `usrPermissions` = 'Student-true|Add Student-true|Edit Student-true|View Student-true|Attendance-true|Enter Attendance-true|Edit Attendance-true|Grades-true|Create Assessment-true|Edit Assessment-true|Edit Mid Terms-true|Demerits-true|Record Demerits-true|Edit Demerits-true|Reports-true|Student Reports-true|Student List-true|Repeating Students-true|Students By Class-true|Student ID Cards-true|Class Reports-true|Class List Report-true|Class Grade Book-true|Report Cards Menu-true|Mid-Term Reports-true|Mid-Term Class Ranking-true|Term End Report-true|Term End Ranking-true|Demerit Reports-true|Demerits By Class-true|Demerits By Student-true|Statistical Reports-true|Class Size Distribution-true|Gender Distribution-true|Manage-true|Class-true|Add Class-true|Edit Class-true|View Class-true|Promotions-true|Assign Promotions-true|Promote Students-true|School-true|Staff-true|Add Staff-true|Edit Staff-true|Subjects-true|Add Subjects-true|Edit Subjects-true|Term-true|Add Term-true|Edit Term-true|Time Slots-true|Add Time Slot-true|Edit Time Slot-true|User Menu-true|Add User-true|Edit User-true|Utilities Menu-true|Mid Term-true|Calculate Mid Term Grades-true|End Of Term-true|Calculate End of Term Grades-true|Close Term-true|'
WHERE `usrGroup` = 'Administration';
UPDATE `User` SET `usrPermissions` = 'Student-true|Add Student-false|Edit Student-false|View Student-true|Attendance-true|Enter Attendance-true|Edit Attendance-true|Grades-true|Create Assessment-true|Edit Assessment-true|Edit Mid Terms-true|Demerits-true|Record Demerits-true|Edit Demerits-true|Reports-false|Student Reports-false|Student List-false|Repeating Students-false|Students By Class-false|Student ID Cards-false|Class Reports-false|Class List Report-false|Class Grade Book-true|Report Cards Menu-false|Mid-Term Reports-false|Mid-Term Class Ranking-false|Term End Report-false|Term End Ranking-false|Demerit Reports-false|Demerits By Class-false|Demerits By Student-false|Statistical Reports-false|Class Size Distribution-false|Gender Distribution-false|Manage-false|Class-false|Add Class-false|Edit Class-false|View Class-false|Promotions-false|Assign Promotions-false|Promote Students-false|School-false|Staff-false|Add Staff-false|Edit Staff-false|Subjects-false|Add Subjects-false|Edit Subjects-false|Term-false|Add Term-false|Edit Term-false|Time Slots-false|Add Time Slot-false|Edit Time Slot-false|User Menu-false|Add User-false|Edit User-false|Utilities Menu-false|Mid Term-false|Calculate Mid Term Grades-false|End Of Term-false|Calculate End of Term Grades-false|Close Term-false|'
WHERE `usrGroup` = 'Teachers';

package ilearn.student

class Student
{
	int id
	String socialSecurityNumber
	String firstName
	String lastName
	String otherNames
	Date dateOfBirth


	static mapping =
	{
		table 'Student'
		id column:'stuID'
		socialSecurityNumber column:'stuSSN'
		firstName column:'stuFirstName'
		lastName column:'stuLastName'
		otherNames column:'stuOtherNames'
		dateOfBirth column:'stuDOB'
	}

	static constraints =
	{
		id( display:true)
		socialSecurityNumber()
		firstName()
		lastName()
		otherNames()
		dateOfBirth()
	}
}

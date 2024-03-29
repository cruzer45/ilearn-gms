package ilearn.student

class Student
{
	int id
	String socialSecurityNumber
	String firstName
	String lastName
	String otherNames
	Date dateOfBirth
	String classCode
	String status
	byte[] photo
	

	static mapping =
	{
		table 'Student'
		id column:'stuID'
		socialSecurityNumber column:'stuSSN'
		firstName column:'stuFirstName'
		lastName column:'stuLastName'
		otherNames column:'stuOtherNames'
		dateOfBirth column:'stuDOB'
		classCode column:'stuClsCode'
		status column:'stuStatus'
		photo column:'stuPhoto' 
	}

	static constraints =
	{
		id( display:true)
		socialSecurityNumber(editable:false)
		firstName(editable:false)
		lastName(editable:false)
		otherNames(editable:false)
		dateOfBirth(editable:false)
		classCode(editable:false)
		status(editable:false)
		photo(editable:false)
	}

	String toString()
	{
		return firstName + " " + lastName
	}
}

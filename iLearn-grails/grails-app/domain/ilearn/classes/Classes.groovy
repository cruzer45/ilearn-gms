package ilearn.classes

class Classes
{


	int id
	String code
	String name
	String description
	String level
	String homeRoom
	int size
	String status

	static mapping =
	{
		table 'Student'
		id column:'clsID'
		code column:'clsCode'
		name column:'clsName'
		description	column:'clsDescription'
		level column:'clsLevel'
		homeRoom column:'clsHomeRoom'
		size column:'clsSize'
		status column:'clsStatus'
	}

	static constraints =
	{
		id()
		code()
		name()
		description()
		level()
		homeRoom()
		size()
		status()
	}
	
	String toString()
	{
		return name
	}
}

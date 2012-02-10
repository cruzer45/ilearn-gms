package ilearn.student

class StudentController {

    def scaffold = Student
	
	
	def displayPhoto = {
		def student = Student.get(params.id)
		response.contentType = "image/png"
		response.contentLength = student?.photo.length
		response.outputStream.write(student?.photo)
	}
	
}

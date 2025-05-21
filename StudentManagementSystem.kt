package com.example

// Student Data Class
data class Student(
    val name: String,
    val rollNumber: Int,
    val grades: MutableList<Int>
)

// extension fun- calculate average grade
fun Student.averageGrade(): Double {
    return if (grades.isNotEmpty())
        grades.average()
    else
        0.0
}

// Student Manager Class
class StudentManager {
    private val students = mutableListOf<Student>()

    fun addStudent(name: String, rollNumber: Int, gradesInput: String) {

        val grades = gradesInput.map{it.toInt()}.toMutableList()
        val student = Student(name, rollNumber, grades)  //data class
        students.add(student)
        println("Student added!")
    }


    fun viewStudents() {
        println("....View Students....")
        if (students.isEmpty()) {
            println("No students found.")
            return
        }
        else {
            students.forEach { student ->
                println("Name: ${student.name} | Roll: ${student.rollNumber} | Avg: ${student.averageGrade()}")
            }
        }
    }

    fun removeStuden(rollNumber :Int){
        val removed = students.removeIf { it.rollNumber == rollNumber }
    }

    fun findTopScorer() {
        val topper = students.maxByOrNull { it.averageGrade() }
        println("....Topper....")
        topper?.let {
            println("${it.name}(${it.averageGrade()})")
        } ?: println("No students to evaluate.")
    }
}

//  main function
fun main() {
    val manager = StudentManager()

    while (true) {

        println("\n1. Add Student")
        println("2. View Students")
        println("3. Find Topper")
        println("4. Exit")
        print("Enter your choice: ")
        when (readLine()) {

            "1" -> {
                print("Enter name: ")
                val name = readLine()?.trim().orEmpty()
                print("Enter roll number: ")
                val roll = readLine()?.toIntOrNull()
                print("Enter grades (comma separated): ")
                val grades = readLine().orEmpty()

                if (name.isNotEmpty() && roll != null) {
                    manager.addStudent(name, roll, grades)
                } else {
                    println("Invalid input. Try again.")
                }
            }

            "2" -> manager.viewStudents()

            "3" -> manager.findTopScorer()

            "4" -> {
                println("Exiting program")
                return
            }

            else -> println("Invalid choice. Try again.")
        }
    }
}

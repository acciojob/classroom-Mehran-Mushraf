package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

    HashMap<String , Student> student_db = new HashMap<>();
    HashMap<String , Teacher> teacher_db = new HashMap<>();
    HashMap<String, String> student_teacher_db = new HashMap<>();


    public void addStudent(Student student) {
        student_db.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacher_db.put(teacher.getName(), teacher);

    }
    public void addStudentTeacherPair(String student, String teacher) {
        student_teacher_db.put(student, teacher);
    }

    public Student getStudentByName(String name) {
        return student_db.get(name);
    }
     public Teacher getTeacherByName(String name) {
        return teacher_db.get(name);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(student_db.keySet());
    }
    public List<String> getStudentsByTeachersName(String name) {
        List<String> list = new ArrayList<>();
        for(String st : student_teacher_db.keySet())
        {
            if(student_teacher_db.get(st).equalsIgnoreCase(name))
                list.add(st);
        }
        return list;
    }

    public void deleteTeacherByName(String name) {
        teacher_db.remove(name);

        List<String> list = new ArrayList<>(student_teacher_db.keySet());
        for(String stud : list) {
            if(student_teacher_db.get(stud).equalsIgnoreCase(name))
            {
                student_db.remove(stud);
                student_teacher_db.remove(stud);
            }
        }
    }

    public void deleteAllTeachers() {
        for(String stud : student_teacher_db.keySet()) {
            student_db.remove(stud);
        }

        teacher_db = new HashMap<>();
        student_teacher_db = new HashMap<>();
    }



}

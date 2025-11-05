package com.example.lab15.service;

import com.example.lab15.model.Course;
import com.example.lab15.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    
    private final CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }
    
    public Optional<Course> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }
    
    
    public Course createCourse(Course course) {
        // Always set id to null to ensure INSERT operation
        Course newCourse = new Course(
            null,  // id will be auto-generated
            course.code(),
            course.name(),
            course.description(),
            course.credits()
        );
        return courseRepository.save(newCourse);
    }
    
    public Optional<Course> updateCourse(Integer id, Course courseDetails) {
        Optional<Course> existingCourseOptional = courseRepository.findById(id);
        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();
            Course updatedCourse = new Course(
                existingCourse.id(),
                courseDetails.code(),
                courseDetails.name(),
                courseDetails.description(),
                courseDetails.credits()
            );
            return Optional.of(courseRepository.save(updatedCourse));
        }
        return Optional.empty();
    }
    
    public boolean deleteCourse(Integer id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

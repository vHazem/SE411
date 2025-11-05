package com.example.lab15.controller;

import com.example.lab15.model.Course;
import com.example.lab15.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	// Create a new course - POST /api/v1/courses
	@PostMapping
	public ResponseEntity<Course> createCourse(@RequestBody Course course) {
		Course createdCourse = courseService.createCourse(course);
		return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
	}

	// Retrieve all courses - GET /api/v1/courses
	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {
		List<Course> courses = courseService.getAllCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	// Retrieve a course by its id - GET /api/v1/courses/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
		var courseOptional = courseService.getCourseById(id);
		if (courseOptional.isPresent()) {
			return new ResponseEntity<>(courseOptional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Update a course - PUT /api/v1/courses/{id}
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @RequestBody Course courseDetails) {
		var updatedCourseOptional = courseService.updateCourse(id, courseDetails);
		if (updatedCourseOptional.isPresent()) {
			return new ResponseEntity<>(updatedCourseOptional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// Delete a course - DELETE /api/v1/courses/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
		if (courseService.deleteCourse(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

package com.example.controller

import com.example.dto.CourseDTO
import com.example.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
class CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody courseDTO: CourseDTO): CourseDTO {
        return courseService.addCourse(courseDTO)

    }

    @GetMapping
    fun retrieveAllCourse() : List<CourseDTO> = courseService.retrieveAllCourses()

    @PutMapping("/{course_id}")
    fun updatingCourse(@RequestBody courseDTO: CourseDTO, @PathVariable("course_id") courseId: Int)
    =courseService.updateCourse(courseId, courseDTO)

    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable("course_id") courseId: Int)
    = courseService.deleteCourse(courseId)
}
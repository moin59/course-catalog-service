package com.example.controller

import com.example.dto.CourseDTO
import com.example.service.CourseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
@Validated
class CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody @Valid courseDTO: CourseDTO): CourseDTO {
        return courseService.addCourse(courseDTO)

    }

    @GetMapping
    fun retrieveAllCourse(@RequestParam("course_name", required = false) courseName: String?) : List<CourseDTO> = courseService.retrieveAllCourses(courseName)

    @PutMapping("/{course_id}")
    fun updatingCourse(@RequestBody courseDTO: CourseDTO, @PathVariable("course_id") courseId: Int)
    =courseService.updateCourse(courseId, courseDTO)

    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable("course_id") courseId: Int)
    = courseService.deleteCourse(courseId)
}
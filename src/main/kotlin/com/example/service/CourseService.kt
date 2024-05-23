package com.example.service

import com.example.dto.CourseDTO
import com.example.entity.Course
import com.example.exception.CourseNotFoundException
import com.example.repository.CourseRepository
import mu.KLogger
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository) {

    companion object: KLogging()

    fun addCourse(courseDTO: CourseDTO): CourseDTO{

        val courseEntity = courseDTO.let {
            Course(null, it.name, it.category)
        }
        courseRepository.save(courseEntity)

        logger.info("Saved Course is: $courseEntity")

        return courseEntity.let{
            CourseDTO(it.id, it.name, it.category)
        }
    }

    fun retrieveAllCourses(): List<CourseDTO> {
        return courseRepository.findAll()
            .map { CourseDTO(it.id, it.name, it.category) }

    }

    fun updateCourse(courseId: Int, courseDTO: CourseDTO): CourseDTO {
        val existingCourse = courseRepository.findById(courseId)

        return if(existingCourse.isPresent){
            existingCourse.get()
                .let{
                    it.name = courseDTO.name
                    it.category = courseDTO.category
                    courseRepository.save(it)
                    CourseDTO(it.id, it.name, it.category)
                }
        }else{
            throw CourseNotFoundException("No course found for the ID: $courseId")
        }

    }

    fun deleteCourse(courseId: Int): Any {

        val existingCourse = courseRepository.findById(courseId)

        return if(existingCourse.isPresent){
            existingCourse.get()
                .let{
                    courseRepository.deleteById(courseId)
                }
        }else{
            throw CourseNotFoundException("No course found for the ID: $courseId")
        }
    }

}

package com.example.service

import com.example.dto.CourseDTO
import com.example.entity.Course
import com.example.repository.CourseRepository
import mu.KLogger
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository) {

    companion object: KLogging()

    fun addCourse(courseDTO: CourseDTO){
        val courseEntity = courseDTO.let {
            Course(null, it.name, it.category)
        }
        courseRepository.save(courseEntity)

        logger.info("Saved Course is: $courseEntity")

        return courseEntity.let{
            CourseDTO(it.id, it.name, it.category)
        }
    }

}

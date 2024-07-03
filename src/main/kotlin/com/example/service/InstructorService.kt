package com.example.service

import com.example.dto.InstructorDTO
import com.example.entity.Instructor
import com.example.repository.InstructorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InstructorService(val instructorRepository: InstructorRepository) {
    fun createInstructor(instructorDTO: InstructorDTO): InstructorDTO {

        val instructorEntity = instructorDTO.let {
            Instructor(it.id, it.name)
        }

        instructorRepository.save(instructorEntity)

        return instructorEntity.let {
            InstructorDTO(it.id, it.name)
        }

    }

    fun findByInstructorId(instructorId: Int): Optional<Instructor> {

        return instructorRepository.findById(instructorId)
    }

}
package com.example.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GreetingService {

    @Value("\${message}")
    lateinit var meesage: String

    fun retrieveGreeting(name: String) = "$name, $meesage"
}
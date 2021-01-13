package com.neuqsoft.webfluxlearning.repo

import com.neuqsoft.webfluxlearning.entity.Icon
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface IconRepo : ReactiveCrudRepository<Icon, Int>
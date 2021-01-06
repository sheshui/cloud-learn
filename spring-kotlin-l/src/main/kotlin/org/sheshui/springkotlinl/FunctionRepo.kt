package org.sheshui.springkotlinl

import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface FunctionRepo : ReactiveCrudRepository<FunctionEntity, Int> {
}
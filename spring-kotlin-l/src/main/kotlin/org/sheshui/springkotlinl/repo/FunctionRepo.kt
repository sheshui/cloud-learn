package org.sheshui.springkotlinl.repo

import org.sheshui.springkotlinl.entity.FunctionEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface FunctionRepo : ReactiveCrudRepository<FunctionEntity, Int> {
}
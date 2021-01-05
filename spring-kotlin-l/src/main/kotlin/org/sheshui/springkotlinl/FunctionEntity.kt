package org.sheshui.springkotlinl

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 *
 * @author sheshui
 * @date 2021/1/4
 * @description
 */
@Table
data class FunctionEntity(
    @Id
    var id: String,

    var functionName: String,

    var functionIcon: String
)

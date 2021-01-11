package org.sheshui.springkotlinl.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 *
 * @author sheshui
 * @date 2021/1/4
 * @description
 */
@Table("function")
data class FunctionEntity(
    @Id
    var id: Int,

    var funcName: String,

    var funcIcon: String,

    var isClose: Int,

    var isAnonymous: Int
)

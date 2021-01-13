package com.neuqsoft.webfluxlearning.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("icon")
data class Icon(
    @Id
    var iconId: Int,

    var iconPath: String

)

package com.ivy.wallet.io.network.data

import com.ivy.wallet.domain.data.IntervalType
import com.ivy.wallet.domain.data.TransactionType
import com.ivy.wallet.io.persistence.data.PlannedPaymentRuleEntity
import java.time.LocalDateTime
import java.util.*

data class PlannedPaymentRuleDTO(
    val startDate: LocalDateTime?,
    val intervalN: Int?,
    val intervalType: IntervalType?,
    val oneTime: Boolean,

    val type: TransactionType,
    val accountId: UUID,
    val amount: Double = 0.0,
    val categoryId: UUID? = null,
    val title: String? = null,
    val description: String? = null,

    val id: UUID = UUID.randomUUID()
) {
    fun toEntity(): PlannedPaymentRuleEntity = PlannedPaymentRuleEntity(
        startDate = startDate,
        intervalN = intervalN,
        intervalType = intervalType,
        oneTime = oneTime,
        type = type,
        accountId = accountId,
        amount = amount,
        categoryId = categoryId,
        title = title,
        id = id,

        isSynced = true,
        isDeleted = false
    )
}
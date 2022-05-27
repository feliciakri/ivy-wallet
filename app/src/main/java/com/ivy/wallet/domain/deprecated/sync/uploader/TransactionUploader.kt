package com.ivy.wallet.domain.deprecated.sync.uploader

import com.ivy.wallet.domain.data.core.Transaction
import com.ivy.wallet.io.network.IvySession
import com.ivy.wallet.io.network.RestClient
import com.ivy.wallet.io.network.request.transaction.DeleteTransactionRequest
import com.ivy.wallet.io.network.request.transaction.UpdateTransactionRequest
import com.ivy.wallet.io.persistence.dao.TransactionDao
import timber.log.Timber
import java.util.*

class TransactionUploader(
    private val dao: TransactionDao,
    restClient: RestClient,
    private val ivySession: IvySession
) {
    private val service = restClient.transactionService

    suspend fun sync(item: Transaction) {
        if (!ivySession.isLoggedIn()) return

        try {
            //update
            service.update(
                UpdateTransactionRequest(
                    transaction = item.toDTO()
                )
            )

            //flag as synced
            dao.save(
                item.copy(
                    isSynced = true
                ).toEntity()
            )
            Timber.d("Transaction updated: $item.")
        } catch (e: Exception) {
            Timber.e("Failed to update with error (${e.message}): $item")
            e.printStackTrace()
        }
    }


    suspend fun delete(id: UUID) {
        if (!ivySession.isLoggedIn()) return

        try {
            //Delete on server
            service.delete(
                DeleteTransactionRequest(
                    id = id
                )
            )

            //delete from local db
            dao.deleteById(id)
            Timber.d("Transaction deleted: $id.")
        } catch (e: Exception) {
            Timber.e("Failed to delete with error (${e.message}): $id")
            e.printStackTrace()

            //delete from local db
            dao.deleteById(id)
        }
    }

}
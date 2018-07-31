package sample.sapient.com.sampleappjaggrat.network

import io.reactivex.Observable
import retrofit2.http.GET
import sample.sapient.com.sampleappjaggrat.database.Transaction

/**
 * The interface which provides methods to get result of webservices
 */
interface TransactionApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/transactions")
    fun getTransactions(): Observable<List<Transaction>>
}
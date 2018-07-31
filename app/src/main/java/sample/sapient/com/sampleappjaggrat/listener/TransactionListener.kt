package sample.sapient.com.sampleappjaggrat.listener

import sample.sapient.com.sampleappjaggrat.model.TransactionViewModel

interface TransactionListener {

    fun onTransactionTapped(model : TransactionViewModel)
}
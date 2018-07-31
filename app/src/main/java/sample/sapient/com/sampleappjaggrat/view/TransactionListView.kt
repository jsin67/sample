package sample.sapient.com.sampleappjaggrat.view;

import sample.sapient.com.sampleappjaggrat.model.TransactionViewModel

interface TransactionListView : BaseView {

    fun updateUI(transaction : ArrayList<TransactionViewModel>, balance: Double)
    fun showDetail(transaction : ArrayList<TransactionViewModel>)
    fun showLoading()
    fun hideLoading()
    fun showError(error : String)
}

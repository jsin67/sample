package sample.sapient.com.sampleappjaggrat

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_transaction_list.*
import sample.sapient.com.sampleappjaggrat.adapter.CustomAdapter
import sample.sapient.com.sampleappjaggrat.listener.TransactionListener
import sample.sapient.com.sampleappjaggrat.model.TransactionViewModel
import sample.sapient.com.sampleappjaggrat.presenter.TransactionPresenterImp
import sample.sapient.com.sampleappjaggrat.view.TransactionListView

class TransactionListActivity :  BaseActivity<TransactionPresenterImp>(), TransactionListView, TransactionListener {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var availableBalanceTv: TextView

    private var transactionViewModelList : ArrayList<TransactionViewModel> = ArrayList()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_list)
        progressBar = this.loading
        availableBalanceTv = this.tv_total_balance
        linearLayoutManager = LinearLayoutManager(this)
        rv_transaction_list.layoutManager = linearLayoutManager
        recyclerView = this.rv_transaction_list
        val adapter = CustomAdapter(transactionViewModelList, this)
        recyclerView.adapter = adapter
        presenter.onViewCreated()
    }

    override fun updateUI(transaction: ArrayList<TransactionViewModel>, balance:Double) {
        progressBar.visibility = View.GONE
        transactionViewModelList.clear()
        transactionViewModelList.addAll(transaction)
        recyclerView.adapter.notifyDataSetChanged()
        val text = String.format(this.getString(R.string.total_balance_format), balance)
        availableBalanceTv.text = text
        availableBalanceTv.visibility = View.VISIBLE
    }

    override fun showDetail(transaction: ArrayList<TransactionViewModel>) {

    }

    override fun instantiatePresenter(): TransactionPresenterImp {
        return TransactionPresenterImp(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE

    }

    override fun onTransactionTapped(model: TransactionViewModel) {
        Toast.makeText(this, model.description, Toast.LENGTH_LONG).show()
    }
}

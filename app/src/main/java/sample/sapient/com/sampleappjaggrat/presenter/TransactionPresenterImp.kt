package sample.sapient.com.sampleappjaggrat.presenter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import sample.sapient.com.sampleappjaggrat.database.Transaction
import sample.sapient.com.sampleappjaggrat.database.TransactionDao
import sample.sapient.com.sampleappjaggrat.model.TransactionViewModel
import sample.sapient.com.sampleappjaggrat.network.APIController
import sample.sapient.com.sampleappjaggrat.network.TransactionApi
import sample.sapient.com.sampleappjaggrat.network.ServiceVolley
import sample.sapient.com.sampleappjaggrat.view.TransactionListView
import java.io.StringReader
import java.math.RoundingMode
import javax.inject.Inject

class TransactionPresenterImp(postView: TransactionListView) : BasePresenter<TransactionListView>(postView){
    @Inject
    lateinit var transactionApi: TransactionApi
    @Inject
    lateinit var postDao: TransactionDao

    private var availableBalance :Double = 0.0

    private var subscription: Disposable? = null

    private val mutablePostList: MutableLiveData<List<Transaction>> = MutableLiveData()

    override fun onViewCreated() {
        val postListObserver = Observer<List<Transaction>> { postList ->
            if (postList != null) {
                view.updateUI(convertModel(postList), availableBalance)
            }
        }

        mutablePostList.observe(view, postListObserver)
        loadPosts()
    }



    fun loadPosts() {
        view.showLoading()
        subscription = Observable.fromCallable({ postDao.all })
                .flatMap { postList -> if (postList.isNotEmpty()) Observable.just(postList) else saveApiTransactionsInDatabase() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { postList -> mutablePostList.value = postList },
                        { view.showError("Error") }
                )
    }

    /**
     * Load the posts from the API and store them in the database.
     * @return an Observable for the list of Post retrieved from API
     */
    private fun saveApiTransactionsInDatabase(): Observable<List<Transaction>> {
        return transactionApi.getTransactions()
                .flatMap { postList -> Observable.fromCallable({ postDao.insertAll(*postList.toTypedArray());postDao.all }) }
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }



    fun downloadTransactionRecord() {

        val service = ServiceVolley()
        val apiController = APIController(service)

        val path = "transactions"
        val params = JSONObject()


        apiController.get(path, params) { response: JSONArray? ->
            Log.d("jaggrat " , " " + response)

            val stringReader = StringReader(response.toString())

            val gsonBuilder = GsonBuilder().serializeNulls()
            val gson = gsonBuilder.create()

            val models: List<Transaction> = gson.fromJson(stringReader , Array<Transaction>::class.java).toList()
            view.updateUI(convertModel(models), availableBalance)
        }

    }

    private fun convertModel(models : List<Transaction>) : ArrayList<TransactionViewModel>{
        val transactionViewModelList : ArrayList<TransactionViewModel> = ArrayList()
        var value = 0.0
        for (model in models){
            value += model.amount
            transactionViewModelList.add(TransactionViewModel(model.id, model.description, model.amount))
        }
        availableBalance = value//.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        return transactionViewModelList
    }

}

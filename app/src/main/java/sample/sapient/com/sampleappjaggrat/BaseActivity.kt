package sample.sapient.com.sampleappjaggrat

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sample.sapient.com.sampleappjaggrat.presenter.BasePresenter
import sample.sapient.com.sampleappjaggrat.view.BaseView

/**
 * BaseActivity for all activity. It provides required methods and presenter
 * instantiation and calls.
 * @param P the type of the presenter the Activity is based on
 */
abstract class BaseActivity<P : BasePresenter<BaseView>> : BaseView, AppCompatActivity() {
    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    override fun getContext(): Context {
        return this
    }
}
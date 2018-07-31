package sample.sapient.com.sampleappjaggrat.presenter

import sample.sapient.com.sampleappjaggrat.component.DaggerPresenterInjector
import sample.sapient.com.sampleappjaggrat.component.PresenterInjector
import sample.sapient.com.sampleappjaggrat.database.ContextModule
import sample.sapient.com.sampleappjaggrat.database.DaoModule
import sample.sapient.com.sampleappjaggrat.database.NetworkModule
import sample.sapient.com.sampleappjaggrat.view.BaseView

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property injector The injector used to inject required dependencies
 * @constructor Injects the required dependencies
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .daoModule(DaoModule)
            .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is TransactionPresenterImp -> injector.inject(this)
        }
    }
}
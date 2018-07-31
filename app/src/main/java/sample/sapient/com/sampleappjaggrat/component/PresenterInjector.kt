package sample.sapient.com.sampleappjaggrat.component

import dagger.BindsInstance
import dagger.Component
import sample.sapient.com.sampleappjaggrat.database.ContextModule
import sample.sapient.com.sampleappjaggrat.database.DaoModule
import sample.sapient.com.sampleappjaggrat.database.NetworkModule
import sample.sapient.com.sampleappjaggrat.presenter.TransactionPresenterImp
import sample.sapient.com.sampleappjaggrat.view.BaseView
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (DaoModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param TransactionPresenterImp TransactionPresenterImp in which to inject the dependencies
     */
    fun inject(transactionPresenter: TransactionPresenterImp)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun daoModule(daoModule: DaoModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}
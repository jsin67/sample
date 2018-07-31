package sample.sapient.com.sampleappjaggrat.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import sample.sapient.com.sampleappjaggrat.database.DaoModule.DATABASE_NAME

/**
 * Module which provides all dependencies about databases
 * @property DATABASE_NAME The name of the database of the application
 */
@Module(includes = [(ContextModule::class)])
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object DaoModule{
    private const val DATABASE_NAME = "Hulk"

    /**
     * Returns the database of the application.
     * @param context Context in which the application is running
     * @return the database of the application
     */
    @Provides
    @JvmStatic
    internal fun provideTransactionDatabase(context: Context): TransactionDatabase {
        return Room.databaseBuilder(context.applicationContext, TransactionDatabase::class.java, DATABASE_NAME).build()
    }

    /**
     * Returns the Dao of Posts.
     * @param db the database of the application
     * @return the Dao of Posts
     */
    @Provides
    @JvmStatic
    internal fun provideTransactionDao(db:TransactionDatabase): TransactionDao {
        return db.transactionDao()
    }
}
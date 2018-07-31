package sample.sapient.com.sampleappjaggrat.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Interface which provide methods for accessing Database Access Objects
 */
@Database(entities = [(Transaction::class)], version = 1)
abstract class TransactionDatabase : RoomDatabase() {
    /**
     * Returns the Database Access Object to deal with Transaction.
     * @return the Database Access Object to deal with Transaction
     */
    abstract fun transactionDao(): TransactionDao
}
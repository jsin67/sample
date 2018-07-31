package sample.sapient.com.sampleappjaggrat.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * The database object in charge of inserting Post objects and retrieving them from Database.
 * @property all the list of all Posts in database
 */
@Dao
interface TransactionDao {
    @get:Query("SELECT * FROM `Transaction` ORDER BY effectiveDate")
    val all: List<Transaction>


    @Insert
    fun insertAll(vararg posts: Transaction)
}
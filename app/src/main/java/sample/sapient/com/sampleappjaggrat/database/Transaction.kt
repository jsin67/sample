package sample.sapient.com.sampleappjaggrat.database;

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class Transaction (
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val description : String,
        val amount : Double,
        val effectiveDate : String)
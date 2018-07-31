package sample.sapient.com.sampleappjaggrat.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import sample.sapient.com.sampleappjaggrat.R
import sample.sapient.com.sampleappjaggrat.listener.TransactionListener
import sample.sapient.com.sampleappjaggrat.model.TransactionViewModel


class CustomAdapter(private val transactions: ArrayList<TransactionViewModel>, val listener: TransactionListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
 
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }
 
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(transactions[position], listener)
    }
 
    override fun getItemCount(): Int {
        return transactions.size
    }
 
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
 
        fun bindItems(transactionViewModel: TransactionViewModel, listener: TransactionListener) {
            val tvAmount = itemView.findViewById(R.id.tv_amount) as TextView
            val tvTitleType  = itemView.findViewById(R.id.tv_title_type) as TextView
            tvAmount.text = transactionViewModel.amount.toString()
            tvTitleType.text = transactionViewModel.description
            itemView.setOnClickListener({ listener.onTransactionTapped(transactionViewModel) })

        }
    }
}
package com.example.examgos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val dataSet: List<Film>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

   inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var onItemClick: ((Film) -> Unit)? = null
        val textViewName: TextView
        val textViewYear: TextView

        init {
            // Define click listener for the ViewHolder's View
            textViewName = view.findViewById(R.id.name_film)
            textViewYear = view.findViewById(R.id.year_film)
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[position])
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewYear.text = dataSet[position].year
        viewHolder.textViewName.text = dataSet[position].name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
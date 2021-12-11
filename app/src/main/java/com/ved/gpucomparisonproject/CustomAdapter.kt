package com.ved.gpucomparisonproject

import android.content.Context
import android.graphics.Color
import android.util.Log.d
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ved.gpucomparisonproject.MainActivity.Companion.selected
import com.ved.gpucomparisonproject.databinding.AdapterCustomBinding

class CustomAdapter(
    private val gpuList: List<Gpu>,
    private val listener: (Gpu) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = AdapterCustomBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_custom, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gpu = gpuList[position]
        with(holder) {
            binding.adapterManufacturer.text = gpu.manufacturer
            if (binding.adapterManufacturer.text.equals("NVIDIA"))
                binding.adapterManufacturer.setTextColor(Color.GREEN)
            else
                binding.adapterManufacturer.setTextColor(Color.RED)
            binding.adapterName.text = gpu.name
            binding.adapterPrice.text = "$" + gpu.price
            itemView.setOnClickListener {
                listener(gpu)
                selected=position
                notifyDataSetChanged()
            }
            if(selected==position) {
                //d("Tag", "position: $position , selected: $selected")
                //binding.linearLayout.elevation = 6.dpToPixels(binding.root.context);
                binding.linearLayout.setCardBackgroundColor(Color.parseColor("#dddddd"))
            }
            else
                binding.linearLayout.setCardBackgroundColor(Color.WHITE)
        }
    }
    override fun getItemCount(): Int {
        return gpuList.size
    }
    public fun updateView(gpuList: List<Gpu>) {
        notifyDataSetChanged()
    }
    fun Int.dpToPixels(context: Context):Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,this.toFloat(),context.resources.displayMetrics
    )

}
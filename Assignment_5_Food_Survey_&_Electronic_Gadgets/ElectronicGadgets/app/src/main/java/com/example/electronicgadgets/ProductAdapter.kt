

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.electronicgadgets.R

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: (Product) -> Unit,
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val productCost: TextView = itemView.findViewById(R.id.productCost)
        val productImageView: ImageView = itemView.findViewById(R.id.productImage)
        val productLogoView: ImageView = itemView.findViewById(R.id.productLogo)


        val addToCartButton: Button = itemView.findViewById(R.id.btnAddToCart)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(productList[position])
                }
            }
            addToCartButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onAddToCartClick(productList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.productImageView.setImageResource(currentItem.productImageResource)
        holder.productLogoView.setImageResource(currentItem.productLogoResource)

        holder.productName.text = currentItem.productName
        holder.productDescription.text = currentItem.productDescription
        holder.productCost.text = "$${currentItem.cost}"
    }

    override fun getItemCount() = productList.size
}
data class Product(
    val productName: String,
    val productDescription: String,
    val cost: Double,
    val productImageResource: Int,
    val productLogoResource: Int
)

package com.example.electronicgadgets



import Product
import ProductAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val products = ArrayList<Product>()
    private val cartItems = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        products.add(Product("iphone 14 Pro", "16gb ram", 2500.00, R.drawable.q4, R.drawable.ic_baseline_insert_photo_24))
        products.add(Product("Washing machine", "18-core GPU", 1500.00, R.drawable.q1, R.drawable.ic_baseline_insert_photo_24))
        products.add(Product("Headset Pro", "12-core CPU", 300.00, R.drawable.q2, R.drawable.ic_baseline_insert_photo_24))
        products.add(Product("MacBook M3 Pro", "12-core CPU\n18-core GPU", 4000.00, R.drawable.q3, R.drawable.ic_baseline_insert_photo_24))

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val productAdapter = ProductAdapter(products,
            onItemClick = { product -> showProductDetail(product) },
            onAddToCartClick = { product -> addToCart(product) }
        )
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnViewCart).setOnClickListener {
            viewCart()
        }
    }

    private fun showProductDetail(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java).apply {
            putExtra("productImageResource", product.productImageResource)
            putExtra("productName", product.productName)
            putExtra("productDescription", product.productDescription)
            putExtra("productCost", product.cost)

        }
        startActivity(intent)
    }

    private fun addToCart(product: Product) {
        cartItems.add(product)
        Toast.makeText(this, "Added ${product.productName} to cart", Toast.LENGTH_SHORT).show()
    }

    private fun viewCart() {
        if (cartItems.isNotEmpty()) {
            val itemsText = cartItems.joinToString(separator = "\n") { it.productName }
            Toast.makeText(this, "Items in cart:\n$itemsText", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show()
        }
    }
}








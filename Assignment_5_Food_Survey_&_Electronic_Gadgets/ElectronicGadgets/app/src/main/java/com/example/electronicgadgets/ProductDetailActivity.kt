package com.example.electronicgadgets


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("productDescription")
        val productCost = intent.getDoubleExtra("productCost", 0.0)
        val productImageResource = intent.getIntExtra("productImageResource", 0)

        val productNameTextView: TextView = findViewById(R.id.productNameTextView)
        val productDescriptionTextView: TextView = findViewById(R.id.productDescriptionTextView)
        val productCostTextView: TextView = findViewById(R.id.productCostTextView)
        val productImageView: ImageView = findViewById(R.id.productImageView)


        productNameTextView.text = productName
        productDescriptionTextView.text = productDescription
        productCostTextView.text = "$$productCost"
        productImageView.setImageResource(productImageResource)

        val btnHome: Button = findViewById(R.id.btnHome)
        btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
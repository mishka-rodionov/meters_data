package com.rodionov.meters_data.presentation.main

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rodionov.meters_data.R
import com.rodionov.meters_data.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


//                Log.d("LOG_TAG", "printOrder: " + receipt.mar);
//                if (receipt.mar.equals("6400001019") || receipt.mar.equals("6400001012")) {
//                if (receipt.mar.equals("6400001006")) {
        //            ArrayList<InfoCheck> receipts = new ArrayList<>();
//        val text = SpannableStringBuilder()
//        val length: Int = text.length
//        text
//            .append("Маршрут: ")
//            .append("receipt.mar")
//            .append("\n")
//            .append("Клиент: ")
//            .append("receipt.client")
//            .append("\n")
//            .append("Кол-во лотков: ")
//            .append("receipt.countTray")
//            .append("\n")
//            .append("Адрес: ")
//            .append("receipt.address")
//            .append("\n")
//            .append("\n")
//            .setSpan(
//                ForegroundColorSpan(resources.getColor(com.google.android.material.R.color.design_dark_default_color_primary)),
//                length,
//                length + 9 + "receipt.mar".length,
//                Spanned.SPAN_INCLUSIVE_INCLUSIVE
//            )
//        val title = SpannableStringBuilder()
//            .append("Your big island ")
//            .bold { append("ADVENTURE") }
//        binding.tvTest.text = text

    }
}
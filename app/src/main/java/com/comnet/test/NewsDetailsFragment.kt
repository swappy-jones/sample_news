package com.comnet.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.comnet.test.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment:Fragment(R.layout.fragment_news_details) {
    val args by navArgs<NewsDetailsFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNewsDetailsBinding.bind(view)

        binding.apply{
            //Glide.with(this@NewsDetailsFragment).load(args.news.thumbnail).into(imgNews)
            webView.settings.javaScriptEnabled=true

            webView.webViewClient = object : WebViewClient() {

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.loader.visibility=View.GONE
                }

                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    url?.let{
                        binding.loader.visibility=View.VISIBLE
                        view?.loadUrl(url)
                    }
                    return true
                }
            }
            webView.loadDataWithBaseURL(null,args.news.content, "text/html", "UTF-8",null)
        }
    }

}
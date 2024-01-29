package id.ridiculousigit.mandiriinsight.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.ridiculousigit.mandiriinsight.R
import id.ridiculousigit.mandiriinsight.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup WebView
        setupWebView()
        // Setup Share Button
        setupShareButton()
        // Setup Back Button
        setupBackButton()
    }

    // Function to setup WebView
    private fun setupWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            loadUrl(args.url ?: "")
            webChromeClient = progressBarWebChromeClient
        }
    }

    // Function to setup Share Button
    private fun setupShareButton() {
        binding.btnShare.setOnClickListener {
            // Create share Intent
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, args.url)
                type = "text/plain"
            }
            // Start share Intent chooser
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share)))
        }
    }

    // Function to setup Back Button
    private fun setupBackButton() {
        // Handle back button click
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        binding.btnBack.setOnClickListener {
            // Check if WebView can go back, if yes, navigate back
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                // Otherwise, navigate up to previous destination
                findNavController().navigateUp()
            }
        }
    }

    // OnBackPressedCallback for handling back press
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Check if WebView can go back, if yes, navigate back
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                // Otherwise, navigate up to previous destination
                findNavController().navigateUp()
            }
        }
    }

    // WebChromeClient for handling progress bar
    private val progressBarWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            // Show loading animation if page is still loading, hide otherwise
            binding.loadingAnimation.visibility = if (newProgress < 100) View.VISIBLE else View.GONE
            if (newProgress < 100) {
                // Play animation if page is still loading
                binding.loadingAnimation.playAnimation()
            } else {
                // Cancel animation if page has finished loading
                binding.loadingAnimation.cancelAnimation()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Stop WebView loading and destroy it to avoid memory leaks
        binding.webView.stopLoading()
        binding.webView.destroy()
        _binding = null
    }
}
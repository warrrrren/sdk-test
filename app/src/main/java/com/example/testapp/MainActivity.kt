package com.example.testapp

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.android.adsdk.ad.original.banner.BannerAd
import com.example.testapp.ui.theme.TestAppTheme
import com.android.adsdk.ad.rendering.banner.BannerAdView
import com.android.adsdk.base.AdViewSize
import com.android.adsdk.base.Error
import com.android.adsdk.base.listeners.OnBidCompletionListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MediaNetAdSDK.init(this, "8CUEJHSH0")

        setContent {
            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Test User")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val adUnit = BannerAd("/45361917/MovieDraw_Frontpage", AdSize.BANNER)
    val request = AdManagerAdRequest.Builder().build()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                val adView = AdManagerAdView(context)
                adUnit.fetchDemandForAd(request, object: OnBidCompletionListener {
                    override fun onSuccess(keywordMap: Map<String, String>?) {
                        adView.loadAd(request)
                    }
                    override fun onError(error: Error) {
                        adView.loadAd(request)
                    }
                })
                adView
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAppTheme {
        Greeting("Android")
    }
}
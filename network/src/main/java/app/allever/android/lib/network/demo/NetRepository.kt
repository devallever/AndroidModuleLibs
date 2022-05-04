package app.allever.android.lib.network.demo

import app.allever.android.lib.network.ApiService
import app.allever.android.lib.network.DefaultRetrofitCallback
import app.allever.android.lib.network.NetworkHandler
import app.allever.android.lib.network.ResponseCallback
import app.allever.android.lib.network.cache.ResponseCache
import app.allever.android.lib.network.response.NetResponse
import kotlinx.coroutines.delay

object NetRepository : NetworkHandler() {
    private val wanAndroidApi by lazy {
        ApiService.get(WanAndroidApi::class.java)
    }

    suspend fun getBannerForJava() = requestForJava {
        wanAndroidApi.getBannerForJava()
    }

    suspend fun getBanner() = request {
        wanAndroidApi.getBanner()
    }

    suspend fun getBanner(responseCache: ResponseCache<*>? = null) =
        request(BaseResponse::class.java, responseCache) {
            wanAndroidApi.getBanner()
        }

    suspend fun test(): String {
        delay(1000)
        return "hello"

    }

    fun getBannerCall(callback: ResponseCallback<List<BannerData>>) {
        wanAndroidApi.getBannerCall().enqueue(DefaultRetrofitCallback(callback))
    }
}
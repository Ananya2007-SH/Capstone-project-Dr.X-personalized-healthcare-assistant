package com.example.drx

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.java.GenerativeModelFutures
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.GenerationConfig
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import java.util.Collections
import java.util.concurrent.Executor

class GeminiPro {

    fun getResponse(query: String, responseCallback: ResponseCallback) {
        val model: GenerativeModelFutures = getModel()
        val content: Content = Content.Builder().text(query).build()
        val executor: (Runnable) -> Unit = Runnable::run

        val response: ListenableFuture<GenerateContentResponse> = model.generateContent(content)
        Futures.addCallback(response, object : FutureCallback<GenerateContentResponse> {
            override fun onSuccess(result: GenerateContentResponse?) {
                val resultString = result?.text.toString()
                responseCallback.onResponse(resultString)
            }

            override fun onFailure(t: Throwable) {
                responseCallback.onError(t)
            }

        }, executor)
    }

    private fun getModel(): GenerativeModelFutures {
        val apiKey = BuildConfig.api_key

        val harassmentSafety: SafetySetting =  SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.ONLY_HIGH)
        val configBuilder: GenerationConfig.Builder = GenerationConfig.builder()
        configBuilder.temperature = 0.9f
        configBuilder.topK = 16
        configBuilder.topP = 0.1f
        val generationConfig: GenerationConfig = configBuilder.build()

        val gm: GenerativeModel = GenerativeModel(
            "gemini-pro",
            apiKey,
            generationConfig,
            Collections.singletonList(harassmentSafety)
        )
        return GenerativeModelFutures.from(gm)
    }
}
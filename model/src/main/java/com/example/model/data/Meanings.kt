package com.example.model.data

import com.google.gson.annotations.SerializedName

class Meanings (
    @SerializedName("translation") val translation: Translation?,
    @SerializedName("imageUrl") val imageUrl: String?
)
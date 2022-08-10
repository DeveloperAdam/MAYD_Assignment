package com.adam.mayd_assignment.data

import com.google.gson.annotations.SerializedName

data class ShortlyDataModel(
    @SerializedName("ok") var ok: Boolean? = null,
    @SerializedName("result") var result: Shortly? = Shortly()
)

data class Shortly(
    @SerializedName("code") var code: String? = null,
    @SerializedName("short_link") var shortLink: String? = null,
    @SerializedName("full_short_link") var fullShortLink: String? = null,
    @SerializedName("short_link2") var shortLink2: String? = null,
    @SerializedName("full_short_link2") var fullShortLink2: String? = null,
    @SerializedName("short_link3") var shortLink3: String? = null,
    @SerializedName("full_short_link3") var fullShortLink3: String? = null,
    @SerializedName("share_link") var shareLink: String? = null,
    @SerializedName("full_share_link") var fullShareLink: String? = null,
    @SerializedName("original_link") var originalLink: String? = null,
    var isCopied : Boolean = false
)
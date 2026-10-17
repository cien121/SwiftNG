package com.v2ray.ang.fmt

import com.v2ray.ang.AppConfig
import com.v2ray.ang.dto.entities.ProfileItem
import com.v2ray.ang.enums.EConfigType
import com.v2ray.ang.extension.idnHost
import com.v2ray.ang.extension.nullIfBlank
import com.v2ray.ang.util.Utils
import java.net.URI

object AnytlsFmt : FmtBase() {
    fun parse(str: String): ProfileItem? {
        val config = ProfileItem.create(EConfigType.ANYTLS)
        val uri = URI(Utils.fixIllegalUrl(str))
        if (uri.rawQuery.isNullOrEmpty()) return null
        val queryParam = getQueryParam(uri)
        config.remarks = Utils.decodeURIComponent(uri.fragment.orEmpty()).let { it.ifEmpty { "none" } }
        config.server = uri.idnHost
        config.serverPort = (uri.port.takeIf { it > 0 } ?: 443).toString()
        config.password = Utils.decodeURIComponent(uri.userInfo.orEmpty())
        config.security = AppConfig.TLS
        config.sni = queryParam["sni"]?.nullIfBlank()
        config.insecure = queryParam["insecure"] == "1"
        config.alpn = queryParam["alpn"]?.nullIfBlank()
        config.fingerPrint = queryParam["fp"]?.nullIfBlank()
        return config
    }

    fun toUri(config: ProfileItem): String {
        val dicQuery = HashMap<String, String>()
        config.sni?.nullIfBlank()?.let { dicQuery["sni"] = it }
        if (config.insecure == true) { dicQuery["insecure"] = "1" }
        config.alpn?.nullIfBlank()?.let { dicQuery["alpn"] = it }
        config.fingerPrint?.nullIfBlank()?.let { dicQuery["fp"] = it }
        return toUri(config, config.password, dicQuery)
    }
}

package gay.vzt.uaparser.regexes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Regexes(
    @SerialName("user_agent_parsers")
    val userAgentParsers: List<UA>,
    @SerialName("os_parsers")
    val osParsers: List<OS>,
    @SerialName("device_parsers")
    val deviceParsers: List<Device>,
) {
    public sealed class Reg {
        public abstract val regex: String
        public abstract val regexFlag: String?

        public val compiled: Regex by lazy {
            val options = buildSet {
                val flags = regexFlag ?: ""
                if ('i' in flags) add(RegexOption.IGNORE_CASE)
            }

            regex.toRegex(options)
        }
    }

    @Serializable
    public data class UA(
        override val regex: String,
        @SerialName("regex_flag")
        override val regexFlag: String? = null,
        @SerialName("family_replacement")
        val familyReplacement: String = "$1",
        @SerialName("v1_replacement")
        val v1Replacement: String = "$2",
        @SerialName("v2_replacement")
        val v2Replacement: String = "$3",
        @SerialName("v3_replacement")
        val v3Replacement: String = "$4",
    ) : Reg()

    @Serializable
    public data class OS(
        override val regex: String,
        @SerialName("regex_flag")
        override val regexFlag: String? = null,
        @SerialName("os_replacement")
        val osReplacement: String = "$1",
        @SerialName("os_v1_replacement")
        val osV1Replacement: String = "$2",
        @SerialName("os_v2_replacement")
        val osV2Replacement: String = "$3",
        @SerialName("os_v3_replacement")
        val osV3Replacement: String = "$4",
        @SerialName("os_v4_replacement")
        val osV4Replacement: String = "$5",
    ) : Reg()

    @Serializable
    public data class Device(
        override val regex: String,
        @SerialName("regex_flag")
        override val regexFlag: String? = null,
        @SerialName("device_replacement")
        val deviceReplacement: String = "$1",
        @SerialName("brand_replacement")
        val brandReplacement: String? = null,
        @SerialName("model_replacement")
        val modelReplacement: String = "$2",
    ) : Reg()
}

package gay.vzt.uaparser

import gay.vzt.uaparser.Agent.Companion.match
import gay.vzt.uaparser.Device.Companion.match
import gay.vzt.uaparser.OS.Companion.match
import gay.vzt.uaparser.regexes.Regexes
import kotlinx.serialization.Serializable

@Serializable
public data class UserAgent(val agent: Agent, val device: Device, val os: OS) {
    public companion object {
        public val Other: UserAgent = UserAgent(Agent.Other, Device.Other, OS.Other)

        public fun Regexes.match(userAgent: String): UserAgent = UserAgent(
            userAgentParsers.match(userAgent),
            deviceParsers.match(userAgent),
            osParsers.match(userAgent)
        )
    }
}

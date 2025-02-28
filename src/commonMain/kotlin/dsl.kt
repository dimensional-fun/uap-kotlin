package gay.vzt.uaparser

import gay.vzt.uaparser.Agent.Companion.match
import gay.vzt.uaparser.Device.Companion.match
import gay.vzt.uaparser.OS.Companion.match
import gay.vzt.uaparser.UserAgent.Companion.match
import gay.vzt.uaparser.regexes.Regexes
import gay.vzt.uaparser.regexes.defaultRegexes

/**
 *
 */
public inline fun String.parseOS(regexes: Regexes = defaultRegexes()): OS =
    regexes.osParsers.match(this)

/**
 *
 */
public inline fun String.parseAgent(regexes: Regexes = defaultRegexes()): Agent =
    regexes.userAgentParsers.match(this)

/**
 *
 */
public inline fun String.parseDevice(regexes: Regexes = defaultRegexes()): Device =
    regexes.deviceParsers.match(this)

/**
 *
 */
public inline fun String.parseUserAgent(regexes: Regexes = defaultRegexes()): UserAgent =
    regexes.match(this)

internal infix fun MatchResult.repl(rp: String) = if (rp.startsWith('$')) {
    rp.drop(1).toIntOrNull()
        ?.let { groupValues.elementAtOrNull(it) }
        ?.takeUnless { it.isBlank() }
} else rp

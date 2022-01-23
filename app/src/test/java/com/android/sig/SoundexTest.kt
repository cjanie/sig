package com.android.sig

import org.junit.Assert.assertEquals
import org.junit.Test


class Soundex {

    private val MAX_CODE_LENGHT = 4

    fun encode(word: String): String {
        return this.zeroPad(this.head(word) + this.encodedDigits(word))
    }

    private fun head(word: String): String {
        return word.substring(0, 1)
    }

    private fun encodedDigits(word: String): String {
        if(word.length > 1)
            return encodedDigit()
        return ""
    }

    private fun encodedDigit(): String {
        return "1"
    }

    private fun zeroPad(headPlusEncodedDigits: String): String {
        val numberOfZerosNeeded = this.MAX_CODE_LENGHT - headPlusEncodedDigits.length
        var zeros = ""
        for(i in 1..numberOfZerosNeeded)
            zeros += "0"
        return headPlusEncodedDigits + zeros
    }
}

// Default Junit 5 test instance is @TestInstance(TestInstance.Lifecycle.PER_METHOD)
// Otherwise add annotation @TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SoundexTest {

    /*
    * Soundex rules cf Wikipedia
    * 1. Retain the first letter. Drop all other occurrences of a, e, i, o, u, y, h, w.
    *
    * 2. Replace consonants with digits (after the first letter):
    * - b, f, p, v: 1
    * - c, g, j, k, q, s, x, z: 2
    * - d, t: 3
    * - l: 4
    * - m, n: 5
    * - r: 6
    *
    * 3. If two adjacent letters encode to the same number, encode them instead as a single number.
    * Also do so if two letters with the same number are separated by h or w
    * (but code them twice if separated by a vowel).
    * This rule also applies to the first letter.
    *
    * 4. Stop when you have a letter and three digits. Zero-pad if needed.
    */

    // Fixture instantiated before each test since default test instance is per method
    private val soundex = Soundex()

    @Test
    fun soundexEncodingRetainsSoleLetterOfOneLetterWord() {
        assertEquals("A000", this.soundex.encode("A"))
    }

    @Test
    fun soundexEncodingPadsWithZerosToEnsureThreeDigits() {
        assertEquals("I000", this.soundex.encode("I"))
    }

    @Test
    fun soundexEncodingReplacesConsonantsWithAppropriateDigits() {
        assertEquals("A100", this.soundex.encode("Ab"))
    }
}
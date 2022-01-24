package com.android.sig

import org.junit.Assert.assertEquals
import org.junit.Test


class Soundex {

    private val MAX_CODE_LENGHT = 4

    fun encode(word: String): String {
        return this.zeroPad(this.head(word) + this.encodedDigits(tail(word)))
    }

    private fun head(word: String): String {
        return word.substring(0, 1)
    }

    private fun tail(word: String): String {
        return word.substring(1)
    }

    private fun encodedDigits(tail: String): String {
        var encoding = ""
        for(i in 0..tail.length - 1) {
            encoding += encodedDigit(tail[i])
        }
        return encoding
    }

    private fun encodedDigit(character: Char): String {
        val encodings = mapOf(
            'b' to "1",
            'f' to "1",
            'p' to "1",
            'v' to "1",
            'c' to "2",
            'g' to "2",
            'j' to "2",
            'k' to "2",
            'q' to "2",
            's' to "2",
            'x' to "2",
            'z' to "2",
            'd' to "3",
            't' to "3",
            'l' to "4",
            'm' to "5",
            'n' to "5",
            'r' to "6"
        )

        if(encodings.containsKey(character))
            return encodings.getValue(character)
        return "" // case what if character is non alphabetic
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
        assertEquals("C600", this.soundex.encode("Cr"))
    }

    // What if key is missing in the map ? to prevent bugs
    @Test
    fun soundexEncodingIgnoresNonAlphabetic() {
        assertEquals("A000", this.soundex.encode("A#"))
    }

    @Test
    fun soundexEncodingReplacesMultipleConsonantsWithDigits() {
        assertEquals("A234", this.soundex.encode("Acdl"))
    }

}
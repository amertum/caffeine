package com.example;

import java.util.List;

import org.junit.Test;

import static java.util.Arrays.asList;

import static org.fest.assertions.Assertions.assertThat;

import static com.example.StringUtils.joinAlphabeticalyReversed;

/**
 * Unit test of {@link StringUtils}.
 */
public class StringUtilsTest {
    
    /**
     * Unit test of {@link StringUtils#joinAlphabeticalyReversed(Iterable, String)}.
     */
    @Test
    public void testJoinAlphabeticalyReversed()
    {
        final List<String> empty = asList();
        final List<String> parts = asList("d", "h", "a", "c");
        
        assertThat(joinAlphabeticalyReversed(null, null)).isEqualTo("");
        assertThat(joinAlphabeticalyReversed(null, "")).isEqualTo("");
        assertThat(joinAlphabeticalyReversed(empty, null)).isEqualTo("");
        assertThat(joinAlphabeticalyReversed(empty, "")).isEqualTo("");
        assertThat(joinAlphabeticalyReversed(empty, ",")).isEqualTo("");
        assertThat(joinAlphabeticalyReversed(parts, "")).isEqualTo("hdca");
        assertThat(joinAlphabeticalyReversed(parts, ",")).isEqualTo("h,d,c,a");
    }
    
}

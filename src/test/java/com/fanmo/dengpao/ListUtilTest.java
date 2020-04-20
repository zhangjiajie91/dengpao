package com.fanmo.dengpao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author fanmo
 * @date 2019/04/27
 */
public class ListUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void equalsWithEmptyTest() {

        Assert.assertTrue(ListUtil.equals(null, null, true));
        Assert.assertTrue(ListUtil.equals(null, null, false));
        Assert.assertFalse(ListUtil.equals(null, new ArrayList<>(), true));
        Assert.assertFalse(ListUtil.equals(null, new ArrayList<>(), false));

    }

    @Test
    public void equalsWithSeqTest() {
        Assert.assertTrue(ListUtil.equals(
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                true));

        Assert.assertTrue(ListUtil.equals(
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                false));

        Assert.assertTrue(ListUtil.equals(
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                new ArrayList<>(Arrays.asList(1, 3, 2)),
                true));

        Assert.assertFalse(ListUtil.equals(
                new ArrayList<>(Arrays.asList(1, 2, 3)),
                new ArrayList<>(Arrays.asList(1, 3, 2)),
                false));

        Assert.assertTrue(ListUtil.equals(
                new ArrayList<>(Arrays.asList(1, 2, 1)),
                new ArrayList<>(Arrays.asList(1, 1, 2)),
                true));

        Assert.assertFalse(ListUtil.equals(
                new ArrayList<>(Arrays.asList(1, 2, 1)),
                new ArrayList<>(Arrays.asList(1, 2, 2)),
                true));

        Assert.assertFalse(ListUtil.equals(
                new ArrayList<>(Arrays.asList(1, 2, 1)),
                new ArrayList<>(Arrays.asList(1, 2, 2)),
                false));
    }

}
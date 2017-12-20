/*
 * Copyright (c) 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itfsw.query.builder.support.builder;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.model.result.SqlQueryResult;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/2 17:56
 * ---------------------------------------------------------------------------
 */
public class SqlBuilderTest {
    private static SqlBuilder builder;

    @BeforeClass
    public static void init() {
        final SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        builder = factory.builder();
    }

    /**
     * equal 操作
     */
    @Test
    public void testOperatorEqual() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-equal.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username = ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username = 'Mistic'", result.getQuery(true));
    }

    /**
     * not equal 操作
     */
    @Test
    public void testOperatorNotEqual() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-not-equal.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username != ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username != 'Mistic'", result.getQuery(true));
    }

    /**
     * in 操作
     */
    @Test
    public void testOperatorIn() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-in.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age IN(?, ?, ?)", result.getQuery());
        Assert.assertEquals(1, result.getParams().get(0));
        Assert.assertEquals(5, result.getParams().get(1));
        Assert.assertEquals(10, result.getParams().get(2));
        Assert.assertEquals("age IN(1, 5, 10)", result.getQuery(true));
    }

    /**
     * not in 操作
     */
    @Test
    public void testOperatorNotIn() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-not-in.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age NOT IN(?, ?, ?)", result.getQuery());
        Assert.assertEquals(1, result.getParams().get(0));
        Assert.assertEquals(5, result.getParams().get(1));
        Assert.assertEquals(10, result.getParams().get(2));
        Assert.assertEquals("age NOT IN(1, 5, 10)", result.getQuery(true));
    }

    /**
     * less 操作
     */
    @Test
    public void testOperatorLess() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-less.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age < ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age < 50", result.getQuery(true));
    }

    /**
     * less or equal 操作
     */
    @Test
    public void testOperatorLessOrEqual() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-less-or-equal.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age <= ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age <= 50", result.getQuery(true));
    }

    /**
     * greater 操作
     */
    @Test
    public void testOperatorGreater() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-greater.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age > ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age > 50", result.getQuery(true));
    }

    /**
     * greater or equal 操作
     */
    @Test
    public void testOperatorGreaterOrEqual() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-greater-or-equal.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age >= ?", result.getQuery());
        Assert.assertEquals(50, result.getParams().get(0));
        Assert.assertEquals("age >= 50", result.getQuery(true));
    }

    /**
     * equal 操作
     */
    @Test
    public void testOperatorBetween() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-between.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age BETWEEN ? AND ?", result.getQuery());
        Assert.assertEquals(5, result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals("age BETWEEN 5 AND 10", result.getQuery(true));
    }

    /**
     * not between 操作
     */
    @Test
    public void testOperatorNotBetween() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-not-between.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("age NOT BETWEEN ? AND ?", result.getQuery());
        Assert.assertEquals(5, result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals("age NOT BETWEEN 5 AND 10", result.getQuery(true));
    }

    /**
     * begins with 操作
     */
    @Test
    public void testOperatorBeginsWith() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-begins-with.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username LIKE(?)", result.getQuery());
        Assert.assertEquals("Mistic%", result.getParams().get(0));
        Assert.assertEquals("username LIKE('Mistic%')", result.getQuery(true));
    }

    /**
     * not begins with 操作
     */
    @Test
    public void testOperatorNotBeginsWith() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-not-begins-with.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username NOT LIKE(?)", result.getQuery());
        Assert.assertEquals("Mistic%", result.getParams().get(0));
        Assert.assertEquals("username NOT LIKE('Mistic%')", result.getQuery(true));
    }

    /**
     * contains 操作
     */
    @Test
    public void testOperatorContains() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-contains.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username LIKE(?)", result.getQuery());
        Assert.assertEquals("%Mistic%", result.getParams().get(0));
        Assert.assertEquals("username LIKE('%Mistic%')", result.getQuery(true));
    }

    /**
     * not contains 操作
     */
    @Test
    public void testOperatorNotContains() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-not-contains.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username NOT LIKE(?)", result.getQuery());
        Assert.assertEquals("%Mistic%", result.getParams().get(0));
        Assert.assertEquals("username NOT LIKE('%Mistic%')", result.getQuery(true));
    }

    /**
     * ends with 操作
     */
    @Test
    public void testOperatorEndsWith() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-ends-with.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username LIKE(?)", result.getQuery());
        Assert.assertEquals("%Mistic", result.getParams().get(0));
        Assert.assertEquals("username LIKE('%Mistic')", result.getQuery(true));
    }

    /**
     * not ends with 操作
     */
    @Test
    public void testOperatorNotEndsWith() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-not-ends-with.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username NOT LIKE(?)", result.getQuery());
        Assert.assertEquals("%Mistic", result.getParams().get(0));
        Assert.assertEquals("username NOT LIKE('%Mistic')", result.getQuery(true));
    }

    /**
     * is empty 操作
     */
    @Test
    public void testOperatorIsEmpty() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-is-empty.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username = ''", result.getQuery());
        Assert.assertEquals(0, result.getParams().size());
        Assert.assertEquals("username = ''", result.getQuery(true));
    }

    /**
     * is not empty 操作
     */
    @Test
    public void testOperatorIsNotEmpty() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-is-not-empty.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username != ''", result.getQuery());
        Assert.assertEquals(0, result.getParams().size());
        Assert.assertEquals("username != ''", result.getQuery(true));
    }

    /**
     * is null 操作
     */
    @Test
    public void testOperatorIsNull() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-is-null.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username IS NULL", result.getQuery());
        Assert.assertEquals(0, result.getParams().size());
        Assert.assertEquals("username IS NULL", result.getQuery(true));
    }

    /**
     * is not null 操作
     */
    @Test
    public void testOperatorIsNotNull() throws IOException {
        final String json = FileHelper.getStringFrom("tasks/operator-is-not-null.json");
        final SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username IS NOT NULL", result.getQuery());
        Assert.assertEquals(0, result.getParams().size());
        Assert.assertEquals("username IS NOT NULL", result.getQuery(true));
    }

    /**
     * and 操作
     */
    @Test
    public void testConditionAnd() throws IOException {
        String json = FileHelper.getStringFrom("tasks/condition-and-1.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username = ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username = 'Mistic'", result.getQuery(true));

        json = FileHelper.getStringFrom("tasks/condition-and-more.json");
        result = builder.build(json);

        Assert.assertEquals("username = ? AND age = ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals("username = 'Mistic' AND age = 10", result.getQuery(true));
    }

    /**
     * or 操作
     */
    @Test
    public void testConditionOr() throws IOException {
        String json = FileHelper.getStringFrom("tasks/condition-or-1.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("username = ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username = 'Mistic'", result.getQuery(true));

        json = FileHelper.getStringFrom("tasks/condition-or-more.json");
        result = builder.build(json);

        Assert.assertEquals("username = ? OR age = ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals("username = 'Mistic' OR age = 10", result.getQuery(true));
    }

    /**
     * not 操作
     */
    @Test
    public void testConditionNot() throws IOException {
        String json = FileHelper.getStringFrom("tasks/condition-not-and-1.json");
        SqlQueryResult result = builder.build(json);

        Assert.assertEquals("NOT ( username = ? )", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("NOT ( username = 'Mistic' )", result.getQuery(true));

        json = FileHelper.getStringFrom("tasks/condition-not-and-more.json");
        result = builder.build(json);

        Assert.assertEquals("NOT ( username = ? AND age = ? )", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals(10, result.getParams().get(1));
        Assert.assertEquals("NOT ( username = 'Mistic' AND age = 10 )", result.getQuery(true));
    }
}
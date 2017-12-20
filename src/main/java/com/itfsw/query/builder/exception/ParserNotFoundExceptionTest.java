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

package com.itfsw.query.builder.exception;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.itfsw.query.builder.SqlQueryBuilderFactory;
import com.itfsw.query.builder.other.CustomSqlParser;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.builder.SqlBuilder;
import com.itfsw.query.builder.support.model.result.SqlQueryResult;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/3 13:54
 * ---------------------------------------------------------------------------
 */
public class ParserNotFoundExceptionTest {
    /**
     * 测试parser-sql 找不到
     */
    @Test(expected = ParserNotFoundException.class)
    public void testSqlParserNotFound() throws IOException {
        final SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        final SqlBuilder builder = factory.builder();

        final String json = FileHelper.getStringFrom("tasks/custom-operator.json");
        builder.build(json);
    }

    /**
     * 测试parser-sql 找不到(添加自定义)
     */
    @Test
    public void testSqlParserNotFoundAndAdd() throws IOException {
        final SqlQueryBuilderFactory factory = new SqlQueryBuilderFactory();
        factory.addRuleParser(new CustomSqlParser());
        final SqlBuilder builder = factory.builder();

        final String json = FileHelper.getStringFrom("tasks/custom-operator.json");
        final SqlQueryResult result = builder.build(json);
        Assert.assertEquals("username <> ?", result.getQuery());
        Assert.assertEquals("Mistic", result.getParams().get(0));
        Assert.assertEquals("username <> 'Mistic'", result.getQuery(true));
    }

}
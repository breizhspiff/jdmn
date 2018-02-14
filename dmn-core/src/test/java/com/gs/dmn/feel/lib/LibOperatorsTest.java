/**
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.feel.lib;

import org.junit.Test;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class LibOperatorsTest<NUMBER, DATE, TIME, DATE_TIME, DURATION> {
    protected abstract FEELLib<NUMBER, DATE, TIME, DATE_TIME, DURATION> getLib();

    //
    // Numeric operator functions
    //
    @Test
    public void testNumber() throws Exception {
        assertEquals("123.56", getLib().number("123.56").toString());
        assertEquals("-123.56", getLib().number("-123.56").toString());
        assertNull(getLib().number(null));
    }

    @Test
    public void testNumericEqual() throws Exception {
        assertTrue(getLib().numericEqual(null, null));
        assertFalse(getLib().numericEqual(null, makeNumber("1")));
        assertFalse(getLib().numericEqual(makeNumber("1"), null));
        assertTrue(getLib().numericEqual(makeNumber("1"), makeNumber("1")));
        assertFalse(getLib().numericEqual(makeNumber("1"), makeNumber("2")));
    }

    @Test
    public void testNumericNotEqual() throws Exception {
        assertFalse(getLib().numericNotEqual(null, null));
        assertTrue(getLib().numericNotEqual(null, makeNumber("1")));
        assertTrue(getLib().numericNotEqual(makeNumber("1"), null));
        assertFalse(getLib().numericNotEqual(makeNumber("1"), makeNumber("1")));
        assertTrue(getLib().numericNotEqual(makeNumber("1"), makeNumber("2")));
    }

    @Test
    public void testNumericLessThan() throws Exception {
        assertNull(getLib().numericLessThan(null, null));
        assertNull(getLib().numericLessThan(null, makeNumber("1")));
        assertNull(getLib().numericLessThan(makeNumber("1"), null));
        assertFalse(getLib().numericLessThan(makeNumber("1"), makeNumber("1")));
        assertTrue(getLib().numericLessThan(makeNumber("1"), makeNumber("2")));
    }

    @Test
    public void testNumericGreaterThan() throws Exception {
        assertNull(getLib().numericGreaterThan(null, null));
        assertNull(getLib().numericGreaterThan(null, makeNumber("1")));
        assertNull(getLib().numericGreaterThan(makeNumber("1"), null));
        assertFalse(getLib().numericGreaterThan(makeNumber("1"), makeNumber("1")));
        assertTrue(getLib().numericGreaterThan(makeNumber("2"), makeNumber("1")));
    }

    @Test
    public void testNumericLessEqualThan() throws Exception {
        assertTrue(getLib().numericLessEqualThan(null, null));
        assertNull(getLib().numericLessEqualThan(null, makeNumber("1")));
        assertNull(getLib().numericLessEqualThan(makeNumber("1"), null));
        assertTrue(getLib().numericLessEqualThan(makeNumber("1"), makeNumber("1")));
        assertFalse(getLib().numericLessEqualThan(makeNumber("2"), makeNumber("1")));
    }

    @Test
    public void testNumericGreaterEqualThan() throws Exception {
        assertTrue(getLib().numericGreaterEqualThan(null, null));
        assertNull(getLib().numericGreaterEqualThan(null, makeNumber("1")));
        assertNull(getLib().numericGreaterEqualThan(makeNumber("1"), null));
        assertTrue(getLib().numericGreaterEqualThan(makeNumber("1"), makeNumber("1")));
        assertFalse(getLib().numericGreaterEqualThan(makeNumber("1"), makeNumber("2")));
    }

    @Test
    public void testNumericAdd() throws Exception {
        assertEqualsNumber(makeNumber("3"), getLib().numericAdd(makeNumber("1"), makeNumber("2")));
        assertNull(getLib().numericAdd(makeNumber("1"), null));
    }

    @Test
    public void testNumericSubtract() throws Exception {
        assertEqualsNumber(makeNumber("-1"), getLib().numericSubtract(makeNumber("1"), makeNumber("2")));
        assertNull(getLib().numericSubtract(null, makeNumber("2")));
    }

    @Test
    public void testNumericMultiply() throws Exception {
        assertEqualsNumber(makeNumber("2"), getLib().numericMultiply(makeNumber("1"), makeNumber("2")));
        assertNull(getLib().numericMultiply(null, makeNumber("2")));
    }

    @Test
    public void testNumericDivide() throws Exception {
        assertEquals("0.5", getLib().numericDivide(makeNumber("1"), makeNumber("2")).toString());
        assertNull(getLib().numericDivide(null, makeNumber("2")));
    }

    @Test
    public void testNumericUnaryMinus() throws Exception {
        assertEqualsNumber(makeNumber("-1"), getLib().numericUnaryMinus(makeNumber("1")));
        assertNull(getLib().numericUnaryMinus(null));
    }

    @Test
    public void testNumericExponentiation() throws Exception {
        assertNull(getLib().numericExponentiation(null, null));
        assertNull(getLib().numericExponentiation(null, makeNumber("10")));
        assertNull(getLib().numericExponentiation(makeNumber("10"), null));

        assertEqualsNumber(makeNumber("1"), getLib().numericExponentiation(makeNumber("2"), makeNumber("0")));
        assertEquals(makeNumber("0.5"), getLib().numericExponentiation(makeNumber("2"), makeNumber("-1")));
        assertEqualsNumber(makeNumber("1024"), getLib().numericExponentiation(makeNumber("2"), makeNumber("10")));
    }

    //
    // Date operator functions
    //
    @Test
    public void testDate() throws Exception {
        assertNull(getLib().date((String)null));
        assertNull(getLib().date(""));
        assertNull(getLib().date("xxx"));

        assertNull("2017-08-25", getLib().date("2017-08-25T11:00:00"));

        assertEqualsTime("2016-08-01", getLib().date("2016-08-01"));
        assertEqualsTime("2016-08-01", getLib().date("2016-08-01"));

        assertEqualsTime("2016-08-01", getLib().date(makeDateAndTime("2016-08-01T12:00:00Z")));

        assertEqualsTime("2016-08-01", getLib().date(makeNumber("2016"), makeNumber("8"), makeNumber("1")));

        assertNull(getLib().date((String) null));
    }

    @Test
    public void testDateEqual() throws Exception {
        assertTrue(getLib().dateEqual(null, null));
        assertFalse(getLib().dateEqual(null, makeDate("2016-08-01")));
        assertFalse(getLib().dateEqual(makeDate("2016-08-01"), null));
        assertTrue(getLib().dateEqual(makeDate("2016-08-01"), makeDate("2016-08-01")));
        assertFalse(getLib().dateEqual(makeDate("2016-08-01"), makeDate("2016-08-02")));
    }

    @Test
    public void testDateNotEqual() throws Exception {
        assertFalse(getLib().dateNotEqual(null, null));
        assertTrue(getLib().dateNotEqual(null, makeDate("2016-08-01")));
        assertTrue(getLib().dateNotEqual(makeDate("2016-08-01"), null));
        assertFalse(getLib().dateNotEqual(makeDate("2016-08-01"), makeDate("2016-08-01")));
        assertTrue(getLib().dateEqual(makeDate("2016-08-01"), makeDate("2016-08-01")));
    }

    @Test
    public void testDateLessThan() throws Exception {
        assertFalse(getLib().dateLessThan(null, null));
        assertNull(getLib().dateLessThan(null, makeDate("2016-08-01")));
        assertNull(getLib().dateLessThan(makeDate("2016-08-01"), null));
        assertFalse(getLib().dateLessThan(makeDate("2016-08-01"), makeDate("2016-08-01")));
        assertTrue(getLib().dateLessThan(makeDate("2016-08-01"), makeDate("2016-08-02")));
    }

    @Test
    public void testDateGreaterThan() throws Exception {
        assertFalse(getLib().dateGreaterThan(null, null));
        assertNull(getLib().dateGreaterThan(null, makeDate("2016-08-01")));
        assertNull(getLib().dateGreaterThan(makeDate("2016-08-01"), null));
        assertFalse(getLib().dateGreaterThan(makeDate("2016-08-01"), makeDate("2016-08-01")));
        assertTrue(getLib().dateGreaterThan(makeDate("2016-08-02"), makeDate("2016-08-01")));
    }

    @Test
    public void testDateLessEqualThan() throws Exception {
        assertTrue(getLib().dateLessEqualThan(null, null));
        assertNull(getLib().dateLessEqualThan(null, makeDate("2016-08-01")));
        assertNull(getLib().dateLessEqualThan(makeDate("2016-08-01"), null));
        assertTrue(getLib().dateLessEqualThan(makeDate("2016-08-01"), makeDate("2016-08-01")));
        assertFalse(getLib().dateLessEqualThan(makeDate("2016-08-03"), makeDate("2016-08-02")));
    }

    @Test
    public void testDateGreaterEqualThan() throws Exception {
        assertTrue(getLib().dateGreaterEqualThan(null, null));
        assertNull(getLib().dateGreaterEqualThan(null, makeDate("2016-08-01")));
        assertNull(getLib().dateGreaterEqualThan(makeDate("2016-08-01"), null));
        assertTrue(getLib().dateGreaterEqualThan(makeDate("2016-08-01"), makeDate("2016-08-01")));
        assertFalse(getLib().dateGreaterEqualThan(makeDate("2016-08-01"), makeDate("2016-08-03")));
    }

    @Test
    public void testDateSubtract() throws Exception {
        assertEqualsTime(null, getLib().dateSubtract(null, null));
        assertEqualsTime(null, getLib().dateSubtract(null, makeDate("2016-08-01")));
        assertEqualsTime(null, getLib().dateSubtract(makeDate("2016-08-01"), null));
    }

    @Test
    public void testDateAddDuration() throws Exception {
        assertEqualsTime(null, getLib().dateAddDuration(null, null));
        assertEqualsTime(null, getLib().dateAddDuration(null, makeDuration("P0Y2M")));
        assertEqualsTime(null, getLib().dateAddDuration(makeDate("2016-08-01"), null));
        assertEqualsTime("2016-10-01", getLib().dateAddDuration(makeDate("2016-08-01"), makeDuration("P0Y2M")).toString());
        assertEqualsTime("2016-06-01", getLib().dateAddDuration(makeDate("2016-08-01"), makeDuration("-P0Y2M")).toString());
    }

    @Test
    public void testDateSubtractDuration() throws Exception {
        assertEqualsTime(null, getLib().dateSubtractDuration(null, null));
        assertEqualsTime(null, getLib().dateSubtractDuration(null, makeDuration("P0Y2M")));
        assertEqualsTime(null, getLib().dateSubtractDuration(makeDate("2016-08-01"), null));
        assertEqualsTime("2016-06-01", getLib().dateSubtractDuration(makeDate("2016-08-01"), makeDuration("P0Y2M")).toString());
        assertEqualsTime("2016-10-01", getLib().dateSubtractDuration(makeDate("2016-08-01"), makeDuration("-P0Y2M")).toString());
    }

    //
    // Time operator functions
    //
    @Test
    public void testTime() throws Exception {
        assertNull(getLib().time((String)null));
        assertNull(getLib().time(""));
        assertNull(getLib().time("xxx"));
        assertNull(getLib().time("13:20:00+01:00@Europe/Paris"));
        assertNull(getLib().time("13:20:00+00:00[UTC]"));
        assertNull(getLib().time(
                makeNumber("12"), makeNumber("00"), makeNumber("00"),
                makeDuration("PT25H10M")));

        // Fix input literal
        assertEqualsTime("11:00:00Z", getLib().time("T11:00:00Z"));
        assertEqualsTime("11:00:00+01:00", getLib().time("11:00:00+0100"));

        assertEqualsTime("11:00:00Z", getLib().time("11:00:00Z"));
        assertEqualsTime("11:00:00.001Z", getLib().time("11:00:00.001Z"));

        assertEqualsTime("11:00:00.001+01:00", getLib().time("11:00:00.001+01:00"));
        assertEqualsTime("11:00:00+01:00", getLib().time("11:00:00+01:00"));

        assertEqualsTime("11:00:00Z", getLib().time(makeDateAndTime("2016-08-01T11:00:00Z")));

        assertEqualsTime("12:00:00+01:10", getLib().time(
                makeNumber("12"), makeNumber("00"), makeNumber("00"),
                makeDuration("PT1H10M")));
    }

    @Test
    public void testTimeEqual() throws Exception {
        assertTrue(getLib().timeEqual(null, null));
        assertFalse(getLib().timeEqual(null, makeTime("12:00:00Z")));
        assertFalse(getLib().timeEqual(makeTime("12:00:00Z"), null));
        assertTrue(getLib().timeEqual(makeTime("12:00:00Z"), makeTime("12:00:00Z")));
        assertFalse(getLib().timeEqual(makeTime("12:00:00Z"), makeTime("12:00:01Z")));

        assertTrue(getLib().timeEqual(makeTime("12:00:00"), makeTime("12:00:00")));
        assertTrue(getLib().timeEqual(makeTime("12:00:00+00:00"), makeTime("12:00:00+00:00")));
        assertTrue(getLib().timeEqual(makeTime("12:00:00"), makeTime("12:00:00+00:00")));
        assertTrue(getLib().timeEqual(makeTime("12:00:00Z"), makeTime("12:00:00+00:00")));
    }

    @Test
    public void testTimeNotEqual() throws Exception {
        assertFalse(getLib().timeNotEqual(null, null));
        assertTrue(getLib().timeNotEqual(null, makeTime("12:00:00Z")));
        assertTrue(getLib().timeNotEqual(makeTime("12:00:00Z"), null));
        assertFalse(getLib().timeNotEqual(makeTime("12:00:00Z"), makeTime("12:00:00Z")));
        assertTrue(getLib().timeNotEqual(makeTime("12:00:00Z"), makeTime("12:00:01Z")));
    }

    @Test
    public void testTimeLessThan() throws Exception {
        assertFalse(getLib().timeLessThan(null, null));
        assertNull(getLib().timeLessThan(null, makeTime("12:00:00Z")));
        assertNull(getLib().timeLessThan(makeTime("12:00:00Z"), null));
        assertFalse(getLib().timeLessThan(makeTime("12:00:00Z"), makeTime("12:00:00Z")));
        assertTrue(getLib().timeLessThan(makeTime("11:00:00Z"), makeTime("12:00:01Z")));
    }

    @Test
    public void testTimeGreaterThan() throws Exception {
        assertFalse(getLib().timeGreaterThan(null, null));
        assertNull(getLib().timeGreaterThan(null, makeTime("12:00:00Z")));
        assertNull(getLib().timeGreaterThan(makeTime("12:00:00Z"), null));
        assertFalse(getLib().timeGreaterThan(makeTime("12:00:00Z"), makeTime("12:00:00Z")));
        assertTrue(getLib().timeGreaterThan(makeTime("13:00:00Z"), makeTime("12:00:01Z")));
    }

    @Test
    public void testTimeLessEqualThan() throws Exception {
        assertTrue(getLib().timeLessEqualThan(null, null));
        assertNull(getLib().timeLessEqualThan(null, makeTime("12:00:00Z")));
        assertNull(getLib().timeLessEqualThan(makeTime("12:00:00Z"), null));
        assertTrue(getLib().timeLessEqualThan(makeTime("12:00:00Z"), makeTime("12:00:00Z")));
        assertFalse(getLib().timeLessEqualThan(makeTime("13:00:00Z"), makeTime("12:00:01Z")));
    }

    @Test
    public void testTimeGreaterEqualThan() throws Exception {
        assertTrue(getLib().timeGreaterEqualThan(null, null));
        assertNull(getLib().timeGreaterEqualThan(null, makeTime("12:00:00Z")));
        assertNull(getLib().timeGreaterEqualThan(makeTime("12:00:00Z"), null));
        assertTrue(getLib().timeGreaterEqualThan(makeTime("12:00:00Z"), makeTime("12:00:00Z")));
        assertFalse(getLib().timeGreaterEqualThan(makeTime("11:00:00Z"), makeTime("12:00:01Z")));
    }

    @Test
    public void testTimeSubtract() throws Exception {
        assertEqualsTime(null, getLib().timeSubtract(null, null));
        assertEqualsTime(null, getLib().timeSubtract(null, makeTime("12:00:00Z")));
        assertEqualsTime(null, getLib().timeSubtract(makeTime("12:00:00Z"), null));
        assertEqualsTime("P0Y0M0DT0H0M0.000S", getLib().timeSubtract(makeTime("12:00:00Z"), makeTime("12:00:00Z")).toString());
        assertEqualsTime("-P0Y0M0DT1H0M0.000S", getLib().timeSubtract(makeTime("12:00:00Z"), makeTime("13:00:00Z")).toString());
    }

    @Test
    public void testTimeAddDuration() throws Exception {
        assertEqualsTime(null, getLib().timeAddDuration(null, null));
        assertEqualsTime(null, getLib().timeAddDuration(null, makeDuration("P0DT1H")));
        assertEqualsTime(null, getLib().timeAddDuration(makeTime("12:00:00Z"), null));
        assertEqualsTime("13:00:01Z", getLib().timeAddDuration(makeTime("12:00:01Z"), makeDuration("P0DT1H")).toString());
        assertEqualsTime("12:00:01Z", getLib().timeAddDuration(makeTime("12:00:01Z"), makeDuration("P1DT0H")).toString());
    }

    @Test
    public void testTimeSubtractDuration() throws Exception {
        assertEqualsTime(null, getLib().timeSubtractDuration(null, null));
        assertEqualsTime(null, getLib().timeSubtractDuration(null, makeDuration("P0DT1H")));
        assertEqualsTime(null, getLib().timeSubtractDuration(makeTime("12:00:01Z"), null));
        assertEqualsTime("11:00:01Z", getLib().timeSubtractDuration(makeTime("12:00:01Z"), makeDuration("P0DT1H")).toString());
        assertEqualsTime("12:00:01Z", getLib().timeSubtractDuration(makeTime("12:00:01Z"), makeDuration("P1DT0H")).toString());
    }

    //
    // Date and time operator functions
    //
    @Test
    public void testDateTime() throws Exception {
        assertNull(getLib().dateAndTime(null));
        assertNull(getLib().dateAndTime(""));
        assertNull(getLib().dateAndTime("xxx"));
        assertNull(getLib().dateAndTime("11:00:00"));
        assertNull(getLib().dateAndTime("2011-12-03T10:15:30+01:00@Europe/Paris"));

        assertNull(getLib().dateAndTime(null, null));
        assertNull(getLib().dateAndTime(null, makeTime("11:00:00Z")));
        assertNull(getLib().dateAndTime(getLib().date("2016-08-01"), null));

        // Fix input literal
        assertEqualsTime("2016-08-01T11:00:00+01:00", getLib().dateAndTime("2016-08-01T11:00:00+0100"));

        assertEqualsTime("2016-08-01T11:00:00Z", getLib().dateAndTime("2016-08-01T11:00:00Z"));
        assertEqualsTime("2016-08-01T11:00:00.001Z", getLib().dateAndTime("2016-08-01T11:00:00.001Z"));
        assertEqualsTime("2016-08-01T11:00:00.001+01:00", getLib().dateAndTime("2016-08-01T11:00:00.001+01:00"));
        assertEqualsTime("2016-08-01T11:00:00+01:00", getLib().dateAndTime("2016-08-01T11:00:00+01:00"));

        assertEqualsTime("2016-08-01T11:00:00Z", getLib().dateAndTime("2016-08-01T11:00:00Z"));

        assertEqualsTime("2016-08-01T11:00:00Z", getLib().dateAndTime("2016-08-01T11:00:00Z"));
        assertEqualsTime("2016-08-01T11:00:00Z", getLib().dateAndTime(makeDate("2016-08-01"), makeTime("11:00:00Z")));
    }

    @Test
    public void testDateTimeEqual() throws Exception {
        assertTrue(getLib().dateTimeEqual(null, null));
        assertFalse(getLib().dateTimeEqual(null, makeDateAndTime("2016-08-01T11:00:00Z")));
        assertFalse(getLib().dateTimeEqual(makeDateAndTime("2016-08-01T11:00:00Z"), null));
        assertTrue(getLib().dateTimeEqual(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:00Z")));
        assertFalse(getLib().dateTimeEqual(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:01")));
    }

    @Test
    public void testDateTimeNotEqual() throws Exception {
        assertFalse(getLib().dateTimeNotEqual(null, null));
        assertTrue(getLib().dateTimeNotEqual(null, makeDateAndTime("2016-08-01T11:00:00Z")));
        assertTrue(getLib().dateTimeNotEqual(makeDateAndTime("2016-08-01T11:00:00Z"), null));
        assertFalse(getLib().dateTimeNotEqual(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:00Z")));
        assertTrue(getLib().dateTimeNotEqual(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:01")));
    }

    @Test
    public void testDateTimeLessThan() throws Exception {
        assertFalse(getLib().dateTimeLessThan(null, null));
        assertNull(getLib().dateTimeLessThan(null, makeDateAndTime("2016-08-01T11:00:00Z")));
        assertNull(getLib().dateTimeLessThan(makeDateAndTime("2016-08-01T11:00:00Z"), null));
        assertFalse(getLib().dateTimeLessThan(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:00Z")));
        assertTrue(getLib().dateTimeLessThan(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2017-08-01T11:00:01")));
    }

    @Test
    public void testDateTimeGreaterThan() throws Exception {
        assertFalse(getLib().dateTimeGreaterThan(null, null));
        assertNull(getLib().dateTimeGreaterThan(null, makeDateAndTime("2016-08-01T11:00:00Z")));
        assertNull(getLib().dateTimeGreaterThan(makeDateAndTime("2016-08-01T11:00:00Z"), null));
        assertTrue(getLib().dateTimeGreaterThan(makeDateAndTime("2017-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:00Z")));
        assertFalse(getLib().dateTimeGreaterThan(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:01Z")));
    }

    @Test
    public void testDateTimeLessEqualThan() throws Exception {
        assertTrue(getLib().dateTimeLessEqualThan(null, null));
        assertNull(getLib().dateTimeLessEqualThan(null, makeDateAndTime("2016-08-01T11:00:00Z")));
        assertNull(getLib().dateTimeLessEqualThan(makeDateAndTime("2016-08-01T11:00:00Z"), null));
        assertTrue(getLib().dateTimeLessEqualThan(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:00Z")));
        assertFalse(getLib().dateTimeLessEqualThan(makeDateAndTime("2016-08-01T11:00:01Z"), makeDateAndTime("2016-08-01T11:00:00Z")));
    }

    @Test
    public void testDateTimeGreaterEqualThan() throws Exception {
        assertTrue(getLib().dateTimeGreaterEqualThan(null, null));
        assertNull(getLib().dateTimeGreaterEqualThan(null, makeDateAndTime("2016-08-01T11:00:00Z")));
        assertNull(getLib().dateTimeGreaterEqualThan(makeDateAndTime("2016-08-01T11:00:00Z"), null));
        assertTrue(getLib().dateTimeGreaterEqualThan(makeDateAndTime("2016-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:00Z")));
        assertFalse(getLib().dateTimeGreaterEqualThan(makeDateAndTime("2015-08-01T11:00:00Z"), makeDateAndTime("2016-08-01T11:00:01Z")));
    }

    @Test
    public void testDateTimeSubtract() throws Exception {
        assertEqualsTime(null, getLib().dateTimeSubtract(null, null));
        assertEqualsTime(null, getLib().dateTimeSubtract(null, makeDateAndTime("2016-08-01T12:00:00Z")));
        assertEqualsTime(null, getLib().dateTimeSubtract(makeDateAndTime("2016-08-01T12:00:00Z"), null));
        assertEqualsTime("P0Y0M0DT0H0M0.000S", getLib().dateTimeSubtract(makeDateAndTime("2016-08-01T12:00:00Z"), makeDateAndTime("2016-08-01T12:00:00Z")).toString());
        assertEqualsTime("-P0Y0M2DT1H0M0.000S", getLib().dateTimeSubtract(makeDateAndTime("2016-08-01T12:00:00Z"), makeDateAndTime("2016-08-03T13:00:00Z")).toString());
    }

    @Test
    public void testDateTimeAddDuration() throws Exception {
        assertEqualsTime(null, getLib().dateTimeAddDuration(null, null));
        assertEqualsTime(null, getLib().dateTimeAddDuration(null, makeDuration("P1YT1H")));
        assertEqualsTime(null, getLib().dateTimeAddDuration(makeDateAndTime("2016-08-01T12:00:00Z"), null));
        assertEqualsTime("2017-08-01T13:00:01Z", getLib().dateTimeAddDuration(makeDateAndTime("2016-08-01T12:00:01Z"), makeDuration("P1YT1H")).toString());
        assertEqualsTime("2015-08-01T11:00:01Z", getLib().dateTimeAddDuration(makeDateAndTime("2016-08-01T12:00:01Z"), makeDuration("-P1YT1H")).toString());
    }

    @Test
    public void testDateTimeSubtractDuration() throws Exception {
        assertEqualsTime(null, getLib().dateTimeSubtractDuration(null, null));
        assertEqualsTime(null, getLib().dateTimeSubtractDuration(null, makeDuration("P1YT1H")));
        assertEqualsTime(null, getLib().dateTimeSubtractDuration(makeDateAndTime("2016-08-01T12:00:00Z"), null));
        assertEqualsTime("2015-08-01T11:00:01Z", getLib().dateTimeSubtractDuration(makeDateAndTime("2016-08-01T12:00:01Z"), makeDuration("P1YT1H")).toString());
        assertEqualsTime("2017-08-01T13:00:01Z", getLib().dateTimeSubtractDuration(makeDateAndTime("2016-08-01T12:00:01Z"), makeDuration("-P1YT1H")).toString());
    }

    //
    // Duration operator functions
    //
    @Test
    public void testMakeDuration() throws Exception {
        assertEquals("P1Y8M", getLib().duration("P1Y8M").toString());
        assertEquals("P2DT20H", getLib().duration("P2DT20H").toString());
        assertNull(getLib().duration("XXX"));
        assertNull(getLib().duration(null));
    }

    @Test
    public void testDurationEqual() throws Exception {
        assertTrue(getLib().durationEqual(null, null));
        assertFalse(getLib().durationEqual(null, makeDuration("P1Y1M1DT1H")));
        assertFalse(getLib().durationEqual(makeDuration("P1Y1M1DT1H"), null));
        assertTrue(getLib().durationEqual(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT1H")));
        assertFalse(getLib().durationEqual(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT2H")));
    }

    @Test
    public void testDurationNotEqual() throws Exception {
        assertFalse(getLib().durationNotEqual(null, null));
        assertTrue(getLib().durationNotEqual(null, makeDuration("P1Y1M1DT1H")));
        assertTrue(getLib().durationNotEqual(makeDuration("P1Y1M1DT1H"), null));
        assertFalse(getLib().durationNotEqual(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT1H")));
        assertTrue(getLib().durationNotEqual(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT2H")));
    }

    @Test
    public void testDurationLessThan() throws Exception {
        assertFalse(getLib().durationLessThan(null, null));
        assertNull(getLib().durationLessThan(null, makeDuration("P1Y1M1DT1H")));
        assertNull(getLib().durationLessThan(makeDuration("P1Y1M1DT1H"), null));
        assertFalse(getLib().durationLessThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT1H")));
        assertTrue(getLib().durationLessThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT2H")));
    }

    @Test
    public void testDurationGreaterThan() throws Exception {
        assertFalse(getLib().durationGreaterThan(null, null));
        assertNull(getLib().durationGreaterThan(null, makeDuration("P1Y1M1DT1H")));
        assertNull(getLib().durationGreaterThan(makeDuration("P1Y1M1DT1H"), null));
        assertFalse(getLib().durationGreaterThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT1H")));
        assertFalse(getLib().durationGreaterThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT2H")));
    }

    @Test
    public void testDurationLessEqualThan() throws Exception {
        assertFalse(getLib().durationLessEqualThan(null, null));
        assertNull(getLib().durationLessEqualThan(null, makeDuration("P1Y1M1DT1H")));
        assertNull(getLib().durationLessEqualThan(makeDuration("P1Y1M1DT1H"), null));
        assertTrue(getLib().durationLessEqualThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT1H")));
        assertTrue(getLib().durationLessEqualThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT2H")));
    }

    @Test
    public void testDurationGreaterEqualThan() throws Exception {
        assertFalse(getLib().durationGreaterEqualThan(null, null));
        assertNull(getLib().durationGreaterEqualThan(null, makeDuration("P1Y1M1DT1H")));
        assertNull(getLib().durationGreaterEqualThan(makeDuration("P1Y1M1DT1H"), null));
        assertTrue(getLib().durationGreaterEqualThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT1H")));
        assertFalse(getLib().durationGreaterEqualThan(makeDuration("P1Y1M1DT1H"), makeDuration("P1Y1M1DT2H")));
    }

    //
    // String operator functions
    //
    @Test
    public void testStringEqual() throws Exception {
        assertTrue(getLib().stringEqual(null, null));
        assertFalse(getLib().stringEqual("a", null));
        assertFalse(getLib().stringEqual(null, "b"));
        assertFalse(getLib().stringEqual("a", "b"));
        assertTrue(getLib().stringEqual("b", "b"));
    }

    @Test
    public void testStringNotEqual() throws Exception {
        assertFalse(getLib().stringNotEqual(null, null));
        assertTrue(getLib().stringNotEqual("a", null));
        assertTrue(getLib().stringNotEqual(null, "b"));
        assertTrue(getLib().stringNotEqual("a", "b"));
        assertFalse(getLib().stringNotEqual("b", "b"));
    }

    @Test
    public void testStringAdd() throws Exception {
        assertEquals("", getLib().stringAdd(null, null));
        assertEquals("a", getLib().stringAdd("a", null));
        assertEquals("b", getLib().stringAdd(null, "b"));
        assertEquals("ab", getLib().stringAdd("a", "b"));
        assertEquals("ba", getLib().stringAdd("b", "a"));
    }

    //
    // Boolean operator functions
    //
    @Test
    public void testBooleanEqual() throws Exception {
        assertTrue(getLib().booleanEqual(null, null));
        assertFalse(getLib().booleanEqual(Boolean.TRUE, null));
        assertFalse(getLib().booleanEqual(null, Boolean.TRUE));
        assertFalse(getLib().booleanEqual(Boolean.FALSE, Boolean.TRUE));
        assertTrue(getLib().booleanEqual(Boolean.TRUE, Boolean.TRUE));
    }

    @Test
    public void testBooleanNotEqual() throws Exception {
        assertFalse(getLib().booleanNotEqual(null, null));
        assertTrue(getLib().booleanNotEqual(Boolean.TRUE, null));
        assertTrue(getLib().booleanNotEqual(null, Boolean.TRUE));
        assertTrue(getLib().booleanNotEqual(Boolean.FALSE, Boolean.TRUE));
        assertFalse(getLib().booleanNotEqual(Boolean.TRUE, Boolean.TRUE));
    }

    @Test
    public void testBooleanNot() throws Exception {
        assertTrue(getLib().booleanNot(Boolean.FALSE));
        assertFalse(getLib().booleanNot(Boolean.TRUE));
        assertNull(getLib().booleanNot(null));
    }

    @Test
    public void testBooleanOr() throws Exception {
        assertFalse(getLib().booleanOr(Boolean.FALSE, Boolean.FALSE));
        assertTrue(getLib().booleanOr(Boolean.FALSE, Boolean.TRUE));
        assertTrue(getLib().booleanOr(Boolean.TRUE, Boolean.FALSE));
        assertTrue(getLib().booleanOr(Boolean.TRUE, Boolean.TRUE));
        assertNull(getLib().booleanOr(Boolean.FALSE, null));
        assertNull(getLib().booleanOr(null, Boolean.FALSE));
        assertTrue(getLib().booleanOr(Boolean.TRUE, null));
        assertTrue(getLib().booleanOr(null, Boolean.TRUE));
        assertNull(getLib().booleanOr(null, null));
    }

    @Test
    public void testBooleanAnd() throws Exception {
        assertFalse(getLib().booleanAnd(Boolean.FALSE, Boolean.FALSE));
        assertFalse(getLib().booleanAnd(Boolean.FALSE, Boolean.TRUE));
        assertFalse(getLib().booleanAnd(Boolean.TRUE, Boolean.FALSE));
        assertTrue(getLib().booleanAnd(Boolean.TRUE, Boolean.TRUE));
        assertFalse(getLib().booleanAnd(Boolean.FALSE, null));
        assertFalse(getLib().booleanAnd(null, Boolean.FALSE));
        assertNull(getLib().booleanAnd(Boolean.TRUE, null));
        assertNull(getLib().booleanAnd(null, Boolean.TRUE));
        assertNull(getLib().booleanAnd(null, null));
    }

    //
    // List operator functions
    //
    @Test
    public void testListEqual() throws Exception {
        assertTrue(getLib().listEqual(null, null));
        assertFalse(getLib().listEqual(Arrays.asList("a"), null));
        assertFalse(getLib().listEqual(null, Arrays.asList("a")));
        assertFalse(getLib().listEqual(Arrays.asList("a"), Arrays.asList("b")));
        assertTrue(getLib().listEqual(Arrays.asList("a"), Arrays.asList("a")));
    }

    @Test
    public void testListNotEqual() throws Exception {
        assertFalse(getLib().listNotEqual(null, null));
        assertTrue(getLib().listNotEqual(Arrays.asList("a"), null));
        assertTrue(getLib().listNotEqual(null, Arrays.asList("a")));
        assertTrue(getLib().listNotEqual(Arrays.asList("a"), Arrays.asList("b")));
        assertFalse(getLib().listNotEqual(Arrays.asList("a"), Arrays.asList("a")));
    }

    protected NUMBER makeNumber(String literal) {
        return getLib().number(literal);
    }

    protected NUMBER makeNumber(long number) {
        return makeNumber(Long.valueOf(number));
    }

    protected NUMBER makeNumber(Long number) {
        return number == null ? null : getLib().number(number.toString());
    }

    protected NUMBER makeNumber(double number) {
        return makeNumber(Double.valueOf(number));
    }

    protected NUMBER makeNumber(Double number) {
        return number == null ? null : getLib().number(number.toString());
    }

    protected List<NUMBER> makeNumberList(Object... numbers) {
        List result = new ArrayList();
        for(Object num: numbers) {
            result.add(makeNumber(String.format("%s", num)));
        }
        return result;
    }

    protected DATE makeDate(String literal) {
        return getLib().date(literal);
    }

    protected TIME makeTime(String literal) {
        return getLib().time(literal);
    }

    protected DATE_TIME makeDateAndTime(String literal) {
        return getLib().dateAndTime(literal);
    }

    protected DURATION makeDuration(String literal) {
        return getLib().duration(literal);
    }

    protected void assertEqualsNumber(NUMBER expected, Object actual) {
        if (expected instanceof BigDecimal && actual instanceof BigDecimal) {
            assertEquals(((BigDecimal) expected).stripTrailingZeros().toPlainString(), ((BigDecimal) actual).stripTrailingZeros().toPlainString());
        } else if (expected instanceof Long && actual instanceof Double) {
            assertEquals(expected, ((Double)actual).longValue());
        } else if (expected instanceof Long && actual instanceof Integer) {
            assertEquals(expected, Long.valueOf((Integer)actual).longValue());
        } else {
            assertEquals(expected, actual);
        }
    }

    protected void assertEqualsNumber(double expected, Object actual, double precision) {
        if (actual instanceof BigDecimal) {
            assertEquals(expected, ((BigDecimal) actual).doubleValue(), precision);
        } else {
            assertEquals(expected, actual);
        }
    }

    protected void assertEqualsTime(String expected, Object actual) {
        if (actual instanceof XMLGregorianCalendar) {
            assertEquals(expected, ((XMLGregorianCalendar) actual).toXMLFormat());
        } else if (actual instanceof LocalDate) {
            String actualText = ((LocalDate) actual).format(DateTimeFormatter.ISO_DATE);
            assertEquals(expected, cleanActualText(actualText));
        } else if (actual instanceof OffsetTime) {
            String actualText = ((OffsetTime) actual).format(DateTimeFormatter.ISO_OFFSET_TIME);
            assertEquals(expected, cleanActualText(actualText));
        } else if (actual instanceof ZonedDateTime) {
            String actualText = ((ZonedDateTime) actual).format(DateTimeFormatter.ISO_DATE_TIME);
            assertEquals(expected, cleanActualText(actualText));
        } else if (actual instanceof String) {
            String actualText = cleanActualText((String) actual);
            assertEquals(expected, cleanActualText(actualText));
        } else {
            assertEquals(expected, actual);
        }
    }

    protected String cleanActualText(String actualText) {
        String[] midnightSuffixes = new String[] {
                "T00:00:00Z[UTC]",
                "T00:00:00Z",
                "T00:00Z[UTC]",
                "T00:00Z"
        };
        String noDatePrefix = "-999999999-01-01T";
        if (actualText.startsWith(noDatePrefix)) {
            actualText = actualText.substring(noDatePrefix.length());
        }
        for(String midnightSuffix: midnightSuffixes) {
            if (actualText.endsWith(midnightSuffix)) {
                actualText = actualText.substring(0, actualText.length() - midnightSuffix.length());
            }
        }
        return actualText;
    }
}
